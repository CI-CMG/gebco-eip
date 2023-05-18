# GEBCO - Enterprise Integration Patterns - Ingest Pipeline Demo

## About
This project contains a demonstration of building a pipeline to ingest 
bathymetric data.  This is an academic example and is NOT a fully functional
pipeline for use in the real world.  There are many edge cases that are
not handled and the data are artificial.  This WILL provide a high level
understanding of how to build a pipeline using some common Enterprise
Integration Patterns (EIPs), including Message Endpoints, Message Channels,
Splitters, Aggregators, Message Routers, Message Filters, and Dead Letter Channels.

This project is written in Java.  However, the use of Java is not meant to be
the purpose of this project.  This is why XML based configuration was chosen over 
the Java DSL, even though the Java DSL is the recommended configuration approach for
actual pipelines.

## Requirements and Setup

### Java and Maven
Install JDK 11 or later.  A useful tool for this is SDKMan: https://sdkman.io/

This project requires Apache Maven.  You can use SDKMan or install it from: https://maven.apache.org/download.cgi

### Docker and PostgreSQL

Docker needs to be installed in order easily to run a PostgreSQL database.

To create and start the database run:
```
./start-db.sh
```

To stop and destroy the database run:
```
./stop-db.sh
```

### ActiveMQ Artemis

ActiveMQ Artemis is a message broker.  Download and install it here:  https://activemq.apache.org/components/artemis/download/

After it is installed configure it by running the following from the Artemis bin directory:
```
./artemis create mybroker
```

And start it by invoking the "run" command from the "mybroker" instance created above.

### Building

From the root of the project run:
```
mvn clean install
```

## Usage

The examples-routes directory contains several route configuration files that
incrementally develop the example detailed here: https://lucid.app/documents/view/ed995d30-5124-47ac-a18f-64060be2c20f.

Replace the route.xml in src/main/resources/camel.  The name does not matter. Only one file should be in this directory.

After updating the file, rebuild the project and in the target directory run:
```
java -jar gebco-eip-<VERSION>-exe.jar
```

