from modelos import Producto, LineaVenta, Venta
from persistencia import (
    cargar_productos_csv,
    guardar_productos_csv,
    guardar_ventas_csv,
    cargar_ventas_csv,
    listar_productos,
    buscar_p_codigo,
)
from Metodos import menu_respuesta, pedir_numero


def main():
    lista_productos = cargar_productos_csv("productos.csv")
    lista_ventas = cargar_ventas_csv("ventas.csv", lista_productos)
    venta_actual = None

    MSJ_MENU = """
--- FLORISTERÍA LILA ---
1) Ver productos
2) Alta producto
3) Reponer stock
4) Nueva venta / Añadir líneas
5) Cerrar venta actual
6) Consultar ventas y acumulado
7) Guardar en CSV
0) Salir
"""

    bandera = True
    while bandera:
        opcion = menu_respuesta(MSJ_MENU, "Elige una opción (0-7): ", 0, 7)
        match opcion:
            case 0:
                bandera = False
                print("Saliendo del programa...")

            case 1:  # Ver productos
                print("\n--- CATÁLOGO DE PRODUCTOS ---")
                print(listar_productos(lista_productos))

            case 2:  # Alta de producto
                codigo = input("Código del producto: ").strip().upper()
                if buscar_p_codigo(lista_productos, codigo):
                    print("Error: Ya existe un producto con ese código.")
                    continue
                nombre = input("Nombre del producto: ").strip()
                cat = input("Categoría: ").strip()
                precio = pedir_numero("Precio (1-999): ", 1, 999)
                stock = pedir_numero("Stock inicial (1-999): ", 1, 999)
                lista_productos.append(Producto(codigo, nombre, cat, precio, stock))
                print(f"Producto '{nombre}' registrado correctamente.")

            case 3:  # Reponer
                print(listar_productos(lista_productos))
                cod = input("Código del producto a reponer: ")
                p = buscar_p_codigo(lista_productos, cod)
                if p:
                    print(f"Stock actual de {p.nombre}: {p.stock}")
                    unidades = pedir_numero("Unidades a añadir: ", 1, 500)
                    p.cambiar_stock(p.stock + unidades)
                    print(f"Nuevo stock de {p.nombre}: {p.stock}")
                else:
                    print("Producto no encontrado.")

            case 4:  # Nueva venta / Añadir líneas
                if venta_actual is None or venta_actual.cerrada:
                    print("\nNo hay ninguna venta abierta.")
                    cliente = input("Introduce nombre del cliente: ").strip()
                    num_venta = len(lista_ventas) + 1
                    venta_actual = Venta(num_venta, cliente)
                    lista_ventas.append(venta_actual)
                    print(f"Venta #{venta_actual.numero} iniciada para {cliente}.")

                # Añadir líneas
                print(listar_productos(lista_productos))
                cod = input("Código del producto a comprar (o 'fin' para salir): ")
                if cod.strip().lower() == "fin":
                    continue
                prod = buscar_p_codigo(lista_productos, cod)
                if prod:
                    if prod.stock <= 0:
                        print("No hay existencias de este producto.")
                        continue
                    cant = pedir_numero(
                        f"Cantidad a comprar (Máx {prod.stock}): ", 1, prod.stock
                    )
                    prod.cambiar_stock(prod.stock - cant)
                    linea = LineaVenta(prod, cant, prod.precio)
                    venta_actual.ingresar_linea(linea)
                    print(f"Línea añadida. Subtotal: {linea.subtotal:.2f}€")
                else:
                    print("Código de producto inexistente.")

            case 5:  # Cerrar venta actual
                if venta_actual and not venta_actual.cerrada:
                    venta_actual.cerrada = True
                    print(venta_actual.to_String())
                    print("Venta cerrada y cobrada con éxito.")
                    venta_actual = None
                else:
                    print("No hay ninguna venta abierta pendiente de cerrar.")

            case 6:  # Consultar ventas e importe acumulado
                if not lista_ventas:
                    print("No hay ventas registradas.")
                    continue
                acumulado = 0.0
                print("\n--- HISTORIAL DE VENTAS ---")
                for v in lista_ventas:
                    print(v.to_String())
                    if v.cerrada:
                        acumulado += v.total()
                print(
                    f"\nIMPORTE TOTAL ACUMULADO (Ventas cerradas): {acumulado:.2f}€\n"
                )

            case 7:  # Guardar
                print(guardar_productos_csv("productos.csv", lista_productos))
                print(guardar_ventas_csv("ventas.csv", lista_ventas))


if __name__ == "__main__":
    main()
