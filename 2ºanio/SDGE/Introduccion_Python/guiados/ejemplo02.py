print("Bienvenido a Puntalon")
print("Presupuesto de Práctica\n")

cliente = input("Cliente: ")
producto = input("Producto: ")
precio_texto = input("Precio unitario (EUR):")
cantidad_texto = input("Cantidad: ")

precio = float(precio_texto)
cantidad = int(cantidad_texto)

print("Calculando importes\n...")

subtotal = precio * cantidad
iva_simulado = subtotal * 0.21
precio_total = subtotal + iva_simulado

print(f"Precio sin IVA: {subtotal}")
print(f"Precio Final: {precio_total}")
