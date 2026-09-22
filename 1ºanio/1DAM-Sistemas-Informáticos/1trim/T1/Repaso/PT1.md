### Ejercicio 1

1. Dentro de tu home, crea un directorio principal llamado `proyecto`.
   
2. Dentro de `proyecto`, crea la siguiente estructura de directorios y archivos:

```
proyecto/
├── docs/
│   ├── manual.txt
│   ├── guia.txt
│   └── referencias.txt
├── src/
│   ├── script1.sh
│   ├── script2.sh
│   └── modulos/
│       └── modulo1.py
├── config/
│   ├── app.cfg
│   └── db.cfg
├── logs/
│   ├── access.log
│   ├── error.log
│   └── backup/
│       └── backup1.log
└── test/
    ├── test1.txt
    ├── test2.txt
    └── resultados/
        └── resultados1.txt
```

*Responde con los comandos utilizados para crear la estructura.*
cd ~
pwd /home/antonioferres
mkdir -p proyecto/{docs,src/modulos,config,logs/backup,test/resultados}
cd proyecto
touch docs/{manual.txt,guia.txt,referencias.txt} src/{script1.sh,script2.sh} config/{app.cfg,db.cfg} logs/{access.log,error.log} test/{test1.txt,test2.txt}
touch src/modulos/modulo1.py logs/backup/backup1.log test/resultados/resultados1.txt
tree

### Ejercicio 2

1. Copia los archivos `manual.txt` y `guia.txt` desde el subdirectorio `docs` a un nuevo subdirectorio llamado `backup_docs` dentro de `proyecto`.

2. Copia de forma recursiva el directorio `config` completo junto con su contenido a un subdirectorio llamado `config_backup` dentro de `proyecto`.

*Responde con los comandos utilizados y verifica la estructura resultante con `ls` o `tree`.*

2.1
pwd /home/antonioferres/proyecto
mkdir back_docs
cp docs/{manual.txt,guia.txt} ./back_docs/

2.2
pwd /home/antonioferres
mkdir config_backup
cp -r config ./config_backup


### Ejercicio 3

1. Mueve el archivo `script1.sh` desde el subdirectorio `src` a `test/resultados`.

2. Renombra `script2.sh` a `script_final.sh` dentro del subdirectorio `src`.

3. Mueve el directorio `backup` completo desde `logs` a `test`.

*Responde con los comandos y verifica la estructura final con `ls` o `tree`.*

3.1
pwd /home/antonioferres/proyecto
mv src/script1.sh test/resultados

3.2
pwd /home/antonioferres/proyecto
mv src/script2.sh src/script_final.sh

3.3
pwd /home/antonioferres/proyecto
mv logs/backup test/
tree

### Ejercicio 4

1. Abre el archivo `manual.txt` dentro de `docs` y agrega las siguientes líneas de texto:
   ```
   Capítulo 1: Introducción
   Capítulo 2: Instalación
   Capítulo 3: Uso Básico
   ```
2. Guarda y cierra el archivo.
3. Visualiza el contenido del archivo editado.
4. Muestra las primeras 10 líneas del archivo.
5. Muestra las primeras dos líneas del archivo.

*Responde con los comandos utilizados*

pwd /home/antonioferres/proyecto
4.1
echo "   Capítulo 1: Introducción
   Capítulo 2: Instalación
   Capítulo 3: Uso Básico" > docs/manual.txt

4.3
nano docs/manual.txt

4.4
head -n 10 docs/manual.txt

4.5
head -n 2 docs/manual.txxt

### Ejercicio 5

1. Busca todos los archivos con extensión `.txt` dentro del directorio `proyecto` y sus subdirectorios.
2. Encuentra todos los **directorios** cuyo nombre contenga la palabra `backup` dentro de `proyecto`.
3. Busca todos los archivos que hayan sido modificados en las últimas 24 horas.

*Responde con los comandos utilizados*
pwd /home/antonioferres/proyecto
5.1
find . -type f -name "*.txt"

5.2
find . -type d -name "*backup*"

5.3
find . -type f -mtime -1

### Ejercicio 6

1. Busca en todos los archivos `.log` dentro de `proyecto` las líneas que contengan la palabra "ERROR" (sin importar mayúsculas o minúsculas).
2. Encuentra las líneas que contengan números (dígitos) dentro de los archivos `.log` dentro de la estructura `proyecto`.

*Responde con los comandos utilizados*
pwd /home/antonioferres

6.1
grep -Ri --include="*.log" "ERROR" proyecto/

6.2
grep -R --include="*.log" -n "[0-9]" proyecto/


### Ejercicio 7

1. Elimina los archivos `app.cfg` y `db.cfg` del subdirectorio `config`.
2. Borra de forma recursiva el subdirectorio `backup_docs` junto con su contenido.
3. Elimina el subdirectorio `modulos` (que contiene el archivo `modulo1.py`) dentro de `src`.

*Responde con los comandos utilizados y verifica la estructura final con `ls` o `tree`.*
pwd /home/antonioferres

7.1
rm config/{app.cfg,db.cfg}

7.2
rm -r back_docs

7.3
rm -r src/modulos

# Ejercicio 7 (alternativa)
cd ~/PT1
find config -type f \( -name "app.cfg" -o -name "db.cfg" \) -delete
rm -rf backup_docs
rm -rf src/modulos

# Ejercicio 8 (alternativa)
grep -Ril "archivo" .
find docs -type f -regex '.*/[bcdfghjklmnpqrstvwxyz].*' -exec bash -c 'mv "$0" docs/' {} \;
find src -mindepth 1 -type d -exec rm -rf {} +