from calculos import calcular_subtotal, aplicar_descuento
print("Bienvenido a Haciendo calculos con Funciones")
bandera = False

precio = -1.0
while precio < 0:
    try:
        precio_temp = float(input("Dime cuanto vale?: "))
        if precio_temp >= 0:
            precio = precio_temp
        else:
            print("Error: El precio no puede ser negativo")
    except ValueError:
        print("Error: Introduce un número válido")
            
cantidad = 0
while cantidad < 1:
    try:
        temp = float(input("Cantidad de producto? (Min 1): "))
        if temp >= 1:
            cantidad = temp
        else:
            print("Error: La cantidad no puede ser negativa")
    except ValueError:
        print("Error: Introduce un número válido")
    
descuento = -1
while not (0 <= descuento <= 99):
    try:
        temp = int(input("Descuento (Ej: 20%): "))
        if 99 >= temp >= 0:
            descuento = temp
        else:
            print("Error: La descuento no puede ser negativa o superior a 99")
    except ValueError:
        print("Error: Introduce un número válido")
        
subtotal = calcular_subtotal(precio, cantidad)
dinero_descuento = aplicar_descuento(subtotal, descuento)

print(f"\nSubtotal: {subtotal}€")
print(f"\nDescuento aplicado: {descuento}€")
print(f"\nTotal a pagar: {dinero_descuento}€")