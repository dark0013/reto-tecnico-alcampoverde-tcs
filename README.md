## Microservicios de Gestión de Clientes y Operaciones Bancarias

Este proyecto incluye dos microservicios construidos con Spring Boot y JPA, encargados de administrar los datos de clientes y las cuentas junto con sus movimientos, todo implementado bajo una arquitectura basada en microservicios.


## 🚀 Requisitos Previos  

Antes de ejecutar el proyecto, asegúrate de tener instalados los siguientes componentes:  

| Componente | Versión Requerida | Descripción |
|------------|------------------|-------------|
| ☕ **Java** | **17** | Necesario para compilar y ejecutar los microservicios. |
| 🗄 **MySQL** | — | Base de datos relacional utilizada para persistir la información. |
| 🛠 **Maven** | **3.6+** | Herramienta para gestionar dependencias y compilar el proyecto. |

## Configuración de la Base de Datos

1. **Instalar MySQL**  
   Verifica que MySQL esté correctamente instalado y funcionando en tu equipo.

2. **Crear el esquema y el usuario**  
   - Crea una base de datos con el nombre `mediapp19`.  
   - Genera un usuario y concédele todos los permisos requeridos sobre este esquema para permitir su uso sin restricciones.
     
## Configuración de Base de Datos

Ejecuta el siguiente script SQL para crear la base de datos y el usuario con los permisos correspondientes:

```sql
CREATE DATABASE IF NOT EXISTS mediapp19;

CREATE USER IF NOT EXISTS 'admin'@'%' IDENTIFIED BY 'admin';

GRANT ALL PRIVILEGES ON mediapp19.* TO 'admin'@'%';

FLUSH PRIVILEGES;


