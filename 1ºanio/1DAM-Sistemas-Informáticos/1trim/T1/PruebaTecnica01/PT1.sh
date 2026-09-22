#!bin/bash

##1. Dentro de tu home, 
##crea un directorio principal llamado `proyecto`.
mkdir -p proyecto/ {docs,src/modulos,config,logs/backup,test/resultados} 
touch ./proyecto/docs/ {manual.txt,guía.txt,referencias.txt} ; touch ./proyecto/src/ {script1.sh,script2.sh} ; touch ./proyecto/src/modulos modulo1.py ; touch ./proyecto/config/ {app.cfg,db.cfg} 
touch ./proyecto/logs/ {access.log,error.log}; touch ./proyecto/logs/backup backup1.log 
touch ./proyecto/test/ {test1.txt,test2.txt};  touch ./proyecto/test/resultados resultados1.txt

### Ejercicio 2
##1. Copia los archivos `manual.txt` y `guia.txt` desde el subdirectorio `docs` a un nuevo subdirectorio llamado `backup_docs` dentro de `proyecto`.
##2. Copia de forma recursiva el directorio `config` completo junto con su contenido a un subdirectorio llamado `config_backup` dentro de `proyecto`.
##*Responde con los comandos utilizados y verifica la estructura resultante con `ls` o `tree`.*
mkdir -p proyecto/ {backup_docs,config_backup}
cd ./proyecto/docs/ 
cp proyecto/docs {manual.txt,guia.txt} proyecto/backup_docs
cp -r proyecto/config/* proyecto/config_backup
ls proyecto/

### Ejercicio 3
##1. Mueve el archivo `script1.sh` desde el subdirectorio `src` a `test/resultados`.
#2. Renombra `script2.sh` a `script_final.sh` dentro del subdirectorio `src`.
#3. Mueve el directorio `backup` completo desde `logs` a `test`.
#*Responde con los comandos y verifica la estructura final con `ls` o `tree`.*

mv -f src/script1.sh test/resultados/ 
mv src/script2.sh src/script_final.sh
mv logs/backup test/
tree

### Ejercicio 4
###1. Abre el archivo `manual.txt` dentro de `docs` y agrega las siguientes líneas de texto:
##Capítulo 1: Introducción
##Capítulo 2: Instalación
#Capítulo 3: Uso Básico
#2. Guarda y cierra el archivo.
#3. Visualiza el contenido del archivo editado.
#4. Muestra las primeras 10 líneas del archivo.
#4. Muestra las primeras dos líneas del archivo.

cd ./docs;
echo "Capítulo 1: Introducción
Capítulo 2: Instalación
Capítulo 3: Uso Básico" > manual.txt;
cat manual.txt
head -n 10 manual.txt
head -n 2 manual.txt


### Ejercicio 5
#1. Busca todos los archivos con extensión `.txt` dentro del directorio `proyecto` y sus subdirectorios.
#2. Encuentra todos los **directorios** cuyo nombre contenga la palabra `backup` dentro de `proyecto`.
#3. Busca todos los archivos que hayan sido modificados en las últimas 24 horas.
find proyecto -name "*.txt"
find proyecto -type d -name "*backup*"
find proyecto -type f -mtime -1

### Ejercicio 6

##1. Busca en todos los archivos `.log` dentro de `proyecto`
##las líneas que contengan la palabra "ERROR" (sin importar mayúsculas o minúsculas).
#2. Encuentra las líneas que contengan números (dígitos) dentro de los archivos 
##`.log` dentro de la estructura `proyecto`.
grep 