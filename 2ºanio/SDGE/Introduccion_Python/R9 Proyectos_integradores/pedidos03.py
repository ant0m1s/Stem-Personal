from Metodos import *

# CLASE PRODUCTO LINEAPEDIDO Y PEDIDO------------------------
class Producto:
    def __init__(self, nombre, precio, stock):
        self.nombre = nombre
        self.precio = precio
        self.stock = stock


class LineaPedido:
    def __init__(self, Producto, cantidad):
        self.Producto = Producto
        self.cantidad = cantidad
        self.subtotal = cantidad * Producto.precio

    def buscar_producto(lista_productos, nombre):
        for linea in lista_productos:
            if nombre == linea["nombre"]:
                return linea
        return None

    def mostrar_producto(Producto):
        mensaje = f"Producto: {Producto['nombre']}\n"
        mensaje += f"Precio: {Producto['precio']}\n"
        return mensaje


class Pedido:
    def __init__(self, cliente):
        self.cliente = cliente
        self.lista_lineas = []

    def agregar_linea(self, linea):
        self.lista_lineas.append(linea)

    def listar_lineas(self):
        mensaje = ""
        contador = 1
        for linea in self.lista_lineas:
            mensaje += f"Linea {contador}\nProducto: {linea.Producto.nombre} | Precio: {linea.Producto.precio}\nCantidad: {linea.cantidad} | Subtotal: {linea.subtotal}€\n"
            contador += 1
        return mensaje

    def total(self):
        mensaje = ""
        total = 0
        if len(pedido1.lista_lineas) >= 1:
            for l in pedido1.lista_lineas:
                total += l.subtotal
                mensaje += f"Sumado {l.subtotal}€\n"
            mensaje += f"\nTotal guardado: {total}€\n"
        else:
            mensaje = "No hay líneas"
        return mensaje

    def guardar_CSV(self, archivo):
        mensaje = ""
        with open(archivo, "w", newline="", encoding="utf-8") as archivo:
            archivo.write("Producto,Precio,Unidades,Subtotal,Total\n")
            for l in self.lista_lineas:
                linea_csv = f"{l.Producto.nombre},{l.Producto.precio},{l.cantidad},{l.subtotal}\n"
                archivo.write(linea_csv)
            archivo.write(pedido1.total())
            mensaje = "Archivo 'pedido.txt' guardado con éxito"
        return mensaje

# FUNCIONES--------------------------------------------
def mostrar_productos(productos):
    mensaje = ""
    for p in productos:
        mensaje += f"Producto {p.nombre} | Precio {p.precio} | Stock {p.stock}\n"
    return mensaje


def guardar_lineas_productos(producto, lista):
    mensaje = ""
    for l in lista:
        if producto == l.Producto.nombre.strip().lower():
            mensaje += f"Producto: {l.Producto.nombre}\n Precio: {l.Producto.precio} | Und. comprada: {l.cantidad}\n"
    return mensaje


# DATOS DE PRUEBA---------------------------
lista_productos = []
producto1 = Producto("Pepino", 25, 50)
producto2 = Producto("Tomate", 15, 70)
producto3 = Producto("Almendra", 50, 20)
producto4 = Producto("Lechuga", 10, 10)
lista_productos.append(producto1)
lista_productos.append(producto2)
lista_productos.append(producto3)
lista_productos.append(producto4)
pedido1 = Pedido("Antonio")
pedido1.agregar_linea(LineaPedido(producto1, 20))
pedido1.agregar_linea(LineaPedido(producto3, 10))
pedido1.agregar_linea(LineaPedido(producto2, 50))

# Main---------------------------
mensaje_menu = "MENÚ Pedido\n0. Salir\n1. Añadir línea de pedido\n2. Listar líneas\n3. Añadir Producto\n4. Buscar por Producto\n5. Mostrar mercado\n6. Calcular el total\n7. Guardar en CSV"
bandera = True
while bandera:
    usuario = menu_respuesta(mensaje_menu, "Elige una opción (0-7): ", 0, 7)
    match usuario:
        case 0:
            print("Terminando programa...")
            bandera = False
        # Añadir línea de pedido
        case 1:
            print(mostrar_productos(lista_productos))
            producto_usu = input("Dime el producto que quieres: ")
            for p in lista_productos:
                if producto_usu.strip().lower() == p.nombre.strip().lower():
                    print(f"Stock actual: {p.stock}")
                    cantidad_usu = pedir_numero(
                        "Cuantas unidades quieres comprar?: ", 1, p.stock
                    )
                    p.stock = p.stock - cantidad_usu
                    subtotal = cantidad_usu * p.precio
                    pedido1.agregar_linea(LineaPedido(p, cantidad_usu))
                    print("Producto comprado correctamente")
        # Listar línea de pedido
        case 2:
            print(pedido1.listar_lineas())
        # Añadir producto
        case 3:
            nombre_p = input("Nombre del producto: ").strip().lower()
            precio_p = pedir_numero("Precio del producto: ", 1, 100)
            stock_p = pedir_numero("Precio del producto: ", 1, 100)
            p_usuario = Producto(nombre_p, precio_p, stock_p)
            lista_productos.append(p_usuario)
            print("Producto agregado correctamente")
        # Buscar línea por producto
        case 4:
            mensaje = ""
            producto_usu = input("Dime el producto que quieres: ").strip().lower()
            mensaje_productos = guardar_lineas_productos(
                producto_usu, pedido1.lista_lineas
            )
            if mensaje_productos != "":
                print(mensaje_productos)
            else:
                print("No hay lineas con este producto")
        # Mostrar Mercado
        case 5:
            print(mostrar_productos(lista_productos))
        # Calcular Total
        case 6:
            print(pedido1.total())
        # Guardar CSV
        case 7:
            print("Guardando pedido CSV\n")
            mensaje_lineas = pedido1.listar_lineas()
            if mensaje_lineas == "":
                print("No hay líneas guardadas")
            else:
                mensaje = pedido1.guardar_CSV("pedido.csv")
                print(mensaje)