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


def verificar_numero(usuario, limite1, limite2):
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
        cliente = None
        nombre = input("A quien buscas?: ").strip()
        for c in catalogo:
            if nombre.lower() == (c["nombre"]).lower():
                return c
    return cliente


def imprimir(c):
    return f"\nCliente: {c['nombre']}\nEmail: {c['email']}\nCiudad: {c['ciudad']}\nEstado: {c['activo']}\n"


def menu_respuesta(menu, pregunta, limite1, limite2):
    print(menu)
    usuario = pedir_numero(pregunta, limite1, limite2)
    return usuario
