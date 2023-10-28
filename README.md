# Docs

http://localhost:8100 (dev)

https://domain (prod)

    /upload

* request
    * body form-data(file=blob)
    * method POST
    * headers
        * Content-Type multipart/form-data
* response
    * 200 url
    * 400 error.message

## File Upload Server (dev)

### Build and start server

docker compose up file-upload-server-dev

## File Upload Server (prod)

### Generate Certificate

mkdir ./letsencrypt

> **Important**
> Override domain

docker compose up certbot

### Build Server

docker build --file Dockerfile --tag file-upload-server:1.0 ./

### Create Volumes

docker volume create file-upload-server-volume

### Run Server

> **Important**
> Override certificate

docker stack deploy -c file-upload-server.yml file-upload-server

### Renew Certificate

docker service scale {server_running_on_443_port}=0

docker compose up certbot-renew

docker service scale {server_running_on_443_port}={expected_replica_count}
