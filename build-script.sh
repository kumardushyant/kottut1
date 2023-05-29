#!/bin/bash
set -e

docker build -t kottut1:test1 .
docker run -d --privileged --rm --name=kottut1 --ulimit nofile=1024 -p 8080:8080 kottut1:test1
echo "Working on creating checkpoint"
sleep 20
docker commit --change='ENTRYPOINT ["/project/restore.sh"]' $(docker ps -aqf "name=kottut1") kottut1:test2
docker kill $(docker ps -aqf "name=kottut1")