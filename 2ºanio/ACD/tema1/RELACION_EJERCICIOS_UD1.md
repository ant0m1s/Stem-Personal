# RELACIÓN DE EJERCICIOS · UNIDAD 1
## Ficheros, XML, JSON y componentes de archivos

Esta carpeta contiene los ejercicios de la **Unidad 1**. Se han separado del cuaderno general para que puedas trabajar por bloques. Conviene seguirlos en orden porque cada grupo reutiliza lo construido en el anterior.

**Sugerencia de uso en clase:** realiza primero E01–E05 en el aula, deja E06–E12 como consolidación y utiliza E13–E15 como ampliación o proyecto final.

## UNIDAD 1 · Ficheros, XML, JSON y componentes de archivos

### E01 · Inspecciona un directorio [I · 30 min · UD1-P1]
**Entrada:** carpeta `datos`, inicialmente vacía. **Programa:** crea `datos` y muestra nombre, tamaño y tipo de los archivos que contiene, sin contar subcarpetas como archivos. **Entrega:** código y dos salidas de consola. **Prueba:** añade `a.txt`, `b.bin` y una subcarpeta; verifica que informa de dos archivos.

### E02 · Carpeta de copias sin pérdidas [I · 25 min · UD1-P1/P7]
**Entrada:** `datos/clubes.txt`. **Programa:** cópialo a `datos/copias/clubes.bak`, comprueba el tamaño de ambos y no borres el origen. **Prueba:** ejecuta dos veces; la segunda no debe fallar aunque el destino exista.

### E03 · Registro de altas en TXT [I · 35 min · UD1-P2]
**Entrada:** los clubes `(1, Granada CF, Granada)`, `(2, Málaga CF, Málaga)`, `(3, Cádiz CF, Cádiz)`. **Programa:** escribe una línea `id;nombre;ciudad` por club y vuelve a leer el fichero en UTF-8. **Prueba:** al reiniciar Java aparecen exactamente tres líneas y las tildes se conservan.

### E04 · Importador resistente [M · 40 min · UD1-P2/P3]
**Entrada:** `clubes_errores.csv` de recursos. **Programa:** procesa cada fila, valida número de campos, ID entero positivo y nombre no vacío; notifica errores por número de línea sin detener la importación. **Prueba:** el archivo contiene filas defectuosas y repetidas; conserva solamente los registros válidos y no duplica IDs.

### E05 · CRUD persistente de clubes [M · 55 min · UD1-P3]
**Entrada:** fichero TXT de E03. **Programa:** menú para crear, buscar por ID, modificar ciudad y eliminar. **Prueba:** crea club ID 4; intenta recrearlo; modifica ciudad; elimina ID 4; repite borrado: la última acción debe responder «no existe».

### E06 · Cambio y restauración [M · 30 min · UD1-P3/P7]
**Entrada:** tres clubes de prueba. **Programa:** guarda copia fechada antes de la modificación y añade «restaurar última copia» previa confirmación. **Prueba:** modifica la ciudad y restaura; ninguna otra fila cambia y ambas versiones pueden encontrarse.

### E07 · Agenda binaria [M · 40 min · UD1-P4]
**Entrada:** objetos `Club(id,nombre,ciudad)`; fichero `clubes.dat`. **Programa:** escribe UNA lista de objetos con `ObjectOutputStream` y léela al reiniciar con `ObjectInputStream`. **Prueba:** modifica un club y reinicia. No uses `ObjectInputStream` con archivos descargados o de origen desconocido.

### E08 · Archivo aleatorio de registros fijos [A · 50 min · UD1-P5]
**Entrada:** registros con `id`, nombre de longitud fija y estado activo. **Programa:** crea tres registros y desactiva el segundo mediante `RandomAccessFile.seek` sin alterar primero ni tercero. **Prueba:** reabre el fichero y lee las tres posiciones; el segundo figura como inactivo.

### E09 · Generador de XML [I · 35 min · UD1-P8]
**Entrada:** la lista E03. **Programa:** crea `clubes.xml` con raíz `clubes` y un elemento `club` por registro. **Prueba:** el XML se abre sin errores en un navegador/editor y conserva las tres tildes y nombres completos.

### E10 · XML a TXT con errores controlados [M · 45 min · UD1-P8]
**Entrada:** `clubes.xml` y una copia manipulada sin elemento `ciudad`. **Programa:** importa, valida y exporta a TXT, sin borrar la información si el XML es inválido. **Prueba:** el XML correcto produce tres líneas; el incompleto produce un error claro, preservando el TXT anterior.

### E11 · JSON de ida y vuelta [M · 45 min · UD1-P9]
**Entrada:** `clubes.json`. **Programa:** deserializa a objetos Java, cambia la ciudad de un club, vuelve a serializar y comprueba la lectura. **Prueba:** el JSON mal formado no puede sustituir al fichero válido.

### E12 · Conversor de formatos [A · 45 min · UD1-P8/P9]
**Entrada:** una lista con cinco clubes. **Programa:** TXT → XML → JSON → TXT; compara por `id`, `nombre` y `ciudad` los datos originales y finales. **Prueba:** incluye un nombre con espacios y uno con tilde; ambos permanecen intactos.

### E13 · Biblioteca de persistencia [A · 45 min · UD1-P10]
**Entrada:** interfaz `ClubesRepository<T,ID>` de la unidad. **Programa:** implementa persistencia TXT y construye un JAR. **Prueba:** un segundo proyecto importa el JAR y realiza alta/búsqueda/baja sin copiar el código fuente del repositorio.

### E14 · Prueba de fallos de archivo [M · 35 min · UD1-P1/P3/P8]
**Entrada:** fichero inexistente, fichero vacío, CSV mal formado y carpeta sin permiso de escritura controlada por el docente. **Programa:** informa de errores sin perder registros válidos ni cerrar inesperadamente el menú. **Prueba:** captura salida de cada uno de los cuatro escenarios.

### E15 · Reto UD1: gestor local listo para usar [A · 70 min · UD1-P6/P10]
**Entrada:** lote del recurso `clubes.csv`. **Programa:** menú CRUD, guardado persistente, copia de seguridad, importación y exportación XML/JSON, búsqueda por nombre y JAR reutilizable. **Prueba de aceptación:** tras 2 reinicios, el número de clubes y los datos coinciden; ID duplicado y archivo corrupto se manejan sin pérdida.

---
