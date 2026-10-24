# 📚 Biblioteca Digital

Proyecto de aprendizaje desarrollado con **Spring Boot**, **Thymeleaf** y **MySQL**.
Permite gestionar autores, libros y editoriales, con registro e inicio de sesión de usuarios.

## ✨ Funcionalidades

- Registro e inicio de sesión con **Spring Security** (contraseñas encriptadas con BCrypt)
- Roles de usuario: `USER` y `ADMIN`
- CRUD de **autores**, **libros** y **editoriales**
- Perfil de usuario con **foto de perfil**
- Menú de navegación con perfil y cierre de sesión
- Página de errores personalizada (400, 401, 403, 404, 500)

## 🛠️ Tecnologías

- Java 17+
- Spring Boot (Web, Data JPA, Security)
- Thymeleaf
- MySQL 8
- Bootstrap 5
- Maven

## 🚀 Cómo ejecutarlo

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/TU_USUARIO/biblioteca.git
   cd biblioteca
   ```
2. Tener MySQL en marcha. La base `biblioteca` se crea sola.
3. Configurar el usuario y la clave de MySQL. Por defecto usa `root` / `root`; para cambiarlos:
   ```bash
   # Windows (PowerShell)
   $env:DB_USERNAME="mi_usuario"; $env:DB_PASSWORD="mi_clave"
   # Linux / macOS
   export DB_USERNAME=mi_usuario DB_PASSWORD=mi_clave
   ```
4. Ejecutar:
   ```bash
   ./mvnw spring-boot:run
   ```
5. Abrir <http://localhost:8081>

## 👤 Crear un administrador

Los usuarios nuevos se registran con rol `USER`. Para convertir uno en administrador:

```sql
UPDATE usuario SET rol = 'ADMIN' WHERE email = 'tu@correo.com';
```

## 📁 Estructura

```
src/main/java/com/egg/biblioteca
├── controladores   # Controladores web
├── entidades       # Entidades JPA y servicios
├── excepciones
└── repositorios
src/main/resources
├── templates       # Vistas Thymeleaf
└── static/css
```

## 📝 Notas

Proyecto realizado con fines educativos.
