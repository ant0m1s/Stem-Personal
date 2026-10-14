print("Bienvenido a Estado de existencias")

producto = input("Nombre del Producto: ")
cantidad_texto = input("Stock del producto (nº int): ")
cantidad = int(cantidad_texto)

agotado = 0
mensaje = ""
if cantidad == 0:
    mensaje = "Agotado"
elif cantidad >= 1 and cantidad <= 5:
    mensaje = "Stock bajo"
elif cantidad > 5:
    mensaje = "Stock suficiente"
else:
    mensaje = "Stock Incorrecto"
    
print(f"Nombre: {producto} con {mensaje}")
