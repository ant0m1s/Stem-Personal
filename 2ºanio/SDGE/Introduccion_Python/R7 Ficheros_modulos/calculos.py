print("Bienvenido a Separar cálculos con Funciones")

def calcular_subtotal(precio, cantidad):
    subtotal = precio * cantidad
    return subtotal


def aplicar_descuento(importe, porcentaje): 
    descuento = importe *(porcentaje / 100)
    return importe - descuento