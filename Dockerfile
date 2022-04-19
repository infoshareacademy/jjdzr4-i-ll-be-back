#### Stage 1: Build the application
FROM adoptopenjdk/openjdk11 as build

# Set the current working directory inside the image
WORKDIR /app

#VOLUME /my_db/mysql

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml files
COPY pom.xml .
COPY WorkAndFun/pom.xml WorkAndFun/pom.xml
COPY WorkAndFunConsole/pom.xml WorkAndFunConsole/pom.xml

# Build all the dependencies in preparation to go offline.
RUN ./mvnw dependency:go-offline -B -e -C

COPY WorkAndFun/src WorkAndFun/src
COPY WorkAndFunConsole/src WorkAndFunConsole/src

RUN ./mvnw package -DskipTests

CMD [ "java", "-jar", "/app/WorkAndFun/target/WorkAndFun-0.0.1-SNAPSHOT.jar" ]