# sb-vuejs-poc

Este proyecto constituye la primera prueba de concepto de una aplicación formada por un backend **Spring Boot** y un frontend **VueJS**

## Aplicación backend - Spring Boot

La aplicación de backend se encuentra en la carpeta `back/` del proyecto. Es una aplicación multi-módulo. El módulo comunidades contiene la aplicación SpringBoot

### Configurar la aplicación

Ir al directorio `comunidades/src/main/resources` y renombrar el fichero `application.properties.TEMPLATE` por `application.properties`

Modificar las propiedades de configuración de la conexión a la base de datos y la clave secreta de firma de los tokens JWT

### Construir la aplicación

Desde el directorio `back/` ejecutar la orden

`./mvnw install`

Esto compilará los módulos y los instalará en el repositorio maven local.
También ejecutará el test inicial de la aplicación. Esto provocará que se inicie el
contexto de **Spring** y se lance el proceso de **Flyway** que aplicará las migraciones iniciales en la base de datos

El siguiente paso será ejecutar la aplicación. Esto se hará:

`./mvnw spring-boot:run -pl comunidades`

Es necesario indicar el módulo del proyecto que incluye la clase Main de la aplicación

### Probar la API REST

Abriremos otro terminal para poder probar cómo funciona la API REST desarrollada. En el ejemplo se usa la herramienta curl pero se puede usar cualquier otra

1. Acceder a una URL protegida sin autenticarse:
```shell
$ curl -s http://localhost:8080/comunidades
```
```json
 {"timestamp":"2020-12-27T09:51:25.006+00:00","status":401,"error":"Unauthorized","message":"Error: Unauthorized","path":"/comunidades"}
```

2. Registro de un nuevo usuario:
```shell
$ curl -s --request POST \
   --url http://localhost:8080/auth/signup \
   --header 'Content-Type: application/json' \
   --data '{"username":"user","email":"user@example.com","password":"secret"}'
```
```json
 {"timestamp":"2020-12-27T09:58:08.012+00:00","status":400,"error":"Bad Request","message":"Error: Username is already taken!","path":"/auth/signup"}
```

```shell
$ curl -s --request POST \
   --url http://localhost:8080/auth/signup \
   --header 'Content-Type: application/json'\
   --data '{"username":"user2","email":"user2@example.com","password":"secret"}'
```
```json
{"message":"User registered sucessfully!"}
```

3. Logarse con el usuario registrado. Quedarse con el token recibido para las siguientes operaciones
```shell
$ curl -s --request POST \
   --url http://localhost:8080/auth/signin \
   --header 'Content-Type: application/json' \
   --data '{"username": "user","password": "secret"}'
```
```json
{"id":1,"username":"user","email":"user@example.com","roles":["ROLE_USER"],"accessToken":"eyJhbG.....BemBbA","tokenType":"Bearer"}
```

4. Volver a pedir la lista de items, ahora sí con autenticación
```shell
$ curl -s --request GET  --url http://localhost:8080/comunidades \
     --header 'Authorization: Bearer eyJhbG.....BemBbA'
```
```json
[]
```

5. Crear un item
```shell
$ curl -s --request POST \
   --url http://localhost:8080/comunidades \
   --header 'Authorization: Bearer eyJhbG.....BemBbA' \
   --header 'Content-Type: application/json' \
   --data '{"nombre":"Comunidad 1"}'
```
```json
{"id":2,"nombre":"Comunidad 1"}
```

```shell
$ curl -s --request GET  --url http://localhost:8080/comunidades \
     --header 'Authorization: Bearer eyJhbG.....BemBbA'
```
```json
[{"id":1,"nombre":"Comunidad 1"},{"id":2,"nombre":"Comunidad 1"}]
```

6. Modificar un item
```shell
$ curl -s --request PUT \
   --url http://localhost:8080/comunidades/2 \
   --header 'Authorization: Bearer eyJhbG.....BemBbA' \
   --header 'Content-Type: application/json' \
   --data '{"nombre":"Comunidad 2"}'
```
```json
{"id":2,"nombre":"Comunidad 2"}
```
```shell
$ curl -s --request GET  --url http://localhost:8080/comunidades \
     --header 'Authorization: Bearer eyJhbG.....BemBbA'
```
```json
[{"id":1,"nombre":"Comunidad 1"},{"id":2,"nombre":"Comunidad 2"}]
```

7. Eliminar un item

```shell
$ curl -s --request DELETE \
   --url http://localhost:8080/comunidades/2 \
   --header 'Authorization: Bearer eyJhbG.....BemBbA'
```
```shell
$ curl -s --request GET  --url http://localhost:8080/comunidades \
     --header 'Authorization: Bearer eyJhbG.....BemBbA'
```
```json
[{"id":1,"nombre":"Comunidad 1"}]
```





## Aplicación frontend - VueJS
