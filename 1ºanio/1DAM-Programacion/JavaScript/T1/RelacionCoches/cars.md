¡Perfecto! Aquí tienes una **relación extra de 5 ejercicios inspirados en la película *Cars***, con temáticas divertidas y educativas centradas en bucles (`while`, `for`) y estructuras condicionales (`if`, `else`, `else if`), como pediste. Todos están pensados en el mismo estilo que los anteriores.

---

## 🚗 Relación Extra: *Cars* - ¡Rayo, gasolina y circuitos!

---

### 1. **¡Rayo McQueen en boxes!**

Crea un programa que simule el paso por boxes de *N* coches. El programa pedirá el nombre del coche y el tiempo que ha tardado en el pit stop. Finaliza cuando se introduzca `"FIN"` como nombre.

Al final, muestra:

* El coche más rápido
* El número total de coches
* El tiempo promedio

#### 🧪 Ejemplo:

```
Nombre del coche 1: Rayo
Tiempo en boxes: 5.4
Nombre del coche 2: Mate
Tiempo en boxes: 7.1
Nombre del coche 3: Luigi
Tiempo en boxes: 4.9
Nombre del coche 4: FIN

Total de coches: 3
Tiempo medio: 5.8 segundos
El coche más rápido fue Luigi con 4.9 segundos
```

---

### 2. **Gasolinera Dinoco**

Simula el repostaje de varios coches. Cada coche tiene un tanque de 60 litros y reposta hasta llenarlo. El programa debe preguntar cuántos litros echa cada vez y detenerse cuando el tanque está lleno.

* Muestra cuántas veces ha repostado y el total gastado (precio/litro: 1.25€)

#### 🧪 Ejemplo:

```
Repostaje 1: 20
Repostaje 2: 30
Repostaje 3: 10

¡Depósito lleno!
Total de repostajes: 3
Total gastado: 75€
```

> 💡 Usa `while` para repetir repostajes y `if` para controlar exceso de litros.

---

### 3. **Entrenamiento en el circuito de Radiador Springs**

Crea un programa que simule un entrenamiento de 5 vueltas para un coche. En cada vuelta, se pide el tiempo (en segundos). Al final, muestra:

* La vuelta más rápida
* El tiempo total
* El tiempo promedio

> Utiliza `for` para iterar las vueltas y `if` para comprobar cuál es la más rápida.

#### 🧪 Ejemplo:

```
Tiempo vuelta 1: 60
Tiempo vuelta 2: 58
Tiempo vuelta 3: 62
Tiempo vuelta 4: 57
Tiempo vuelta 5: 59

Tiempo total: 296
Tiempo medio: 59.2
La vuelta más rápida fue la 4 con 57 segundos.
```

---

### 4. **Clasificación de la Copa Pistón**

El programa pide el número de coches en carrera. Por cada coche, solicita su nombre y puntos conseguidos (máximo 100). Si los puntos son:

* 80 o más: 🥇 **Oro**
* Entre 50 y 79: 🥈 **Plata**
* Menos de 50: 🥉 **Fuera del podio**

> Usa `for` y `if/else if/else` para determinar la categoría.

#### 🧪 Ejemplo:

```
Coche 1: Rayo - 95 puntos → Oro
Coche 2: Sally - 78 puntos → Plata
Coche 3: Guido - 42 puntos → Fuera del podio
```

---

### 5. **¡Luigi cuenta neumáticos!**

Luigi tiene una tienda de neumáticos. Cada cliente pide un número de ruedas (entre 1 y 4). El programa continúa hasta que se introduzca un 0. Al final:

* Muestra cuántos neumáticos se vendieron en total
* Cuántos clientes atendió
* Cuántos pidieron 4 neumáticos (cambio completo)

> Usa `while` y condiciones para contar.

#### 🧪 Ejemplo:

```
Cliente 1: 2 neumáticos
Cliente 2: 4 neumáticos
Cliente 3: 1 neumático
Cliente 4: 4 neumáticos
Cliente 5: 0

Total de clientes: 4
Neumáticos vendidos: 11
Clientes con cambio completo: 2
```