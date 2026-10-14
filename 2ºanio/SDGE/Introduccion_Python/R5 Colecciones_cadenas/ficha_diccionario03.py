print("Bienvenido a Ficha con diccionario")
cliente = {"nombre": "Antonio",
           "ciudad": "Motril",
           "email": "antonio@gmail.com",
           "activo": True}

print("--- Ficha Inicial ---")
print(f"Alumno: {cliente["nombre"]} | Ciudad: {cliente["ciudad"]} | Email: {cliente["email"]} | Activo: {cliente['activo']}")

cliente["ciudad"] = "Granada"
cliente["telefono"] = "606042120"

print("\n--- Ficha Actualizada ---")
print(f"Alumno: {cliente["nombre"]} | Ciudad: {cliente["ciudad"]} | Email: {cliente["email"]} | Activo: {cliente['activo']} | Teléfono: {cliente['telefono']}")