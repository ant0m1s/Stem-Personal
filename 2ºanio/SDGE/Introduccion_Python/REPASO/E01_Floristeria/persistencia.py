from modelos import *

import csv
import os

DIRECTORIO = os.path.dirname(os.path.abspath(__file__))


def cargar_productos_csv(nombre_archivo="productos.csv"):
    lista_cargada = []
    ruta_completa = os.path.join(DIRECTORIO, nombre_archivo)

    try:
        with open(ruta_completa, mode="r", encoding="utf-8") as archivo:
            lector = csv.DictReader(archivo)
            for row in lector:
                p = Producto(
                    row['codigo'],
                    row['nombre'],
                    row['categoria'],
                    float(row['precio']),
                    int(row['stock']),
                )
                lista_cargada.append(p)
        print(f"Se cargaron {len(lista_cargada)} productos correctamente")
    except FileNotFoundError:
        print(f"Archivo {ruta_completa} no existe. Cargando catálogo inicial...")
        lista_cargada = [
            Producto("FLO01", "Rosas", "Flores", 3.50, 15),
            Producto("FLO02", "Tulipanes", "Flores", 2.25, 24),
            Producto("FLO03", "Orquídeas", "Flores", 18.0, 4),
        ]
    return lista_cargada


def guardar_productos_csv(nombre_archivo, lista):
    ruta_completa = os.path.join(DIRECTORIO, nombre_archivo)
    try:
        # newline="" es necesario al usar el módulo csv para evitar líneas en blanco extra en Windows
        with open(ruta_completa, mode="w", newline="", encoding="utf-8") as archivo:
            escritor = csv.writer(archivo)
            # Escribir cabecera
            escritor.writerow(["codigo", "nombre", "categoria", "precio", "stock"])
            # Escribir filas
            for p in lista:
                escritor.writerow([p.codigo, p.nombre, p.categoria, p.precio, p.stock])
        return f"Archivo '{nombre_archivo}' guardado con éxito."
    except Exception as e:
        return f"Error al guardar el archivo: {e}"


def guardar_ventas_csv(nombre_archivo, lista):
    ruta_completa = os.path.join(DIRECTORIO, nombre_archivo)
    try:
        with open(ruta_completa, mode="w", newline="", encoding="utf-8") as archivo:
            escritor = csv.writer(archivo)
            escritor.writerow(["numero", "cliente", "codigo_p", "cantidad", "precio_u"])
            for v in lista:
                if not v.lista_linea:
                    escritor.writerow([v.numero, v.cliente, v.cerrada, "", 0, 0.0])
                else:
                    for l in v.lista_lineas:
                        escritor.writerow(
                            [
                                v.numero,
                                v.cliente,
                                v.cerrada,
                                l.Producto.codigo,
                                l.cantidad,
                                l.precio_unitario,
                            ]
                        )
        return f"Ventas guardadas con éxito en '{nombre_archivo}'"
    except Exception as e:
        return f"Error al guardar ventas"


def cargar_ventas_csv(nombre_archivo, lista_productos):
    ruta = os.path.join(DIRECTORIO, nombre_archivo)
    ventas_dict = {}
    if not os.path.exists(ruta):
        return []

    try:
        with open(ruta, mode="r", encoding="utf-8") as f:
            lector = csv.DictReader(f)
            for row in lector:
                num = int(row["numero"])
                if num not in ventas_dict:
                    cerrada = row["cerrada"].strip().lower() == "true"
                    ventas_dict[num] = Venta(num, row["cliente"], cerrada=cerrada)

                cod = row["codigo_p"].strip()
                if cod:
                    prod = buscar_p_codigo(lista_productos, cod)
                    if prod:
                        linea = LineaVenta(
                            prod, int(row["cantidad"]), float(row["precio_u"])
                        )
                        ventas_dict[num].ingresar_linea(linea)
        print(f"Se cargaron {len(ventas_dict)} ventas correctamente.")
        return list(ventas_dict.values())
    except Exception as e:
        print(f"Error al cargar ventas: {e}")
        return []


def listar_productos(lista):
    if len(lista) >= 1:
        mensaje = ""
        for l in lista:
            mensaje += f"Codigo {l.codigo} | Nombre: {l.nombre} | Categoría {l.categoria}\nPrecio: {l.precio} | Stock: {l.stock} unidades.\n"
        return mensaje
    else:
        mensaje = "Lista Vacía"
        return mensaje


def listar_ventas(lista):
    if len(lista) >= 1:
        mensaje = ""
        for l in lista:
            mensaje += f"Número {l.numero} | Cliente: {l.cliente}\n"
        return mensaje
    else:
        mensaje = "Lista Vacía"
        return mensaje


def buscar_p_codigo(lista, codigo):
    for p in lista:
        if codigo.stip().lower() == p.codigo.strip().lower():
            return p


def buscar_v_numero(lista, numero):
    for v in lista:
        if numero == v.numero:
            return v
