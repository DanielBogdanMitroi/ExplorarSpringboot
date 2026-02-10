# ExplorarSpringboot

## Descripción
Aplicación web Spring Boot con CRUD completo para MySQL usando REST API. Este proyecto implementa las guías oficiales de Spring:
- [Accessing Data with MySQL](https://spring.io/guides/gs/accessing-data-mysql)
- [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa)

## Características
- ✅ API REST completa con operaciones CRUD (Create, Read, Update, Delete)
- ✅ Integración con MySQL usando Spring Data JPA
- ✅ Endpoints para gestión de usuarios
- ✅ Configuración flexible mediante variables de entorno

## Requisitos Previos
- Java 17 o superior
- Maven 3.6+
- MySQL 8.0+

## Configuración de la Base de Datos

### 1. Crear la base de datos y usuario
```sql
CREATE DATABASE db_example;
CREATE USER 'springuser'@'%' IDENTIFIED BY 'ThePassword';
GRANT ALL PRIVILEGES ON db_example.* TO 'springuser'@'%';
FLUSH PRIVILEGES;
```

### 2. Configurar las credenciales
Puedes modificar la configuración en `src/main/resources/application.properties` o usar variables de entorno:
- `MYSQL_HOST`: Host de MySQL (por defecto: localhost)
- `MYSQL_USER`: Usuario de MySQL (por defecto: springuser)
- `MYSQL_PASSWORD`: Contraseña de MySQL (por defecto: ThePassword)

## Instalación y Ejecución

### 1. Clonar el repositorio
```bash
git clone https://github.com/DanielBogdanMitroi/ExplorarSpringboot.git
cd ExplorarSpringboot
```

### 2. Compilar el proyecto
```bash
mvn clean install
```

### 3. Ejecutar la aplicación
```bash
mvn spring-boot:run
```

La aplicación se iniciará en `http://localhost:8080`

## API Endpoints

### CREATE - Crear un nuevo usuario
```bash
curl -X POST http://localhost:8080/api/users/add \
  -d "name=Juan Perez" \
  -d "email=juan@example.com"
```

### READ - Obtener todos los usuarios
```bash
curl http://localhost:8080/api/users/all
```

### READ - Obtener un usuario por ID
```bash
curl http://localhost:8080/api/users/1
```

### UPDATE - Actualizar un usuario
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -d "name=Juan Martinez" \
  -d "email=juanm@example.com"
```

### DELETE - Eliminar un usuario
```bash
curl -X DELETE http://localhost:8080/api/users/1
```

## Estructura del Proyecto
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
│   │       └── application.properties               # Configuración
│   └── test/
│       └── java/
├── pom.xml                                          # Dependencias Maven
└── README.md
```

## Tecnologías Utilizadas
- **Spring Boot 3.2.1**: Framework principal
- **Spring Data JPA**: Persistencia de datos
- **Spring Web**: API REST
- **MySQL Connector**: Driver de MySQL
- **Hibernate**: ORM
- **Maven**: Gestión de dependencias

## Guías de Referencia
- [Spring Boot Maven Plugin](https://docs.spring.io/spring-boot/docs/3.2.1/maven-plugin/reference/html/)
- [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.1/reference/htmlsingle/index.html#web)
- [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.1/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)

## Solución de Problemas

### Error de conexión a MySQL
- Verifica que MySQL esté ejecutándose
- Confirma las credenciales en `application.properties`
- Asegúrate de que la base de datos `db_example` existe

### Puerto 8080 en uso
Puedes cambiar el puerto en `application.properties`:
```properties
server.port=8081
```

## Licencia
Este proyecto es de código abierto y está disponible bajo la licencia MIT.