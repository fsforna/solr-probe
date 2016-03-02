# solr-probe
A probe to check the availability of the Solr >= 1.4 index server. The probe aims to monitor the health status of a Solr server. The probe will send a warning message to the configured email address if an abnormal condition is detected, so you can take countermeasures promptly.

The probe reads a Solr JSON summary and analyzes **openedAt** and **registeredAt** index dates. It then computes the time elapsed between the configured number of days. If the time is greater than the configured threshold, it reckons that the index generation or update is stuck, and it sends a warning email message.

# How to build it yourself
Clone the project and configure the probe by editing the `conf.properties` file. Then run `mvn package`.
To execute the program you simply use:

    java -Djsse.enableSNIExtension=false -Dconf=/conf.properties -cp /YOUR_PATH_LIB/lib/*:/solr-probe-1.0-SNAPSHOT.jar it.tai.solr.SolrMain

# What to do, if the automatic message arrives

So, what to do if the automatic message will arrive?

For sure, the information you have (in time) is that your Solr indexes are not changed for too much time and this could be very suspectious in a used environment. No matter if your environment is quite "static" but in average, the indexes not updated, definitely indicates an abnormal condition to consider.

For example, it may happen that the SOLR process went down due to an out-of-memory failure (which balancers normally fail to detect in a clustered environment). In our experience is happened that no errors (or warnings) were stored in the log files, even if the Solr process no longer indexed any content.

# Author
Francesco Fornasari
