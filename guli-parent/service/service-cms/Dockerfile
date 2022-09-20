FROM adoptopenjdk/openjdk8:ubi
RUN mkdir /app
COPY ./service/service-cms/target/service-cms.jar /app
COPY ./wait-for-it.sh /app
WORKDIR /app
CMD ["java", "-jar", "service-cms.jar"]