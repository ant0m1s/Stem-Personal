class Empleado:
    def __init__(self, nombre, salario):
        self.nombre = nombre
        self.salario = salario
        
class Comercial(Empleado):
    def __init__(self, nombre, salario, ventas):
        super().__init__(nombre, salario)
        self.ventas = ventas
        
    def calcular_bonus(self):
        return self.ventas * 0.05
    
    def resumen(self):
        bonus = self.calcular_bonus()
        total = bonus + self.ventas
        mensaje = f"--- Ficha Técnica ---\nNombre: {self.nombre}\nSalario: {self.salario}\nVentas: {self.ventas}\nBonus: {bonus}\nTotal: {total}"
        return mensaje
        

comercial1 = Comercial("Mateo", 1500.99, 10000)
print(comercial1.resumen())