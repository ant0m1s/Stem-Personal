print("Bienvenido a Categorías únicas y Búsqueda de texto")

categorias = ["Informática", "Informática", "Periféricos", "Accesorios", "Audio", "Periféricos"]
productos = [
    {"nombre": "Ordenador", "categoria": "Informática"},
    {"nombre": "Ratón", "categoria": "Periféricos"},
    {"nombre": "Teclado", "categoria": "Periféricos"},
    {"nombre": "Cascos", "categoria": "Audio"},
    {"nombre": "Alfombrilla", "categoria": "Accesorios"}
]

categorias_unicas = set(categorias)
print("Categorías únicas:")
for cate in categorias_unicas:
    print(f"- {cate}")
    
busqueda = input("\nIntroduce una busqueda: ")

print(f"\nResultados para {busqueda}")
encontrados = 0

for p in productos:
    if busqueda.lower() in p["categoria"].lower():
        print(f"Nombre: {p["nombre"]} | Categoría: {p['categoria']}")
        encontrados += 1
        
if encontrados == 0:
    print("No se encontro ningún similar")