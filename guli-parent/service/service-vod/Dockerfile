FROM adoptopenjdk/openjdk8:ubi
RUN mkdir /app
COPY ./service/service-vod/target/service-vod.jar /app
COPY ./wait-for-it.sh /app
WORKDIR /app
CMD ["java", "-jar", "service-vod.jar"]