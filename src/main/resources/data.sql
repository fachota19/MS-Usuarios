-- ===========================================
-- Inicialización de datos MS-USUARIOS
-- ===========================================

-- TIPOS DE ROL
INSERT INTO tipos_rol (id, nombre, descripcion) 
VALUES (1, 'CLIENTE', 'Usuario cliente que solicita transportes')
ON CONFLICT (id) DO NOTHING;

INSERT INTO tipos_rol (id, nombre, descripcion) 
VALUES (2, 'TRANSPORTISTA', 'Usuario transportista que realiza traslados')
ON CONFLICT (id) DO NOTHING;

INSERT INTO tipos_rol (id, nombre, descripcion) 
VALUES (3, 'OPERADOR', 'Usuario operador que gestiona el sistema')
ON CONFLICT (id) DO NOTHING;

SELECT setval('tipos_rol_id_seq', (SELECT COALESCE(MAX(id), 1) FROM tipos_rol));

-- USUARIOS BASE
INSERT INTO usuarios (id, username, email, keycloak_id, tipo_rol_id)
VALUES (1, 'cliente1', 'cliente1@example.com', NULL, 1)
ON CONFLICT (id) DO NOTHING;

INSERT INTO usuarios (id, username, email, keycloak_id, tipo_rol_id)
VALUES (2, 'transportista1', 'transportista1@example.com', NULL, 2)
ON CONFLICT (id) DO NOTHING;

INSERT INTO usuarios (id, username, email, keycloak_id, tipo_rol_id)
VALUES (3, 'operador1', 'operador1@example.com', NULL, 3)
ON CONFLICT (id) DO NOTHING;

SELECT setval('usuarios_id_seq', (SELECT COALESCE(MAX(id), 1) FROM usuarios));

-- CLIENTES
INSERT INTO clientes (id, nombre, apellido, email, telefono, usuario_id)
VALUES (1, 'Juan', 'Pérez', 'juan.perez@example.com', '+54-351-1234567', 1)
ON CONFLICT (id) DO NOTHING;

INSERT INTO clientes (id, nombre, apellido, email, telefono, usuario_id)
VALUES (2, 'María', 'González', 'maria.gonzalez@example.com', '+54-351-7654321', NULL)
ON CONFLICT (id) DO NOTHING;

SELECT setval('clientes_id_seq', (SELECT COALESCE(MAX(id), 1) FROM clientes));

-- TRANSPORTISTAS
INSERT INTO transportistas (id_transportista, nombre, telefono, email, usuario_id)
VALUES (1, 'Carlos Rodríguez', '+54-351-9998888', 'carlos.rodriguez@example.com', 2)
ON CONFLICT (id_transportista) DO NOTHING;

INSERT INTO transportistas (id_transportista, nombre, telefono, email, usuario_id)
VALUES (2, 'Ana Martínez', '+54-351-7778888', 'ana.martinez@example.com', NULL)
ON CONFLICT (id_transportista) DO NOTHING;

SELECT setval('transportistas_id_transportista_seq', (SELECT COALESCE(MAX(id_transportista), 1) FROM transportistas));