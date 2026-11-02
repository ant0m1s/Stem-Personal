class Trabajo:
    def __init__(self, cliente, horas, estado):
        self.cliente = cliente
        self.horas = horas
        self.estado = estado

class Reparacion(Trabajo):
    def __init__(self, cliente, horas, estado, precio_pieza):
        super().__init__(cliente, horas, estado)
        self.precio_pieza = precio_pieza

    def calcular_importe(self):
        return (self.horas * 25) + self.precio_pieza

class AsistenciaRemota(Trabajo):
    def __init__(self, cliente, horas, estado):
        super().__init__(cliente, horas, estado)

    def calcular_importe(self):
        return self.horas * 20