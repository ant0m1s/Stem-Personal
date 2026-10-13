class Producto:
    def __init__(self, nombre, precio, stock):
        self.nombre = nombre
        self.precio = precio
        self.stock = stock

    def mostrar(self):
        print(f"Producto: {self.nombre} | Precio: {self.precio:.2f} € | Stock: {self.stock}")


prod1 = Producto("Teclado Mecánico", 39.00, 20)
prod2 = Producto("Ratón", 8.99, 5)

prod1.mostrar()
prod2.mostrar()