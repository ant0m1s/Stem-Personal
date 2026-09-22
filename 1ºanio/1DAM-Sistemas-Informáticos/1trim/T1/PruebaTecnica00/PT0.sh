#!bin/bash

ls . | grep .txt|

ls /alumnoT | wc

***


### 4. Busca archivos `.conf` que sean mayores a 5 KB 
##y que hayan sido modificados en los últimos 7 días:
find . -name "*.conf" -size +5k -mtime -7

### 5. Busca archivos `.log` de entre 1 y 5 MB 
###que no se hayan modificado en los últimos 30 días:
find . -name "*.log" -size +1M -size -5M -mtime +30