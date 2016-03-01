/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tai.solr.services;

import it.tai.solr.dao.entities.SolrSummaryEntity;
import it.tai.solr.data.SolrResponse;
import java.util.List;

/**
 *
 * @author francesco
 */
public interface SolrSummaryService {
    
    public void checkLastEntry();
    
    public SolrSummaryEntity findById(long id);
   
    public List<SolrSummaryEntity> findAll();

    public void save(SolrResponse solrResponse);

    public void update(SolrResponse solrResponse);

    public void delete(SolrResponse solrResponse);

    public void shutdown();
}
