# 🧩 Microservicio de Usuarios

<div align="center">

**Proyecto TPI – UTN FRC – Grupo 114**

[![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen?style=for-the-badge&logo=spring)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=for-the-badge&logo=postgresql)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker)](https://www.docker.com/)

*Sistema de gestión de usuarios, clientes y transportistas con autenticación integrada*

</div>

---

## 📋 Tabla de Contenidos

- [Descripción](#-descripción)
- [Tecnologías](#-tecnologías)
- [Inicio Rápido](#-inicio-rápido)
- [Configuración de pgAdmin](#-configuración-de-pgadmin)
- [Endpoints y Pruebas](#-endpoints-y-pruebas)
- [Ejemplos de Uso](#-ejemplos-de-uso)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Solución de Problemas](#-solución-de-problemas)
- [Equipo](#-equipo)

---

## 🎯 Descripción

Este microservicio proporciona una solución completa para la gestión de usuarios con diferentes roles, permitiendo:

- ✅ Registro de usuarios con roles **CLIENTE** o **TRANSPORTISTA**
- ✅ Creación automática de entidades asociadas
- ✅ Consultas individuales y listados completos
- ✅ Validaciones robustas (duplicados, roles inválidos, etc.)
- ✅ Persistencia en PostgreSQL con Spring Data JPA
- ✅ Autenticación mediante Keycloak preconfigurado

---

## 🛠️ Tecnologías

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Java | 17 | Lenguaje principal |
| Spring Boot | 3.1.5 | Framework backend |
| Spring Data JPA | - | ORM y persistencia |
| Hibernate | 6 | Motor de JPA |
| PostgreSQL | 15 | Base de datos |
| Docker Compose | - | Orquestación de contenedores |
| pgAdmin | 4 | Gestión de BD |
| Keycloak | - | Autenticación y autorización |

---

## 🚀 Inicio Rápido

### Prerequisitos

- Docker y Docker Compose instalados
- Puerto 5432 (PostgreSQL), 5050 (pgAdmin) y 8080 (Keycloak) disponibles

### 1️⃣ Clonar el repositorio

```bash
git clone https://github.com/Grupo-114/TPI-Backend---Grupo-114.git
cd MS-Usuarios
```

### 2️⃣ Levantar los servicios

```bash
# Limpiar contenedores y volúmenes previos
docker compose down -v

# Construir e iniciar
docker compose up --build
```

> **⚠️ Importante:** El flag `-v` elimina volúmenes anteriores para evitar conflictos con datos obsoletos.

### 3️⃣ Verificar que los servicios estén activos

- 🗄️ **PostgreSQL**: `localhost:5432`
- 🖥️ **pgAdmin**: [http://localhost:5050](http://localhost:5050)
- 🔐 **Keycloak**: `localhost:8080`

---

## 🗃️ Configuración de pgAdmin

### Acceso inicial

Navega a: **http://localhost:5050**

**Credenciales:**
- 📧 Email: `admin@admin.com`
- 🔑 Password: `admin`

### Crear servidor

1. Click en **Add New Server** (➕)
2. Completa los siguientes campos:

#### ⚙️ Pestaña General
| Campo | Valor |
|-------|-------|
| Name | `db_usuarios` |

#### ⚙️ Pestaña Connection
| Campo | Valor |
|-------|-------|
| Host name/address | `db_usuarios` |
| Port | `5432` |
| Maintenance database | `postgres` |
| Username | `postgres` |
| Password | `admin` |
| Save password | ✅ |

3. Click en **Save**

✅ **Resultado esperado:** Verás el servidor conectado con la base de datos `usuariosdb`

---

## 🧪 Endpoints y Pruebas

### Herramientas recomendadas

- 📄 **VSCode REST Client** (archivo `test.http` incluido)
- 🚀 **Postman**
- ⚡ **Thunder Client**
- 💻 **curl**

### Usando test.http

1. Abre el archivo `test.http` en VSCode
2. Instala la extensión "REST Client" si no la tienes
3. Haz clic en **"Send Request"** sobre cada endpoint

---

## 📝 Ejemplos de Uso

### 1. Crear un usuario tipo CLIENTE

```json
POST http://localhost:8080/usuarios

{
  "username": "carlos123",
  "email": "carlos@mail.com",
  "rol": "CLIENTE"
}
```

### 2. Crear un cliente asociado al usuario

```json
POST http://localhost:8080/clientes

{
  "nombre": "Carlos",
  "apellido": "Lopez",
  "email": "carlos@mail.com",
  "telefono": "3517770000",
  "usuarioId": 1
}
```

### 3. Crear un usuario tipo TRANSPORTISTA

```json
POST http://localhost:8080/usuarios

{
  "username": "pedro01",
  "email": "pedro@mail.com",
  "rol": "TRANSPORTISTA"
}
```

### 4. Crear un transportista asociado

```json
POST http://localhost:8080/transportistas

{
  "nombre": "Pedro",
  "telefono": "3515551111",
  "email": "pedro@mail.com",
  "usuarioId": 2
}
```

---

## 💡 Tips y Mejores Prácticas

### ✅ Usernames únicos
El campo `username` debe ser único. Usa variaciones como:
- `user1`, `user2`, `user3`
- `juan01`, `juan02`
- `carlos_admin`, `carlos_user`

### ⚠️ Relación 1:1
Cada usuario puede tener **solo un** cliente o transportista asociado.

Intentar crear duplicados retornará:
```
409 CONFLICT - Cliente ya existe para este usuario
```

### 🔄 Reset completo

Para empezar desde cero:

```bash
docker compose down -v
docker compose up --build
```

Luego reconfigura el servidor en pgAdmin siguiendo los pasos anteriores.

---

## 📁 Estructura del Proyecto

```
MS-Usuarios/
├── 📂 src/
│   ├── 📂 main/java/ar/edu/utn/frc/backend/grupo114/
│   │   ├── 📂 controller/         # Controladores REST
│   │   ├── 📂 service/            # Interfaces de servicios
│   │   ├── 📂 service/impl/       # Implementaciones
│   │   ├── 📂 model/              # Entidades JPA
│   │   ├── 📂 repository/         # Repositorios JPA
│   │   └── 📂 exception/          # Excepciones personalizadas
│   └── 📂 resources/
│       ├── application.properties # Configuración de Spring
│       └── data.sql               # Datos iniciales (seed)
├── 📄 Dockerfile                   # Imagen del microservicio
├── 📄 docker-compose.yml          # Orquestación de servicios
├── 📄 pom.xml                      # Dependencias Maven
└── 📄 test.http                    # Colección de pruebas
```

---

## 🔧 Solución de Problemas

### Puerto 5432 ocupado
```bash
# Ver qué proceso usa el puerto
lsof -i :5432

# Detener PostgreSQL local si está corriendo
sudo systemctl stop postgresql
```

### pgAdmin no se conecta
1. Verifica que el contenedor esté corriendo: `docker ps`
2. Revisa los logs: `docker compose logs db_usuarios`
3. Asegúrate de usar `db_usuarios` como host (no `localhost`)

### Base de datos con datos viejos
```bash
docker compose down -v  # El flag -v es crucial
docker compose up --build
```

---

## 👥 Equipo

**Grupo 114 – UTN FRC**  
Proyecto TPI Backend de Aplicaciones (2025)

---