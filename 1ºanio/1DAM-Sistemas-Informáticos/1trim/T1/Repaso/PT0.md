## PT0

### 1. Crear el siguiente árbol de directorios:

```
PT0/
└── file1
└── dir1/
    └── dir2/
        └── dir3/
        └── dir4/
    └── file2
```
Respuesta
cd ~
/home/antonioferres/PT0
mkdir -p PT0/dir1/dir2/{dir3,dir4};
cd PT0;
touch file1 dir1/file2
tree

### 2. Mueve el directorio `dir3` dentro del directorio `dir1` y renombrarlo como `newDir3`:
/home/antonioferres/PT0
mv dir1/dir2/dir3 dir1/newDir3
tree

### 3. Copia el directorio `dir4` dentro del directorio `dir1`:
/home/antonioferres/PT0
cp -r dir1/dir2/dir4 dir1
tree

### 4. Busca archivos `.conf` que sean mayores a 5 KB y que hayan sido modificados en los últimos 7 días:
/home/antonioferres/PT0
find . -type f -name "*.conf" -size +5k -mtime -7

### 5. Busca archivos `.log` de entre 1 y 5 MB que no se hayan modificado en los últimos 30 días:
/home/antonioferres/PT0
find . -type f -name "*.log" -size +1M -size +5M -mtime +30