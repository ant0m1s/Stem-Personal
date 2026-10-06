print("Bienvenido a Descuento por cantidad")
precio_texto = input("Precio del producto (nº float): ")
print("MENU DESCUENTOS\n <5 Articulos: 0%\n 5-9 Articulos: 5%\n >=10 Articulos: 10%\n")
cantidad_texto = input("Cantidad de productos (nº int): ")
cantidad = int(cantidad_texto)
precio = float(precio_texto)
precio_mostrar = 0


if cantidad >= 5 and cantidad < 9:
    precio_descontar = 0.05 * precio
    precio_mostrar = precio - precio_descontar
elif cantidad >= 10:
    precio_descontar = 0.1 * precio
    precio_mostrar = precio - precio_descontar
    
print("Total Compra\n")
print(f"Cantidad de Productos Comprados: {cantidad}")
print(f"Precio sin descuento: {precio}")
print(f"Precio con descuento: {precio_mostrar}")