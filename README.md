# Banco via docker
docker run --name postgres -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:alpine

docker start postgres