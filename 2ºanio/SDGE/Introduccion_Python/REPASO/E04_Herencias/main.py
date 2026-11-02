from modelos import *
from persistencia import *
from Metodos import menu_respuesta, pedir_numero


def main():
    estados = ["Pendiente", "En Curso", "Finalizado"]
    FILTRAR = ["cliente", "tipo", "estado"]
    FILTRAR_ESTADO = ["Reparacion", "AsistenciaRemota"]
    lista_trabajos = cargar_trabajos_csv("trabajos.csv")
    if not lista_trabajos:
        lista_trabajos = [
            Reparacion("P01", "Antonio", 80, "Pendiente", 80),
            Reparacion("P02", "Miguel", 41, "Pendiente", 20),
            AsistenciaRemota("A01", "Manuel", 15, "En Curso"),
            AsistenciaRemota("A02", "Virginia", 15, "Finalizado"),
            AsistenciaRemota("A03", "Loli", 5, "Pendiente"),
        ]
    LIMITE1 = 1
    LIMITE2 = 99

    MSJ_MENU = """
    --- REPARACIONES ---
    1) Listar trabajos
    2) Alta reparación
    3) Alta remota
    4) Cambiar estado
    5) Filtrar
    6) Resumen por Estado
    7) Guardar en CSV
    0) Salir
    """

    bandera = True
    while bandera:
        print(MSJ_MENU)
        opcion = menu_respuesta("Elige una opción (0-7): ", 0, 7)
        match opcion:
            case 0:
                bandera = False
                print("Saliendo del programa...")
            case 1:
                print("\n----- CATÁLOGO --------")
                print(listar_productos(lista_trabajos))
            case 2:
                codigo = input("Introduce el codigo: ")
                cliente = input("Nombre del cliente: ")
                horas = pedir_numero("Horas: ", 1, 99)
                estado = pedir_palabra_lista(
                    "Estado (Pendiente, En Curso, Finalizado): ", estados
                )
                precio_p = pedir_numero("Precio de la reparación?: ", LIMITE1, LIMITE2)
                repa_u = Reparacion(codigo, cliente, horas, estado, precio_p)
                lista_trabajos.append(repa_u)
                print(repa_u.to_String())
            case 3:
                codigo = input("Introduce el codigo: ")
                cliente = input("Nombre del cliente: ")
                horas = pedir_numero("Horas: ", 1, 99)
                correcto_e = True
                while correcto_e:
                    estado = input("Estado (Pendiente, En Curso, Finalizado): ")
                    if comprobar_lista(estado, estados):
                        correcto_e = False
                remota_u = AsistenciaRemota(codigo, cliente, horas, estado)
                lista_trabajos.append(remota_u)
                print(remota_u.to_String())
            case 4:
                print("\n----- CATÁLOGO --------")
                print(listar_productos(lista_trabajos))
                codigo_buscar = input("Elige un codigo: ")
                trabajo = buscar_trabajo_codigo(lista_trabajos, codigo_buscar)
                if trabajo != None:
                    print(f"Estado actual: {trabajo.estado}")
                    estado = pedir_palabra_lista(
                        "Cambiar Estado (Pendiente, En Curso, Finalizado): ", estados
                    )
                    if trabajo.estado.lower() == estado.lower():
                        print("Mismo estado anterior")
                    else:
                        trabajo.estado = estado
            case 5:
                palabra_filtrar = pedir_palabra_lista(
                    "Filtrar por (Cliente, tipo o estado): ", FILTRAR
                )
                if palabra_filtrar == "cliente":
                    cliente_filtrar = input("Nombre del cliente para filtrar: ")
                    print(filtrar_productos_cliente(lista_trabajos, cliente_filtrar))
                elif palabra_filtrar == "tipo":
                    tipo_filtrar = pedir_palabra_lista(
                        "Reparacion o AsistenciaRemota: ", FILTRAR_ESTADO
                    )
                    print(filtrar_productos_tipo(lista_trabajos, tipo_filtrar))
                else:
                    tipo_estado = pedir_palabra_lista(
                        "Filtrar por estado (Pendiente, En Curso, Finalizado): ",
                        estados,
                    )
                    print(filtrar_productos_estado(lista_trabajos, tipo_estado))
            case 6:
                print("\n----- Trabajos ordenados por Estado --------")
                print(listar_productos_estado(lista_trabajos))
            case 7:
                guardado = guardar_trabajos_csv("trabajos.csv", lista_trabajos)
                if guardado:
                    print("Trabajos guardados correctamente")
                else:
                    print("Error al guardar en CSV")
    pass


if __name__ == "__main__":
    main()
