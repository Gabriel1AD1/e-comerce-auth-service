# Auth Service

El servicio de autenticación es un microservicio que maneja el registro de usuarios, inicio de sesión y renovación de
tokens en un sistema distribuido. Este servicio permite gestionar la autenticación mediante JWT y está diseñado para ser
utilizado por otros microservicios.

## Características

- **Autenticación JWT**: Proporciona tokens JWT para autenticar usuarios en el sistema.
- **Registro de usuarios**: Permite la creación de nuevos usuarios con un tipo de usuario específico.
- **Renovación de tokens**: Ofrece un endpoint para renovar tokens JWT mediante el uso de un refresh token.

## Endpoints

### 1. **Login** (Iniciar sesión)

**URL**: `/auth/login`  
**Método**: `POST`

**Descripción**: Este endpoint permite a los usuarios iniciar sesión proporcionando su email y contraseña. Si las
credenciales son correctas, se genera un JWT que el usuario puede utilizar para autenticarse en otros servicios.

**Cuerpo de la solicitud**:

```json
{
  "email": "usuario@ejemplo.com",
  "password": "contraseña_segura"
}
```

**Respuesta**:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJVU0VSIiwidXNlcl9pZCI6Miwicm9sZXMiOltdLCJpYXQiOjE3Mzc5MTY1NTMsImV4cCI6MTczNzkxNjU1N30.0Dkt3YT3trikAlZdIdh1EiRt6fojXg_NUmJ0pWzBM2o",
  "expiration": 3600,
  "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJSRUZSRVNIIiwidXNlcl9pZCI6MiwiaWF0IjoxNzM3OTE2NTU0LCJleHAiOjE3Mzc5MTcxNTh9.cJ5-fg6zkxqx8sb_zzbaB6mauFHSOnwq6RPa2fnW9x4"
}
```

### 2. **Refresh Token** (Renovar token)

**URL**: `/auth/refresh-token`  
**Método**: `POST`

**Descripción**: Permite renovar el token JWT usando un refresh token previamente obtenido. Este endpoint proporciona un
nuevo token de acceso.

**Cuerpo de la solicitud**:

```json
{
  "refresh-token": "refreshTokenPrevio"
}
```

**Respuesta**:

```json
{
  "newToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### 3. **Sign Up** (Registrar nuevo usuario)

**URL**: `/auth/sign-up/{type-user}`  
**Método**: `POST`

**Descripción**: Registra un nuevo usuario en el sistema, especificando un tipo de usuario (por ejemplo, `admin`,
`user`).

**Cuerpo de la solicitud**:

```json
{
  "email": "nuevo@usuario.com",
  "password": "contraseña_segura",
  "tipoUsuario": "admin"
}
```

**Respuesta**:

```json
{
  "message": "Usuario creado con éxito."
}
```

## Dependencias

El servicio utiliza las siguientes dependencias:

- **Spring Boot**: Framework principal para el desarrollo del microservicio.
- **Spring Security**: Para la implementación de la seguridad y autenticación.
- **Spring Data JPA**: Para la integración con bases de datos relacionales.
- **Java JWT**: Para la creación y validación de tokens JWT.
- **OpenFeign**: Para la comunicación entre microservicios.
- **Lombok**: Para reducir la verbosidad del código mediante generación automática de getters, setters, etc.
- **MapStruct**: Para mapear objetos entre DTOs y entidades.

## Instalación

### Requisitos

- **JDK 17** o superior.
- **Maven** o **Gradle**.
- **MySQL** (si utilizas base de datos relacional).

### Pasos para correr el servicio localmente:

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/tu_usuario/auth-service.git
   cd auth-service
   ```

2. **Configurar las variables de entorno**:
   Crea un archivo `.env` en la raíz del proyecto con las siguientes variables:
   ```bash
   DB_URL=jdbc:mysql://localhost:3306/tu_base_de_datos
   DB_USERNAME=tu_usuario
   DB_PASSWORD=tu_contraseña
   JWT_SECRET=clave_secreta
   ```

3. **Ejecutar el servicio**:
    - Si usas Gradle:
      ```bash
      ./gradlew bootRun
      ```

    - Si usas Maven:
      ```bash
      mvn spring-boot:run
      ```

4. **Acceder al servicio**:
   El servicio estará disponible en `http://localhost:4001`.

## Contribuciones

Si deseas contribuir a este proyecto, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una rama nueva (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios.
4. Haz un commit de tus cambios (`git commit -am 'Agregada nueva funcionalidad'`).
5. Empuja tus cambios a tu fork (`git push origin feature/nueva-funcionalidad`).
6. Abre un Pull Request para discutir tus cambios.

## Licencia

Este proyecto está licenciado bajo la **Apache License 2.0**. Puedes ver los detalles completos en el
archivo [LICENSE](./LICENSE).

---

¡Gracias por usar nuestro servicio de autenticación!

```

Este es un archivo `README.md` completo para tu proyecto. Contiene:

- Una descripción de lo que hace el servicio.
- Los endpoints que ofrece y sus detalles.
- Instrucciones para instalar y ejecutar el servicio localmente.
- Información sobre cómo contribuir al proyecto.
- Un agradecimiento final.

Puedes usar este contenido como base para tu repositorio en GitHub. ¡Espero que te sea útil!