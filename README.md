# SIGYM - Sistema de Gestión de Gimnasio

## Descripción

SIGYM es un sistema desarrollado como proyecto final del diplomado
**Desarrollo de Sistemas con Tecnología Java**.

El sistema permite administrar prospectos, membresías, servicios y pagos.

Cuenta con una API REST y una interfaz web desarrollada con Thymeleaf.

También implementa seguridad mediante Spring Security, JWT,
Refresh Token y Cookies HttpOnly.

---

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- Thymeleaf
- JWT
- Refresh Token
- Lombok
- MariaDB
- Maven
- Postman
- BCrypt
- SLF4J / Logback

---

## Funcionalidades principales

### Prospectos

- Registrar prospectos.
- Consultar prospectos.
- Editar prospectos.
- Eliminar prospectos.

### Membresías

- Crear membresías.
- Consultar membresías.
- Editar membresías.
- Eliminar membresías.
- Asociar una membresía con un prospecto.
- Asociar uno o varios servicios a una membresía.

### Servicios

- Registrar servicios.
- Consultar servicios.
- Editar servicios.
- Eliminar servicios.

### Pagos

- Registrar pagos.
- Consultar pagos.
- Editar pagos.
- Eliminar pagos.
- Asociar pagos con membresías.

---

## Seguridad

SIGYM utiliza Spring Security para proteger los recursos del sistema.

Se implementaron los siguientes mecanismos:

- Autenticación mediante usuario y contraseña.
- Contraseñas cifradas con BCrypt.
- JWT para autenticación de la API REST.
- Refresh Token para renovar el Access Token.
- Cookies HttpOnly para la autenticación web.
- Protección de endpoints.
- Respuesta HTTP 401 para accesos no autorizados.

---


## Usuario administrador

Al iniciar la aplicación, Spring Boot comprueba si existe
un usuario administrador con el nombre configurado.

Si no existe, se crea automáticamente utilizando las
siguientes variables de entorno:

- `ADMIN_USERNAME`: nombre del usuario administrador.
- `ADMIN_PASSWORD`: contraseña del administrador.

El usuario se crea con el rol `ADMIN` y estado activo.

La contraseña se almacena mediante un hash BCrypt
utilizando `PasswordEncoder`.

**Nota:** Si el usuario ya existe en la base de datos,
la aplicación no modifica su contraseña.


## Ejecución del proyecto

### Requisitos

Para ejecutar el proyecto se requiere:

- JDK 17
- MariaDB
- Maven
- IntelliJ IDEA o algún IDE compatible con Maven

### Pasos de ejecución

1. Configurar la base de datos MariaDB.
2. Revisar la configuración de conexión en:

```text
src/main/resources/application.properties
```

3. Abrir el proyecto en IntelliJ IDEA.
4. Esperar a que Maven descargue las dependencias.
5. Ejecutar la clase principal:

```text
SigymRestApplication
```

6. Esperar a que en la consola aparezca:

```text
Started SigymRestApplication
```

7. Abrir en el navegador:

```text
http://localhost:8080/
```

---

## Flujo de uso web


1. El usuario ingresa a:

   http://localhost:8080/

2. El sistema redirige automáticamente a la pantalla de inicio de sesión.

3. El usuario inicia sesión con las credenciales configuradas mediante las variables de entorno:

    - Usuario: valor de `ADMIN_USERNAME`
    - Contraseña: valor de `ADMIN_PASSWORD`

4. Spring Security valida las credenciales.
5. El sistema genera:
    - Access Token
    - Refresh Token
6. Los tokens se almacenan mediante Cookies HttpOnly.
7. El usuario accede al menú principal de SIGYM.
8. Desde el menú principal puede acceder a:
    - Prospectos
    - Membresías
    - Servicios
    - Pagos
9. Cada módulo permite realizar operaciones de alta, consulta, modificación y eliminación.
10. Al cerrar sesión se eliminan las cookies de autenticación.

---

## Interfaz web

### Inicio

```text
http://localhost:8080/
```

### Prospectos

```text
http://localhost:8080/web/prospectos
```

### Membresías

```text
http://localhost:8080/web/membresias
```

### Servicios

