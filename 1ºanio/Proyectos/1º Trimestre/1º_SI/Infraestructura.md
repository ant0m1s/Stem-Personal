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
