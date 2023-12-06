FROM openjdk:17-oracle
EXPOSE 8080
ADD target/stock-0.0.1.jar stock.jar
ENTRYPOINT ["java","-jar", "stock.jar"]