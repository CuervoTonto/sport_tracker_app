INSERT INTO `usuarios` (username, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, fecha_nacimiento, correo, celular, contrasenia) VALUES
  ('juanperez', 'Juan', 'Carlos', 'Pérez', 'González', '1990-05-15', 'juan.perez@mail.com', '3001234567', 'contrasenia123'),
  ('mariarodriguez', 'María', 'Fernanda', 'Rodríguez', 'Sánchez', '1985-03-21', 'maria.rod@mail.com', '3102345678', 'mariacontrasenia'),
  ('pedrolopez', 'Pedro', 'Alberto', 'López', 'Martínez', '1992-07-30', 'pedro.lopez@mail.com', '3203456789', 'pedro1234'),
  ('luismoreno', 'Luis', 'Antonio', 'Moreno', 'Gutiérrez', '1994-09-10', 'luis.moreno@mail.com', '3104567890', 'luis12345'),
  ('julianavila', 'Julián', 'Eduardo', 'Ávila', 'Ramírez', '1988-12-05', 'julian.avila@mail.com', '3005678901', 'julian1234'),
  ('sofiahernandez', 'Sofía', 'Alejandra', 'Hernández', 'Torres', '1993-02-11', 'sofia.hernandez@mail.com', '3106789012', 'sofia1234'),
  ('carolinafernandez', 'Carolina', 'Isabel', 'Fernández', 'Pérez', '1991-11-22', 'carolina.fernandez@mail.com', '3207890123', 'carolina1234'),
  ('alejandrosilva', 'Alejandro', 'Mauricio', 'Silva', 'Ruiz', '1995-04-18', 'alejandro.silva@mail.com', '3008901234', 'alejandro1234'),
  ('valentinagomez', 'Valentina', 'Mariana', 'Gómez', 'Jiménez', '1990-06-23', 'valentina.gomez@mail.com', '3109012345', 'valentina1234'),
  ('danielpinto', 'Daniel', 'David', 'Pinto', 'Córdoba', '1989-01-14', 'daniel.pinto@mail.com', '3200123456', 'daniel12345');

INSERT INTO `grupos` (nombre, fecha_creacion, administrador) VALUES
  ('Pedalistas de Bogotá', '2023-10-01 09:00:00', 'juanperez'),
  ('Riders Cali', '2023-10-02 10:00:00', 'mariarodriguez'),
  ('Ciclismo de Montaña Antioquia', '2023-10-03 11:00:00', 'pedrolopez'),
  ('Ruta Barranquilla', '2023-10-04 12:00:00', 'luismoreno'),
  ('Ciclismo Urbano Medellín', '2023-10-05 13:00:00', 'julianavila'),
  ('Bicicross Santander', '2023-10-06 14:00:00', 'sofiahernandez'),
  ('Aventureros del Ciclo Cartagena', '2023-10-07 15:00:00', 'carolinafernandez'),
  ('Ciclistas en Acción Pasto', '2023-10-08 16:00:00', 'alejandrosilva'),
  ('Tour de Valle del Cauca', '2023-10-09 17:00:00', 'valentinagomez'),
  ('Bikers del Caribe', '2023-10-10 18:00:00', 'danielpinto');

INSERT INTO `grupo_usuarios` (grupo_nombre, usuario_username) VALUES
  ('Pedalistas de Bogotá', 'juanperez'),
  ('Riders Cali', 'mariarodriguez'),
  ('Ciclismo de Montaña Antioquia', 'pedrolopez'),
  ('Ruta Barranquilla', 'luismoreno'),
  ('Ciclismo Urbano Medellín', 'julianavila'),
  ('Bicicross Santander', 'sofiahernandez'),
  ('Aventureros del Ciclo Cartagena', 'carolinafernandez'),
  ('Ciclistas en Acción Pasto', 'alejandrosilva'),
  ('Bikers del Caribe', 'valentinagomez'),
  ('Bikers del Caribe', 'danielpinto');

