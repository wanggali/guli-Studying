FROM adoptopenjdk/openjdk8:ubi
RUN mkdir /app
COPY ./service/service-ucenter/target/service-ucenter.jar /app
COPY ./wait-for-it.sh /app
WORKDIR /app
CMD ["java", "-jar", "service-ucenter.jar"]