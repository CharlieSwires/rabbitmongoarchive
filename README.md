<p>http://localhost:9900/UniCard/publish</p>
<p>docker build --tag rabbit:latest .</p>
<p>docker run --name rabbit --link rabbitmq --link archiver -d -p 9900:8080 rabbit:latest</p>
