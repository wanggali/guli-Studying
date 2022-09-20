FROM adoptopenjdk/openjdk8:ubi
RUN mkdir /app
COPY ./service/service-oss/target/service-oss.jar /app
COPY ./wait-for-it.sh /app
WORKDIR /app
CMD ["java", "-jar", "service-oss.jar"]