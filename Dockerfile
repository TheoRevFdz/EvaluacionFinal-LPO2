FROM openjdk:17 as builder

WORKDIR /app/evaluacion-final

COPY ./pom.xml /app
COPY ./.mvn ./.mvn
COPY ./mvnw .
COPY ./pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
# RUN ./mvnw dependency:go-offline
COPY ./src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/evaluacion-final/target/EvaluacionFinal-LPO2-0.0.1-SNAPSHOT.jar .

ENV PORT 8081

EXPOSE $PORT

CMD ["java", "-Dspring.profiles.active=prod", "-jar", "EvaluacionFinal-LPO2-0.0.1-SNAPSHOT.jar"]
