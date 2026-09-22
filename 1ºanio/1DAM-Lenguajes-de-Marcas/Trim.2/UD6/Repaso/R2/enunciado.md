# Relación R2 — Ultramarinos: Preparación de pedidos

## Objetivo
Practicar manipulación del DOM usando `dataset`, selección de elementos, eventos, modificación de contenido y creación de nodos. Te doy un js, tu haz el tuyo propio si no quieres seguir el que te doy

## Qué debes hacer

### 1. Marcar producto activo
Cuando se pulse sobre un producto:
- Se marcará como producto activo.
- Se quitará la marca al resto.
- Se mostrará su información en la zona de resumen.

### 2. Añadir unidades al pedido
Cuando se pulse el botón **“Añadir al pedido”**:
- Si hay un producto activo, se añadirá 1 unidad a su cantidad pedida.
- Debes actualizar:
  - el valor guardado en `dataset`
  - el número visible del producto
  - el resumen superior

### 3. Filtrar por sección
Con el desplegable:
- Si se elige `Todas`, se muestran todos los productos.
- Si se elige una sección, solo se muestran los productos de esa sección.

### 4. Crear una línea en el pedido
Al pulsar el botón **“Pasar a pedido”**:
- Si hay un producto activo, debes crear una línea dentro de la lista `#listaPedido`.
- Esa línea debe incluir:
  - nombre del producto
  - sección
  - unidades pedidas

### 5. Añadir nuevo producto
Con el formulario:
- Leer nombre, sección y unidades iniciales.
- Validar:
  - nombre no vacío
  - unidades mayor o igual que 0
- Crear un nuevo producto con `dataset`.
- Insertarlo en la zona de productos.

