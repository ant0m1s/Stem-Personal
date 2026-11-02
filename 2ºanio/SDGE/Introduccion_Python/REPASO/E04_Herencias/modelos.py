class Trabajo:
    def __init__(self, codigo, cliente, horas, estado):
        self.codigo = codigo
        self.cliente = cliente
        self.horas = horas
        self.estado = estado


class Reparacion(Trabajo):
    def __init__(self, codigo, cliente, horas, estado, precio_pieza):
        super().__init__(codigo, cliente, horas, estado)
        self.precio_pieza = precio_pieza

    def calcular_importe(self):
        return (self.horas * 25) + self.precio_pieza

    def to_String(self):
        mensaje = ""
        mensaje += f"-----------Reparación----------\n"
        mensaje += f"Codigo {self.codigo}\n"
        mensaje += f"Cliente {self.cliente} ({self.horas} horas)\n"
        mensaje += f"Estado {self.estado} | Precio de Pieza {self.precio_pieza}€\n"
        mensaje += f"Subtotal {self.calcular_importe()}€\n"
        return mensaje


class AsistenciaRemota(Trabajo):
    def __init__(self, codigo, cliente, horas, estado):
        super().__init__(codigo, cliente, horas, estado)

    def calcular_importe(self):
        return self.horas * 20

    def to_String(self):
        mensaje = ""
        mensaje += f"-----------Asistencia Remota----------\n"
        mensaje += f"Codigo {self.codigo}\n"
        mensaje += f"Cliente {self.cliente} ({self.horas} horas)\n"
        mensaje += f"Estado {self.estado}\n"
        mensaje += f"Subtotal {self.calcular_importe()}€\n"
        return mensaje
