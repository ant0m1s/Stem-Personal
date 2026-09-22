# Ejercicios comandos Linux/UNIX

## Órdenes Linux relacionadas con archivos y directorios

### 1. Crea un archivo de tamaño 0

```bash
touch fichero
```

### 2. Listar todos los archivos del directorio `bin`.

```bash
ls ./bin
```
  
### 3. Listar todos los archivos del directorio `etc` que empiecen por *t* en orden inverso.

```bash
grep -r ./etc/t*
```

### 4. Listar todos los archivos del directorio `dev` que empiecen por *tty* y tengan 5 caracteres.

```bash
grep ./dev/tty??
```

### 5. Listar todos los archivos del directorio `dev` que empiecen por *tty* y acaben en *1,2,3 ó 4*.

```bash
ls ./dev/tty*[1-4]
```

```bash
ls ./dev/tty*[1,2,3,4]
```

### 6. Listar todos los archivos, incluidos los ocultos, del directorio raíz.

```bash
ls -a /
```

### 8. Listar todos los archivos del directorio `usr` y sus subdirectorios.

```bash
ls -R /usr
```

### 9. Cambiarse al directorio tmp y verificarlo.

```bash
cd /tmp
pwd
```

### 10. Mostrar el día y la hora actual.

```bash
date
```

### 11. Con un solo comando posicionarse en el directorio $HOME y verificarlo.

```bash
cd $HOME ; pwd
```

### 12. Crear los directorios `dir1`, `dir2` y `dir3` en el directorio `PRUEBA`. Dentro de `dir1` crear el directorio `dir11`. Dentro del directorio `dir3` crear el directorio `dir31` Dentro del directorio `dir31`, crear los directorios `dir311` y `dir312`.

```bash
mkdir PRUEBA
mkdir PRUEBA/{dir1, dir2, dir3}
cd PRUEBA/dir3
mkdir PRUEBA/dir3/dir31
mkdir dir31/{dir311, dir312}
```

### 13. Copiar un archivo `/etc/magic` a un archivo llamado `mensaje` de vuestro directorio `PRUEBA`.

```bash
cp /etc/magic PRUEBA/mensaje
```

### 14. Copiar mensaje en `dir1` y `dir2`, y verificarlo.

```bash
cd PRUEBA
cp mensaje dir1
cp mensaje dir2
```

### 15. Copiar en el directorio `dir311` los archivos de `/bin` que tengan una *a* como segunda letra y su nombre tenga cuatro letras.

```bash
cp /bin/?a?? PRUEBA/dir3/dir31/dir311
```

### 16. Mover el directorio `dir31` y sus subdirectorios debajo de `dir2`.

```bash
mv -a PRUEBA/dir3/dir31 PRUEBA/dir2
```

### 17. Mostrar por pantalla los archivos ordinarios del directorio `HOME` y sus subdirectorios.

```bash
ls -R ~
```

### 18. Ocultar el archivo mensaje del directorio dir2.

```bash
mv PRUEBA/dir2/mensaje PRUEBA/dir2/.mensaje
```

### PT8.1.- Sitúate en tu `HOME`.

```bash
cd ~
```

### PT8.2.- Crea un directorio `examen`.

```bash
mkdir examen

```

### PT8.3.- Dentro de `examen`, crea los directorios `ejercicios1` y `ejercicios2`.

```bash

mkdir examen/{ejercicios1, ejercicios2}
```

### PT8.4.- Crea un fichero de texto llamado `file1.dat` en `ejercicios1`.

```bash
 touch examen/ejercicios1/file1.dat
```

### PT8.5.- Haz una copia del fichero `file1.dat` dentro de `ejercicios2`.

```bash
cp examen/ejercicios1/file1.dat examen/ejercicios2
```

### PT8.6.- Sitúate en `ejercicios2`.

```bash
cd examen/ejercicios2
```

### PT8.7.- Mueve el archivo `file1.dat` hasta `ejercicios2/mover`, poniendo como nombre de destino `file1_movido.dat`. Ten en cuenta que, posiblemente, tendrás que crear antes el directorio.

```bash
mkdir mover
mv ./file1.dat mover/file1_movido.dat

```

### PT8.8.- Copia el archivo `/etc/crontab` a un archivo llamado `copia_crontab` de tu directorio `examen`.

```bash
cp /etc/crontab examen/copia_crontab
```

### PT8.9.- Copiar en el directorio `ejercicios1` los archivos de `/bin` que tengan una *"a"* como segunda letra y su nombre termine en *"o"* ó en *"e"*.

```bash
cp /bin/?a*o /bin/?a*e  ./ejercicios1/
```

### PT8.10.- Sitúate en tu *HOME* y elimina el directorio `examen`.

```bash
cd ~
rm examen
```


### 19.Borrar los archivos y directorios de `dir1`, incluido el propio directorio.

```bash
rm -r dir1
```

### 20. Copiar al directorio `dir312` los ficheros del directorio `/dev` que empiecen por *t*, acaben en un número del *5* al *8* y tengan cinco letras en su nombre.

```bash
cp  ./dev/t????[5-8] hola/dir312
```

### 21. Mover el directorio `dir312` debajo de `dir3`.

```bash
mv hola/dir312 dir/dir3
```

### 22. Borrar todos los archivos y directorios creados en el directorio `PRUEBA`.

```bash
rm -r PRUEBA/*
```

### 23. Crear el directorio `dir2` y `dir3` en el directorio `PRUEBA`.

```bash
mkdir PRUEBA/{dir2,dir3}
```


### 25. Cambiar el directorio actual al directorio `dir3`, y crear cuatro nuevos directorios llamados `dira`, `dirb`, `dirc`, y `dird` bajo el directorio actual.

```bash
cd dir3;
mkdir {dira,dirb,dirv,dird}
```

### 26. Comprobar los permisos de acceso de los directorios recién creados.

```bash
ls -ld PRUEBA
```
