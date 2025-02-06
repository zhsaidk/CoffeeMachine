FROM alpine:latest AS base

RUN apk add openjdk17

COPY . /CoffeeMachine

WORKDIR /CoffeeMachine

RUN ./gradlew bootJar

FROM alpine:latest
RUN apk add openjdk17
WORKDIR /app
COPY --from=base /CoffeeMachine/build/libs/CoffeeMachine-*jar ./service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "service.jar"]
CMD ["--spring.config.location=classpath:/application.yaml,classpath:/application-dev.yaml"]
