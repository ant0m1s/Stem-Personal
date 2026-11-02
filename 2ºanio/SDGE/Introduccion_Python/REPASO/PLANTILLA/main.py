from modelos import *
from persistencia import *
from Metodos import menu_respuesta, pedir_numero


def main():
    estados = ["Pendiente", "En Curso", "Finalizado"]
    lista_trabajos = [Reparacion("Antonio", 80, "Pendiente", 80), Reparacion("Miguel", 41, "Pendiente", 20),]

    MSJ_MENU = """
    --- REPARACIONES ---
    1) Listar trabajos
    2) Alta reparación
    3) Alta remota
    4) Cambiar estado
    5) Buscar/filtrar
    6) Resumen
    7) Guardar en CSV
    0) Salir
    """

    bandera = True
    while bandera:
        opcion: menu_respuesta("Elige una opción (0-7): ", 0, 7)
        match opcion:
            case 0:
                bandera = False
                print("Saliendo del programa...")
            case 1:
                print("\n----- CATÁLOGO --------")
                print()
            case 2:
                pass
            case 3:
                pass
            case 4:
                pass
            case 5:
                pass
            case 6:
                pass
            case 7:
                pass
    pass


if __name__ == "__main__":
    main()
