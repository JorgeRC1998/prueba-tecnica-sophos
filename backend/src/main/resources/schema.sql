DROP TABLE IF EXISTS USUARIOS;
DROP TABLE IF EXISTS TAREAS;

CREATE TABLE USUARIOS (
	ID NUMERIC(9,0) PRIMARY KEY,
	NOMBRE VARCHAR(25),
	IDENTIFICACION VARCHAR(15),
	TIPO_USUARIO VARCHAR(15),
	USUARIO VARCHAR(100),
	PASSWORD VARCHAR(100)
);

CREATE TABLE TAREAS (
	ID NUMERIC(9,0) PRIMARY KEY,
	TITULO VARCHAR(50),
	DESCRIPCION VARCHAR(500),
	ESTADO VARCHAR(15),
	ID_USUARIO NUMERIC(9,0) 
);

CREATE SEQUENCE SEQ_USUARIOS_ID
	START WITH 2
	INCREMENT BY 1
	MINVALUE 2;

CREATE SEQUENCE SEQ_TAREAS_ID
	START WITH 1
	INCREMENT BY 1
	MINVALUE 1;