print("Bienvenido a Mejor canal de ventas")
ventas_tienda = float(input("Ventas de Tienda: "))
ventas_web = float(input("Ventas de la Web: "))
ventas_telefono = float(input("Ventas por Telefono: "))
maximo_vendedor = ""

if ventas_tienda > ventas_web and ventas_tienda > ventas_telefono:
    maximo_vendedor = "Sector Tienda"
elif ventas_web > ventas_tienda and ventas_web > ventas_telefono:
    maximo_vendedor = "Sector Web"
elif ventas_telefono > ventas_web and ventas_telefono > ventas_tienda:
    maximo_vendedor = "Sector Telefono"
else:
    maximo_vendedor = "Existe un empate"

print(f"Sector 1: {ventas_tienda}")
print(f"Sector 2: {ventas_web}")
print(f"Sector 3: {ventas_telefono}")
print(f"Sector mas vendedor: {maximo_vendedor}")

