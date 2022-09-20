FROM adoptopenjdk/openjdk8:ubi
RUN mkdir /app
COPY ./service/service-statistics/target/service-statistics.jar /app
COPY ./wait-for-it.sh /app
WORKDIR /app
CMD ["java", "-jar", "service-statistics.jar"]