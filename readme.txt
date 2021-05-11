docker images


docker ps -a

docker stop id

docker rm id


docker run -d --name selenium-hub selenium/hub

docker logs 7e30a27d6d17


docker run -d -p 4446:4444 --name selenium-hub -P selenium/hub

docker ps -a --> port yönlendirilmesi gözükmeli


docker run -d -p 5901:5900  -P --link selenium-hub:hub selenium/node-chrome-debug

keremulusoy@Kerem-MBP ~ % docker ps -a
CONTAINER ID   IMAGE                        COMMAND                  CREATED          STATUS          PORTS                    NAMES
8fa70feece28   selenium/node-chrome-debug   "/opt/bin/entry_poin…"   10 seconds ago   Up 8 seconds    0.0.0.0:5901->5900/tcp   zealous_keldysh
9c649cbdafaa   selenium/node-chrome-debug   "/opt/bin/entry_poin…"   26 seconds ago   Created                                  elegant_margulis
66f8f15653ad   selenium/hub                 "/opt/bin/entry_poin…"   12 minutes ago   Up 12 minutes   0.0.0.0:4446->4444/tcp   selenium-hub


docker run -d -p 5902:5900  -P --link selenium-hub:hub selenium/node-firefox-debug


docker-compose up -d

docker-compose ps

docker-compose logs chromefoxnode
docker-compose logs firefoxnode
docker-compose logs seleniumhub
docker-compose stop