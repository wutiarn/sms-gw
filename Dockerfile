FROM openjdk:14-alpine
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package

FROM openjdk:14-alpine
WORKDIR /app
COPY --from=0 /app/target/sms-gw.jar .
CMD java -Xms64m -Xmx128m -jar sms-gw.jar
