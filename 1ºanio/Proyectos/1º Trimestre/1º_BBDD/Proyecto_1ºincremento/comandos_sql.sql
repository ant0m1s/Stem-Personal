CREATE TABLE Centro (
nombre_centro VARCHAR(100) PRIMARY KEY,
anio_apertura SMALLINT,
direccion VARCHAR(255)
);

CREATE TABLE MiembroUGR (
DNI VARCHAR(10) PRIMARY KEY,
Fecha_Nacimiento DATE,
Provincia VARCHAR(50),
Pais VARCHAR(50)
);

CREATE TABLE Estudiante (
TUI VARCHAR(20) PRIMARY KEY,
Nombre_estudiante VARCHAR(100) NOT NULL
);

CREATE TABLE Deporte (
nombre_deporte VARCHAR(50) PRIMARY KEY,
max_jugadores TINYINT UNSIGNED,
min_jugadores TINYINT UNSIGNED
);

CREATE TABLE Profesores (
Departamento VARCHAR(100) PRIMARY KEY,
Tipo_de_contrato VARCHAR(50),
Sueldo DECIMAL(10, 2)
);

CREATE TABLE Colegios (
nombre_colegio VARCHAR(100) PRIMARY KEY,
FOREIGN KEY (nombre_colegio) 
REFERENCES Centro(nombre_centro)
);

CREATE TABLE Facultad (
nombre_facultad VARCHAR(100) PRIMARY KEY,
FOREIGN KEY (nombre_facultad) 
REFERENCES Centro(nombre_centro)
);

CREATE TABLE Staff (
DNI VARCHAR(10) PRIMARY KEY,
Tipo_de_contrato VARCHAR(50),
Sueldo DECIMAL(10, 2),
FOREIGN KEY (DNI) 
REFERENCES MiembroUGR(DNI)
);

CREATE TABLE Profesor_imparte_Facultades (
Departamento VARCHAR(100),
nombre_facultad VARCHAR(100),
PRIMARY KEY (Departamento, nombre_facultad),
FOREIGN KEY (Departamento) 
REFERENCES Profesores(Departamento),
FOREIGN KEY (nombre_facultad) 
REFERENCES Facultad(nombre_facultad)
);

CREATE TABLE Torneo (
id_torneo INT AUTO_INCREMENT PRIMARY KEY,
anio YEAR,
nombre_deporte VARCHAR(50) NOT NULL,
max_equipos TINYINT UNSIGNED,
min_equipos TINYINT UNSIGNED,
FOREIGN KEY (nombre_deporte) 
REFERENCES Deporte(nombre_deporte)
);

CREATE TABLE MiembroUGR_arbitra_torneo (
DNI VARCHAR(10),
id_torneo INT,
PRIMARY KEY (DNI, id_torneo),
FOREIGN KEY (DNI) 
REFERENCES MiembroUGR(DNI),
FOREIGN KEY (id_torneo) 
REFERENCES Torneo(id_torneo)
);

CREATE TABLE Estudiante_matriculado_Facultad (
TUI VARCHAR(20),
nombre_facultad VARCHAR(100),
PRIMARY KEY (TUI, nombre_facultad),
FOREIGN KEY (TUI) 
REFERENCES Estudiante(TUI),
FOREIGN KEY (nombre_facultad) 
REFERENCES Facultad(nombre_facultad)
);

CREATE TABLE Estudiante_vive_colegio (
TUI VARCHAR(20),
nombre_colegio VARCHAR(100),
PRIMARY KEY (TUI, nombre_colegio),
FOREIGN KEY (TUI) 
REFERENCES Estudiante(TUI),
FOREIGN KEY (nombre_colegio) 
REFERENCES Colegios(nombre_colegio)
);

CREATE TABLE Equipo (
ID_equipo INT AUTO_INCREMENT PRIMARY KEY,
nombre_equipo VARCHAR(100) NOT NULL,
curso_inscrip VARCHAR(10),
nombre_centro VARCHAR(100) NOT NULL,
capitan VARCHAR(20) NOT NULL,
id_torneo INT NOT NULL,
FOREIGN KEY (nombre_centro) 
REFERENCES Centro(nombre_centro),
FOREIGN KEY (capitan)
REFERENCES Estudiante(TUI),
FOREIGN KEY (id_torneo) 
REFERENCES Torneo(id_torneo)
);

CREATE TABLE MiembroUGR_forma_Equipo (
DNI VARCHAR(10),
ID_equipo INT,
PRIMARY KEY (DNI, ID_equipo),
FOREIGN KEY (DNI) 
REFERENCES MiembroUGR(DNI),
FOREIGN KEY (ID_equipo) 
REFERENCES Equipo(ID_equipo)
);

CREATE TABLE Partido (
id_partido INT AUTO_INCREMENT PRIMARY KEY,
ubicacion VARCHAR(100),
arbitro VARCHAR(10),
FOREIGN KEY (arbitro) 
REFERENCES MiembroUGR(DNI)
);

CREATE TABLE Prueba_atletismo (
id_partido INT PRIMARY KEY,
FOREIGN KEY (id_partido) 
REFERENCES Partido(id_partido)
);

CREATE TABLE Equipo_participa_Atletismo (
ID_equipo INT,
ID_Prueba INT,
PRIMARY KEY (ID_equipo, ID_Prueba),
FOREIGN KEY (ID_equipo) 
REFERENCES Equipo(ID_equipo),
FOREIGN KEY (ID_Prueba) 
REFERENCES Prueba_atletismo(id_partido)
);

CREATE TABLE Partido_deportes (
id_partido INT PRIMARY KEY,
equipo_local INT NOT NULL,
equipo_visitante INT NOT NULL,
FOREIGN KEY (id_partido) 
REFERENCES Partido(id_partido),
FOREIGN KEY (equipo_local) 
REFERENCES Equipo(ID_equipo),
FOREIGN KEY (equipo_visitante) 
REFERENCES Equipo(ID_equipo)
);