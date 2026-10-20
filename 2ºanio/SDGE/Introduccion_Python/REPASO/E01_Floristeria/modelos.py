class Producto:
    def __init__(self, codigo, nombre, categoria, precio, stock):
        self.codigo = codigo
        self.nombre = nombre
        self.categoria = categoria
        self.precio = precio
        self.stock = stock

    def crear_codigo(self):
        contador = 1
        return f"FLO" + contador

    def cambiar_stock(self, nuevo_stock):
        self.stock = nuevo_stock

    def to_String(self):
        mensaje = f"Nombre: {self.nombre}\nCategoría: {self.categoria} Precio: {self.precio}€\n"
        return mensaje


class LineaVenta:
    def __init__(self, Producto, cantidad, precio_unitario):
        self.Producto = Producto
        self.cantidad = cantidad
        self.precio_unitario = precio_unitario
        self.subtotal = cantidad * precio_unitario


class Venta:
    def __init__(self, numero, cliente, cerrada=False):
        self.numero = numero
        self.cliente = cliente
        self.cerrada = cerrada
        self.lista_lineas = []

    def ingresar_linea(self, linea):
        self.lista_lineas.append(linea)

    def subtotal_venta(self):
        return sum(l.subtotal for l in self.lista_lineas)

    def total(self):
        sub = self.subtotal_venta()
        if sub >= 50:
            return sub * 0.90
        return sub

    def to_String(self):
        estado = "CERRADA" if self.cerrada else "ABIERTA"
        mensaje = f"Venta {self.numero}------------------------"
        mensaje += f"Nombre de Cliente: {self.cliente} ({estado})\nLINEAS DE PEDIDO:\n"
        if len(self.lista_lineas) > 0:
            for l in self.lista_lineas:
                mensaje += f"\n{l.Producto.to_String()}{l.cantidad} uds a {l.precio_unitario} ud/€ "
            mensaje += f"Total: {self.total()}€"
        else:
            mensaje += "SIN LINEAS"
        return mensaje
