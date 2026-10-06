print("Bienvenido a Clasificación de Pedido")
precio_texto = input("Precio del pedido (nº float): ")
premium_texto = input("Eres premium? (S/N): ")
precio = float(precio_texto)
mensaje_mostrar = ""
es_premium = premium_texto == "s" or premium_texto == "S"

if precio > 500 or (es_premium and precio > 250):
    mensaje_mostrar = "Pedido Prioritario"
else:
    mensaje_mostrar = "Pedido Normal"
    
print(f"Precio del Pedido: {precio}")
print(f"Estado Actual: {mensaje_mostrar}")