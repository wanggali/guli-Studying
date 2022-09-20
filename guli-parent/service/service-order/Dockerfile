FROM adoptopenjdk/openjdk8:ubi
RUN mkdir /app
COPY ./service/service-order/target/service-order.jar /app
COPY ./wait-for-it.sh /app
WORKDIR /app
CMD ["java", "-jar", "service-order.jar"]