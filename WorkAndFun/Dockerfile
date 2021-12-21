FROM openjdk:11.0.13-jdk-slim-buster

# dodajemy pustą przestrzeń do kontenera, w postaci folderu który będzie utworzony w katalogu głównym kontenera.
# Jednocześnie ustawiamy w application.properties URL do bazy, jako plik, który będzie umiejscowiony w folderze: "jdbc:h2:file:./my_db/DB_1"
# Folder ten musi nazywać się tak jak folder ustawiony w VOLUME. W tym przypadku plik będzie nazywał się "DB_1" i będzie w folderze "my_db"
VOLUME /my_db

ADD target/WorkAndFun-0.0.1-SNAPSHOT.jar .
CMD java -jar WorkAndFun-0.0.1-SNAPSHOT.jar