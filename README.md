# api-noticias

El proyecto Noticias esta hecho en Java y Maven
Se creo el proyecto desde https://start.spring.io/
Java version 11

Instalar java jdk 11 
https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html

Instalar IntelliJ IDEA Community Edition 
https://www.jetbrains.com/idea/download/#section=windows

Instalar Laravel 7
https://laravel.com/docs/7.x/installation

Instalar Mysql
https://dev.mysql.com/downloads/installer/

Las depedencias descargara automaticamente IntelliJ IDEA

Confguración "application.properties"

#Server por 8088
server.port = 8088

# config base de datos
spring.jpa.hibernate.ddl-auto=none
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/news
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# config jpa
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true

spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=5MB

# Caperta donde se guardan las imagenes "images" se crea en la carpeta raiz automaticamente
storage.location=images

