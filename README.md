rabbit
------
<p>in git bash</p>
<p>git clone https://gitlab.com/UnicardLtd/product/RabbitMQ-Broker.git</p>
<p>This contains the java</p>

build
-----

<p>you'll need an application.properties file for the RabbitMQ settings and spring</p>
<p>mvn package</p>

<p>produces rabbit-0.0.1-SNAPSHOT.jar in target</p>


deploy
------
<p>docker build --tag rabbit2:latest .</p>
<p>docker run --name rabbit2 --link rabbitmq -d -p 9901:8080 rabbit2:latest</p> 


RESTful
-------
<code> payload:
[
    {
        "customerId": "charlie2",
        "usedDate": "2022-10-12T00:00:00.500+00:00",
        "xmlData": "<start attrib=\"ishvie\">hurbfwrbwrb</start>"
    },
    {
        "customerId": "swires2",
        "usedDate": "2022-10-13",
        "xmlData": "<start></start>"
    }
]
</code>
<p>POST - http://localhost:9901/UniCard/api/v1/dataarray</p>

