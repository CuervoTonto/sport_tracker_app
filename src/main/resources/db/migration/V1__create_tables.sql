CREATE TABLE IF NOT EXISTS `usuarios` (
    `username` VARCHAR(100) NOT NULL,
    `primer_nombre` VARCHAR(100) NOT NULL,
    `segundo_nombre` VARCHAR(100),
    `primer_apellido` VARCHAR(100) NOT NULL,
    `segundo_apellido` VARCHAR(100),
    `fecha_nacimiento` DATE NOT NULL,
    `correo` VARCHAR(100) NOT NULL,
    `celular` VARCHAR(12) NOT NULL,
    `contrasenia` VARCHAR(60) NOT NULL,

    CONSTRAINT pk_usuarios_username PRIMARY KEY (`username`)
);

-- @TODO: refactor on ER
CREATE TABLE IF NOT EXISTS `grupos` (
    `nombre` VARCHAR(100) NOT NULL,
    `fecha_creacion` DATETIME,
    `administrador` VARCHAR(100) NOT NULL,
    
    CONSTRAINT pk_grupos_nombre PRIMARY KEY (`nombre`),
    CONSTRAINT fk_grupos_administrador FOREIGN KEY (`administrador`)
        REFERENCES `usuarios` (`username`)
        ON DELETE CASCADE
);

