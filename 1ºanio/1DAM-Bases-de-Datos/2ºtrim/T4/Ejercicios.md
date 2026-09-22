## Ejercicio3

### -- 1. Recupera los nombres de los equipos y su ciudad, usando alias para los nombres de las columnas.

SELECT nombre AS 'Nombre del Equipo', ciudad AS 'Sede del Club'
FROM equipos;

### -- 2. Muestra los nombres de los jugadores y puntos por juego, ordenados de mayor a menor.

SELECT nombre, puntos_por_juego
FROM jugadores
ORDER BY puntos_por_juego DESC;

### -- 3. Recupera los nombres de los equipos y la longitud de sus nombres, ordenados por esta longitud de mayor a menor.

SELECT nombre, LENGTH(nombre) AS longitud_nombre
FROM equipos
ORDER BY longitud_nombre DESC;

### -- 4. Muestra los nombres de las ciudades y la suma total de campeonatos ganados por equipos en cada ciudad, ordenados por la suma de mayor a menor.

SELECT ciudad, SUM(campeonatos) AS total_campeonatos
FROM equipos
GROUP BY ciudad
ORDER BY total_campeonatos DESC;

## Ejercicio4

### -- 1. Encuentra el número total de empleados por departamento.

SELECT departamento, COUNT(\*) AS total_empleados
FROM empleado
GROUP BY departamento;

### -- 2. Calcula el salario promedio por ciudad, mostrando solo las ciudades con un salario promedio mayor a 4000.

SELECT ciudad, AVG(salario) AS salario_promedio
FROM empleado
GROUP BY ciudad
HAVING AVG(salario) > 4000;

### -- 3. Encuentra el total de ventas por cada cliente.

SELECT cliente, SUM(importe) AS total_comprado
FROM venta
GROUP BY cliente
ORDER BY total_comprado DESC;

### -- 4. Muestra los departamentos con más de 3 empleados.

SELECT departamento
FROM empleado
GROUP BY departamento
HAVING COUNT(\*) > 3;

### -- 5. Calcula el promedio de edad de los empleados por ciudad.

SELECT ciudad, AVG(edad) AS edad_promedio
FROM empleado
GROUP BY ciudad;

### -- 6. Muestra las ciudades en las que hay empleados, sin duplicados.

SELECT DISTINCT ciudad
FROM empleado;

### -- 7. Muestra el número total de ventas y la suma total de importes.

SELECT COUNT(\*) AS numero_ventas, SUM(importe) AS suma_importes
FROM venta;

### -- 8. Encuentra el/los cliente/clientes con la venta más baja.

SELECT cliente, importe
FROM venta
WHERE importe = (SELECT MIN(importe) FROM venta);

### -- 9. Encuentra el cliente que realizó la venta de mayor importe.

### -- 10. Encuentra los empleados con un salario mayor al promedio de todos los salarios.

-- Ejercicios de Subconsultas

### -- 1. Lista los nombres de empleados que trabajan en departamentos existentes en la tabla departamento.

### -- 2. Encuentra todos los empleados cuyo salario sea mayor a 4000 y muestra su nombre y salario.

### -- 3. Encuentra el nombre del empleado con el salario más alto.

### -- 4. Calcula el promedio de salarios por departamento y muestra los departamentos con un salario promedio mayor a 5000.

### -- 5. Encuentra los clientes que han realizado ventas superiores al promedio de todas las ventas.

## Ejercicio5

-- Ejercicios de Funciones de Texto

### -- 1. Concatenar nombres y apellidos de los ciudadanos en un único campo "nombre_completo".

SELECT CONCAT(nombre, ' ', apellido) AS nombre_completo FROM Ciudadano;

### -- 2. Obtener las primeras 3 letras de los nombres de los ciudadanos.

SELECT LEFT(nombre, 3) FROM Ciudadano;

### -- 3. Calcular la longitud de los nombres de los ciudadanos.

SELECT nombre, LENGTH(nombre) AS longitud FROM Ciudadano;

### -- 4. Reemplazar la palabra "hospitales" por "clínicas" en las descripciones de actividades.

SELECT REPLACE(descripcion, 'hospitales', 'clínicas') FROM Actividad;

### -- 5. Eliminar espacios al inicio y al final de los nombres de los ministerios.

SELECT TRIM(nombre) FROM Ministerio;

-- Ejercicios de Funciones NuEXTRACTméricas

### -- 6. Redondear el presupuesto de las actividades a millones.

SELECT descripcion, ROUND(presupuesto_asignado / 1000000) AS millones FROM Actividad;

### -- 7. Calcular la diferencia entre los ingresos anuales de cada ciudadano y el promedio de ingresos.

SELECT nombre, ingresos_anuales,
ingresos_anuales - (SELECT AVG(ingresos_anuales) FROM Ciudadano) AS diferencia_promedio
FROM Ciudadano;

### -- 8. Obtener el entero más cercano hacia abajo y hacia arriba de los presupuestos de los ministerios.

SELECT presupuesto, FLOOR(presupuesto) AS abajo, CEIL(presupuesto) AS arriba FROM Ministerio;

### -- 9. Generar un número aleatorio para asignar un identificador único temporal a cada actividad.

-- Ejercicios de Funciones de Fecha y Hora

### -- 10. Calcular la antigüedad de cada ministerio en años.

SELECT nombre, TIMESTAMPDIFF(YEAR, fecha_creacion, CURDATE()) AS antiguedad FROM Ministerio;

-- Para mi esta es la mejor:

### -- 11. Formatear las fechas de inicio y fin de las actividades en formato "DD-MM-YYYY".

SELECT DATE_FORMAT(fecha_inicio, '%d-%m-%Y') AS inicio,
DATE_FORMAT(fecha_fin, '%d-%m-%Y') AS fin
FROM Actividad;

### -- 12. Calcular cuántos días faltan para que termine cada actividad.

SELECT descripcion, DATEDIFF(fecha_fin, CURDATE()) AS dias_restantes FROM Actividad;

### -- 13. Extraer el mes y el año de las fechas de nacimiento de los ciudadanos.

SELECT nombre, MONTH(fecha_nacimiento) AS mes, YEAR(fecha_nacimiento) AS anio FROM Ciudadano;

-- Para mi la mejor es:

### -- 14. Filtrar ciudadanos nacidos después del año 1990.

SELECT * FROM Ciudadano WHERE YEAR(fecha_nacimiento) > 1990;

### -- 15. Calcular el tiempo total en días que dura cada actividad gubernamental.

SELECT descripcion, DATEDIFF(fecha_fin, fecha_inicio) AS duracion_dias FROM Actividad;