# Build stage using Tomcat and Java 8
FROM tomcat:9.0-jdk8-temurin AS build

# Install Ant
RUN apt-get update && \
    apt-get install -y ant && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY . .

# Build using Tomcat's server libraries
RUN ant -Dj2ee.server.home=/usr/local/tomcat -f build.xml clean dist

# Runtime stage
FROM tomcat:9.0-jdk8-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/dist/CyberThreatReportingSystem.war \
    /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["sh", "-c", "sed -i \"s/port=\\\"8080\\\"/port=\\\"${PORT:-8080}\\\"/\" /usr/local/tomcat/conf/server.xml && catalina.sh run"]