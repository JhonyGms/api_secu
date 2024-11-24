# Proyecto API con Spring Boot

Este es un proyecto de demostración que utiliza Spring Boot para crear una API REST segura con autenticación JWT.

## Requisitos

- Java 21
- Maven

## Configuración del Proyecto

1. Clona el repositorio:
   ```bash
   git clone  https://github.com/JhonyGms/api_secu.git

2. Abre el proyecto en tu IDE favorito.
3. Ejecuta la aplicación.
4. Abre tu navegador y navega a `http://localhost:8080/swagger-ui.html` para ver la documentación de la API.
5. Para autenticarte, envía una solicitud POST a `http://localhost:8080/authenticate` con el siguiente cuerpo:
   ```json
   {
       "username": "defaultUser",
       "password": "defaultPassword"
   }
   ```
6. Copia el token JWT de la respuesta.
