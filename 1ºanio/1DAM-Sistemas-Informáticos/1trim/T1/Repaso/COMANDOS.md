## Dentro de tu home, crea un directorio principal llamado `proyecto`
---
mkdir -p proyecto/{docs,src/modulos,config,logs/backup,test/resultados}


## Dentro de `proyecto`, crea la siguiente estructura de directorios y archivos:
---
touch docs/{manual.txt,guia.txt,referencias.txt} src/{script1.sh,script2.sh} config/{app.cfg,db.cfg} logs/{access.log,error.log} test/{test1.txt,test2.txt}
touch src/modulos/modulo1.py logs/backup/backup1.log test/resultados/resultados1.txt


## Copia de forma recursiva:
---
mkdir config_backup
cp -r config ./config_backup


## Copia los archivos `manual.txt` y `guia.txt`
---
mkdir back_docs
cp docs/{manual.txt,guia.txt} ./back_docs/


## Mueve el archivo `script1.sh` desde el subdirectorio `src` a `test/resultados`
---
mv src/script1.sh test/resultados


## Renombra `script2.sh` a `script_final.sh
---
mv src/script2.sh src/script_final.sh


## Mueve el directorio `backup` completo desde `logs` a `test`
---
mv logs/backup test/


## Abre el archivo `manual.txt` dentro de `docs` y agrega las siguientes líneas de texto:
---
echo "   Capítulo 1: Introducción
   Capítulo 2: Instalación
   Capítulo 3: Uso Básico" > docs/manual.txt


## Visualiza el contenido del archivo editado.
---
nano docs/manual.txt


## Muestra las primeras 10 líneas del archivo.
---
head -n 10 docs/manual.txt


## Muestra las primeras dos líneas del archivo.
---
head -n 2 docs/manual.txt


## Busca todos los archivos con extensión `.txt` dentro del directorio `proyecto` y sus subdirectorios.
---
find . -type f -name "*.txt"


## Encuentra todos los **directorios** cuyo nombre contenga la palabra `backup` dentro de `proyecto`.
---
find . -type d -name "*backup*"


## Busca todos los archivos que hayan sido modificados en las últimas 24 horas.
---
find . -type f -mtime -1


## Busca en todos los archivos `.log` dentro de `proyecto` las líneas que contengan la palabra "ERROR" (sin importar mayúsculas o minúsculas).
---
grep -Ri --include="*.log" "ERROR" proyecto/


## Encuentra las líneas que contengan números (dígitos) dentro de los archivos `.log` dentro de la estructura `proyecto`.
---
grep -R --include="*.log" -n "[0-9]" proyecto/


## Elimina los archivos `app.cfg` y `db.cfg` del subdirectorio `config`.
---
rm config/{app.cfg,db.cfg}


## Borra de forma recursiva el subdirectorio `backup_docs` junto con su contenido.
---
rm -r back_docs


## Elimina el subdirectorio `modulos` (que contiene el archivo `modulo1.py`) dentro de `src`.
---
rm -r src/modulos
