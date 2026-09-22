# Comandos utiles para este tema:

## CREACIÓN GRUPOS

+ Crear un grupo:
    sudo groupadd ("nombre grupo")

## CREACIÓN USUARIOS 

+ Crear un usuario: 
    sudo useradd "nombre usuario"

+ Crear un usuario con un grupo principal: 

    sudo useradd -g ("grupo principal") "nombre usuario"

+ Crear un usuario con un grupo principal y grupos secundarios:

    sudo useradd -g ("grupo principal") -G ("grupo secundario") "nombre usuario"

+ Crear un usuario con un grupo principal, grupos secundarios, tendrá una carpeta peronal en (/home/*) shell asociada a bash:

    sudo useradd -g ("grupo principal") -G ("grupo secundario") -G ("grupo secundario") -m -s /bin/bash ("nombre usuario")


## CAMBIAR/CREAR CONTRASEÑA

+ Cambia o crea una contraseña:

    sudo passwd ("nombre usuario")


## CAMBIAR DE USUARIO

+ Cambia de usuario (desde terminal):

    su ("nombre usuario")

