print("Bienvenido al Mini CRM local")
from Metodos import *


# ---------------FUNCIONES-----------------
def mostrar_catalogo(catalogo):
    mensaje = ""
    if len(catalogo) == 0:
        mensaje = "Catálogo vacío."
        return mensaje
    else:
        print("Imprimiendo lista:\n")
        for linea in catalogo:
            mensaje += f"Cliente: {linea['nombre']}\nEmail: {linea['email']}\nCiudad: {linea['ciudad']}\nEstado: {linea['activo']}\n----------------------\n"
        return mensaje


def cambiar_estado(linea, estado):
    mensaje = ""
    # Darse de Alta
    if estado:
        if linea["activo"]:
            mensaje = f"{linea['nombre']}: Estaba dado de alta..."
        else:
            linea["activo"] = True
            mensaje = f"{linea['nombre']}: Dado de alta correctamente"
    # Darse de Baja
    else:
        if not linea["activo"]:
            mensaje = f"{linea['nombre']}: Estaba dado de baja..."
        else:
            linea["activo"] = False
            mensaje = f"{linea['nombre']}: Dado de baja correctamente\n"
    return mensaje


def guardar_csv(catalogo, archivo):
    mensaje = ""
    with open(archivo, "w", encoding="utf-8") as archivo:
        archivo.write("Nombre,Email,Ciudad,Estado\n")
        for c in catalogo:
            linea_csv = f"{c['nombre']},{c['email']},{c['ciudad']},{c['activo']}\n"
            archivo.write(linea_csv)
        mensaje = "Archivo 'clientes.csv' guardado con éxito\n"
    return mensaje


def menu_respuesta(menu):
    print(menu)
    usuario = pedir_numero("Elige una opción (0-5): ", 0, 5)
    return usuario


# ------------DATOS PRUEBA--------------
catalogo_clientes = []
catalogo_clientes.append(
    {
        "nombre": "Antonio",
        "email": "mister@gmail.com",
        "ciudad": "Motril",
        "activo": False,
    }
)
catalogo_clientes.append(
    {
        "nombre": "Marino",
        "email": "thesea@gmail.com",
        "ciudad": "Motril",
        "activo": False,
    }
)
catalogo_clientes.append(
    {
        "nombre": "Juande",
        "email": "elamo@gmail.com",
        "ciudad": "Motril",
        "activo": False,
    }
)
catalogo_clientes.append(
    {
        "nombre": "Mabululu",
        "email": "gamez@gmail.com",
        "ciudad": "Cartagena",
        "activo": True,
    }
)

# ------------MAIN--------------
mensaje_menu = "MENÚ OPCIONES\n0. Salir\n1. Listar Clientes\n2. Darse de Alta\n3. Darse de Baja\n4. Buscar por Nombre\n5. Guardar en CSV"
bandera = True
while bandera:
    usuario = menu_respuesta(mensaje_menu)

    # --------------Salir
    if usuario == 0:
        print("Terminando programa...")
        bandera = False

    # --------------Listar Clientes
    elif usuario == 1:
        print(mostrar_catalogo(catalogo_clientes))

    # --------------Darse de Alta
    elif usuario == 2:
        mensaje_catalogo = mostrar_catalogo(catalogo_clientes)
        if mensaje_catalogo == "Catalógo vacío.":
            print(mensaje_catalogo)
        else:
            print(mensaje_catalogo)
            linea = buscar_nombre(catalogo_clientes)
            if linea is not None:
                estado = True
                print(cambiar_estado(linea, estado))
            else:
                print(f"{linea}: No encontrado")

    # --------------Darse de Baja
    elif usuario == 3:
        mensaje_catalogo = mostrar_catalogo(catalogo_clientes)
        if mensaje_catalogo == "Catalógo vacío.":
            print(mensaje_catalogo)
        else:
            print(mensaje_catalogo)
            linea = buscar_nombre(catalogo_clientes)
            if linea is not None:
                estado = False
                print(cambiar_estado(linea, estado))
            else:
                print(f"{linea}: No encontrado")

    # --------------Buscar por Nombre
    elif usuario == 4:
        mensaje_catalogo = mostrar_catalogo(catalogo_clientes)
        if mensaje_catalogo == "Catalógo vacío.":
            print(mensaje_catalogo)
        else:
            print(mensaje_catalogo)
            linea = buscar_nombre(catalogo_clientes)
            mensaje = ""
            if linea is not None:
                mensaje = f"Cliente: {linea['nombre']}\nEmail: {linea['email']}\nCiudad: {linea['ciudad']}\nEstado: {linea['activo']}"
            else:
                mensaje = "No se encontró ningún cliente con ese nombre"
            print(mensaje)

    # --------------Guardar en CSV
    elif usuario == 5:
        print("Guardando catálogo CSV\n")
        mensaje_catalogo = mostrar_catalogo(catalogo_clientes)
        if mensaje_catalogo == "Catalógo vacío.":
            print(mensaje_catalogo)
        else:
            mensaje = guardar_csv(catalogo_clientes, "clientes.txt")
            print(mensaje)
