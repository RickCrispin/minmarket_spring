# Demo Spring Boot - Instalación y puesta en marcha

## Descripción

Proyecto Spring Boot con JSP, MySQL y Maven.

## Requisitos previos

- Java 17 instalado
- Maven instalado (o usar `mvnw` / `mvnw.cmd` incluido)
- MySQL 8+ instalado
- XAMPP o Laragon para gestionar MySQL (opcional pero recomendado)

## Configuración de MySQL

Este proyecto usa la base de datos `minimarket_ventas` en MySQL.
Los valores en `src/main/resources/application.properties` son:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/minimarket_ventas
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

Si cambias el host, el puerto, el usuario o la contraseña, actualiza este archivo.

## Crear la base de datos

### Opción 1: XAMPP

1. Abre el panel de control de XAMPP.
2. Inicia `Apache` y `MySQL`.
3. Haz clic en `Admin` de MySQL para abrir phpMyAdmin.
4. En phpMyAdmin, crea la base de datos `minimarket_ventas`:
   - Nombre: `minimarket_ventas`
   - cotejamiento: `utf8mb4_unicode_ci` (recomendado)

5. Importa el script SQL desde `src/main/resources/db.sql`:
   - Selecciona la base de datos `minimarket_ventas`
   - Ve a la pestaña `Importar`
   - Selecciona el archivo `db.sql`
   - Ejecuta la importación
6. (Alternativa) Crear base:
    - Ingresar a la ruta `src/main/resources/`
    - Localizar el archivo `db.sql` y copiar el script
    - Ejecutarlo en el panel de phpMyAdmin.

### Opción 2: Laragon

1. Abre Laragon.
2. Inicia `Apache` y `MySQL`.
3. Haz clic en el icono de Laragon y selecciona `MySQL` > `Admin` o `phpMyAdmin`.
4. Crea la base de datos `minimarket_ventas`.
5. Importa `src/main/resources/db.sql` desde phpMyAdmin o ejecuta el script directamente con MySQL:

```bash
mysql -u root -p < src/main/resources/db.sql
```

> Si tu contraseña de MySQL no es vacía, reemplaza `-p` con `-pTU_CONTRASENA` o ingrésala cuando se solicite.

## Instalar dependencias con Maven

Desde la raíz del proyecto, ejecuta:

```powershell
.\\mvnw.cmd clean install
```

O si tienes Maven instalado globalmente:

```powershell
mvn clean install
```

Esto descargará dependencias y compilará el proyecto.

## Inicializar el proyecto

Para arrancar la aplicación:

```powershell
.\\mvnw.cmd spring-boot:run
```

O con Maven instalado globalmente:

```powershell
mvn spring-boot:run
```

Después de iniciar, accede a la aplicación en:

```
http://localhost:8085
```

## Notas importantes

- El archivo `src/main/resources/application.properties` define la conexión a MySQL.
- El script SQL `src/main/resources/db.sql` crea las tablas necesarias.
- Si usas otro usuario o contraseña, ajusta también `spring.datasource.username` y `spring.datasource.password`.

## Problemas comunes

- Si MySQL usa otro puerto, actualiza `spring.datasource.url`.
- Si la base de datos no existe, crea `minimarket_ventas` y ejecuta `db.sql`.
- Si el servidor Tomcat embebido no arranca, revisa los logs en la consola para mensajes de error.



## FUTURAS IMPLEMENTACIONES:

Stock ingresando los datos en la tabla detalle de venta y restando stock de la tabla venta, y cancelar o al ocurrir un error se eliminen las columnas de detalle de venta de la venta y se elimine la columna de ventas haciendo que esos nunca hubieran existido, asi mismo se devuelve el valor de stock a cada producto, y en la base de datos mysql asegurar que cuando en el sistema ocurra un error, una transaction devuelva a la normalidad como si no hubiera ocurrido el proceso y devolviendo todo a su lugar. 


Asi mismo cuando se quiera recuperar en el mismo sistema un proveso de venta almacenado en la tabla de ventas y se mire claramente que quedo pendiente, se pueda buscar para continuar, cancelarla o confirmarla.
