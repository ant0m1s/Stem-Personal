print("Bienvenido a Lista de productos")

productos = ["Teclado", "Ratón", "Monitor"]
print(f"Lista de productos: {productos}")

productos.append("Webcam")
print(f"Lista actualizada: {productos}")

productos.remove("Ratón")
print("Ratón eliminado")

productos.sort()
print("Ordenamos por orden alfabético")

for indice, producto in enumerate(productos, start=1):
    print(f"{indice}. {producto}")