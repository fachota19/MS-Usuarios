-- =================================================================================
-- 1. TIPOS DE ROL (Fundamental para que funcione tu lógica de negocio)
-- =================================================================================
INSERT INTO tipos_rol (id, nombre, descripcion) 
VALUES (1, 'CLIENTE', 'Rol para usuarios que contratan servicios')
ON CONFLICT (id) DO NOTHING;

INSERT INTO tipos_rol (id, nombre, descripcion) 
VALUES (2, 'TRANSPORTISTA', 'Rol para usuarios que realizan los viajes')
ON CONFLICT (id) DO NOTHING;

INSERT INTO tipos_rol (id, nombre, descripcion) 
VALUES (3, 'OPERADOR', 'Rol administrativo para gestión del sistema')
ON CONFLICT (id) DO NOTHING;

-- Actualizar secuencia para evitar errores de "duplicate key" al crear nuevos roles
SELECT setval('tipos_rol_id_seq', (SELECT MAX(id) FROM tipos_rol));


-- =================================================================================
-- 2. USUARIOS BASE (Necesarios para loguearse o asociar clientes/transportistas)
-- =================================================================================
-- Usuario Cliente
INSERT INTO usuarios (id, username, email, tipo_rol_id, keycloak_id)
VALUES (1, 'cliente_test', 'cliente@test.com', 1, NULL)
ON CONFLICT (id) DO NOTHING;

-- Usuario Transportista
INSERT INTO usuarios (id, username, email, tipo_rol_id, keycloak_id)
VALUES (2, 'transportista_test', 'transp@test.com', 2, NULL)
ON CONFLICT (id) DO NOTHING;

-- Usuario Operador
INSERT INTO usuarios (id, username, email, tipo_rol_id, keycloak_id)
VALUES (3, 'operador_test', 'admin@test.com', 3, NULL)
ON CONFLICT (id) DO NOTHING;

-- Actualizar secuencia de usuarios
SELECT setval('usuarios_id_seq', (SELECT MAX(id) FROM usuarios));


-- =================================================================================
-- 3. CLIENTES (Datos de personas/empresas asociados a usuarios)
-- =================================================================================
INSERT INTO clientes (id, nombre, apellido, email, telefono, usuario_id)
VALUES (1, 'Juan', 'Pérez', 'juan.perez@gmail.com', '3511112222', 1)
ON CONFLICT (id) DO NOTHING;

INSERT INTO clientes (id, nombre, apellido, email, telefono, usuario_id)
VALUES (2, 'Maria', 'Gomez', 'maria.gomez@hotmail.com', '3513334444', NULL)
ON CONFLICT (id) DO NOTHING;

-- Actualizar secuencia de clientes
SELECT setval('clientes_id_seq', (SELECT MAX(id) FROM clientes));


-- =================================================================================
-- 4. TRANSPORTISTAS (Datos de choferes asociados a usuarios)
-- =================================================================================
-- Nota: Usamos id_transportista porque así lo definiste en tu Entity @Column
INSERT INTO transportistas (id_transportista, nombre, email, telefono, usuario_id)
VALUES (1, 'Logística Rápida S.A.', 'contacto@logistica.com', '1144445555', 2)
ON CONFLICT (id_transportista) DO NOTHING;

INSERT INTO transportistas (id_transportista, nombre, email, telefono, usuario_id)
VALUES (2, 'Fletes Roberto', 'roberto@fletes.com', '1166667777', NULL)
ON CONFLICT (id_transportista) DO NOTHING;

-- Actualizar secuencia de transportistas
SELECT setval('transportistas_id_transportista_seq', (SELECT MAX(id_transportista) FROM transportistas));