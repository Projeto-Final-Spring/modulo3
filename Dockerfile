FROM amazoncorretto:24-alpine
LABEL MANTAINER="MODULO3"

WORKDIR /app

EXPOSE 8081

RUN wget -O dd-java-agent.jar 'https://dtdg.co/latest-java-tracer'

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]