FROM gradle:8.5-jdk17 AS build
ENV APP_HOME=/app
WORKDIR ${APP_HOME}
COPY --chown=gradle:gradle . ${APP_HOME}
RUN gradle clean build --no-daemon

FROM openjdk:17.0.2-slim AS builder
ARG JAR_FILE=config-server-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/app
WORKDIR ${APP_HOME}
COPY --from=build ${APP_HOME}/build/libs/${JAR_FILE} ${APP_HOME}/${JAR_FILE}
RUN java -Djarmode=layertools -jar ${JAR_FILE} extract

FROM openjdk:17.0.2-slim
ENV APP_HOME=/app
WORKDIR ${APP_HOME}
COPY --from=builder ${APP_HOME}/dependencies/ ./
COPY --from=builder ${APP_HOME}/spring-boot-loader/ ./
COPY --from=builder ${APP_HOME}/snapshot-dependencies/ ./
COPY --from=builder ${APP_HOME}/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]