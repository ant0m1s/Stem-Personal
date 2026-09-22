Aquí tienes la **Chuleta Definitiva para Copiar y Pegar**.

Está organizada por bloques funcionales (esqueletos). Tu estrategia en el examen será:

1. Copiar el **"Bloque 1 (Base)"** en cada archivo HTML.
2. Copiar el **"Bloque 2 (Menú)"** dentro del `header` de cada archivo.
3. Copiar el **esqueleto específico** (Lista, Tabla o Formulario) dentro del `main` según lo que te pida el enunciado.

---

### 1. BLOQUE BASE: Estructura HTML5 (Archivo Nuevo)

_Copia esto para crear `index.html`, `listas.html`, etc._

```html
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- Metadatos UD2 -->
    <meta name="description" content="RELLENAR DESCRIPCION" />
    <meta name="author" content="TU NOMBRE" />
    <title>TITULO PESTAÑA</title>

    <!-- CSS Estricto de Apuntes UD3 -->
    <style>
      body {
        font-family: sans-serif;
        margin: 20px;
      }

      /* Estilos de Tablas (UD3 2.1 y 2.3) */
      table {
        border-collapse: collapse;
        width: 80%;
        margin-bottom: 20px;
      }
      th,
      td {
        border: 1px solid black;
        padding: 10px;
      }
      th {
        background-color: #f2f2f2;
        text-align: center;
      }

      /* Navegación básica */
      nav ul {
        list-style-type: none;
        padding: 0;
      }
      nav ul li {
        display: inline;
        margin-right: 15px;
      }
    </style>
  </head>
  <body>
    <!-- ANCLA INICIO -->
    <a id="arriba"></a>

    <header>
      <h1>TITULO PRINCIPAL PAGINA (H1)</h1>

      <!-- PEGAR AQUÍ EL BLOQUE 2 (MENÚ) -->
    </header>
    <hr />

    <main>
      <!-- PEGAR AQUÍ EL CONTENIDO ESPECÍFICO (Listas, Tablas o Formulario) -->
    </main>

    <footer>
      <hr />
      <p>&copy; 2024 TU NOMBRE - Pie de página</p>
    </footer>
  </body>
</html>
```

---

### 2. BLOQUE MENÚ: Navegación (Igual para todos)

_Copia esto dentro del `<header>` de **todos** tus archivos._

```html
<nav>
  <!-- Menú de navegación -->
  <ul>
    <!-- Ajustar href al nombre real de tus archivos -->
    <li><a href="index.html">Inicio</a></li>
    <li><a href="listas.html">Listas</a></li>
    <li><a href="tablas.html">Tablas</a></li>
    <li><a href="formulario.html">Formulario</a></li>
  </ul>
</nav>
```

---

### 3. BLOQUE LISTAS: Esqueletos Variados (UD3)

_Ideal para el archivo `listas.html`. Copia y pega el que necesites._

#### A. Navegación Interna (Índice)

```html
<section>
  <h3>Índice Rápido:</h3>
  <ul>
    <li><a href="#id1">Ir a sección 1</a></li>
    <li><a href="#id2">Ir a sección 2</a></li>
  </ul>
</section>
```

#### B. Listas Ordenadas y Desordenadas

```html
<section>
  <!-- LISTA ORDENADA (Cambiar type: "1", "A", "I" | start: número) -->
  <h2 id="id1">Título Lista Ordenada</h2>
  <ol type="1" start="1">
    <li>Elemento 1</li>
    <li>Elemento 2</li>
  </ol>

  <!-- LISTA DESORDENADA (Cambiar style: square, circle, disc) -->
  <h2>Título Lista Desordenada</h2>
  <ul style="list-style-type: square;">
    <li>Elemento A</li>
    <li>Elemento B</li>
  </ul>

  <p><a href="#arriba">⬆ Volver</a></p>
</section>
```

#### C. Lista Anidada (Jerarquía) y Compleja (Items Ricos)

```html
<section>
  <h2 id="id2">Lista Anidada y Compleja</h2>
  <ul>
    <!-- Item con contenido rico (Img, Texto, Link) -->
    <li>
      <strong>Elemento Padre 1</strong>
      <br />
      <img src="foto.jpg" alt="desc" width="50" />
      <!-- Sublista dentro del LI -->
      <ol>
        <li>Hijo 1.1</li>
        <li>Hijo 1.2</li>
      </ol>
    </li>
    <li>Elemento Padre 2</li>
  </ul>
</section>
```

