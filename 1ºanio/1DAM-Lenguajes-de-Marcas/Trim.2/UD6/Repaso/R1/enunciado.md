# Relación R1 — Balonmano: Centro de Estadísticas (DOM + dataset)

## Objetivo
Practicar **manipulación del DOM** (selección, estilos, creación/eliminación de nodos, eventos) usando **atributos `data-*`** con `dataset`.

### ¿Qué es `dataset`?
En HTML puedes guardar información “extra” en un elemento con atributos `data-*`, por ejemplo:
- `data-posicion="Extremo"`
- `data-goles="12"`
- `data-dorsal="7"`

En JavaScript se accede con `element.dataset`:
- **Acceder**: `element.dataset.goles` (lee el valor como string)
- **Modificar**: `element.dataset.goles = "13"` (cambia el atributo `data-goles`)

> Importante: `dataset` siempre devuelve **texto**. Si necesitas operar con números, convierte con `Number(...)` o `parseInt(...)`.

---

## Estructura del proyecto
- `index.html` (único HTML de la relación)
- `css/style.css`
- `js/script.js` (vacío para que lo completes)

---

## Ejercicios (todo en el mismo `index.html`)

### 1) Carga inicial: leer `dataset` y mostrar resumen
Al cargar la página:
1. Selecciona el **primer jugador** (la primera tarjeta `.jugador`).
2. **Lee** sus datos desde `dataset` (nombre, dorsal, posición y goles).
3. Muestra ese resumen en el panel `#panelDetalles`:
   - Nombre y dorsal en `#detalleNombre`
   - Posición en `#detallePosicion`
   - Goles en `#detalleGoles`

📌 **Pista**: el nombre está como texto dentro de un `<h3>` en la tarjeta.

---

### 2) Click en tarjeta: seleccionar jugador y actualizar panel
Cuando el usuario haga click en una tarjeta `.jugador`:
1. Marca visualmente la tarjeta seleccionada (clase `.seleccionado`).
2. Quita `.seleccionado` al resto de tarjetas.
3. Actualiza el panel de detalles (`#panelDetalles`) usando el `dataset` del jugador clicado.

---

### 3) Botón “+1 gol”: modificar `dataset` y el texto visible
Al pulsar el botón `#btnSumarGol`:
1. Suma **1** al valor `data-goles` del jugador seleccionado.
2. Actualiza:
   - El `dataset` del jugador (`data-goles`)
   - El texto del panel `#detalleGoles`
   - El pequeño contador dentro de la tarjeta (span `.goles`)

✅ Aquí tienes el ejemplo completo pedido:
- **Acceder**: `jugador.dataset.goles`
- **Modificar**: `jugador.dataset.goles = nuevoValor`

---

### 4) Filtrado por posición usando `dataset`
Con el `<select id="filtroPosicion">`:
1. En el evento `change`, recorre todas las tarjetas `.jugador`.
2. Muestra solo las que cumplan:
   - Si el filtro es `"todas"` → se ven todas.
   - Si no → se ven solo las cuyo `dataset.posicion` coincide con el valor del select.
3. Las que no cumplan deben ocultarse (por ejemplo, `style.display = "none"`).

---

### 5) Añadir jugador (crear nodos + dataset)
En el formulario `#formNuevoJugador`:
1. Al enviar, evita recarga.
2. Valida que:
   - Nombre no esté vacío
   - Dorsal sea >= 1
   - Goles sea >= 0
3. Crea una nueva tarjeta `.jugador` con la misma estructura que las existentes.
4. Asigna correctamente los `data-*` (dorsal, goles, posición).
5. Inserta la tarjeta al final de `#listaJugadores` y deja el formulario limpio.

---

### 6) Borrar jugador seleccionado
Al pulsar `#btnBorrar`:
1. Si hay un jugador seleccionado, elimínalo del DOM.
2. Tras borrar, selecciona automáticamente el **primer jugador** que quede (si existe) y actualiza el panel.

---


