# Getting Started
Api specs are available at http://localhost:8000/swagger-ui/index.html#/

You can use below credentials for accessing apis. Credentials are managed using in-memory db.
Grocery application uses h2 db for managing items and grocery inventory gets initialy loaded with data.sql.
No initial data of orders are kept.

username password
admin	 admin
user     user

Dockerfile and compose are present in dir. docker-compose can be used to scale the application.
Docker image is available at https://hub.docker.com/r/amarpreetsingha/grocery
