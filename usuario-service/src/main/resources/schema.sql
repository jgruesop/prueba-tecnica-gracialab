
INSERT INTO rol (id_rol, nombre) VALUES (NULL, "ADMIN");
INSERT INTO rol (id_rol, nombre) VALUES (NULL, "INVITADO");
INSERT INTO usuario (id_usuario, apellido, email, nombre, password, roles_id_rol) VALUES (NULL, 'ADMIN', 'admin@gmail.com', 'ADMIN', '$2a$10$NlmUEFoRFTKIIJLqAW8ZG.1WbteqLnm2eomWwG71YvemjYW79kHP6', 1);
INSERT INTO usuario (id_usuario, apellido, email, nombre, password, roles_id_rol) VALUES (NULL, 'INVITADO', 'invitado@gmail.com', 'INVITADO', '$2a$10$NlmUEFoRFTKIIJLqAW8ZG.1WbteqLnm2eomWwG71YvemjYW79kHP6', 2);
