cd deploy
docker-compose up -d --scale ms-orders=9 --no-recreate
