/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tai.solr.dao.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author francesco
 */
@Entity
@Table( name = "SOLRSUMMARY" )
public class SolrSummaryEntity implements Serializable {
    
    private static final Logger logger = LoggerFactory.getLogger(SolrSummaryEntity.class);
    
    private static final long serialVersionUID = 1L;
    
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "ID" )
    @Id
    private long id;
    
    @Column( name = "NUMDOCS" )
    private int numdocs;
    
    @Column( name = "MAXDOCS" )
    private int maxdocs;
    
    @Column( name = "OPENEDAT" )
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date openedAt;
    
    @Column( name = "REGISTEREDAT" )
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date registeredAt;
    
    @Column( name = "SOLRURL" )
    private String url;
    
    @Column( name = "RAWRESPONSE" )
    @Lob
    private String rawResponse;
    
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the numdocs
     */
    public int getNumdocs() {
        return numdocs;
    }

    /**
     * @param numdocs the numdocs to set
     */
    public void setNumdocs(int numdocs) {
        this.numdocs = numdocs;
    }

    /**
     * @return the maxdocs
     */
    public int getMaxdocs() {
        return maxdocs;
    }

    /**
     * @param maxdocs the maxdocs to set
     */
    public void setMaxdocs(int maxdocs) {
        this.maxdocs = maxdocs;
    }

    /**
     * @return the openedAt
     */
    public Date getOpenedAt() {
        return openedAt;
    }

    /**
     * @param openedAt the openedAt to set
     */
    public void setOpenedAt(Date openedAt) {
        this.openedAt = openedAt;
    }

    /**
     * @return the registeredAt
     */
    public Date getRegisteredAt() {
        return registeredAt;
    }

    /**
     * @param registeredAt the registeredAt to set
     */
    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append("---- SOLR SUMMARY ---\n");
        sb.append("openedAt=").append(openedAt).append("\n");
        sb.append("registeredAt=").append(registeredAt).append("\n");
        sb.append("number of documents=").append(numdocs).append("\n");
        sb.append("max documents=").append(maxdocs).append("\n");
        
        return sb.toString(); 
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
    
    
    
}
