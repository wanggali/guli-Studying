FROM adoptopenjdk/openjdk8:ubi
RUN mkdir /app
COPY ./service/service-acl/target/service-acl.jar /app
COPY ./wait-for-it.sh /app
WORKDIR /app
CMD ["java", "-jar", "service-acl.jar"]