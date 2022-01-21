FROM gradle:7.3-jdk17-alpine as build-gradle

WORKDIR /app
COPY src /app/src
COPY build.gradle settings.gradle /app/

RUN gradle build

FROM anapsix/alpine-java:latest

ENV MYSQL_ADDRESS="localhost:3306"
ENV MYSQL_USERNAME=""
ENV MYSQL_PASSWORD=""
ENV SMTP_HOST=""
ENV SMTP_USERNAME=""
ENV SMTP_PASSWORD=""
ENV MAIL_DOMAIN=""

WORKDIR /app

COPY --from=build-gradle /app/build/libs libs/
COPY --from=build-gradle /app/build/resources resources/
COPY --from=build-gradle /app/build/classes classes/

ENTRYPOINT ["java","-jar","libs/hoawiki.jar"]
CMD [
"-DMYSQL_ADDRESS=${MYSQL_ADDRESS}",
"-DMYSQL_USERNAME=${MYSQL_USERNAME}",
"-DMYSQL_PASSWORD=${MYSQL_PASSWORD}",
"-DSMTP_HOST=${SMTP_HOST}",
"-DSMTP_USERNAME=${SMTP_USERNAME}",
"-DSMTP_PASSWORD=${SMTP_PASSWORD}",
"-DMAIL_DOMAIN=${MAIL_DOMAIN}"
    ]

EXPOSE 8085