```text
http://localhost:8080/web/servicios
```

### Pagos

```text
http://localhost:8080/web/pagos
```

---

## API REST

SIGYM también puede utilizarse mediante Postman.

Los endpoints REST están protegidos mediante JWT.

---

## Login REST

Para iniciar sesión desde Postman:

```text
POST http://localhost:8080/api/auth/login
```


**Body → raw → JSON**

```json
{
  "username": "TU_USUARIO_ADMIN",
  "password": "TU_PASSWORD_ADMIN"
}
```


El servidor devuelve una respuesta similar a:

```json
{
  "accessToken": "JWT_GENERADO",
  "refreshToken": "REFRESH_TOKEN_GENERADO"
}
```

---

## Uso del Access Token

Para acceder a los endpoints protegidos se debe utilizar el Access Token
como Bearer Token.

Ejemplo:

```text
GET http://localhost:8080/api/prospectos
```

En Postman:

```text
Authorization
Type: Bearer Token
Token: ACCESS_TOKEN
```

Si la petición se realiza sin un token válido, el sistema responde:

```text
401 Unauthorized
```

Si se utiliza un JWT válido, el sistema responde:

```text
200 OK
```

---

## Refresh Token

El Access Token tiene una duración limitada.

Para obtener un nuevo Access Token se utiliza el Refresh Token.

Petición:

```text
POST http://localhost:8080/api/auth/refresh
```

Body:

```json
{
  "refreshToken": "REFRESH_TOKEN"
}
```

El sistema valida el Refresh Token y genera un nuevo Access Token sin
solicitar nuevamente el usuario y la contraseña.

---

## Endpoints principales

### Autenticación

```text
POST /api/auth/login
POST /api/auth/refresh
```

### Prospectos

```text
GET    /api/prospectos
GET    /api/prospectos/{id}
POST   /api/prospectos
PUT    /api/prospectos/{id}
DELETE /api/prospectos/{id}
```

### Membresías

```text
GET    /api/membresias
GET    /api/membresias/{id}
POST   /api/membresias
PUT    /api/membresias/{id}
DELETE /api/membresias/{id}
```

### Servicios

```text
GET    /api/servicios
GET    /api/servicios/{id}
POST   /api/servicios
PUT    /api/servicios/{id}
DELETE /api/servicios/{id}
```

### Pagos

```text
GET    /api/pagos
GET    /api/pagos/{id}
POST   /api/pagos
PUT    /api/pagos/{id}
DELETE /api/pagos/{id}
```

---

## Modelo de datos

Las principales entidades del sistema son:

- Usuario
- Prospecto
- Membresia
- Servicio
- Pago
- RefreshToken

Las relaciones principales son:

- Un prospecto puede estar relacionado con membresías.
- Una membresía pertenece a un prospecto.
- Una membresía puede tener varios servicios.
- Un servicio puede estar relacionado con varias membresías.
- Un pago pertenece a una membresía.
- Un Refresh Token pertenece a un usuario.

---

## Estructura del proyecto

La estructura principal del código es:

```text
src/main/java/sigym_rest
├── config
├── controller
├── dto
├── exception
├── mapper
├── model
├── repository
├── response
├── security
└── service
```

Las vistas Thymeleaf se encuentran en:

```text
src/main/resources/templates
```

La configuración principal se encuentra en:

```text
src/main/resources/application.properties
```

---

## Logging

El proyecto utiliza SLF4J y Logback para registrar eventos importantes
durante la ejecución.

Entre los eventos que pueden registrarse se encuentran:

- Inicio de sesión.
- Generación de tokens.
- Creación de registros.
- Actualización de registros.
- Eliminación de registros.
- Errores durante la ejecución.

---

## Flujo general del sistema

```text
Usuario
   |
   v
Login
   |
   v
Spring Security
   |
   v
JWT + Refresh Token
   |
   v
Cookie HttpOnly / Bearer Token
   |
   v
Controller
   |
   v
Service
   |
   v
Repository
   |
   v
MariaDB
```

---

## Proyecto final

Proyecto desarrollado para el módulo de Spring Security del diplomado
**Desarrollo de Sistemas con Tecnología Java**.