INSERT INTO `anuncios` (titulo, contenido, grupo_nombre) VALUES
  ('Próxima Ruta de Ciclismo', 'Una nueva ruta en el municipio de Chía, Bogotá, para disfrutar del ciclismo de montaña.', 'Pedalistas de Bogotá'),
  ('Nuevo Evento de Ciclismo Urbano', '¡Únete al evento de ciclismo urbano por las calles de Cali! Apúntate a la carrera.', 'Riders Cali'),
  ('Torneo de Bicicross en Santander', 'Competencia de bicicross para todos los niveles. ¡Aprovecha y muestra tus habilidades!', 'Bicicross Santander'),
  ('Ruta de Ciclismo de Montaña', 'Este fin de semana tenemos una salida a las montañas de Antioquia. ¡Ven a disfrutar del paisaje!', 'Ciclismo de Montaña Antioquia'),
  ('Tour Barranquilla – Cartagena', 'Recorrido ciclístico por la costa caribeña de Colombia. Abierto a todos los niveles.', 'Ruta Barranquilla'),
  ('Ruta Nocturna en Medellín', '¡Pedalea por Medellín bajo las estrellas! Inscripciones abiertas.', 'Ciclismo Urbano Medellín'),
  ('Festival Cicloturismo Cartagena', 'Evento de cicloturismo que recorrerá los puntos más importantes de Cartagena.', 'Aventureros del Ciclo Cartagena'),
  ('Rally Ciclista en Pasto', 'Competencia en ruta para los ciclistas más valientes. Un reto para todos los amantes de la bicicleta.', 'Ciclistas en Acción Pasto'),
  ('Evento en el Valle del Cauca', 'Competencia de ciclismo por la ruta más conocida del Valle del Cauca, abierta a aficionados.', 'Tour de Valle del Cauca'),
  ('Desafío en el Caribe', 'Un desafío ciclístico en las playas de Santa Marta. Ideal para ciclistas experimentados.', 'Bikers del Caribe');

INSERT INTO `actividades` (nombre, descripcion, fecha, hora_inicio, hora_fin, grupo_nombre) VALUES
  ('Ruta Nocturna Bogotá', 'Recorrido en grupo por las principales avenidas de Bogotá a altas horas de la noche.', '2023-10-20', '22:00:00', '23:59:00', 'Pedalistas de Bogotá'),
  ('Torneo de Bicicross Cali', 'Competencia de bicicross en la pista más desafiante de la región.', '2023-10-21', '08:00:00', '14:00:00', 'Bicicross Santander'),
  ('Carrera de Montaña Antioquia', 'Reto en bicicleta de montaña por las rutas más difíciles de Antioquia.', '2023-10-22', '06:30:00', '14:00:00', 'Ciclismo de Montaña Antioquia'),
  ('Ruta Barranquilla – Cartagena', 'Un desafío de resistencia entre Barranquilla y Cartagena. Inscripciones abiertas.', '2023-10-23', '05:00:00', '17:00:00', 'Ruta Barranquilla'),
  ('Evento Ciclismo Urbano Medellín', 'Competencia y evento de ciclismo urbano en Medellín. Incluye actividades para todos los niveles.', '2023-10-24', '09:00:00', '18:00:00', 'Ciclismo Urbano Medellín'),
  ('Rally Ciclista Valle del Cauca', 'Desafío de ciclismo por las rutas del Valle del Cauca, con diferentes niveles de dificultad.', '2023-10-25', '07:00:00', '16:00:00', 'Tour de Valle del Cauca'),
  ('Competencia de Ciclismo Urbano Santa Marta', 'Competencia urbana por las calles de Santa Marta, abierta a ciclistas de todas las edades.', '2023-10-26', '10:00:00', '15:00:00', 'Bikers del Caribe'),
  ('Maratón de Ciclismo Pasto', 'Una maratón de ciclismo a través de la geografía de Nariño. ¡No te lo pierdas!', '2023-10-27', '06:00:00', '18:00:00', 'Ciclistas en Acción Pasto'),
  ('Cicloturismo Cartagena', 'Recorrido turístico en bicicleta por la ciudad amurallada. Para ciclistas amantes del turismo.', '2023-10-28', '08:00:00', '14:00:00', 'Aventureros del Ciclo Cartagena'),
  ('Rally Ciclismo Cartagena', 'Carrera de ciclismo en rutas históricas y culturales de Cartagena.', '2023-10-29', '07:00:00', '17:00:00', 'Aventureros del Ciclo Cartagena');

