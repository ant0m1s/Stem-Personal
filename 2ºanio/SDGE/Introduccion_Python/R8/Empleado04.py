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
        print(f"--- Ficha Técnica ---")
        print(f"Nombre: {self.nombre}")
        print(f"Salario: {self.salario}")
        print(f"Ventas: {self.ventas}")
        print(f"Bonus: {bonus}")
        print(f"Total: {total}")
        

comercial1 = Comercial("Mateo", 1500.99, 10000)
comercial1.resumen()