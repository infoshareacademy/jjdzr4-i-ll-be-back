# Instrukcja do korzystania z docker-compose.yml

##UWAGA! Wszystkie komendy ```docker-compose``` należy wykonywać z poziomu folderu, w którym znajduje się plik docker-compose.yml
<br></br>
### Z aplikacji można korzystać na 2 sposoby:
### 1) Można odpalać aplikację lokalnie, przez IntelliJ, i łączyć się z DB, która jest w kontenerze.
#### W tym celu, zanim uruchomisz aplikację, należy uruchomić komendę: 

```
docker-compose up -d db
```
#### Ta komenda uruchomi kontener z DB, który będzie dostępny na porcie 3310 (na host, czyli lokalnie!).
#### Lokalnie aplikacja ma ustawiony (w application.properties) connection też do portu 3310.
#### W tym momencie, po wystartowaniu kontenera, można uruchamiać aplikację lokalnie.
<br></br>
### 2) Można odpalać aplikacje i DB w kontenerach, które będą połączone.
#### W tym celu, przy pierwszym uruchomieniu, należy uruchomić komendę:
```
docker-compose up -d --build
```
#### To polecenie uruchomi obydwa kontenery.
### UWAGA! 
#### Komendę `--build` należy wykonać tylko wówczas, jeśli nanieśliśmy w kodzie jakieś zmiany i należy przebudować obraz dockerowy.
#### Jeśli zmian nie było, to do uruchomienia kontenerów wystarczy komenda:
```
docker-compose up db
```
#### Po uruchomieniu kontener z aplikacją jest dostępny na porcie 8081 (http://localhost:8081/). Z kolei kontener z aplikacją komunikuje się z kontenerem z DB po porcie 3306, czyli po porcie wewnętrznym kontenera.

### Dla ciekawskich:

#### Można jednocześnie uruchomić aplikację+DB w kontenerach oraz uruchomić jednocześnie aplikację lokalnie. W tym przypadku aplikacja lokalna będzie dostępna na porcie 8080, z kolei aplikacja z kontenera będzie dostępna na porcie 8081.
#### W tym przypadku dwie aplikacje będą odnosić się do tej samej DB, która jest w kontenerze, czyli zmiana na danych w jednej aplikacji będzie od razu widoczna w drugiej aplikacji.