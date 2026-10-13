print("Bienvenido a Resumen de precios")

precios = [99.99, 5.50, 10, 49.95, 70.99]
for indice, precio in enumerate(precios, start=1):
    print(f"{indice}. {precio}")

print("Calculando distintos resultados...\n")

cantidad = len(precios)
total = sum(precios)
minimo = min(precios)
maximo = max(precios)
media = total / cantidad

print(f"Cantidad de precios: {cantidad}")
print(f"Sumatoria de precios: {total}")
print(f"Precio más pequeño: {minimo}")
print(f"Precio más grande: {maximo}")
print(f"Media de Precios: {media}")