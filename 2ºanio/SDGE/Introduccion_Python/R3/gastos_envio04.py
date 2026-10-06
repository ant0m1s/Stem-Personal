print("Bienvenido a Gastos de envío")
destino_texto = input("Introduce un destino (PENINSULA, BALEARES o CANARIAS): ")
precio_texto = input("Total del pedido (nº float): ")

precio = float(precio_texto)
destino = destino_texto.strip().upper()
precio_original = precio
envio_canarias = 18
envio_baleares = 12
envio_peninsula = 6
mensaje = ""

if destino == "PENINSULA":
    if precio < 100:
        precio += envio_peninsula
elif destino == "CANARIAS":
    precio += envio_canarias 
elif destino == "BALEARES":
    precio += envio_baleares
else:
    precio = 0
    mensaje = "Destino Incorrecto"

print(f"Iniciando transacción\n----")
if mensaje == "":
    print(f"Destino: {destino_texto}")
    print(f"Precio sin envio: {precio_original}")
    print(f"Precio con envio: {precio}")
else:
    print(f"{mensaje}")
