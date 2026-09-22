# Implementación de Infraestructura TI para Nucleo

## Ejercicio 1
### Creación de usuarios

#### Parte A
Creación de grupos
```bash
sudo addgroup tecnicos
sudo addgroup desarrolladores
sudo addgroup administradores

```
#### Parte B
Creación de usuarios
```bash
sudo useradd -m -g tecnicos tecnico1
sudo useradd -m -g tecnicos tecnico2
sudo useradd -m -g tecnicos tecnico3
sudo useradd -m -g tecnicos tecnico4
sudo useradd -m -g tecnicos tecnico5

sudo useradd -m -g desarrolladores desarrollador1
sudo useradd -m -g desarrolladores desarrollador2
sudo useradd -m -g desarrolladores desarrollador3
sudo useradd -m -g desarrolladores desarrollador4
sudo useradd -m -g desarrolladores desarrollador5

sudo useradd -m -g administradores administrador1
sudo useradd -m -g administradores administrador2
```


## Ejercicio 2
### Creación de estructura de archivos y carpetas

#### Parte A
```bash
cd opt
sudo mkdir compartido
cd compartido
mkdir src docs config recovery

```
#### Parte B
```bash
sudo chgrp administradores config
sudo chmod 710 config

```
#### Parte C
```bash
sudo addgroup conjunto

sudo usermod -aG conjunto desarrollador1
sudo usermod -aG conjunto desarrollador2
sudo usermod -aG conjunto desarrollador3
sudo usermod -aG conjunto desarrollador4
sudo usermod -aG conjunto desarrollador5
sudo usermod -aG conjunto administrador1
sudo usermod -aG conjunto administrador2

sudo chgrp conjunto src
sudo chmod 764 src

sudo chgrp administradores recovery
sudo chmod 764 recovery
```
#### Parte D
```bash
sudo addgroup conjuntoCompleto

sudo usermod -aG conjuntoCompleto tecnico1
sudo usermod -aG conjuntoCompleto tecnico2
sudo usermod -aG conjuntoCompleto tecnico3
sudo usermod -aG conjuntoCompleto tecnico4
sudo usermod -aG conjuntoCompleto tecnico5
sudo usermod -aG conjuntoCompleto desarrollador1
sudo usermod -aG conjuntoCompleto desarrollador2
sudo usermod -aG conjuntoCompleto desarrollador3
sudo usermod -aG conjuntoCompleto desarrollador4
sudo usermod -aG conjuntoCompleto desarrollador5
sudo usermod -aG conjuntoCompleto administrador1
sudo usermod -aG conjuntoCompleto administrador2

sudo chgrp conjuntoCompleto docs
sudo chmod 765 docs

sudo chmod 764 docs/*
```

## Ejercicio 3
### Automatización

```bash
#!/bin/bash
#### Parte A
mv *.py src/ 2>/dev/null
mv *.c src/ 2>/dev/null
mv *.cpp src/ 2>/dev/null
mv *.js src/ 2>/dev/null
mv *.sh src/ 2>/dev/null
#### Parte B
mv *.cfg config/ 2>/dev/null
#### Parte C
mv *.pdf docs/ 2>/dev/null
mv *.md docs/ 2>/dev/null
mv *.doc docs/ 2>/dev/null
mv *.docx docs/ 2>/dev/null
mv *.odt docs/ 2>/dev/null
mv *.txt docs/ 2>/dev/null
#### Parte D
mv *.sql recovery/ 2>/dev/null

```

## TAREAS INCREMENTO
## Ejercicio 1
### Gestión de usuarios y grupos

#### Parte A
(-E se utiliza en change para fijar una fecha de caducidad y en useradd sirve para aplicar cuando creas el usuario)
```bash
useradd -e 2025-07-31 usuario1
useradd -e 2025-07-31 usuario2
useradd -e 2025-07-31 usuario3

```

(si ya existe, así:)
```bash
chage -E 2025-07-31 usuario1

```

#### Parte B
```bash
chage -M 60 usuario1
chage -M 60 usuario2

```

#### Parte C
(-M sirve para establecer el nº maximo de dias que puede pasar sin cambiar la contraseña)
```bash
chage -M 30 administrador1
chage -M 30 administrador2

```

#### Parte D
(-M utilizado para crear usuarios sin /home)
```bash
sudo useradd -M tecnico1

```

## Ejercicio 2
### Gestión y monitoreo de procesos
top -u probr | head -n 12 | tail -n 6 > /home/desarrollador1/uso_cpu.log
top -u probr | head -n 12 | tail -n 6 > /home/desarrollador2/uso_cpu.log
top -u probr | head -n 12 | tail -n 6 > /home/desarrollador3/uso_cpu.log
top -u probr | head -n 12 | tail -n 6 > /home/desarrollador4/uso_cpu.log
top -u probr | head -n 12 | tail -n 6 > /home/desarrollador5/uso_cpu.log


## Ejercicio 3
### Programación de tareas

#### Parte A

(recuerda que  crontab -e es el comando que se utiliza siempre para editar la tabla de tareas programadas)

(primero se pone el minuto, despues hora, dia del mes, mes, dia de la semana, y por ultimo el comando)
```bash
0 2 * * 0 cp -r /home/compartido/documentos/ /home/compartido/backup/

```
#### Parte B
```bash
at now + 1 month
at> cp -r /opt /home/compartido/backup/opt_backup_mensual
chmod -R 755 /home/compartido/backup/opt_backup_mensual
```
Ctrl+D --> salir

#### Parte C
(Para bloquear el viernes:)
```bash
0 21 * * 5 usermod -L dev1
0 21 * * 5 usermod -L dev2
```
(Para cada desarrollador)
---

(Para desbloquearlo el lunes sería:)
```bash
0 5 * * 1 usermod -U dev1
0 5 * * 1 usermod -U dev2
```

