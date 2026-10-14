print("Bienvenido a Función leer_entero")
def leer_entero(mensaje, minimo, maximo):
    while True:
        try:
            valor = int(input(mensaje))
            if minimo <= valor <= maximo:
                return valor
            else:
                print(f"Error: Debe estar entre {minimo} y {maximo}")
        except ValueError:
            print("Error: Debes introducir un número entero válido")
            

cantidad = leer_entero("Introduce una cantidad (1-100): ", 1, 100)
print(f"\nCantidad válida introducida = {cantidad}")