INSERT INTO `departamentos` (nombre) VALUES
  ('Antioquia'),
  ('Cundinamarca'),
  ('Atlántico'),
  ('Valle del Cauca'),
  ('Santander'),
  ('Bolívar'),
  ('Nariño'),
  ('Magdalena'),
  ('Boyacá'),
  ('Huila');

INSERT INTO `ciudades` (nombre, departamento_nombre) VALUES
  ('Medellín', 'Antioquia'),
  ('Bogotá', 'Cundinamarca'),
  ('Barranquilla', 'Atlántico'),
  ('Cali', 'Valle del Cauca'),
  ('Bucaramanga', 'Santander'),
  ('Cartagena', 'Bolívar'),
  ('Pasto', 'Nariño'),
  ('Santa Marta', 'Magdalena'),
  ('Tunja', 'Boyacá'),
  ('Neiva', 'Huila');

INSERT INTO `competencias` (titulo, descripcion, ciudad_nombre) VALUES
  ('Maratón Medellín 2023', 'Competencia de maratón a través de las principales avenidas de Medellín', 'Medellín'),
  ('Copa Barranquilla Fútbol', 'Competencia de fútbol entre equipos regionales en Barranquilla', 'Barranquilla'),
  ('Carrera de ciclismo Bogotá', 'Competencia de ciclismo en las montañas de la capital colombiana', 'Bogotá'),
  ('Competencia de natación Cali', 'Competencia de natación en el Lago de Cali', 'Cali'),
  ('Torneo de ajedrez Bucaramanga', 'Torneo de ajedrez abierto en Bucaramanga', 'Bucaramanga'),
  ('Festival de Salsa Cali', 'Competencia de salsa entre grupos locales en la ciudad de Cali', 'Cali'),
  ('Festival de Guitarra Cartagena', 'Competencia para guitarristas locales e internacionales en Cartagena', 'Cartagena'),
  ('Maratón de San Silvestre Pasto', 'Competencia de maratón tradicional en Pasto', 'Pasto'),
  ('Rally de coches Santa Marta', 'Competencia de rally en las carreteras de Santa Marta', 'Santa Marta'),
  ('Competencia de cocina Tunja', 'Concurso gastronómico de cocina típica boyacense en Tunja', 'Tunja');

INSERT INTO `competidores` (competencia_titulo, usuario_username) VALUES
  ('Maratón Medellín 2023', 'juanperez'),
  ('Maratón Medellín 2023', 'mariarodriguez'),
  ('Copa Barranquilla Fútbol', 'pedrolopez'),
  ('Carrera de ciclismo Bogotá', 'luismoreno'),
  ('Competencia de natación Cali', 'julianavila'),
  ('Torneo de ajedrez Bucaramanga', 'sofiahernandez'),
  ('Festival de Salsa Cali', 'carolinafernandez'),
  ('Festival de Guitarra Cartagena', 'alejandrosilva'),
  ('Maratón de San Silvestre Pasto', 'valentinagomez'),
  ('Rally de coches Santa Marta', 'danielpinto');

INSERT INTO `planes` (id, nombre, descripcion) VALUES
  ('plan_basico', 'Plan Básico', 'Plan con acceso limitado a contenido y funciones básicas.'),
  ('plan_avanzado', 'Plan Avanzado', 'Acceso completo a todas las funciones, incluyendo contenidos exclusivos.'),
  ('plan_premium', 'Plan Premium', 'Plan exclusivo con beneficios adicionales como acceso prioritario y soporte personalizado.'),
  ('plan_empresarial', 'Plan Empresarial', 'Plan dirigido a empresas, con características avanzadas y soporte exclusivo.'),
  ('plan_estudiantes', 'Plan Estudiantes', 'Descuentos para estudiantes con acceso básico a la plataforma.'),
  ('plan_familia', 'Plan Familiar', 'Plan para familias con múltiples cuentas y acceso completo.'),
  ('plan_deportes', 'Plan Deportes', 'Plan dedicado a deportes con contenido exclusivo sobre eventos y entrenamientos.'),
  ('plan_cultura', 'Plan Cultura', 'Acceso a contenido cultural, museos, y eventos en Colombia.'),
  ('plan_exclusivo', 'Plan Exclusivo', 'Plan único con acceso a eventos y contenido exclusivo solo para miembros premium.'),
  ('plan_turismo', 'Plan Turismo', 'Plan para quienes aman viajar, con acceso a descuentos en viajes y actividades turísticas.');

