docker build --tag rabbit:latest .
docker run --name rabbit --link rabbitmq -d -p 9900:8080 rabbit:latest
