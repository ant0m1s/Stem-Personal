-- Ejercicios (Ampliados)
### -- 1. Listar todos los títulos de las películas.

SELECT titulo FROM pelicula;

### -- 2. Mostrar los géneros de las películas sin repetir.

SELECT DISTINCT genero FROM pelicula;

### -- 3. Seleccionar el título y año de todas las películas estrenadas después del año 2000.

SELECT titulo, anio FROM pelicula WHERE anio > 2000;

### -- 4. Listar los nombres de todos los directores y sus países de origen.

SELECT nombre, pais_origen FROM director;

### -- 5. Mostrar los títulos de las películas con duración mayor a 150 minutos.

SELECT titulo FROM pelicula WHERE duracion > 150;

### -- 6. Seleccionar el título de las películas del género 'Drama'.

SELECT titulo FROM pelicula WHERE genero = 'Drama';

### -- 7. Mostrar los id de los actores que participan en la película 'Titanic'.
### -- Enunciado: Mostrar los id de los actores asociados con el id de 'Titanic'.

SELECT r.id_actor 
FROM reparto r 
JOIN pelicula p ON r.id_pelicula = p.id 
WHERE p.titulo = 'Titanic';


### -- 8. Contar cuántas películas hay en cada género.

SELECT genero, COUNT(*) AS total_peliculas 
FROM pelicula 
GROUP BY genero;

### -- 9. Mostrar los directores nacidos después de 1950.

SELECT nombre FROM director WHERE anio_nacimiento > 1950;

### -- 10. Listar los títulos de las películas y su duración ordenados de mayor a menor.

SELECT titulo, duracion FROM pelicula ORDER BY duracion DESC;

### -- 12. Seleccionar las películas cuya duración está entre 120 y 180 minutos.

SELECT titulo FROM pelicula WHERE duracion BETWEEN 120 AND 180;

### -- 13. Mostrar los títulos de las películas ordenados por año de estreno.

SELECT titulo, anio FROM pelicula ORDER BY anio ASC;

### -- 14. Calcular el promedio de duración de las películas.

SELECT AVG(duracion) AS promedio_duracion FROM pelicula;

### -- 15. Mostrar los actores que no nacieron en EE.UU.

SELECT nombre FROM actor WHERE pais_origen <> 'EE.UU.';

### -- 17. Mostrar el id de las películas que tienen más de un actor en su reparto.

SELECT id_pelicula 
FROM reparto 
GROUP BY id_pelicula 
HAVING COUNT(id_actor) > 1;

### -- 18. Calcular el número total de actores en la base de datos.

SELECT COUNT(*) AS total_actores FROM actor;

### -- 19. Mostrar las películas que no tienen actores en el reparto.

SELECT p.titulo 
FROM pelicula p 
LEFT JOIN reparto r ON p.id = r.id_pelicula 
WHERE r.id_pelicula IS NULL;

### -- 20. Seleccionar las películas estrenadas en los últimos 10 años.

SELECT titulo, anio 
FROM pelicula 
WHERE anio >= 2016;