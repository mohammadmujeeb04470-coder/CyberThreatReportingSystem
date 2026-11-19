# Build stage
FROM eclipse-temurin:8-jdk

WORKDIR /app

# Install Ant
RUN apt-get update && \
    apt-get install -y ant && \
    rm -rf /var/lib/apt/lists/*

# Copy project
COPY . .

# Build the WAR file
RUN ant -f build.xml clean dist


# Runtime stage
FROM tomcat:9.0-jdk8-temurin

# Remove default Tomcat application
RUN rm -rf /usr/local/tomcat/webapps/*

# Deploy our application as the root application
COPY --from=0 /app/dist/CyberThreatReportingSystem.war \
    /usr/local/tomcat/webapps/ROOT.war

# Render will provide the PORT environment variable
EXPOSE 8080

CMD ["sh", "-c", "sed -i \"s/port=\\\"8080\\\"/port=\\\"${PORT:-8080}\\\"/\" /usr/local/tomcat/conf/server.xml && catalina.sh run"]