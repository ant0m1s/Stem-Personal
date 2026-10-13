print("Bienvenido a Tabla de previsiones")
opcion_menu = None
iteracciones = 0
numero_productos = int(input("Nº de Productos: "))
numero_meses = int(input("Nº de Meses: "))

if numero_productos <= 0:
    print(f"Número de productos incorrectos {numero_productos}")
else:
    if numero_meses <= 0:
        print(f"Número de meses incorrectos {numero_meses}")
    else:
        for prod in range(1, numero_productos + 1):
            for mes in range(1, numero_meses + 1):
                print(f"Producto {prod} - Mes {mes}")
                iteracciones+= 1
    print(f"Finalizando programa...\nNúmero iterraciones: {iteracciones}")
                



