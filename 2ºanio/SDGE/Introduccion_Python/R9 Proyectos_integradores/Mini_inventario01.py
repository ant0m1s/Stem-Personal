print("Bienvenido al Menú")
# --------------METODOS-------------------
def imprimir(mensaje):
    return print(mensaje)


def pedir_numero(mensaje, limite1, limite2):
    while True:
        usuario = input(mensaje)
        try:
            temp = int(usuario)
            if limite1 <= temp <= limite2:
                usuario = temp
                return temp
            else:
                imprimir("Error: Opción no debe ser negativa")
        except ValueError:
            imprimir("Error: Introduce una opción permitida")


def buscar_nombre(catalogo, nombre=None):
    if not nombre:
        nombre = input("Que producto quieres buscar?: ").strip()
    for linea in catalogo:
        if nombre.lower() == linea["nombre"].lower():
            return linea
        else:
            return None


def cambiar_stock(catalogo):
    producto = buscar_nombre(catalogo)
    mensaje = ""
    if producto:
        imprimir(f"Stock actual de {producto['nombre']}: {producto['stock']}")
        nuevo_stock = pedir_numero("Nuevo stock (1-100 debe ser INT): ", 1, 100)
        producto["stock"] = nuevo_stock
        mensaje += "Stock cambiado correctamente"
    else:
        mensaje += "Error: No se encontró ningún producto con ese nombre"

    return mensaje


def mostrar_catalogo(catalogo):
    mensaje = ""
    if len(catalogo_productos) == 0:
        mensaje = "Catalógo vacío."
        return mensaje
    else:
        imprimir("Menú para modificar Stock\nImprimiendo lista:\n")
        for linea in catalogo_productos:
            mensaje += f"Producto: {linea['nombre']}\nCategoría: {linea['categoría']}\nPrecio: {linea['precio']}\nStock: {linea['stock']}\n----------------------\n"
        return mensaje

# -------------MAIN--------------------------
mensaje_menu = "\n0. Salir\n1. Listar catalogo_productos\n2. Añadir Producto\n3. Modificar Stock\n4. Buscar por Nombre\n5. Guardar en CSV"
bandera = True
catalogo_productos = []
while bandera:
    imprimir(mensaje_menu)
    usuario = pedir_numero("Elige una opción (0-5): ", 0, 5)
    if usuario == 0:
        imprimir("Terminando Programa...")
        bandera = False
    elif usuario == 1:
        imprimir(mostrar_catalogo(catalogo_productos))

    elif usuario == 2:
        imprimir("Añadir Producto:\n")
        categoria = input("A que categoría pertenece?: ")
        nombre = input("Nombre del producto: ")
        precio = pedir_numero("Precio del producto (1-1000 debe ser int): ", 1, 1000)
        stock = pedir_numero("Cuanto stock hay? (1-100 debe ser INT): ", 1, 100)
        catalogo_productos.append(
            {"nombre": nombre, "categoría": categoria, "precio": precio, "stock": stock}
        )
        imprimir("Producto añadido correctamente\n")

    elif usuario == 3:
        mensaje = mostrar_catalogo(catalogo_productos)
        imprimir(mensaje)
        if mensaje == "Catalógo vacío.":
            break
        else:
            imprimir(cambiar_stock(catalogo_productos))

    elif usuario == 4:
        mensaje = mostrar_catalogo(catalogo_productos)
        imprimir(mensaje)
        if mensaje == "Catalógo vacío.":
            break
        else:
            producto = buscar_nombre(catalogo_productos)
            if producto:
                imprimir(
                    f"Producto: {producto['nombre']}\nCategoría: {producto['categoría']}\nPrecio: {producto['precio']}\nStock: {producto['stock']}\n----------------------\n"
                )
            else:
                imprimir("Error: No se encontró ningún producto con ese nombre")

    elif usuario == 5:
        imprimir("Guardando catálogo CSV\n")
        mensaje = mostrar_catalogo(catalogo_productos)
        if mensaje == "Catalógo vacío.":
            imprimir("Catalógo vacío.")
        else:
            with open("inventario.txt", "w", encoding="utf-8") as archivo:
                archivo.write("Nombre,Categoria,Precio,Stock\n")
                for prod in catalogo_productos:
                    linea_csv = f"{prod['nombre']},{prod['categoría']},{prod['precio']},{prod['stock']}\n"
                    archivo.write(linea_csv)

            imprimir("Archivo 'inventario.csv' guardado con éxito!")
