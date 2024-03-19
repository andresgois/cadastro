FROM maven:3.6-jdk-8 AS build

WORKDIR /app

RUN apt update && apt install -y wget

COPY pom.xml .

COPY src src
#RUN mvn clean install
RUN mvn package

#RUN export DOCKER_BUILDKIT=0
#RUN export COMPOSE_DOCKER_CLI_BUILD=0
#FROM andresgois/wildfly20.0.0-final
FROM andresgois/wildfly21

#RUN find /opt/wildfly-20.0.0.Final/standalone/deployments/ -type f -name "*." -exec rm -f {} \;
USER root

COPY --from=build /app/target/*.war /opt/wildfly-20.0.0.Final/standalone/deployments/
RUN /opt/wildfly-20.0.0.Final/bin/add-user.sh admin admin --silent
RUN rm -rf /opt/wildfly-20.0.0.Final/standalone/configuration/standalone_xml_history/current
RUN rm -rf /opt/wildfly-20.0.0.Final/standalone/deployments/*.failed
RUN chmod -R 777 /opt/wildfly-20.0.0.Final/
#COPY jboss/ /opt/wildfly-20.0.0.Final/modules/
#COPY lib/ /opt/wildfly-20.0.0.Final/modules/system/layers/base/org/
#COPY standalone-full.xml /opt/wildfly-20.0.0.Final/standalone/configuration/standalone-full.xml
#COPY standalone.conf /opt/wildfly-20.0.0.Final/bin/standalone.conf

RUN chmod +x /opt/wildfly-20.0.0.Final/bin/standalone.sh

EXPOSE 8080

#CMD ["/opt/wildfly-20.0.0.Final/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
#CMD ["/opt/wildfly-20.0.0.Final/bin/standalone.sh", "-b", "0.0.0.0", "-c", "standalone-full.xml"]
CMD ["/opt/wildfly-20.0.0.Final/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "-c", "standalone-full.xml"]
