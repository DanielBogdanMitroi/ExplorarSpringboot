# Guía de Configuración WAMP

## 📦 Requisitos Previos

- WAMP Server instalado y ejecutándose
- Java 17 o superior
- Maven

## 📖 Importación de Base de Datos

### Opción 1: Usar phpMyAdmin (Recomendado)

1. **Iniciar WAMP**
   - Abre WAMP y espera a que el icono se ponga verde
   - Verifica que Apache y MySQL/MariaDB estén ejecutándose

2. **Abrir phpMyAdmin**
   - Haz clic en el icono de WAMP en la bandeja del sistema
   - Selecciona: `phpMyAdmin`
   - O navega a: `http://localhost/phpmyadmin`

3. **Importar el Script**
   - En phpMyAdmin, haz clic en la pestaña **"SQL"** en el menú superior
   - Copia y pega el contenido del archivo `wamp-setup-database.sql`
   - Haz clic en **"Continuar"** o **"Go"**

4. **Verificar la Importación**
   - En el panel izquierdo, deberías ver la base de datos `db_example`
   - Haz clic en ella y verifica que la tabla `user` existe
   - Haz clic en la tabla `user` y luego en **"Examinar"** para ver los datos de ejemplo

### Opción 2: Usar la Línea de Comandos de MySQL

```bash
# Navega a la carpeta bin de MySQL en WAMP
cd C:\wamp64\bin\mysql\mysql8.0.X\bin

# Ejecuta el script (reemplaza la ruta con la ubicación de tu archivo)
mysql -u root -p < C:\ruta\a\tu\wamp-setup-database.sql

# Si no tienes contraseña para root (por defecto en WAMP):
mysql -u root < C:\ruta\a\tu\wamp-setup-database.sql
```

## 🚀 Ejecutar la Aplicación

1. **Verifica que WAMP esté corriendo**
   ```
   Icono de WAMP = Verde
   ```

2. **Importa la base de datos** (usando uno de los métodos anteriores)

3. **Ejecuta la aplicación Spring Boot**
   ```bash
   ./mvnw spring-boot:run
   # o en Windows:
   mvnw.cmd spring-boot:run
   ```

4. **Prueba la aplicación**
   - API REST: `http://localhost:8080/demo/all`
   - Agregar usuario: `http://localhost:8080/demo/add?name=Juan&email=juan@example.com`

## ✅ Checklist de Verificación

- [ ] WAMP instalado y ejecutándose (icono verde)
- [ ] Base de datos `db_example` creada en phpMyAdmin
- [ ] Tabla `user` visible en la base de datos
- [ ] Aplicación Spring Boot ejecutándose sin errores de conexión
- [ ] Puedes acceder a los endpoints REST

## 🔍 Troubleshooting

### Error de Conexión
```
Communications link failure
```
**Solución**: Verifica que WAMP esté corriendo y que el puerto sea 3306

### Error de Autenticación
```
Access denied for user 'root'@'localhost'
```
**Solución**: Verifica la contraseña en `application.properties`. Por defecto WAMP usa `root` sin contraseña.

### Puerto 8080 Ocupado
```
Port 8080 is already in use
```
**Solución**: Cambia el puerto en `application.properties`:
```properties
server.port=8081
```

## 📝 Configuración Personalizada

Si necesitas cambiar la configuración por defecto:

1. **Cambiar contraseña de base de datos**: Edita `src/main/resources/application.properties`
2. **Cambiar puerto del servidor**: Modifica `server.port` en `application.properties`
3. **Cambiar nombre de base de datos**: Actualiza `spring.datasource.url` y ejecuta el script SQL modificado
