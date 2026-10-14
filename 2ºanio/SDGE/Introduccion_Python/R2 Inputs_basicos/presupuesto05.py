print("Bienvenido a Presupuesto de dos líneas")

cliente = input("Nombre del Cliente: ")
producto1 = input("Nombre Producto 01/02: ")
precio_p1 = input("Precio P01 (nº float): ")
cantidad_p1 = input("Cantidad P01 (nº int): ")
producto2 = input("Nombre Producto 02/02: ")
precio_p2 = input("Precio P02 (nº float): ")
cantidad_p2 = input("Cantidad P02 (nº int): ")

precio1 = float(precio_p1)
precio2 = float(precio_p2)
cantidad1 = int(cantidad_p1)
cantidad2 = int(cantidad_p2)

print("\nCalculando importes...\n")

dineroFinal_p1 = precio1 * cantidad1
dineroFinal_p2 = precio2 * cantidad2
dineroFinal = dineroFinal_p1 + dineroFinal_p2

print(f"Cliente: {cliente}")
print(f"Producto (01/02)-> {producto1} || (02/02)-> {producto2}")
print(f"Cantidad (01/02): {cantidad1} || (02/02): A{cantidad2}")
print(f"Precio Producto (01/02): {dineroFinal_p1}€ || (02/02): {dineroFinal_p2}€")
print(f"Coste total: {dineroFinal}€")
