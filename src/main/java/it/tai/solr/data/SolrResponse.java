/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tai.solr.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.tai.solr.dao.entities.SolrSummaryEntity;
import java.util.Date;
/**
 *
 * @author francesco
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SolrResponse {
  
    private int numDocs;
    private int maxDoc;
    private Date openedAt;
    private Date registeredAt;
    
    private String url;
    private String rawResponse;
           

    /**
     * @return the numDocs
     */
    public int getNumDocs() {
        return numDocs;
    }

    /**
     * @return the maxDoc
     */
    public int getMaxDoc() {
        return maxDoc;
    }

    /**
     * @return the openedAt
     */
    public Date getOpenedAt() {
        return openedAt;
    }

    /**
     * @return the registeredAt
     */
    public Date getRegisteredAt() {
        return registeredAt;
    }

    /**
     * @return the rawResponse
     */
    public String getRawResponse() {
        return rawResponse;
    }

    /**
     * @param rawResponse the rawResponse to set
     */
    public void setRawResponse(String rawResponse) {
        this.rawResponse = rawResponse;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    public SolrSummaryEntity toSolrSummaryEntity(SolrResponse solrResponse) {
        SolrSummaryEntity entity = new SolrSummaryEntity();
        entity.setMaxdocs(solrResponse.getMaxDoc());
        entity.setNumdocs(solrResponse.getNumDocs());
        entity.setOpenedAt(solrResponse.getOpenedAt());
        entity.setRegisteredAt(solrResponse.getRegisteredAt());
        entity.setRawResponse(solrResponse.getRawResponse());
        entity.setUrl(solrResponse.getUrl());

        return entity;
    } 

  
}
