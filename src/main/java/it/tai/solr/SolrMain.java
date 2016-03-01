/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tai.solr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author francesco
 */
public class SolrMain {
    
    private static final Logger log = LoggerFactory.getLogger(SolrMain.class);
    
    public static void main(String[] args) {
        
        log.info("--- STARTING Solr Probes ---");
        
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-bean.xml");
        
        
    }
    
}
