<code>
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
<p>http://localhost:9900/UniCard/publish</p>
<p>docker build --tag rabbit:latest .</p>
<p>docker run --name rabbit --link rabbitmq --link archiver -d -p 9900:8080 rabbit:latest</p>
