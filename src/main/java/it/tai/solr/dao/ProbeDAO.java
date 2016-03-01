/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tai.solr.dao;

import it.tai.solr.dao.entities.SolrSummaryEntity;
import java.util.List;

/**
 *
 * @author francesco
 */
public interface ProbeDAO {
    
    public SolrSummaryEntity getLastEntry();

    public SolrSummaryEntity findById(long id);
   
    public List<SolrSummaryEntity> findAll();

    public void save(SolrSummaryEntity solrSummaryEntity);

    public void update(SolrSummaryEntity solrSummaryEntity);

    public void delete(SolrSummaryEntity solrSummaryEntity);

    public void shutdown();

}
