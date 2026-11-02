def listar_productos(lista, tipo):
    if len(lista) >= 1:
        mensaje = ""
        for l in lista:
            if tipo == 1:
                mensaje += f"({l.estado}) Cliente {l.cliente} | Horas: {l.horas} | Precio pieza:  {l.categoria}€\nImporte:  {l.calcular_importe()}€."
            else:
                mensaje += f"({l.estado}) Cliente {l.cliente} | Horas: {l.horas}\nImporte:  {l.calcular_importe()}€."
        return mensaje
    else:
        mensaje = "Lista Vacía"
        return mensaje

def comprobar_lista(palabra, lista):
    for l in lista:
        if palabra.strip().lower() == l.strip().lower():
            return palabra