INSERT INTO `pagos` (fecha, monto, usuario_username) VALUES
  ('2023-10-15 09:30:00', 99999.99, 'juanperez'),
  ('2023-10-16 10:00:00', 129999.99, 'mariarodriguez'),
  ('2023-10-17 14:00:00', 49999.99, 'pedrolopez'),
  ('2023-10-18 16:30:00', 79999.99, 'luismoreno'),
  ('2023-10-19 17:45:00', 59999.99, 'julianavila'),
  ('2023-10-20 09:00:00', 99999.99, 'sofiahernandez'),
  ('2023-10-21 13:15:00', 129999.99, 'carolinafernandez'),
  ('2023-10-22 10:30:00', 49999.99, 'alejandrosilva'),
  ('2023-10-23 12:00:00', 79999.99, 'valentinagomez'),
  ('2023-10-24 15:30:00', 59999.99, 'danielpinto');

INSERT INTO `suscripciones` (fecha_inicio, fecha_fin, usuario_username, pago_id, plan_id) VALUES
  ('2023-10-01', '2023-11-01', 'juanperez', 1, 'plan_basico'),
  ('2023-10-02', '2023-11-02', 'mariarodriguez', 2, 'plan_avanzado'),
  ('2023-10-03', '2023-11-03', 'pedrolopez', 3, 'plan_premium'),
  ('2023-10-04', '2023-11-04', 'luismoreno', 4, 'plan_empresarial'),
  ('2023-10-05', '2023-11-05', 'julianavila', 5, 'plan_estudiantes'),
  ('2023-10-06', '2023-11-06', 'sofiahernandez', 6, 'plan_familia'),
  ('2023-10-07', '2023-11-07', 'carolinafernandez', 7, 'plan_deportes'),
  ('2023-10-08', '2023-11-08', 'alejandrosilva', 8, 'plan_cultura'),
  ('2023-10-09', '2023-11-09', 'valentinagomez', 9, 'plan_exclusivo'),
  ('2023-10-10', '2023-11-10', 'danielpinto', 10, 'plan_turismo');

INSERT INTO `metas` (kilometros_objetivos, kilometros_actuales, fecha_inicio, fecha_fin, usuario_username) VALUES
  (100.0, 20.0, '2023-10-01', '2023-12-31', 'juanperez'),
  (200.0, 50.0, '2023-10-02', '2023-12-31', 'mariarodriguez'),
  (150.0, 30.0, '2023-10-03', '2023-12-31', 'pedrolopez'),
  (180.0, 40.0, '2023-10-04', '2023-12-31', 'luismoreno'),
  (220.0, 70.0, '2023-10-05', '2023-12-31', 'julianavila'),
  (250.0, 90.0, '2023-10-06', '2023-12-31', 'sofiahernandez'),
  (130.0, 20.0, '2023-10-07', '2023-12-31', 'carolinafernandez'),
  (160.0, 50.0, '2023-10-08', '2023-12-31', 'alejandrosilva'),
  (140.0, 60.0, '2023-10-09', '2023-12-31', 'valentinagomez'),
  (190.0, 80.0, '2023-10-10', '2023-12-31', 'danielpinto');

INSERT INTO `progresos` (kilometros_recorridos, fecha, usuario_username) VALUES
  (20.0, '2023-10-10 09:00:00', 'juanperez'),
  (50.0, '2023-10-11 10:00:00', 'mariarodriguez'),
  (30.0, '2023-10-12 11:00:00', 'pedrolopez'),
  (40.0, '2023-10-13 12:00:00', 'luismoreno'),
  (70.0, '2023-10-14 13:00:00', 'julianavila'),
  (90.0, '2023-10-15 14:00:00', 'sofiahernandez'),
  (20.0, '2023-10-16 15:00:00', 'carolinafernandez'),
  (50.0, '2023-10-17 16:00:00', 'alejandrosilva'),
  (60.0, '2023-10-18 17:00:00', 'valentinagomez'),
  (80.0, '2023-10-19 18:00:00', 'danielpinto');
