class Producto:
    def __init__(self, nombre, precio, stock):
        self.nombre = nombre
        self.precio = precio
        self.stock = stock

    def mostrar(self):
        print(f"Producto: {self.nombre} | Precio: {self.precio:.2f} € | Stock: {self.stock}")
    
    def vender(self, cantidad):
        if self.stock >= cantidad:
            self.stock -= cantidad
            return True
        else:
            return False
            
    def reponer(self, cantidad):
            self.stock += cantidad


prod1 = Producto("Teclado Mecánico", 39.00, 20)

print("--- Estado Inicial ---")
prod1.mostrar()

print("--- Vendidas 10 ---")
prod1.vender(10)
prod1.mostrar()

print("--- Reponiendo 2 Productos ---")
prod1.reponer(2)
prod1.mostrar()