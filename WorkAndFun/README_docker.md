# Instrukcja do budowania obrazu Dockerowego i uruchomienia kontenera z aplikacją

## I. WERSJA DŁUGA

### Najpierw upewnij się że masz spakowaną aplikację do .jar w folderze target

### 1) Żeby zbudowac obraz dockerowy, będąc w katalogu gdzie znajduje się Dockerfile, wpisz polecenie:

    
$ docker build . -t **<własna nazwa obrazu (malymi literami)>**


*przykład:*

$ docker build . -t docker-work-and-fun-app

### 2) Uruchamiamy kontener z aplikacją, jednocześnie podpinając volume lokalny.

   
$ docker run -d --volume=/**<ścieżka do volume lokalnego na komputerze>**:/**<ścieżka do volume w kontenerze>** *<nazwa obrazu z aplikacją>*

   przykład:
   
$ docker run -d -p 8080:8080 --volume=/home/maks/WorkAndFun_db/my_db:/my_db docker-work-and-fun-app


* mozna usunąć przekierowanie portów "-p 8080:8080" (jest dobrowolne)
* UWAGA! ścieżka do volume NIE może zawierać znaków specjalnych, takich jak np. "&", ponieważ takie znaki nie są obsługiwane przez konsole. Jeżeli jednak chcemy taki znak użyć, podczas wpisania do konsoli, zamiast np. "/home/maks/Work&Fun_db/my_db" należy wpisać "/home/maks/Work \\& Fun_db/my_db"

Poleceniem --volume spinamy volume z kontenera z volumem lokalnym, na dysku.
Umożliwi to np. podpięcie DB, która znajduje się lokalnie na dysku w postaci pliku, pod ścieżką wpisaną podczas "docker run" poniżej, z DB która jest wewnątrz kontenera.
W przypadku takiego podpięcia, lokalny volume "podmienia" lub zastępuje volume utworzony w kontenerze. A więc jeśli aplikacja w kontenerze korzysta z DB, która leży w volume kontenera, to w przypadku podpięcia lokalnego volume, aplikacja będzie korzystac z DB który leży w tym lokalnym volume.

### 3) Aplikacja działa, możemy dodawać dane do DB za pomocą np. POSTów. Widzimy, że mamy plik "nazwa.mv.db" zarówno w lokalnym folderze jak i w kontenerze.
   Kontener teraz możemy zatrzymywać, uruchamiać ponownie, nawet możemy całkowicie usunąć i postawić na nowo (ważne, żeby z tym samym volume mounting do lokalnego pliku)  i dane nam nie zginą, bo są w lokalnym pliku.

Jeśli ktoś utworzy nowy kontener, a w lokalnym volume nie będzie miał DB, to utworzy mu się pusta baza.

Dobrą praktyką jest tworzenie kontenerów, i podłączenie do nich plików zewnętrznych plików, np. z bazą danychm jak w tym przypadku.

___________________________________

## II. WERSJA KRÓTKA

Znajdując się w katalogu aplikacji, gdzie jest umiejscowiony plik Dockerfile, wpisz:

1)      docker build . -t docker-work-and-fun-app
   
   
2)      docker run -d -p 8080:8080 --volume=/home/username/WorkAndFun_db/my_db:/my_db docker-work-and-fun-app

* UWAGA! ścieżka do volume NIE może zawierać znaków specjalnych, takich jak np. "&", ponieważ takie znaki nie są obsługiwane przez konsole. Jeżeli jednak chcemy taki znak użyć, podczas wpisania do konsoli, zamiast np. "/home/maks/Work&Fun_db/my_db" należy wpisać "/home/maks/Work \& Fun_db/my_db"

Kontener uruchomiony. DB aplikacji podpięta do lokalnej ścieżki "home/username/WorkAndFun_db/my_db"