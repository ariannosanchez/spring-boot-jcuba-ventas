/* Clientes */

INSERT INTO clientes (id, nombre, apellidos, email, fecha_creacion) VALUES (1, 'Edwin', 'Torres Hernández', 'd16221@idat.edu.pe', NOW());
INSERT INTO clientes (id, nombre, apellidos, email, fecha_creacion) VALUES (2, 'Jeremy', 'Ayza Matias', 'pt77820505@idat.edu.pe', NOW());
INSERT INTO clientes (id, nombre, apellidos, email, fecha_creacion) VALUES (3, 'Arianno', 'Sanchez Mitma', 'sl76130644@idat.edu.pe', NOW());
INSERT INTO clientes (id, nombre, apellidos, email, fecha_creacion) VALUES (4, 'Alvaro', 'Argumedo Quispe', 'sm72804638@idat.edu.pe', NOW());
INSERT INTO clientes (id, nombre, apellidos, email, fecha_creacion) VALUES (5, 'Yessica', 'Faichin Diaz', 'a20007257@idat.edu.pe', NOW());
INSERT INTO clientes (id, nombre, apellidos, email, fecha_creacion) VALUES (6, 'William', 'Goicochea Moro', 'ln76472403@idat.edu.pe', NOW());
INSERT INTO clientes (id, nombre, apellidos, email, fecha_creacion) VALUES (7, 'Alisson', 'Mamani Benavente', 'at77658363@idat.edu.pe', NOW());
INSERT INTO clientes (id, nombre, apellidos, email, fecha_creacion) VALUES (8, 'David', 'Esparta Aguilar', 'a20203029@idat.edu.pe', NOW());
INSERT INTO clientes (id, nombre, apellidos, email, fecha_creacion) VALUES (9, 'Harold', 'Mallma Llamoca', 'sl77667981@idat.edu.pe', NOW());
INSERT INTO clientes (id, nombre, apellidos, email, fecha_creacion) VALUES (10, 'Jorge', 'Enriquez De La Cruz', 'a20203361@idat.edu.pe', NOW());
INSERT INTO clientes (id, nombre, apellidos, email, fecha_creacion) VALUES (11, 'Juan', 'Albornoz Alejo', 'sm74140409@idat.edu.pe', NOW());
INSERT INTO clientes (id, nombre, apellidos, email, fecha_creacion) VALUES (12, 'Alexander', 'Siccha Palacios', 'sl75674580@idat.edu.pe', NOW());

/* Ventas */

INSERT INTO ventas (id, descripcion, observacion, fecha_creacion, clientes_id) VALUES (1, 'Primera Venta', 'Ninguna', NOW(), 1);

/* Productos */

INSERT INTO productos (id, nombre, precio, fecha_creacion) VALUES (1, 'GeForce RTX 3060 GAMING X 12G', 2330.59, NOW());
INSERT INTO productos (id, nombre, precio, fecha_creacion) VALUES (2, 'GeForce RTX 3070 GAMING X TRIO', 4660.99, NOW());
INSERT INTO productos (id, nombre, precio, fecha_creacion) VALUES (3, 'GeForce RTX™ 3080 GAMING X TRIO 10G', 7056.30, NOW());


/*EMPLEADOS*/
INSERT INTO empleados (id, nombre, apellidos, dni, email, edad, sexo, foto, fecha_creacion) VALUES (1, 'Carlos', 'Palca Vilca', '65984512', 'carlos@gmail.com', 40, 'Masculino', '003-Rubén-Belluomo-INFOR-2018.jpg', NOW());
INSERT INTO empleados (id, nombre, apellidos, dni, email, edad, sexo, foto, fecha_creacion) VALUES (2, 'Martin', 'Mauca Quispe', '66973425', 'martin@gmail.com', 38, 'Masculino', 'profesor-home-contador-publico-ofrece-clases-contabilidad-basica-para-estudiantes-universitarios-personas-deseen-conocer-materia.jpg', NOW());
INSERT INTO empleados (id, nombre, apellidos, dni, email, edad, sexo, foto, fecha_creacion) VALUES (3, 'Nicole', 'Llaranga Bandera', '78643235', 'nicole@gmail.com', 22, 'Femenino', 'solange-contreras-direccion-de-personas-uandes-1.jpg', NOW());
INSERT INTO empleados (id, nombre, apellidos, dni, email, edad, sexo, foto, fecha_creacion) VALUES (4, 'Meliana', 'Palca Vilca', '68753422', 'meliana@gmail.com', 26, 'Femenino', 'Andrea-Miranda-scaled-e1575916521788.jpg', NOW());
INSERT INTO empleados (id, nombre, apellidos, dni, email, edad, sexo, foto, fecha_creacion) VALUES (5, 'Juana', 'Campo Napa', '69865347', 'juana@gmail.com', 35, 'Femenino', 'Javiera-Lopez-1.jpg', NOW());
INSERT INTO empleados (id, nombre, apellidos, dni, email, edad, sexo, foto, fecha_creacion) VALUES (6, 'Gisella', 'Rodrigues Tito', '62357466', 'gisella@gmail.com', 40, 'Femenino', 'Anita-Astudillo.jpg', NOW());

/* VentaProducto */

INSERT INTO venta_producto (id, cantidad, producto_id, venta_id) VALUES (1, 1, 1, 1);

/* Usuario */

INSERT INTO usuario (id, usuario, clave, activo) VALUES (1, "arianno", "$2a$10$BRDU0pb9QFO5Dyu2Fy9ChudMcJwu6wg2pdPb9f3o0dW0kNFE87c8a", 1);
INSERT INTO usuario (id, usuario, clave, activo) VALUES (2, "admin", "$2a$10$RoUKv4BZkhPM7sxNVZxx7evxCNg/5CHaTh3TGZIVnRsC5JSN68g8m", 1);
INSERT INTO usuario (id, usuario, clave, activo) VALUES (3, "moderador", "$2a$10$A8pPvfMK1OM/VyvU/f1.2OV9UXWIvEEJV79RW344MszFdeNmKDZV2", 1);
INSERT INTO usuario (id, usuario, clave, activo) VALUES (4, "usuario", "$2a$10$gzM3rMK6AnGPx48WtSLLdOzHcfmkLRMoKTroBeRY5G/cY0LgeV9Gy", 1);

/* Rol */

INSERT INTO rol (id, rol, usuario_id) VALUES (1, "ROLE_ADMIN", 1);
INSERT INTO rol (id, rol, usuario_id) VALUES (2, "ROLE_MODERATOR", 1);
INSERT INTO rol (id, rol, usuario_id) VALUES (3, "ROLE_USER", 1);
INSERT INTO rol (id, rol, usuario_id) VALUES (4, "ROLE_ADMIN", 2);
INSERT INTO rol (id, rol, usuario_id) VALUES (5, "ROLE_MODERATOR", 2);
INSERT INTO rol (id, rol, usuario_id) VALUES (6, "ROLE_USER", 2);
INSERT INTO rol (id, rol, usuario_id) VALUES (7, "ROLE_MODERATOR", 3);
INSERT INTO rol (id, rol, usuario_id) VALUES (8, "ROLE_USER", 3);
INSERT INTO rol (id, rol, usuario_id) VALUES (9, "ROLE_USER", 4);

