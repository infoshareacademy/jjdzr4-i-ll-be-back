#FROM openjdk:11.0.13-jdk-slim-buster

# dodajemy pustą przestrzeń do kontenera, w postaci folderu który będzie utworzony w katalogu głównym kontenera.
# Jednocześnie ustawiamy w application.properties URL do bazy, jako plik, który będzie umiejscowiony w folderze: "jdbc:h2:file:./my_db/DB_1"
# Folder ten musi nazywać się tak jak folder ustawiony w VOLUME. W tym przypadku plik będzie nazywał się "DB_1" i będzie w folderze "my_db"
#VOLUME /my_db/mysql
#
#ADD target/WorkAndFun-0.0.1-SNAPSHOT.jar .
#CMD java -jar WorkAndFun-0.0.1-SNAPSHOT.jar


#### Stage 1: Build the application
FROM adoptopenjdk/openjdk11 as build

# Set the current working directory inside the image
WORKDIR /app

#VOLUME /my_db/mysql

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .

# Build all the dependencies in preparation to go offline.
# This is a separate step so the dependencies will be cached unless
# the pom.xml file has changed.
RUN ./mvnw dependency:go-offline -B

# Copy the project source
COPY src src

# Package the application
RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

#### Stage 2: A minimal docker image with command to run the app
FROM adoptopenjdk/openjdk11:jre

ARG DEPENDENCY=/app/target/dependency

# Copy project dependencies from the build stage
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","pl.infoshare.workandfun.WorkAndFunApplication"]


