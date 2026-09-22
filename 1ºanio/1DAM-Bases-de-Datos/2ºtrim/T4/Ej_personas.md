## Ejercicios tabla personas

1. Muestra la media de salario agrupada por ocupación
select ocupacion, avg(ingresos_anuales) from personas group by ocupacion
2. Muestra la media de salarios en cada provincia
select provincia, avg(ingresos_anuales) from personas group by provincia
3. Muestra la media de edad de cada nivel socioeconómico
select avg(datediff(now(), fecha_nacimiento)/365.25) as edad_media from personas group by ocupacion
4. Dime la edad de las personas de menos de 60 años ordenadas por situación laboral.
5. Muestra el nombre de la persona jubilada de más edad.
6. Dime la provincia con la máxima edad media.
7. Dime los hijos del ciudadano con id 1190
8. Muestra los nietos del ciudadano con id = 1190
9. Muestra la madre con más hijos
10. Muestra la media de hijos por hombre
11. Dime la media de hijos de personas con menos de 30 años.
12. Muestra el valor de numero de hijos que más se repite entre las mujeres.
13. Dime la media de edad de las mujeres sin hijos de Sevilla provincia
14. Dime la media de las modas del campo edad.
15. Dime la provincia con menor edad media de las personas estudiantes.
16. Órdename los municipios por numero total de hijos en cada uno.
17. ordena las provincias por mayor número medio de hijos por mujer (tasa de natalidad)
18. Dime los municipios que no superan la tasa de reemplazo (más de 2 hijos por mujer) pero tienen un salario medio mayor a 3000 euros al mes
19. Dime la provincia con la moda de edad más alta.
20. Dime los 5 sevillanos (municipio) de más edad ordenados por la longitud de su apellido.
21. Dime el municipio con menos tasa de natalidad (num_hijos por mujer) de entre los municipios de Huelva con salario medio menor de 20000 euros anuales
