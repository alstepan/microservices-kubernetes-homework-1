FROM openjdk:17
ADD target/scala-2.13/hello-scala-assembly-1.0.jar /opt/hello-scala/hello-scala-assembly-1.0.jar
WORKDIR /opt/hello-scala
EXPOSE 8000/tcp
CMD ["java", "-jar", "hello-scala-assembly-1.0.jar"]