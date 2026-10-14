print("Bienvenido a Precio Valido")
precio= -1.0

while precio < 0:
    usuario = input("Introduce un precio (>= 0): ")
    try:
        precio_temp = float(usuario)
        if precio_temp >= 0:
            precio = precio_temp
        else:
            print("Error: El precio no puede ser negativo")
    except ValueError:
        print("Error: Introduce un número válido")
        
print(f"\nPrecio válido: {precio}€")