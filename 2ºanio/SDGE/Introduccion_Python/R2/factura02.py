print("Bienvenido a Factura de Servicio")

cliente = input("Nombre del cliente: ")
servicio = input("Nombre del servicio: ")
precio_texto = input("Precio unitario (nº float):")
cantidad_texto = input("Horas de servicio (nº int): ")

precio = float(precio_texto)
cantidad = int(cantidad_texto)

print("Calculando importes\n...")

subtotal = precio * cantidad
iva_simulado = subtotal * 0.21
precio_total = subtotal + iva_simulado

print("Imprimiendo ticket\n...")
print(f"{cliente}")
print(f"{servicio}")
print(f"Horas de servicio {cantidad_texto}")
print(f"Precio sin IVA: {subtotal}")
print(f"Precio Final: {precio_total}")
