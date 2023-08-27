## File Upload Server

http://localhost:8100/upload

* req form-data
    * file=url
* res json
    * 200 url
    * 400 error.message

### Generate Certificate

mkdir ./letsencrypt

sudo docker compose up certbot

### Build Server

sudo docker build --file Dockerfile --tag file-upload-server:1.0 ./

### Run Server

sudo docker stack deploy -c file-upload-server.yml file-upload-server

https://fus1.duckdns.org:450/upload

### Renew Certificate

sudo docker service scale {server_running_on_443_port}=0

sudo docker compose up certbot-renew

sudo docker service scale {server_running_on_443_port}={expected_replica_count}
