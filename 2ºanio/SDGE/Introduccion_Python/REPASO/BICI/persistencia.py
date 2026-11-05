"""UD1 · PT1 · Persistencia CSV. Sin print() ni input()."""

import csv

from modelos import Bicicleta

CAMPOS = ["codigo", "propietario", "modelo", "valor", "estado"]


def cargar_bicicletas(ruta):
    """Lee el CSV y devuelve una lista de objetos Bicicleta.

    Convierte el valor a número y respeta el estado del archivo.
    La primera línea contiene las cabeceras indicadas en CAMPOS.
    """
    # TODO: csv.DictReader, with open(..., encoding="utf-8", newline="")
    bicicletas = []
    with open(ruta, mode="r", encoding="utf-8", newline="") as archivo:
        lector = csv.DictReader(archivo)
        if lector.fieldnames != CAMPOS:
            raise ValueError("Las columnas del CSV no tienen el formato esperado")
        
        for fila in lector:
            bici = Bicicleta(
                fila["codigo"].strip(),
                fila["propietario"].strip(),
                fila["modelo"].strip(),
                fila["valor"],
                fila["estado"].strip(),
            )
            bicicletas.append(bici)
    return bicicletas


def guardar_bicicletas(ruta, bicicletas):
    """Escribe el CSV con cabecera y todas las bicicletas; devuelve True al guardar."""
    # TODO: csv.DictWriter, with open(..., encoding="utf-8", newline="")
    filas = []
    for b in bicicletas:
        filas.append(
            {
                "codigo": b.codigo,
                "propietario": b.propietario,
                "modelo": b.modelo,
                "valor": b.valor,
                "estado": b.estado,
            }
        )

    with open(ruta, mode="w", encoding="utf-8", newline="") as f:
        escritor = csv.DictWriter(f, fieldnames=CAMPOS)
        escritor.writeheader()
        escritor.writerows(filas)
    return True
