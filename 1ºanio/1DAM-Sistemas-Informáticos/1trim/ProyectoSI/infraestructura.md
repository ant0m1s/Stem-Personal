# Proyecto_GranadaRecords Diciembre SI

- Para instalar herramientas de ACL:
sudo apt update && sudo apt install acl -y

----------------------INICIO------------------------
## 1. Estructura de Carpetas y Grupos

- Creamos grupos editores, cámaras y promotores
sudo groupadd editores
sudo groupadd camaras
sudo groupadd promotores

- Creamos grupo común "empleados" para gestionar el acceso a la raíz de forma segura
sudo groupadd empleados_gr

-----
- Creamos sus carpetas
sudo mkdir -p /opt/compartido/recursos
sudo mkdir -p /opt/compartido/videos_finales
sudo mkdir -p /opt/compartido/estadisticas

--------
### Permisos de la carpeta raíz (/opt/compartido): 
(El PDF dice que NO sea legible para otros usuarios)
(Usaremos 770: root(rwx), grupo(rwx), otros(---))

sudo chgrp empleados_gr /opt/compartido
sudo chmod 770 /opt/compartido

-----------------------------------------------------
## 2. Gestión de Usuarios y Contraseñas

- 4 Usuarios por grupo con políticas de contraseña distintas
	· Editores/Cámaras: Sin home (-M) y cambio cada 15 días (chage -M 15).
	· Promotores: Con home y cambio cada 30 días (chage -M 30).

### Para los editores
sudo useradd -M -g editores -G empleados_gr -s /bin/bash editor1
sudo chage -M 15 editor1
sudo useradd -M -g editores -G empleados_gr -s /bin/bash editor2
sudo chage -M 15 editor2
sudo useradd -M -g editores -G empleados_gr -s /bin/bash editor3
sudo chage -M 15 editor3
sudo useradd -M -g editores -G empleados_gr -s /bin/bash editor4
sudo chage -M 15 editor4

### Para las cámaras
sudo useradd -M -g camaras -G empleados_gr -s /bin/bash camara1
sudo chage -M 15 camara1
sudo useradd -M -g camaras -G empleados_gr -s /bin/bash camara2
sudo chage -M 15 camara2
sudo useradd -M -g camaras -G empleados_gr -s /bin/bash camara3
sudo chage -M 15 camara3
sudo useradd -M -g camaras -G empleados_gr -s /bin/bash camara4
sudo chage -M 15 camara4

### Para los promotores
sudo useradd -m -g promotores -G empleados_gr -s /bin/bash promotor1
sudo chage -M 30 promotor1
sudo useradd -m -g promotores -G empleados_gr -s /bin/bash promotor2
sudo chage -M 30 promotor2
sudo useradd -m -g promotores -G empleados_gr -s /bin/bash promotor3
sudo chage -M 30 promotor3
sudo useradd -m -g promotores -G empleados_gr -s /bin/bash promotor4
sudo chage -M 30 promotor4

-----------------------------------------------------
## 3. Permisos Específicos (ACLs)

### - **Recursos**: Cámaras rwx (escriben bruto), editores r-x (leen para editar).

(Permisos para acceso a carpeta)
sudo setfacl -m g:camaras:rwx,g:editores:rx /opt/compartido/recursos

(Permisos por defecto para futuros archivos)
sudo setfacl -d -m g:camaras:rwx,g:editores:rx /opt/compartido/recursos

### - **Videos_finales**: Editores rwx (suben final), cámaras y promotores r-x (revisan).

sudo chmod 750 /opt/compartido/videos_finales

(Permisos para acceso a carpeta)
sudo setfacl -m g:editores:rwx,g:camaras:rx,g:promotores:rx /opt/compartido/videos_finales

(Permisos por defecto para futuros archivos)
sudo setfacl -d -m g:editores:rwx,g:camaras:rx,g:promotores:rx /opt/compartido/videos_finales

