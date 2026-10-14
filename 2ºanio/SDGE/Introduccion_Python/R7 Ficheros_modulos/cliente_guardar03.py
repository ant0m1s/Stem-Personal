print("Bienvenido a Guardar clientes en texto")
alumnos = []
with open("clientes.txt", "w", encoding="utf-8") as archivo:
    for i in range(1, 4):
        print(f"\nCliente {i}:")
        nombreCliente = input("Nombre: ")
        ciudadCliente = input("Ciudad: ")
        archivo.write(f"\n{nombreCliente};{ciudadCliente}\n")
    
print("\n--- Contenido clientex.txt ---")
with open("clientes.txt", "r", encoding="utf-8") as archivo:
    contenido = archivo.read()
    print(contenido)