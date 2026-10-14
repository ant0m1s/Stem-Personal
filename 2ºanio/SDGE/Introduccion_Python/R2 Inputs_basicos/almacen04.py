print("Bienvenido a Gestión almacén")

producto = input("Nombre de producto: ")
cantidadInicial = input("Cantidad inicial (nº int): ")
cantidad_recibida = input("Stock recibido (nº int): ")
cantidad_vendida = input("Productos vendidos (nº int): ")

cantInicial = int(cantidadInicial)
cantRecibida = int(cantidad_recibida)
cantVendida = int(cantidad_vendida)

print("Haciendo inventario\n...")

cantActual = cantInicial + cantRecibida
cantFinal = int
if cantActual > cantVendida : 
    cantFinal = cantActual - cantVendida
    print("Imprimiendo ticket\n----")
    print(f"Producto {producto}")
    print(f"Cantidad Inicial {cantInicial}")
    print(f"Stock recibido {cantRecibida}")
    print(f"Cantidad Vendida {cantVendida}")
    print(f"Inventario Final: {cantFinal}")
else :
    print(f"No se puede vender {cantVendida} teniendo {cantActual} ")
    print("Errorrr...")