---

### 4. BLOQUE TABLAS: Esqueleto Pro (UD3)

_Ideal para `tablas.html`. Incluye `thead`, `tfoot` y estilos en línea._

```html
<section>
  <h2>Título de la Tabla</h2>

  <!-- width % y border solid (UD3 2.1) -->
  <table style="border: 2px solid black;">
    <!-- CABECERA -->
    <thead>
      <tr>
        <!-- scope="col" para columnas -->
        <th scope="col">Cabecera 1</th>
        <th scope="col">Cabecera 2</th>
        <!-- Colspan para fusionar columnas -->
        <th scope="col" colspan="2">Cabecera Fusionada</th>
      </tr>
    </thead>

    <!-- PIE (Totales/Notas) -->
    <tfoot>
      <tr>
        <td colspan="4" style="text-align: center; background-color: #eee;">
          Nota al pie de tabla
        </td>
      </tr>
    </tfoot>

    <!-- CUERPO -->
    <tbody>
      <!-- Fila simple con estilo de fondo (UD3 2.2) -->
      <tr style="background-color: #fff;">
        <!-- scope="row" para la primera celda de la fila -->
        <th scope="row">Dato Fila 1</th>
        <td>Dato 1.2</td>
        <td>Dato 1.3</td>
        <td>Dato 1.4</td>
      </tr>

      <!-- Fila con Fusión Vertical (Rowspan) -->
      <tr>
        <!-- Rowspan fusiona filas hacia abajo -->
        <td rowspan="2" style="vertical-align: middle;">Dato Fusionado</td>
        <td>Dato 2.2</td>
        <td>Dato 2.3</td>
        <td>Dato 2.4</td>
      </tr>
      <tr>
        <!-- La primera celda se salta por el rowspan -->
        <td>Dato 3.2</td>
        <td>Dato 3.3</td>
        <td>Dato 3.4</td>
      </tr>
    </tbody>
  </table>
</section>
```

---

### 5. BLOQUE FORMULARIO: Esqueleto Completo (UD3)

_Ideal para `formulario.html`. Incluye subida de archivos y validaciones._

```html
<section>
  <h2>Título Formulario</h2>

  <!-- Form Config: POST y multipart para archivos -->
  <form action="#" method="POST" enctype="multipart/form-data">
    <!-- TEXTO (pattern para formato estricto) -->
    <p>
      <label for="id_txt">Texto (Requerido):</label><br />
      <input
        type="text"
        id="id_txt"
        name="nombre_txt"
        required
        placeholder="Ejemplo..."
        minlength="3"
      />
    </p>

    <!-- EMAIL y PASS -->
    <p>
      <label for="id_email">Email:</label><br />
      <input type="email" id="id_email" name="nombre_email" required />
    </p>
    <p>
      <label for="id_pass">Contraseña:</label><br />
      <input type="password" id="id_pass" name="nombre_pass" />
    </p>

    <!-- FECHA / CALENDARIO -->
    <p>
      <label for="id_date">Fecha:</label><br />
      <input type="date" id="id_date" name="nombre_fecha" />
    </p>

    <!-- RADIOS (Mismo 'name' para grupo único) -->
    <p>
      <strong>Selecciona uno:</strong><br />
      <input
        type="radio"
        id="rad1"
        name="grupo_radio"
        value="opcion1"
        checked
      />
      <label for="rad1">Opción 1</label>

      <input type="radio" id="rad2" name="grupo_radio" value="opcion2" />
      <label for="rad2">Opción 2</label>
    </p>

    <!-- CHECKBOX (Varios) -->
    <p>
      <strong>Selecciona varios:</strong><br />
      <input type="checkbox" id="chk1" name="grupo_check" value="val1" />
      <label for="chk1">Casilla 1</label>
    </p>

    <!-- SELECT (Desplegable) -->
    <p>
      <label for="id_sel">Desplegable:</label><br />
      <select id="id_sel" name="nombre_select">
        <option value="val1">Opción A</option>
        <option value="val2">Opción B</option>
      </select>
    </p>

    <!-- TEXTAREA (Comentarios) -->
    <p>
      <label for="id_msg">Área de Texto:</label><br />
      <textarea id="id_msg" name="nombre_msg" rows="4" cols="50"></textarea>
    </p>

    <!-- ARCHIVO -->
    <p>
      <label for="id_file">Subir Archivo:</label><br />
      <input type="file" id="id_file" name="nombre_archivo" />
    </p>

    <!-- BOTONES -->
    <p>
      <button type="submit">Enviar</button>
      <button type="reset">Limpiar</button>
    </p>
  </form>
</section>
```

