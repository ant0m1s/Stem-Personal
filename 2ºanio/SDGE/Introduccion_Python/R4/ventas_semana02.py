print("Bienvenido a Ventas de una semana")
dias_semana = 7
importe_diario = 0
importe_total = 0

for i in range(1, dias_semana + 1):
    importe_diario = int(input(f"Ventas dia {i}: "))
    importe_total += importe_diario
    print(f"Dia {i}: {importe_diario}\n")
    
print(f"Total Semanal: {importe_total}")
    