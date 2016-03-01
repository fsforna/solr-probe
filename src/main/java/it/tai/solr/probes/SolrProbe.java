/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tai.solr.probes;

import it.tai.solr.dao.ProbeDAO;
import it.tai.solr.data.SolrResponse;
import it.tai.solr.exception.UnsupportedActionException;
import it.tai.solr.http.HttpInvoker;
import it.tai.solr.services.SolrSummaryService;
import java.io.IOException;
import java.util.List;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 *
 * @author francesco
 */
public class SolrProbe extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(SolrProbe.class);
        
    private HttpInvoker httpInvoker;

    private SolrSummaryService solrSummaryService;

    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {

        try {
            SolrResponse resp = httpInvoker.getSolrReport(HttpInvoker.REPORT_ACTION.SUMMARY);
            
            //save response
            solrSummaryService.save(resp);
            
            //Check
            solrSummaryService.checkLastEntry();
            
        } catch (IOException ex) {
            logger.error("IOException", ex);
        } catch (UnsupportedActionException ex) {
            logger.error("UnsupportedActionException", ex);
        }

    }

    /**
     * @return the httpInvoker
     */
    public HttpInvoker getHttpInvoker() {
        return httpInvoker;
    }

    /**
     * @param httpInvoker the httpInvoker to set
     */
    public void setHttpInvoker(HttpInvoker httpInvoker) {
        this.httpInvoker = httpInvoker;
    }

    /**
     * @param solrSummaryService the solrSummaryService to set
     */
    public void setSolrSummaryService(SolrSummaryService solrSummaryService) {
        this.solrSummaryService = solrSummaryService;
    }

}
