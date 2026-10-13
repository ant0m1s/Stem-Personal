class Cliente:
    def __init__(self, nombre, email, ciudad):
        self.nombre = nombre
        self.email = email
        self.ciudad = ciudad
    
    def cambiar_ciudad(self, nueva_ciudad):
        self.ciudad = nueva_ciudad
        
    def ficha(self):
        mensaje = "Nombre: " + self.nombre + " | Ciudad: " + self.ciudad + " | Email: " + self.email
        return mensaje
    
    
c1 = Cliente("Juan", "juanillo@gmail.com", "Motril")
print("Cliente creado...")
print(c1.ficha())
print("\nActualizando ciudad...")
c1.cambiar_ciudad("Melilla")
print(c1.ficha())