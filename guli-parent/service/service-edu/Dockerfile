FROM adoptopenjdk/openjdk8:ubi
RUN mkdir /app
COPY ./service/service-edu/target/service-edu.jar /app
COPY ./wait-for-it.sh /app
WORKDIR /app
CMD ["java", "-jar", "service-edu.jar"]