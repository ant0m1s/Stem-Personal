print("Bienvenido a Catalogo con listas diccionario")
catalogo_producto = [
    {"nombre": "Plátano", "categoria": "Fruta", "precio": 3.99, "stock": 1},
    {"nombre": "Melón", "categoria": "Fruta", "precio": 4.99, "stock": 0},
    {"nombre": "M4", "categoria": "Arma", "precio": 43.99, "stock": 7},
    {"nombre": "Tomate", "categoria": "Fruta", "precio": 1.99, "stock": 4}
]

productos_disponibles = 0

for producto in catalogo_producto:
    if producto["stock"] > 0:
        productos_disponibles += 1
        print(f"Producto: {producto["nombre"]} | Categoría: {producto["categoria"]} | Precio: {producto["precio"]} | Stock: {producto["stock"]}")

print(f"\nTotal de productos disponibles: {productos_disponibles}")