---

### 6. BLOQUE EXTRAS UD2: Multimedia y Texto

_Para decorar cualquier página (`index.html`)._

```html
<section>
  <!-- IMAGEN CON SEMÁNTICA (Figure) -->
  <figure>
    <img src="imagen.jpg" alt="Descripción para ciegos" width="200" />
    <figcaption>Pie de foto explicativo</figcaption>
  </figure>

  <!-- VIDEO -->
  <video width="300" controls>
    <source src="video.mp4" type="video/mp4" />
  </video>

  <!-- FORMATOS DE TEXTO -->
  <p>
    <strong>Negrita (Importante)</strong>, <em>Cursiva (Énfasis)</em>,
    <br />Salto de línea.
  </p>
  <blockquote>Cita textual larga.</blockquote>

  <!-- ENLACES -->
  <a href="https://google.com" target="_blank"
    >Enlace externo (Nueva pestaña)</a
  >
  <a href="mailto:correo@test.com">Enlace email</a>
</section>
```

Aquí tienes el **BLOQUE 7** para añadir a tu chuleta.

Este bloque recoge **exclusivamente** el CSS que tus apuntes (UD3) mencionan como la "forma moderna" de dar formato a tablas y listas. Copia esto y pégalo al final de tu documento de chuleta.

---

### 7. BLOQUE CSS: Estilos Clave y Explicados (UD3)

_Tus apuntes recomiendan usar esto en lugar de atributos antiguos como `border="1"` o `cellpadding`._

#### A. Para poner en el `<head>` (Estilos Generales)

_Usa esto para definir cómo se ven **todas** las tablas de golpe._

```html
<style>
  /* 1. Bordes de Tabla (UD3 2.1) 
       Sustituye al atributo border="1". 
       'collapse' es vital para que las líneas sean finas y no dobles. */
  table {
    border: 1px solid black;
    border-collapse: collapse;
    width: 80%; /* Sustituye a width="80%" */
  }

  /* 2. Celdas y Encabezados (UD3 2.3) */
  th,
  td {
    border: 1px solid black; /* Borde en cada casilla */
    padding: 10px; /* ESPACIO INTERNO: Sustituye a cellpadding */
    text-align: left; /* ALINEACIÓN: Sustituye a align="left" */
  }

  /* 3. Encabezados destacados (UD3 2.3) */
  th {
    background-color: #f2f2f2; /* Color de fondo gris claro */
    text-align: center; /* Centrar textos de cabecera */
    font-weight: bold; /* Negrita */
  }
</style>
```

#### B. Para poner en las Etiquetas (Estilos en Línea)

_Usa esto si quieres cambiar el estilo de **una sola cosa** (una fila específica, una lista concreta)._

```html
<!-- TABLAS: Dar color a una fila completa (UD3 2.2) -->
<!-- Útil para destacar ofertas o encabezados sin usar <th> -->
<tr style="background-color: #ffffcc; text-align: center;">
  <td>Dato destacado</td>
  <td>Dato destacado</td>
</tr>

<!-- TABLAS: Bordes inline (UD3 2.1) -->
<!-- Si no te dejan usar <style> en el head, ponlo directo en la etiqueta -->
<table style="border: 1px solid black; width: 100%;">
  <!-- contenido -->
</table>

<!-- LISTAS: Cambiar la viñeta (UD3 1.2) -->
<!-- Valores: disc (punto), circle (círculo), square (cuadrado) -->
<ul style="list-style-type: square;">
  <li>Elemento con cuadrado</li>
</ul>
<ul style="list-style-type: circle;">
  <li>Elemento con círculo hueco</li>
</ul>
```

---

### 📝 Resumen rápido para el examen:

1.  **`border: 1px solid black;`**: Siempre que te pidan una tabla con bordes.
2.  **`padding: Xpx;`**: Si te piden "espacio dentro de las celdas" (o relleno).
3.  **`background-color: #...;`**: Para sombrear filas o cabeceras.
4.  **`text-align: center;`**: Para centrar el contenido.
5.  **`style="..."`**: Si te piden cambiar algo puntual en una etiqueta específica.
