FROM maven:3.6-jdk-8 AS build

WORKDIR /app

RUN apt update && apt install -y wget

COPY pom.xml .

COPY src src
RUN mvn clean install
RUN mvn package

#RUN export DOCKER_BUILDKIT=0
#RUN export COMPOSE_DOCKER_CLI_BUILD=0
FROM andresgois/wildfly20.0.0-final

COPY --from=build /app/target/*.war /opt/wildfly-20.0.0.Final/standalone/deployments/
#COPY jboss/ /opt/wildfly-20.0.0.Final/modules/
COPY lib/ /opt/wildfly-20.0.0.Final/modules/system/layers/base/org/
COPY standalone-full.xml /opt/wildfly-20.0.0.Final/standalone/configuration/standalone-full.xml
COPY standalone.conf /opt/wildfly-20.0.0.Final/bin/standalone.conf

USER root
RUN chmod +x /opt/wildfly-20.0.0.Final/bin/standalone.sh

EXPOSE 8080

CMD ["/opt/wildfly-20.0.0.Final/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]