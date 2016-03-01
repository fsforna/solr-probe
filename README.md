# solr-probe
A probe to check the availability of the Solr >= 1.4 index server. The probe aims to monitor the health status of a Solr server. The probe will send a warning message to the configured email address if an abnormal condition is detected, so you can take countermeasures promptly.

The probe reads a Solr JSON summary and analyzes **openedAt** and **registeredAt** index dates. It then computes the time elapsed between the configured number of days. If the time is greater than the configured threshold, it reckons that the index generation or update is stuck, and it sends a warning email message.

# How to build it yourself
Clone the project and configure the probe by editing the `conf.properties` file. Then run `mvn package`.
To execute the program you simply use:

    java -Djsse.enableSNIExtension=false -Dconf=/conf.properties -cp /YOUR_PATH_LIB/lib/*:/solr-probe-1.0-SNAPSHOT.jar it.tai.solr.SolrMain

# Author
Francesco Fornasari
