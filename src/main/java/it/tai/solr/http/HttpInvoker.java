/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tai.solr.http;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.tai.solr.data.SolrResponse;
import it.tai.solr.exception.UnsupportedActionException;
import java.io.IOException;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author francesco
 */
public class HttpInvoker {

    private static final Logger logger = LoggerFactory.getLogger(HttpInvoker.class);

    private String solrURL;
        
    /**
     * @param solrURL the solrURL to set
     */
    public void setSolrURL(String solrURL) {
        this.solrURL = solrURL;
    }

    public static enum REPORT_ACTION {

        SUMMARY("SUMMARY"),
        REPORT("REPORT");

        private final String code;

        REPORT_ACTION(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public SolrResponse getSolrReport(REPORT_ACTION action) throws IOException, UnsupportedActionException {
        
        String url;
        
        switch (action) {
            case SUMMARY:
                url = solrURL + "action=" + REPORT_ACTION.SUMMARY.getCode() + "&wt=json";
                break;
            case REPORT:
                url = solrURL + "action=" + REPORT_ACTION.REPORT.getCode() + "&wt=json";
                break;
            default:
                throw new UnsupportedActionException("Unsupported report action");
        }      
       
        CloseableHttpClient httpclient = HttpClients.createDefault();
        
        try {
            HttpGet httpget = new HttpGet(url);
            logger.info("Excuting SOLR report request: " + httpget.getRequestLine());
            long currTime = System.currentTimeMillis();

            CloseableHttpResponse response = httpclient.execute(httpget);
            responseTime("SOLR report ", currTime);

            checkResponse(response);

            String data = EntityUtils.toString(response.getEntity());

            if (logger.isDebugEnabled()) {
                logger.debug("Response data \n" + data);
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
                       
            JsonNode root = mapper.readTree(data).path("Summary").path("alfresco").path("Searcher");
            
            SolrResponse toReturn = mapper.readValue(root.traverse(), SolrResponse.class);
            toReturn.setRawResponse(data);
            toReturn.setUrl(httpget.getRequestLine().getUri());
            
            return toReturn;

        } finally {
            httpclient.close();
        }
    }
    
    private void checkResponse(CloseableHttpResponse response) throws IOException {
        if (response == null) {
            throw new IOException("No data response");
        }
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new IOException("Response status code: " + response.getStatusLine().getStatusCode());
        }
    }

    private void responseTime(String operation, long currTime) {
        logger.info(operation + " server response in " + (currTime - System.currentTimeMillis() / 1000.0) + " seconds");
    }

}
