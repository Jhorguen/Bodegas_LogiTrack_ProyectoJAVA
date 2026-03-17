# LogiTrack - Sistema de Gestión de Bodegas

Sistema backend desarrollado en Spring Boot para la gestión de bodegas, productos, movimientos de inventario y auditorías de la empresa LogiTrack S.A.

## Tecnologías

- Java 21
- Spring Boot 3.3.5
- Spring Security + JWT
- Spring Data JPA
- MySQL
- Swagger / OpenAPI 3
- HTML / CSS / JavaScript

## Estructura del Proyecto

```
src/
├── controller/        # Controladores REST
├── service/           # Lógica de negocio
├── repository/        # Acceso a datos (JPA)
├── model/             # Entidades y enums
├── dto/               # Objetos de transferencia
├── config/            # Configuración de seguridad
├── security/          # JWT (filtro y utilidad)
└── exception/         # Manejo global de errores
frontend/
├── index.html         # Login y registro
├── dashboard.html     # Panel principal
├── bodegas.html       # CRUD de bodegas
├── productos.html     # CRUD de productos
├── movimientos.html   # Registro de movimientos
├── auditorias.html    # Registro de auditorías
├── css/styles.css     # Estilos
└── js/api.js          # Consumo de la API
```

## Requisitos

- Java 21
- MySQL 8+
- Maven (incluido con Maven Wrapper)

## Instalación y Ejecución

1. Clonar el repositorio:
```bash
git clone https://github.com/Jhorguen/Bodegas_LogiTrack_ProyectoJAVA.git
cd Bodegas_LogiTrack_ProyectoJAVA
```

2. Crear la base de datos:
```sql
CREATE DATABASE logitrack_db;
```

3. Configurar credenciales en `src/main/resources/application.properties` si es necesario.

4. Ejecutar el proyecto:
```bash
./mvnw spring-boot:run
```

5. Abrir `frontend/index.html` en el navegador.

## Endpoints Principales

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | /auth/register | Registro de usuario |
| POST | /auth/login | Inicio de sesión |
| GET | /bodegas | Listar bodegas |
| POST | /bodegas | Crear bodega |
| GET | /productos | Listar productos |
| GET | /productos/stock-bajo | Productos con stock < 10 |
| POST | /movimientos | Registrar movimiento |
| GET | /movimientos/por-fecha | Filtrar por rango de fechas |
| GET | /auditorias | Listar auditorías |
| GET | /auditorias/por-usuario | Filtrar por usuario |

## Documentación API

Swagger UI disponible en: `/swagger-ui.html`

## Roles

| Rol | Permisos |
|-----|----------|
| ADMIN | CRUD completo, auditorías, movimientos |
| EMPLEADO | Consultar bodegas/productos, registrar movimientos |

## Usuarios de Prueba

| Usuario | Contraseña | Rol |
|---------|------------|-----|
| admin | admin123 | ADMIN |
| empleado1 | admin123 | EMPLEADO |
| empleado2 | admin123 | EMPLEADO |

## Demo en Producción

- **Backend:** https://bodegaslogitrackproyectojava-production.up.railway.app
- **Swagger:** https://bodegaslogitrackproyectojava-production.up.railway.app/swagger-ui.html
- **Frontend:** https://jhorguen.github.io/Bodegas_LogiTrack_ProyectoJAVA/frontend/index.html
