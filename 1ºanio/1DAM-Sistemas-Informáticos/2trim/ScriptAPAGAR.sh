#!/bin/bash

# Variables de configuración
USUARIO_REMOTO="pj"
IP_COMPANERO="192.168.1.145"
NOMBRE="ninobeats"

echo "Iniciando proceso en el equipo remoto..."

# Conexión SSH y ejecución de comandos
ssh "$USUARIO_REMOTO@$IP_COMPANERO"
    # 1. Crear la carpeta con el nombre específico
    mkdir -p "conexion_$NOMBRE"
    
    # 2. Generar el archivo con el mensaje solicitado
    echo "gano yo, te fastidias" > "conexion_$NOMBRE/mensaje.txt"
    
    # 3. Orden de apagado inmediato
    # Nota: Se usa sudo porque apagar el sistema es una tarea de root
    sudo shutdown -h now

echo "Script ejecutado. Si el usuario tiene permisos, el equipo remoto se está apagando."