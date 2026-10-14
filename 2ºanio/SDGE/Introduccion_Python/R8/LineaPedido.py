class LineaPedido:
    def __init__(self, producto, precio, cantidad):
        self.producto = producto
        self.precio = precio
        self.cantidad = cantidad

    def subtotal(self):
        return self.precio * self.cantidad


class Pedido:
    def __init__(self, cliente):
        self.cliente = cliente
        self.lista_lineas = []

    def agregar_linea(self, linea):
        self.lista_lineas.append(linea)

    def total(self):
        return sum(linea.subtotal() for linea in self.lista_lineas)

    def mostrar(self):
        mensaje = f"--- Ficha Pedido ---\nCliente: {self.cliente}\n\nLíneas:\n"
        for linea in self.lista_lineas:
            mensaje += f"Producto: {linea.producto}\n Precio: {linea.precio}\n Cantidad = {linea.cantidad} und\n"
        return mensaje


linea1 = LineaPedido("Bocadillo pollo", 60, 2)
linea2 = LineaPedido("BMW", 180, 1)
linea3 = LineaPedido("Casa", 300, 1)

pedido1 = Pedido("Antonio Miguel")
pedido1.agregar_linea(linea1)
pedido1.agregar_linea(linea2)
pedido1.agregar_linea(linea3)

print(pedido1.mostrar())

total = pedido1.total()

print(f"Total utilizando total(): {total}€")
