# Ejercicios de XML y DTD (Academia)

---

## Ejercicio 1: DTD interna (crear el XML)
A continuación se proporciona una **DTD interna** sobre un alumno de una **academia**.  
Debes crear un documento XML válido que siga estas reglas.

```xml
<!DOCTYPE alumno [
<!ELEMENT alumno (nombre, apellido, curso)>
<!ELEMENT nombre (#PCDATA)>
<!ELEMENT apellido (#PCDATA)>
<!ELEMENT curso (#PCDATA)>
]>
```

📌 **Tarea:** Crea un XML con un alumno ficticio que respete la DTD.

---

## Ejercicio 2: DTD externa (crear el DTD)
A continuación se proporciona un **XML con referencia a una DTD externa** sobre un curso de una **academia**.  
Tu tarea es escribir el archivo `curso.dtd` que defina las reglas para que el XML sea válido.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE curso SYSTEM "curso.dtd">
<curso>
  <nombre>Desarrollo Web</nombre>
  <profesor>María López</profesor>
  <horas>120</horas>
</curso>
```

📌 **Tarea:** Crea el archivo `curso.dtd` que valide este documento.