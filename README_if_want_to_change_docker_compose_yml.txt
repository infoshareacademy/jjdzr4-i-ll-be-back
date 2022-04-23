$ docker-compose down   -> stop containers
$ docker volume rm $(docker volume ls -q)  -> unmount/remove all volumes (neccessary step!)
$ docker-compose up -d <service name from docker-compose.yml> -> restart service again
