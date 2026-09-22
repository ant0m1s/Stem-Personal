COMANDOS sandboxsql
1.
create table PUBLICACION (ISSN int primary key, TITULO varchar (50) not null, NUMERO int, ANIO int);

2.
create table ARTICULO (NOMBRE varchar(50) primary key, PAGINA_INICIO int, PAGINA_FIN int, ISSN_PUBLICACION int, foreign key (ISSN_PUBLICACION) references PUBLICACION(ISSN) );

3.
insert into PUBLICACION (ISSN, TITULO, NUMERO, ANIO)
values (23456, "La rana amazonica", 25, 1987), (2345, "La guerra de los mundos", 21, 2001), (2341, "Los numero impares", 23, 2025);

4.
select * from PUBLICACION;

5. -> (Busco titulo (dentro de PUBLICACION) y los años superiores al 2000)
select TITULO from PUBLICACION
  where ANIO >= 2000;

6.
insert into ARTICULO(NOMBRE, PAGINA_INICIO, PAGINA_FIN, ISSN_PUBLICACION)
values ("La historia de Malaga", 12, 15, 2341)

7.
DROP table ARTICULO

8.

