print("Bienvenido a CSV local de productos")
import csv

productos = [
    {"nombre": "Teclado", "precio": 49.00, "stock": 15},
    {"nombre": "Televisión", "precio":139.00, "stock": 30},
    {"nombre": "Mando Xbox", "precio": 39.00, "stock": 2},
    {"nombre": "Monitor 24pp", "precio": 249.00, "stock": 8}
]

columnas = ["nombre", "precio", "stock"]

with open("productos.csv", mode="w", newline="", encoding="utf-8") as archivo:
    escritor = csv.DictWriter(archivo, fieldnames=columnas)
    escritor.writeheader()
    escritor.writerows(productos)
    
print("Archivo creado correctamente.\n")

print("--- Contenido de productos.csv ---\n")
with open("productos.csv", mode="r", encoding="utf-8") as archivo:
    lector = csv.reader(archivo)
    for fila in lector:
        print(fila)