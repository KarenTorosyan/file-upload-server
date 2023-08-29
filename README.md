## File Upload Server

    /upload

* req form-data
    * file=url
* res json
    * 200 url
    * 400 error.message

### Generate Certificate

mkdir ./letsencrypt

> **Important**
>  Override  domain

sudo docker compose up certbot

### Build Server

sudo docker build --file Dockerfile --tag file-upload-server:1.0 ./

### Create Volumes

sudo docker volume create file-upload-server-volume

### Run Server

> **Important**
>  Override   certificate

sudo docker stack deploy -c file-upload-server.yml file-upload-server

### Renew Certificate

sudo docker service scale {server_running_on_443_port}=0

sudo docker compose up certbot-renew

sudo docker service scale {server_running_on_443_port}={expected_replica_count}
