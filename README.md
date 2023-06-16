rabbit2
-------
<p>in git bash</p>
<p>git clone https://githib.com/CharlieSwires/rabbitmongoarchive.git</p>
<p>This contains the java</p>

build
-----

<p>you'll need an application.properties file for the RabbitMQ settings and spring</p>
<p>mvn package</p>

<p>produces rabbit2-0.0.1-SNAPSHOT.jar in target</p>


deploy
------
<p>docker run -d --name some-mongo \
	-e MONGO_INITDB_ROOT_USERNAME=root \
	-e MONGO_INITDB_ROOT_PASSWORD=? \
	mongo:6.0
</p>
<p>If there isn't one deployed and running you'll need rabbitmq.</p>
<p>docker pull rabbitmq:3.10-management</p>
<p>docker run --name rabbitmq --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:3.10-management</p>
<br>
<p>docker build --tag rabbit2:latest .</p>
<p>docker run --name rabbit2 --link some-mongo --link rabbitmq -d -p 9901:8080 rabbit2:latest</p>

