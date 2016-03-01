# solr-probe
A SOLR simple probe to check availability of your SOLR >=1.4 index server. The probe aims to be used to monitoring the health status of solr server. The probe will send an warning message to configured email address, if an abnormal solr condition will be checked; so you can manage an operation promptly.

The probe checks a json SOLR summary to analyze openedAt and registeredAt index dates to check the time elapsed between the configured number of days. If the time is greater than the configured threshold, we can reckon that the SOLR server should have stuck index generation or updating and we send a warning message to check the SOLR servers.

# How to build it yourself
Clone the project and configure the probe by editing the conf.properties file. So run the mvn package. 
To execute the program you simply use:

java -Djsse.enableSNIExtension=false -Dconf=/conf.properties -cp /YOUR_PATH_LIB/lib/*:/solr-probe-1.0-SNAPSHOT.jar it.tai.solr.SolrMain

# Author
Francesco Fornasari



