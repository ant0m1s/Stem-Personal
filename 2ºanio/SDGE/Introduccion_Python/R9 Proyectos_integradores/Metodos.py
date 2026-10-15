# ------------------- ONLY METODOS ---------------------
def pedir_numero(mensaje, limite1, limite2):
    while True:
        usuario = input(mensaje)
        try:
            temp = int(usuario)
            if limite1 <= temp <= limite2:
                usuario = temp
                return temp
            else:
                print("Error: Opción no debe ser negativa")
        except ValueError:
            print("Error: Introduce una opción permitida")


def buscar_nombre(catalogo, nombre=None):
    if not nombre:
        nombre = input("A quien buscas?: ").strip()
        cliente = None
        for c in catalogo:
            if nombre.lower() == (c["nombre"]).lower():
                cliente = c
    return cliente
