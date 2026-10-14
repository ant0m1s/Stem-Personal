print("Bienvenido a Presupuesto con descuento")

producto = input("Nombre del Producto: ")
precio_texto = input("Precio producto (nº float):")
cantidad_texto = input("Cantidad (nº int): ")
descuento_texto = input("Porcentaje para Descuento: ")

precio = float(precio_texto)
cantidad = int(cantidad_texto)
descuento = float(descuento_texto)

print("Calculando importes\n...")

subtotal = precio * cantidad
descuentoDinero = (descuento/100)*subtotal
precio_total = subtotal - descuentoDinero

print("Imprimiendo ticket\n----")
print(f"Producto obtenido: {producto}")
print(f"Cantidad {cantidad_texto}")
print(f"Precio subtotal: {subtotal}")
print(f"Descuento aplicado {descuentoDinero}€")
print(f"Precio con Descuento: {precio_total}\n----")
