/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tai.solr.services.impl;

import it.tai.solr.dao.ProbeDAO;
import it.tai.solr.dao.entities.SolrSummaryEntity;
import it.tai.solr.data.SolrResponse;
import it.tai.solr.services.SolrSummaryService;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author francesco
 */
public class SolrSummaryServiceImpl implements SolrSummaryService {

    private static final Logger logger = LoggerFactory.getLogger(SolrSummaryServiceImpl.class);

    private ProbeDAO probeDAO;

    private String days;

    private MailSender mailSender;

    private SimpleMailMessage warningSolrMessage;

    @Override
    public SolrSummaryEntity findById(long id) {
        return probeDAO.findById(id);
    }

    @Override
    public List<SolrSummaryEntity> findAll() {
        return probeDAO.findAll();
    }

    @Override
    public void save(SolrResponse solrResponse) {
        probeDAO.save(solrResponse.toSolrSummaryEntity(solrResponse));
    }

    @Override
    public void update(SolrResponse solrResponse) {
        probeDAO.update(solrResponse.toSolrSummaryEntity(solrResponse));
    }

    @Override
    public void delete(SolrResponse solrResponse) {
        probeDAO.delete(solrResponse.toSolrSummaryEntity(solrResponse));
    }

    @Override
    public void shutdown() {
        probeDAO.shutdown();
    }

    @Override
    public void checkLastEntry() {

        SolrSummaryEntity entry = probeDAO.getLastEntry();

        DateTime solrOpenedAt = new DateTime(entry.getOpenedAt().getTime());

        int diff = Days.daysBetween(solrOpenedAt, DateTime.now()).getDays();

        if (logger.isDebugEnabled()) {
            logger.debug(entry.toString());
        }

        if (diff >= Integer.valueOf(days)) {
            if (logger.isDebugEnabled()) {
                logger.debug("Exceeded threshold between last SOLR opened at date");
            }
            
            sendPreConfiguredMail("Last opened at SOLR entry " + solrOpenedAt, warningSolrMessage);
        }

    }

    private void sendPreConfiguredMail(String message, SimpleMailMessage simpleMailMessage) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(simpleMailMessage);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    /**
     * @param probeDAO the probeDAO to set
     */
    public void setProbeDAO(ProbeDAO probeDAO) {
        this.probeDAO = probeDAO;
    }

    /**
     * @param days the days to set
     */
    public void setDays(String days) {
        this.days = days;
    }

    /**
     * @param mailSender the mailSender to set
     */
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * @param warningSolrMessage the warningSolrMessage to set
     */
    public void setWarningSolrMessage(SimpleMailMessage warningSolrMessage) {
        this.warningSolrMessage = warningSolrMessage;
    }

}
