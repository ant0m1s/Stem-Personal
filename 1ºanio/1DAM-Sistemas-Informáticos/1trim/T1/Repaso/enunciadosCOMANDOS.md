Los dos primeros ejercicios podéis hacerlos directamente en la terminal o dentro de un script. Os recomiendo practicar la creación de scxripts pero muchos se quedarán en una línea y basta con identificar la orden correcta.

```
/
└── home/
    └──tu_usuario/
└── etc/
    └── mysql/
    └── fonts/
    └── systemd/
        └── system/
    
```


## Ejercicio 1
- Crea un script (en la carpeta que quieras) que lea el contenido de la carpeta mysql
- modificalo para que no devuelva solamente los nombres de archivos y carpetas en mysql sino también sus permisos actuales.
- modificalo para que lea el contenido de fonts de manera recursiva. Es decir, que abra todo lo que haya en las subcarpetas de fonts.

*Responde con los comandos utilizados*
1.1
ls etc/mysql

1.2
ls -l etc/mysql

1.3
ls -r etc/fonts


## Ejercicio 2
- Crea un script (en la carpeta que quieras) que lea el contenido del archivo fonts.conf, que se encuentra en fonts.
- Cambia el script de carpeta y piensa si tendrías que modificar el código para que funcione.
- Modifica el script para que en lugar de leer el contenido del archivo fonts.conf, me devuelva el número de caracteres, palabras y lineas.

*Responde con los comandos utilizados*
2.1
cat etc/font/fonts.conf

2.2
XXXX

2.3
wc etc/font/fonts.conf


## Ejercicio 3
- Escribe un script en bash (en la carpeta que quieras) que se ejecute con 1 parámetro. El primer parámetro será la ruta de una carpeta.
- El programa debe devolver por la terminal el número de archivos que contiene la carpeta pasada como primer parámetro. 

*Responde con los comandos utilizados*

3.2
ls -l | wc -l

## Ejercicio 4.

Escribe un programa en bash que se ejecute con 2 parámetros. El primero sera una carpeta y el segundo una cadena de letras, la que sea, una palabra por ejemplo.

El programa debe buscar todos los archivos que contengan esa cadena de caracteres y devolver **el número** de archivos que tengan la cadena en su nombre de archivo, no en su interior.

Por ejemplo, si ejecutamos dentro de `carpeta` esta orden:

```
└── carpeta/
    └── carpeta1/
        └── prueba1.sh
        └── prueba.txt
        └── log.txt
        └── prueba2.sh
        └── prueba3.md
```


``` bash
./ejercicio4.sh carpeta1 prueba
```
Debería devolver el número 4.

*Responde con los comandos utilizados*
/Home/antonioferres/
find "$DIR" -type f -maxdepth 1
grep -F "$CADENA"
wc -l


###  Ej 1 extra:

dentro de una carpeta, crea dos carpetas documentacion y documentacion2
dos archivos, archivo1.txt y archivo2.txt
dos programas, programa1.sh y programa2.sh


crear 3 archivos que se llamen carpetas, programas y archivos

**Respuesta:**
*Responde con los comandos utilizados*

/Home/antonioferres/PT_Enunciados/
cd ..

mkdir PT_Enunciados
mkdir {documentacion,documentacion2}
touch {archivo1.txt,archivo2.txt,programa1.sh,programa2.sh,carpetas,programas,archivos}

-------------------------

crea un script dentro de tu carpeta que haga lo siguiente:
quiero que almaceneis en "carpetas" los nombres de las carpetas que estén en vuestra carpeta, en programas los nombres de los los programas (.sh)  y en archivos los nombres de los archivos(.txt).

**Respuesta:**
crear ej1.sh en nuestra carpeta
*Responde con los comandos utilizados*

/Home/antonioferres/PT_Enunciados/

 "Para programas"
find -name "*programa*" -type f
 "Para archivos"
find -name "*archivo*" -type f
 "Para carpetas"
find -name "*documentacion*" -type d


### Ej 2 extra:

rellena archivo1.txt y archivo2.txt con algo de texto, el que tu quieras.

escribe un script que almacene dentro de archivo2.txt el contenido de archivo2.txt y detras el contenido de archivo1.txt

sabrías hacerlo a la inversa? que se almacene primero archivo1.txt y después archivo2.txt.

**Respuesta:**
*Responde con los comandos utilizados*

/Home/antonioferres/PT_Enunciados/

nano archivo1.txt <relleno el texto>
nano archivo2.txt <relleno el texto>

cat archivo2.txt archivo1.txt > temp.txt;
cat archivo1.txt archivo2.txt > temp.txt


### Ej3 extra:

Repite el ejercicio 1 pero como lo pensamos originalmente. En este caso, carpetas, archivos y programas serán carpetas y hay que almacenar en cada uno lo que corresponde. 
carpetas, archivos y programas (las carpetas) deben estar fuera de tu carpeta actual. crealas donde tu quieras.

En carpetas, mover todas las carpetas que haya en la carpeta donde estas haciendo el ejercicio, en archivos los archivos y en programas los programas.

NOTA: carpetas, archivos y programas no puede estar dentro de la carpeta donde estás trabajando. Qué pasa si intentamos hacerlo con carpetas dentro de nuestra carpeta? Por qué falla?

**Respuesta:**
*Responde con los comandos utilizados*

/Home/antonioferres/PT_Enunciados/

mv *.sh programas

mv *.txt archivos