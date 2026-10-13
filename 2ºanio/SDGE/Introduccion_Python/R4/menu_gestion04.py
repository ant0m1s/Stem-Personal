print("Bienvenido a Menú de Gestion")
opcion_menu = None
iteracciones = 0

while opcion_menu != 0:
    opcion_menu = int(input("1. Alta ficticia\n2. Consulta ficticia\n3. Informe ficticio\n0. Salir\nElección: "))
    if opcion_menu == 1:
        print("Alta ficticia\n")
        iteracciones += 1
    elif opcion_menu == 2:
        print("Estas en Consulta ficticia\n")
        iteracciones += 1
    elif opcion_menu == 3:
        print("Informe ficticia\n")
        iteracciones += 1
    elif opcion_menu == 0:
            print("Saliendo del programa")
    else:
        print("Opción Incorrecta")
        
print(f"\nIterraciones: {iteracciones}")