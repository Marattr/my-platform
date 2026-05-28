FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY currency-service.jar currency-service.jar

RUN chgrp -R 0 /app && \
    chmod -R g=u /app

USER 1001

HEALTHCHECK --interval=30s --timeout=5s --start-period=60s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=50.0"

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar currency-service.jar"]