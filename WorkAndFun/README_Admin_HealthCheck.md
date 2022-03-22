Krotki opis:

Jeżeli chcesz używać monitorowania statusu aplikacji,
najpierw należy uruchomić aplikację waf-admin.

Następnie dopiero uruchomić aplikację WorkAndFunApplication,
ponieważ podczas uruchomienia aplikacji, łączy się ona
z waf-admin i tam się rejestruje.

Jeżeli postąpisz odwrotnie zauważysz w logach na konsoli,
że rejestrowanie w waf-admin sie nie powiodlo :)

aplikacja waf-admin uruchamia sie na porcie 8081