print("Bienvenido a Serie de Códigos de Pedido")
numero_pedidos = input("Número de pedidos (nº int): ")
pedidos = int(numero_pedidos)
if pedidos < 1:
    print(f"Número de pedidos no valido: {pedidos}")
else:
    for i in range(1, pedidos + 1):
        print(f"PED-{i:03d}")