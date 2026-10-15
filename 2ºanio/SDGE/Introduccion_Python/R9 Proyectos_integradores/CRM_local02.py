print("Bienvenido al Mini CRM local")
from Metodos import *


# ---------------FUNCIONES-----------------
def comprobar_lista_vacia(lista):
    if len(lista) == 0:
        return False
    else:
        return True


def mostrar_catalogo(catalogo):
    bandera = comprobar_lista_vacia(catalogo)
    if bandera:
        mensaje = "Imprimiendo lista:\n"
        for linea in catalogo:
            mensaje += imprimir(linea)
        return mensaje
    else:
        return None


def cambiar_estado(estado):
    mensaje_catalogo = mostrar_catalogo(catalogo_clientes)
    if mensaje_catalogo == "Catalógo vacío.":
        return mensaje_catalogo
    else:
        print(mensaje_catalogo)
        linea = buscar_nombre(catalogo_clientes)
        if linea is not None:
            if estado == 1:
                if linea["activo"]:
                    mensaje = f"{linea['nombre']}: Estaba dado de alta...\n"
                else:
                    linea["activo"] = True
                    mensaje = f"{linea['nombre']}: Dado de alta correctamente\n"
            else:
                if not linea["activo"]:
                    mensaje = f"{linea['nombre']}: Estaba dado de baja...\n"
                else:
                    linea["activo"] = False
                    mensaje = f"{linea['nombre']}: Dado de baja correctamente\n"
        else:
            mensaje = f"{linea}: Cliente encontrado"
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


def menu_respuesta(menu, pregunta, limite1, limite2):
    print(menu)
    usuario = pedir_numero(pregunta, limite1, limite2)
    return usuario


def guardar_c(nombre, email, ciudad, activo):
    if activo == "S":
        activo = True
    else:
        activo = False

    catalogo_clientes.append(
        {"nombre": nombre, "email": email, "ciudad": ciudad, "activo": activo}
    )
    return "Añadido correctamente"


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
mensaje_menu = "MENÚ OPCIONES\n0. Salir\n1. Listar Clientes\n2. Crear Cliente\n3. Cambiar de estado\n4. Buscar por Nombre\n5. Guardar en CSV"
mensaje_estado = "MENÚ ESTADO\n0. Salir\n1. Darse de alta\n2. Darse de baja"
bandera = True
while bandera:
    usuario = menu_respuesta(mensaje_menu, "Elige una opción (0-5): ", 0, 5)
    # --------------Salir
    if usuario == 0:
        print("Terminando programa...")
        bandera = False

    # --------------Listar Clientes
    elif usuario == 1:
        print(mostrar_catalogo(catalogo_clientes))

    # --------------Crear Cliente
    elif usuario == 2:
        print("MENÚ CREAR CLIENTE")
        nombre = input("Nombre del Cliente: ")
        email = input("Ingresa email: ")
        ciudad = input("Tu ciudad: ")
        estado = ""
        while estado not in ("S", "N"):
            estado = input("Estas dado de alta (S/N): ").strip().upper()
        print(guardar_c(nombre, email, ciudad, estado))

    # --------------Cambiar Estado (Alta o Baja)
    elif usuario == 3:
        ele_usu = menu_respuesta(mensaje_estado, "Opción: ", 0, 2)
        if ele_usu == 1:
            print(cambiar_estado(ele_usu))
        elif ele_usu == 2:
            print(cambiar_estado(ele_usu))
        else:
            print("Saliendo...")

    # --------------Buscar por Nombre
    elif usuario == 4:
        mensaje_catalogo = mostrar_catalogo(catalogo_clientes)
        if mostrar_catalogo(catalogo_clientes):
            print(mensaje_catalogo)
            cliente = buscar_nombre(catalogo_clientes)
            if cliente is not None:
                print(imprimir(cliente))
            else:
                print("Cliente no encontrado")
        else:
            print(mensaje_catalogo)

    # --------------Guardar en CSV
    elif usuario == 5:
        print("Guardando catálogo CSV\n")
        mensaje_catalogo = mostrar_catalogo(catalogo_clientes)
        if mensaje_catalogo == "Catalógo vacío.":
            print(mensaje_catalogo)
        else:
            mensaje = guardar_csv(catalogo_clientes, "clientes.txt")
            print(mensaje)
