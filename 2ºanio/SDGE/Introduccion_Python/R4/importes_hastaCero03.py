print("Bienvenido a Ventas de una semana")
iteracciones = 0
total_importe = 0
importe_dia = 1

while importe_dia != 0:
    importe_dia = int(input(f"Suma el importe: "))
    iteracciones += 1
    total_importe += importe_dia

print(f"\nIterraciones: {iteracciones - 1}\nTotal importado: {total_importe}")