### - **Estadísticas**: SOLO promotores.

sudo chgrp promotores /opt/compartido/estadisticas
sudo chmod 770 /opt/compartido/estadisticas

### Ejemplo para comprobación
El usuario "camara1" crea un archivo en recursos

sudo -u camara1 touch /opt/compartido/recursos/bruto_test.raw

Verificamos que "editor1" solamente puede leerlo y que el permiso se ha heredado

sudo getfacl /opt/compartido/recursos/bruto_test.raw

-----------------------------------------------------
## 4. Script de Organización de Archivos

Archivo: `/usr/local/bin/mover_extensiones.sh` (Ejecución cada 2 días vía Cron)

````
#!/bin/bash
DEST="/opt/compartido"

# RAW, WAV -> Recursos
find $DEST -maxdepth 2 -type f \( -name "*.RAW" -o -name "*.WAV" \) -exec mv {} $DEST/recursos/ \;

# mp3, mp4, jpg, svg -> Videos Finales
find $DEST -maxdepth 2 -type f \( -name "*.mp3" -o -name "*.mp4" -o -name "*.jpg" -o -name "*.svg" \) -exec mv {} $DEST/videos_finales/ \;

# xls, doc, py, etc -> Estadisticas
find $DEST -maxdepth 2 -type f \( -name "*.xls*" -o -name "*.doc*" -o -name "*.py" -o -name "*.csv" -o -name "*.json" -o -name "*.csv" -o -name "*.xlsx" -o -name "*.odt" -o -name "*.docx" \) -exec mv {} $DEST/estadisticas/ \;
````


--------------------------------------------------------
## 5. Script de Clasificación por Mes y Tamaño

### Script, organizador de todo el contenido dentro de recursos (Se ejecuta a diario).  

Archivo: /usr/local/bin/clasificar_recursos.sh

````
#!/bin/bash
RECURSOS_DIR="/opt/compartido/recursos"

# Buscamos archivos directamente en recursos (no en subcarpetas)
find "$RECURSOS_DIR" -maxdepth 1 -type f | while read -r archivo; do
    # Sacar mes en español (o inglés según sistema) y tamaño
    MES=$(date -r "$archivo" +%B)
    TAMANO_BYTES=$(stat -c%s "$archivo")
    
    # Lógica de carpetas de tamaño
    if [ $TAMANO_BYTES -lt 10485760 ]; then RANGO="menos_10MB"
    elif [ $TAMANO_BYTES -lt 104857600 ]; then RANGO="10-100MB"
    elif [ $TAMANO_BYTES -lt 1073741824 ]; then RANGO="100MB-1GB"
    else RANGO="mas_de_1GB"
    fi

    DESTINO="$RECURSOS_DIR/$MES/$RANGO"
    mkdir -p "$DESTINO"
    mv "$archivo" "$DESTINO"
done
````


--------------------------------------------------
## 6. Monitorización (Cron)

### Para automatizar todo, editamos el cron (crontab -e)

#### Cada 2 días: Script de extensiones
0 0 */2 * * /bin/bash /usr/local/bin/mover_extensiones.sh

#### Diariamente: Script de clasificación
0 1 * * * /bin/bash /usr/local/bin/clasificar_recursos.sh

#### Cada miércoles a las 9:00: Monitorizar editores
0 9 * * 3 /bin/bash /usr/local/bin/monitorizarEditores.sh

````
#!/bin/bash
EDITORES=("editor1" "editor2" "editor3" "editor4")

for usuario in "${EDITORES[@]}"
do
    ps -u "$usuario" -o pid,%mem,%cpu,user,comm --sort=-%mem | head -n 6 > "/opt/compartido/$usuario"
done
````
(He puesto head -n 6 porque la primera línea es el encabezado (PID, %MEM...), y luego vienen los 5 procesos. Si pones head -n 5, solo verías 4 procesos.)
