# Tarea: Página web del restaurante “Shushio”

Vas a crear **una sola página web** para el restaurante japonés **Shushio**.  
En esta página deben aparecer **listas** y **tablas**, aplicando todo lo aprendido en la UD3.  
Se utilizarán únicamente elementos HTML básicos, sin archivos CSS externos.

---

## 1. Descripción general

La página representará el sitio web del restaurante **Shushio**, y deberá incluir:

1. Un encabezado con el nombre del restaurante.  
2. Un apartado con el **menú del día**, usando listas.  
3. Dos tablas con información del menú y la carta.  

Todo irá en un solo archivo HTML.

---

## 2. Apartado de listas: “Menú del día”

Crea una sección titulada **“Menú del día”** donde aparezcan las diferentes partes del menú.

1. Crea una **lista principal** con estas tres secciones:
   - Entrantes  
   - Platos principales  
   - Postres  

2. Dentro de cada una de esas secciones, añade una **lista interna** con al menos **tres platos** japoneses (por ejemplo: edamame, gyozas, sushi variado, ramen, dorayaki...).

3. En la parte de **Platos principales**, uno de los platos debe tener **otra lista dentro** con sus posibles variedades o acompañamientos (por ejemplo: “Sushi variado” → “Nigiri”, “Maki”, “Uramaki”).

4. En **una** de las listas numeradas, haz que **no empiece en 1** (usando el atributo que permite elegir el número inicial).

5. En **una** de las listas sin ordenar, cambia el estilo de la viñeta con `list-style-type` directamente en el elemento (por ejemplo: `square`, `circle` o `disc`).

6. Añade al final del apartado una frase como:  
   > *“Todos los menús incluyen té verde o agua.”*

---

## 3. Primera tabla: “Carta y precios”

Crea una tabla con los platos y precios del restaurante.

1. La tabla debe tener **4 columnas**:
   - Categoría  
   - Plato  
   - Calorías  
   - Precio  

2. La primera fila será el **encabezado** con esos nombres.

3. Rellena la tabla con **al menos 6 filas** con datos de platos (entrantes, principales y postres).

4. Al final, añade una **fila especial** con información del menú completo:
   - La primera celda mostrará el texto `Menú completo`.  
   - La segunda celda debe **ocupar dos columnas** y mostrar el texto “Incluye bebida y postre”.  
   - La última celda mostrará el precio total del menú (por ejemplo: `18,50 €`).

5. Añade un borde visible (`border`) y usa los atributos `cellpadding` y `cellspacing` para mejorar el aspecto de la tabla.

---

## 4. Segunda tabla: “Combinaciones especiales”

Shushio también ofrece combinaciones especiales que agrupan varios platos.  
Crea una **segunda tabla** donde sea necesario utilizar **colspan** para unir celdas.

**Requisitos:**

1. La tabla debe tener al menos **tres filas**.  
2. La primera fila será un **encabezado** con dos columnas:
   - “Combinación”  
   - “Incluye”  
3. En la segunda fila, la celda de “Combinación” debe ocupar **dos columnas unidas** con el texto:  
   `Menú Sushi Deluxe`.  
4. En la siguiente fila, escribe los platos que incluye esa combinación (por ejemplo: Sushi variado, Sopa de miso, Té verde).  
5. Añade al final una fila con otra combinación distinta, sin usar `colspan` en esa última.
6. La tabla también debe tener **borde visible** y atributos `cellpadding` y `cellspacing`.

---

## 5. Requisitos técnicos

- Todo el trabajo debe estar en **un único archivo HTML**.  
- No se puede usar un archivo CSS externo.  
- Solo se permite `style="list-style-type: ..."` en los elementos de lista.  
- Utiliza nombres de platos japoneses reales o inventados para mantener la temática.  
- Comprueba que todas las listas y tablas se vean correctamente en el navegador.

---

## 6. Extra (opcional)

- Añade al principio una lista con “Normas del restaurante” (por ejemplo: horario, tipo de cocina, pedidos para llevar).  
- Incluye una fila adicional en cualquiera de las tablas con un plato “fuera de carta”.

