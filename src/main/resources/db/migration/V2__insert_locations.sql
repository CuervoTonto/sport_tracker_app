-- Primero, insertamos los departamentos (tabla padre)
INSERT INTO departamentos (nombre) VALUES ('Quindío');
INSERT INTO departamentos (nombre) VALUES ('Risaralda');
INSERT INTO departamentos (nombre) VALUES ('Caldas');

-- Ahora, insertamos las ciudades (tabla hija)
INSERT INTO ciudades (nombre, departamento_nombre) VALUES ('Armenia', 'Quindío');
INSERT INTO ciudades (nombre, departamento_nombre) VALUES ('Calarcá', 'Quindío');
INSERT INTO ciudades (nombre, departamento_nombre) VALUES ('Montenegro', 'Quindío');
INSERT INTO ciudades (nombre, departamento_nombre) VALUES ('Filandia', 'Quindío');
INSERT INTO ciudades (nombre, departamento_nombre) VALUES ('Salento', 'Quindío');
INSERT INTO ciudades (nombre, departamento_nombre) VALUES ('Pereira', 'Risaralda');
INSERT INTO ciudades (nombre, departamento_nombre) VALUES ('Manizales', 'Caldas');