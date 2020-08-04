# Spark Applications
Apache Spark is a cluster computing platform designed to be fast and general-
purpose. 
Spark extends the popular MapReduce model to efficiently sup‐
port more types of computations, including interactive queries and stream process‐
ing. Speed is important in processing large datasets, as it means the difference
between exploring data interactively and waiting minutes or hours. One of the main
features Spark offers for speed is the ability to run computations in memory, but the
system is also more efficient than MapReduce for complex applications running on
disk. for more details, go to [spark docs](https://spark.apache.org/docs/latest/)
## setup Apache Spark in ubuntu
spark is built to run in scala (JVM language) so at the first you must install JDK and Scala in your machine\
**First**, update the package index by in your terminal typing:

```
sudo apt-get update
```
1- Now you can install the JDK with the following command:

```
sudo apt-get install default-jdk
```
2- install scala
```
sudo apt-get install scala
```
After installing scala try typing in terminal **scala**
