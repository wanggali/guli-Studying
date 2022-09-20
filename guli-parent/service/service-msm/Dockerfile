FROM adoptopenjdk/openjdk8:ubi
RUN mkdir /app
COPY ./service/service-msm/target/service-msm.jar /app
COPY ./wait-for-it.sh /app
WORKDIR /app
CMD ["java", "-jar", "service-msm.jar"]