-- @TODO: refactor on ER
CREATE TABLE IF NOT EXISTS `grupo_usuarios` (
    `grupo_nombre` VARCHAR(100) NOT NULL,
    `usuario_username` VARCHAR(100) NOT NULL,
    
    CONSTRAINT pk_grupo_usuarios PRIMARY KEY (`grupo_nombre`, `usuario_username`),
    CONSTRAINT fk_grupo_usuarios_grupo FOREIGN KEY (`grupo_nombre`)
        REFERENCES `grupos` (`nombre`)
        ON DELETE CASCADE,
    CONSTRAINT fk_grupo_usuarios_usuario FOREIGN KEY (`usuario_username`)
        REFERENCES `usuarios` (`username`)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `anuncios` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `titulo` VARCHAR(150) NOT NULL,
    `contenido` MEDIUMTEXT NOT NULL,
    `grupo_nombre` VARCHAR(100) NOT NULL,
    
    CONSTRAINT pk_anuncios_id PRIMARY KEY (`id`),
    CONSTRAINT fk_anuncios_grupo FOREIGN KEY (`grupo_nombre`)
        REFERENCES `grupos` (`nombre`)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `adjuntos` (
    `anuncio_id` BIGINT NOT NULL,
    `nombre` VARCHAR(255) NOT NULL,
    `ruta` VARCHAR(255) NOT NULL,

    CONSTRAINT pk_adjuntos_anuncio_id_nombre PRIMARY KEY (`anuncio_id`, `nombre`),
    CONSTRAINT fk_adjuntos_anuncio FOREIGN KEY (`anuncio_id`)
        REFERENCES `anuncios` (`id`)
        ON DELETE CASCADE
);

-- @TODO: refactor en ER
CREATE TABLE IF NOT EXISTS `actividades` (
    `nombre` VARCHAR(100) NOT NULL,
    `descripcion` MEDIUMTEXT NOT NULL,
    `fecha` DATE NOT NULL,
    `hora_inicio` TIME NOT NULL,
    `hora_fin` TIME NOT NULL,
    `grupo_nombre` VARCHAR(100) NOT NULL,

    CONSTRAINT pk_actividades_nombre PRIMARY KEY (`nombre`),
    CONSTRAINT fk_actividades_grupo FOREIGN KEY (`grupo_nombre`)
        REFERENCES `grupos` (`nombre`)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `departamentos` (
    `nombre` VARCHAR(100) NOT NULL,

    CONSTRAINT pk_departamentos PRIMARY KEY (`nombre`)
);

CREATE TABLE IF NOT EXISTS `ciudades` (
    `nombre` VARCHAR(100) NOT NULL,
    `departamento_nombre` VARCHAR(100) NOT NULL,
    
    CONSTRAINT pk_ciudades PRIMARY KEY (`nombre`),
    CONSTRAINT fk_ciudades_departamento FOREIGN KEY (`departamento_nombre`)
        REFERENCES `departamentos` (`nombre`)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `competencias` (
    `titulo` VARCHAR(100) NOT NULL,
    `descripcion` MEDIUMTEXT NOT NULL,
    `ciudad_nombre` VARCHAR (100), -- ???
    
    CONSTRAINT pk_competencias PRIMARY KEY (`titulo`),
    CONSTRAINT fk_competencias_ciudad FOREIGN KEY (`ciudad_nombre`)
        REFERENCES `ciudades` (`nombre`)
        ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS `competidores` (
    `competencia_titulo` VARCHAR(100) NOT NULL,
    `usuario_username` VARCHAR(100) NOT NULL,
    
    CONSTRAINT pk_competidores PRIMARY KEY (`competencia_titulo`, `usuario_username`),
    CONSTRAINT fk_competidores_competencia FOREIGN KEY (`competencia_titulo`)
        REFERENCES `competencias` (`titulo`)
        ON DELETE CASCADE,
    CONSTRAINT fk_competidores_usuario FOREIGN KEY (`usuario_username`)
        REFERENCES `usuarios` (`username`)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `planes` (
    `id` VARCHAR(50) NOT NULL,
    `nombre` VARCHAR(100) NOT NULL,
    `descripcion` MEDIUMTEXT NOT NULL,

    CONSTRAINT pk_planes PRIMARY KEY (`id`)
);

-- @TODO: refactor and add relation with user on ER
CREATE TABLE IF NOT EXISTS `pagos` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `fecha` DATETIME NOT NULL,
    `monto` FLOAT NOT NULL,
    `usuario_username` VARCHAR(100),

    CONSTRAINT pk_pagos PRIMARY KEY (`id`),
    CONSTRAINT fk_pagos_usuario FOREIGN KEY (`usuario_username`)
        REFERENCES `usuarios` (`username`)
        ON DELETE SET NULL
);

-- @TODO: refactor multiplicity on ER
CREATE TABLE IF NOT EXISTS `suscripciones` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `fecha_inicio` DATE NOT NULL,
    `fecha_fin` DATE NOT NULL,
    `usuario_username` VARCHAR(100) NOT NULL,
    `pago_id` BIGINT NOT NULL,
    `plan_id` VARCHAR(50) NOT NULL,
    
    CONSTRAINT pk_suscripciones PRIMARY KEY (`id`),
    CONSTRAINT fk_suscripciones_usuario FOREIGN KEY (`usuario_username`)
        REFERENCES `usuarios` (`username`)
        ON DELETE CASCADE,
    CONSTRAINT fk_suscripciones_plan FOREIGN KEY (`plan_id`)
        REFERENCES `planes` (`id`)
        ON DELETE CASCADE,
    CONSTRAINT fk_suscripciones_pago FOREIGN KEY (`pago_id`)
        REFERENCES `pagos` (`id`)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `metas` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `kilometros_objetivos` FLOAT NOT NULL,
    `kilometros_actuales` FLOAT NOT NULL,
    `fecha_inicio` DATE NOT NULL,
    `fecha_fin` DATE NOT NULL,
    `usuario_username` VARCHAR(100) NOT NULL,
    
    CONSTRAINT pk_metas PRIMARY KEY (`id`),
    CONSTRAINT fk_metas_usuario FOREIGN KEY (`usuario_username`)
        REFERENCES `usuarios` (`username`)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `progresos` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `kilometros_recorridos` FLOAT NOT NULL,
    `fecha` DATETIME NOT NULL,
    `usuario_username` VARCHAR(100) NOT NULL,

    CONSTRAINT pk_progresos PRIMARY KEY (`id`),
    CONSTRAINT fk_progresos_usuario FOREIGN KEY (`usuario_username`)
        REFERENCES `usuarios` (`username`)
        ON DELETE CASCADE
);
