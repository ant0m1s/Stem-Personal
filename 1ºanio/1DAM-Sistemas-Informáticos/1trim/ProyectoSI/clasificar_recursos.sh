#!/bin/bash
RECURSOS_DIR="/opt/compartido/recursos"

# Buscamos archivos directamente en recursos (no en subcarpetas)
find "$RECURSOS_DIR" -maxdepth 1 -type f | while read -r archivo; do
    # Sacar mes en español (o inglés según sistema) y tamaño
    MES=$(date -r "$archivo" +%B)
    TAMANO_BYTES=$(stat -c%s "$archivo")
    
    # Lógica de carpetas de tamaño
    if [ $TAMANO_BYTES -lt 10485760 ]; then RANGO="menos_10MB" # Opcional
    elif [ $TAMANO_BYTES -lt 104857600 ]; then RANGO="10-100MB"
    elif [ $TAMANO_BYTES -lt 1073741824 ]; then RANGO="100MB-1GB"
    else RANGO="mas_de_1GB"
    fi

    DESTINO="$RECURSOS_DIR/$MES/$RANGO"
    mkdir -p "$DESTINO"
    mv "$archivo" "$DESTINO"
done