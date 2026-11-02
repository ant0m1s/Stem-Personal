from modelos import *

import csv
import os

DIR = os.path.dirname(os.path.abspath(__file__))


def guardar_trabajos_csv(nombre_archivo, lista):
    """
    Guarda los trabajos en el archivo CSV usando DictWriter.
    Incluye la columna 'tipo' para poder distinguir la clase al cargar.
    Retorna True si todo fue bien o False si hubo un error.
    """
    ruta = os.path.join(DIR, nombre_archivo)
    campos = ["tipo", "codigo", "cliente", "horas", "estado", "precio_pieza"]
    filas = []

    for t in lista:
        es_repa = isinstance(t, Reparacion)
        filas.append(
            {
                "tipo": type(t).__name__,
                "codigo": t.codigo,
                "cliente": t.cliente,
                "horas": t.horas,
                "estado": t.estado,
                "precio_pieza": t.precio_pieza if es_repa else 0,
            }
        )

    try:
        with open(ruta, mode="w", newline="", encoding="utf-8") as f:
            writer = csv.DictWriter(f, fieldnames=campos)
            writer.writeheader()
            writer.writerows(filas)
        return True
    except Exception:
        return False


def cargar_trabajos_csv(nombre_archivo):
    """
    Lee el archivo CSV e instancia Reparacion o AsistenciaRemota
    dependiendo del valor de la columna 'tipo'.
    Retorna la lista de objetos cargados (o vacía si no existe el archivo).
    """
    ruta = os.path.join(DIR, nombre_archivo)
    if not os.path.exists(ruta):
        return []

    lista_cargada = []
    try:
        with open(ruta, mode="r", encoding="utf-8") as f:
            lector = csv.DictReader(f)
            for f in lector:
                tipo = f["tipo"].strip()
                codigo = f["codigo"].strip()
                cliente = f["cliente"].strip()
                horas = float(f["horas"])
                estado = f["estado"].strip()

                if tipo == "Reparacion":
                    precio_pieza = (
                        float(f["precio_pieza"]) if f["precio_pieza"] else 0.0
                    )
                    obj = Reparacion(codigo, cliente, horas, estado, precio_pieza)
                else:
                    obj = AsistenciaRemota(codigo, cliente, horas, estado)

                lista_cargada.append(obj)
    except Exception:
        return []

    return lista_cargada


def listar_productos(lista):
    if len(lista) >= 1:
        mensaje = ""
        for l in lista:
            if isinstance(l, Reparacion):
                mensaje += f"({l.estado}) Cliente {l.cliente} | Horas: {l.horas} | Precio pieza:  {l.precio_pieza}€\nImporte:  {l.calcular_importe()}€.\n"
            else:
                mensaje += f"({l.estado}) Cliente {l.cliente} | Horas: {l.horas}\nImporte:  {l.calcular_importe()}€."
        return mensaje
    else:
        mensaje = "Lista Vacía"
        return mensaje


def listar_productos_estado(lista):
    if len(lista) >= 1:
        mensaje_pen = "----------Pendientes-----------\n"
        mensaje_cur = "----------En curso--------\n"
        mensaje_fi = "----------Finalizados--------\n"
        total = 0
        for l in lista:
            if isinstance(l, Reparacion):
                total += l.calcular_importe()
                if l.estado == "Pendiente":
                    mensaje_pen += f"({l.estado}) Cliente {l.cliente} | Horas: {l.horas} | Precio pieza:  {l.precio_pieza}€\nImporte:  {l.calcular_importe()}€.\n"
                elif l.estado == "En curso":
                    mensaje_cur += f"({l.estado}) Cliente {l.cliente} | Horas: {l.horas} | Precio pieza:  {l.precio_pieza}€\nImporte:  {l.calcular_importe()}€.\n"
                else:
                    mensaje_fi += f"({l.estado}) Cliente {l.cliente} | Horas: {l.horas} | Precio pieza:  {l.precio_pieza}€\nImporte:  {l.calcular_importe()}€.\n"
            else:
                total += l.calcular_importe()
                if l.estado == "Pendiente":
                    mensaje_pen += f"({l.estado}) Cliente {l.cliente} | Horas: {l.horas}\nImporte: {l.calcular_importe()}€.\n"
                elif l.estado == "En curso":
                    mensaje_cur += f"({l.estado}) Cliente {l.cliente} | Horas: {l.horas}\nImporte: {l.calcular_importe()}€.\n"
                else:
                    mensaje_fi += f"({l.estado}) Cliente {l.cliente} | Horas: {l.horas}\nImporte: {l.calcular_importe()}€.\n"
        return mensaje_pen + mensaje_cur + mensaje_fi + f"Total trabajos {total}€"
    else:
        mensaje = "Lista Vacía"
        return mensaje


def filtrar_productos_tipo(lista, tipo):
    if len(lista) >= 1:
        mensaje = f"----------Filtrado por {tipo}-----------\n"
        total = 0
        encontrados = False
        for l in lista:
            if type(l).__name__.lower() == tipo.strip().lower():
                encontrados = True
                total += l.calcular_importe()
                mensaje += l.to_String()
        if not encontrados:
            return f"No se encontraron trabajos del tipo {tipo}"
        else:
            return mensaje + f"Total trabajos {total}€"
    else:
        mensaje = "Lista Vacía"
        return mensaje


def filtrar_productos_cliente(lista, cliente):
    mensaje = f"-----------Filtrado por nombre {cliente}----------------\n"
    total = 0
    if len(lista) >= 1:
        for l in lista:
            if l.cliente.strip().lower() == cliente.strip().lower():
                total += l.calcular_importe()
                mensaje += l.to_String()
    return mensaje + f"Total trabajos {total}€"


def filtrar_productos_estado(lista, estado):
    mensaje = f"-----------Filtrado por {estado}----------------\n"
    total = 0
    if len(lista) >= 1:
        for l in lista:
            if l.estado.strip().lower() == estado.strip().lower():
                total += l.calcular_importe()
                mensaje += l.to_String()
    return mensaje + f"Total trabajos {total}€"


def buscar_trabajo_codigo(lista, codigo):
    for l in lista:
        if l.codigo.strip().lower() == codigo.strip().lower():
            return l


def pedir_palabra_lista(mensaje, lista):
    while True:
        estado = input(mensaje)
        valida = comprobar_lista(estado, lista)
        if valida:
            return valida


def comprobar_lista(palabra, lista):
    for l in lista:
        if palabra.strip().lower() == l.strip().lower():
            return l
    return None
