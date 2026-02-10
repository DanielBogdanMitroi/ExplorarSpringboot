# Guía Completa - Aplicación CRUD Spring Boot con MySQL

## 📚 Tabla de Contenidos
1. [Introducción](#introducción)
2. [Requisitos Previos](#requisitos-previos)
3. [Configuración de MySQL](#configuración-de-mysql)
4. [Instalación y Ejecución](#instalación-y-ejecución)
5. [Uso de la Interfaz Web](#uso-de-la-interfaz-web)
6. [API REST Endpoints](#api-rest-endpoints)
7. [Ejemplos con cURL](#ejemplos-con-curl)
8. [Arquitectura del Proyecto](#arquitectura-del-proyecto)
9. [Solución de Problemas](#solución-de-problemas)

## 📖 Introducción

Esta aplicación es un ejemplo completo de CRUD (Create, Read, Update, Delete) implementado con Spring Boot y MySQL. Sigue las mejores prácticas de las guías oficiales de Spring:
- [Accessing Data with MySQL](https://spring.io/guides/gs/accessing-data-mysql)
- [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa)

### Características Principales
- ✅ API REST completa con Spring Boot
- ✅ Persistencia de datos con MySQL
- ✅ Interfaz web moderna y responsive
- ✅ Spring Data JPA para abstracción de base de datos
- ✅ Validación y manejo de errores
- ✅ Configuración mediante variables de entorno

## 🔧 Requisitos Previos

Antes de comenzar, asegúrese de tener instalado:

- **Java Development Kit (JDK) 17 o superior**
  ```bash
  java -version
  ```

- **Apache Maven 3.6 o superior**
  ```bash
  mvn -version
  ```

- **MySQL Server 8.0 o superior**
  ```bash
  mysql --version
  ```

- **Git** (opcional, para clonar el repositorio)
  ```bash
  git --version
  ```

## 🗄️ Configuración de MySQL

### Paso 1: Iniciar MySQL
```bash
# En Linux/Mac
sudo systemctl start mysql

# En Windows (como administrador)
net start MySQL80
```

### Paso 2: Ejecutar el Script de Configuración
```bash
# Opción 1: Desde la línea de comandos
mysql -u root -p < setup-database.sql

# Opción 2: Desde el cliente MySQL
mysql -u root -p
source setup-database.sql;
```

### Paso 3: Verificar la Configuración
```bash
mysql -u springuser -pThePassword -e "SHOW DATABASES;"
```

### Configuración Manual (Alternativa)
Si prefiere configurar manualmente, ejecute estos comandos en MySQL:
```sql
CREATE DATABASE db_example;
CREATE USER 'springuser'@'%' IDENTIFIED BY 'ThePassword';
GRANT ALL PRIVILEGES ON db_example.* TO 'springuser'@'%';
FLUSH PRIVILEGES;
```

## 🚀 Instalación y Ejecución

### Método 1: Usando el Script Automático
```bash
# Configurar la contraseña (requerido)
export MYSQL_PASSWORD=ThePassword

# Ejecutar el script
./run.sh
```

### Método 2: Usando Maven
```bash
# 1. Configurar la contraseña (requerido)
export MYSQL_PASSWORD=ThePassword

# 2. Compilar el proyecto
mvn clean install

# 3. Ejecutar la aplicación
mvn spring-boot:run
```

### Método 3: Usando el JAR generado
```bash
# 1. Configurar la contraseña (requerido)
export MYSQL_PASSWORD=ThePassword

# 2. Compilar y empaquetar
mvn clean package

# 3. Ejecutar el JAR
java -jar target/accessing-data-mysql-0.0.1-SNAPSHOT.jar
```

La aplicación se iniciará en: **http://localhost:8080**

## 🌐 Uso de la Interfaz Web

Una vez que la aplicación esté ejecutándose, abra su navegador y visite:
```
http://localhost:8080
```

### Características de la Interfaz
- **Crear Usuario**: Formulario para agregar nuevos usuarios
- **Listar Usuarios**: Ver todos los usuarios registrados
- **Buscar Usuario**: Buscar un usuario específico por ID
- **Actualizar Usuario**: Modificar la información de un usuario
- **Eliminar Usuario**: Borrar un usuario del sistema

## 🔌 API REST Endpoints

### 1. CREATE - Crear un nuevo usuario
```
POST /api/users/add
Parámetros: name, email
```

### 2. READ - Obtener todos los usuarios
```
GET /api/users/all
```

### 3. READ - Obtener un usuario por ID
```
GET /api/users/{id}
```

### 4. UPDATE - Actualizar un usuario
```
PUT /api/users/{id}
Parámetros: name (opcional), email (opcional)
```

### 5. DELETE - Eliminar un usuario
```
DELETE /api/users/{id}
```

## 💻 Ejemplos con cURL

### Crear un Usuario
```bash
curl -X POST "http://localhost:8080/api/users/add" \
  -d "name=Juan Pérez" \
  -d "email=juan@example.com"
```

### Obtener Todos los Usuarios
```bash
curl http://localhost:8080/api/users/all
```

### Obtener Usuario por ID
```bash
curl http://localhost:8080/api/users/1
```

### Actualizar Usuario
```bash
curl -X PUT "http://localhost:8080/api/users/1" \
  -d "name=Juan Martinez" \
  -d "email=juanm@example.com"
```

### Eliminar Usuario
```bash
curl -X DELETE http://localhost:8080/api/users/1
```

## 🏗️ Arquitectura del Proyecto

```
ExplorarSpringboot/
├── src/
│   ├── main/
│   │   ├── java/com/example/accessingdatamysql/
│   │   │   ├── AccessingDataMysqlApplication.java  # Clase principal
│   │   │   ├── User.java                           # Entidad JPA
│   │   │   ├── UserRepository.java                 # Repositorio
│   │   │   └── MainController.java                 # Controlador REST
│   │   └── resources/
│   │       ├── application.properties               # Configuración
│   │       └── static/
│   │           └── index.html                       # Interfaz web
│   └── test/
│       └── java/
├── pom.xml                                          # Dependencias Maven
├── setup-database.sql                               # Script de BD
├── run.sh                                           # Script de ejecución
├── GUIA.md                                          # Esta guía
└── README.md                                        # README principal
```

### Componentes Principales

#### 1. **User.java** - Entidad JPA
Define la estructura de datos del usuario:
- `id`: Identificador único (auto-generado)
- `name`: Nombre del usuario
- `email`: Correo electrónico

#### 2. **UserRepository.java** - Repositorio
Interfaz que extiende `CrudRepository` proporcionando:
- Operaciones CRUD automáticas
- Métodos de consulta personalizados

#### 3. **MainController.java** - Controlador REST
Maneja todas las solicitudes HTTP:
- Mapeo de rutas
- Validación de datos
- Respuestas HTTP apropiadas

#### 4. **application.properties** - Configuración
Contiene:
- Configuración de la base de datos
- Propiedades de Hibernate
- Variables personalizables

## 🔧 Configuración Avanzada

### Variables de Entorno
Puede personalizar la configuración sin modificar archivos:

```bash
# Linux/Mac
export MYSQL_HOST=192.168.1.100
export MYSQL_USER=miusuario
export MYSQL_PASSWORD=mipassword

# Windows (CMD)
set MYSQL_HOST=192.168.1.100
set MYSQL_USER=miusuario
set MYSQL_PASSWORD=mipassword

# Ejecutar la aplicación
mvn spring-boot:run
```

### Cambiar el Puerto de la Aplicación
Agregue en `application.properties`:
```properties
server.port=8081
```

O use variable de entorno:
```bash
export SERVER_PORT=8081
```

### Configuración de Hibernate
Las opciones disponibles en `application.properties`:

```properties
# Estrategias de DDL
spring.jpa.hibernate.ddl-auto=update  # update, create, create-drop, validate, none

# Mostrar SQL en consola
spring.jpa.show-sql=true

# Formatear SQL
spring.jpa.properties.hibernate.format_sql=true
```

## 🐛 Solución de Problemas

### Error: "Access denied for user 'springuser'"
**Solución**: Verificar credenciales en MySQL
```bash
mysql -u root -p
GRANT ALL PRIVILEGES ON db_example.* TO 'springuser'@'%';
FLUSH PRIVILEGES;
```

### Error: "Communications link failure"
**Solución**: Verificar que MySQL esté corriendo
```bash
# Linux
sudo systemctl status mysql

# Windows
net start MySQL80
```

### Error: "Port 8080 is already in use"
**Solución**: Cambiar el puerto o detener la aplicación que lo usa
```bash
# Encontrar el proceso
lsof -i :8080

# Cambiar puerto en application.properties
server.port=8081
```

### Error: "Table 'db_example.user' doesn't exist"
**Solución**: Verificar configuración de Hibernate
```properties
spring.jpa.hibernate.ddl-auto=update
```

### Error al compilar: "Invalid target release: 17"
**Solución**: Verificar versión de Java
```bash
java -version
# Debe ser Java 17 o superior
```

## 📊 Pruebas

### Probar con Postman
1. Importar colección de endpoints
2. Configurar base URL: `http://localhost:8080`
3. Ejecutar las solicitudes

### Probar desde la Interfaz Web
1. Abrir `http://localhost:8080`
2. Crear algunos usuarios de prueba
3. Verificar todas las operaciones CRUD

### Verificar en MySQL
```bash
mysql -u springuser -pThePassword db_example
SELECT * FROM user;
```

## 📚 Recursos Adicionales

- [Documentación Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Hibernate ORM](https://hibernate.org/)
- [MySQL Documentation](https://dev.mysql.com/doc/)

## 🤝 Contribuir

Si desea contribuir a este proyecto:
1. Fork el repositorio
2. Cree una rama para su feature
3. Haga commit de sus cambios
4. Push a la rama
5. Abra un Pull Request

## 📝 Licencia

Este proyecto está bajo la licencia MIT.

## ✨ Características Futuras

- [ ] Autenticación y autorización
- [ ] Paginación en la lista de usuarios
- [ ] Búsqueda avanzada
- [ ] Validación de datos más robusta
- [ ] Tests unitarios e integración
- [ ] Documentación con Swagger
- [ ] Docker containerization

---

¡Gracias por usar esta aplicación! Si tiene preguntas o sugerencias, no dude en abrir un issue.
