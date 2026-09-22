# Relación de ejercicios 4: Todo a la vez en todas partes

### 1.- Crea un programa que reciba las calificaciones de N estudiantes (se piden calificaciones hasta que el usuario introduzca un "-1") y calcule el promedio. Luego muestra cuántos estudiantes están aprobados (>=5) y cuantos suspensos.
*Ejemplo:*
```
Introduce la calificacion del alumno 1: 4
Introduce la calificacion del alumno 2: 1
Introduce la calificacion del alumno 3: 10
Introduce la calificacion del alumno 4: 8
Introduce la calificacion del alumno 5: -1

Hay 2 alumnos aprobados y 2 alumnos suspensos.
La calificación media es 5.75
```

*Solución:*
```javascript
let numeroAlumno = 1
let sumatorio = 0
let alumnosAprobados = 0
let alumnosSuspensos = 0

let nota = prompt("Introduce la calificacion del alumno " + numeroAlumno + ":")
nota = Number(nota)

while(nota != -1){
    sumatorio += nota // sumatorio = sumatorio + nota
    numeroAlumno++

    if(nota<5){
        alumnosSuspensos++
    }else{
        alumnosAprobados++
    }

    nota = prompt("Introduce la calificacion del alumno " + numeroAlumno + ":")
    nota = Number(nota)
}

let media = sumatorio / numeroAlumno

console.log("Hay " + alumnosAprobados + " alumnos aprobados y " + alumnosSuspensos + " alumnos suspensos.")

console.log("La calificación media es "+media)

```

### 2.- Crea un programa que simule un cajero. El usuario tiene un saldo inicial de 1000 euros y el cajero permite al usuario retirar dinero mientras su saldo no se quede por debajo de cero.
*Ejemplo:*
```
Saldo: 1000 | Introduzca saldo a retirar: 400
Saldo: 600 | Introduzca saldo a retirar: 200
Saldo: 400 | Introduzca saldo a retirar: 500
Saldo insuficiente.
Saldo: 400 | Introduzca saldo a retirar: 400
Te has quedado sin saldo ¡A trabajar!
```

*Solución:*
```javascript
let saldo = 1000
let retirada = 0

while (saldo > 0){
    retirada = prompt("Saldo: " + saldo + " | Introduzca saldo a retirar:")

    if(retirada > saldo){
        console.log("Saldo insuficiente.")
    }else{
        saldo -= retirada // saldo = saldo - retirada
    }
}

console.log("Te has quedado sin saldo ¡A trabajar!")
```

### 3.- Crea un programa para dividir cuentas y calcular propinas. El programa debe pedir productos de una cuenta hasta que el usuario introduce un 0. El prgrama pide personas a repartir y porcentaje de propina que quieren dejar.
*Ejemplo:*
```
Precio del producto 1: 10
Precio del producto 2: 20
Precio del producto 3: 15
Precio del producto 4: 35
Precio del producto 5: 0

¿Cuánta propina quieres dejar?: 5
¿Cúantas personas sois?: 4

Total sin propina: 80€
Total con propina del 5%: 84€

Precio por cabeza: 21€
```

*Solución:*
```javascript
let totalCuenta = 0
let precioProducto
let propina
let personas
let precioPorCabeza
let totalCuentaConPropina
let numeroProducto = 1

while(precioProducto != 0){
    precioProducto = prompt("Precio del producto " + numeroProducto + ":")
    precioProducto = Number(precioProducto)

    totalCuenta += precioProducto
    numeroProducto++
}

propina = prompt("¿Cuánta propina quieres dejar?:")
propina = Number(propina)

personas = prompt("¿Cúantas personas sois?:")
personas = Number(personas)

totalCuentaConPropina = totalCuenta + totalCuenta * propina / 100
precioPorCabeza = totalCuentaConPropina / personas

console.log("Total sin propina: " + totalCuenta + "€")
console.log("Total con propina del " + propina + "%: " + totalCuentaConPropina + "€")
console.log("Precio por cabeza: " + precioPorCabeza + "€")
```

### 4.- Calculadora de combustible. El programa debe pedir una cantidad de litros inicial. un precio por litro y un consumo e ir calculando el precio de cada viaje (pidiendo los kilometros) y el combustible restante

*Ejemplo:*
```
Introduce el combustible inicial: 50
Precio del litro de combustible: 1.45
Consumo cada 100km: 5.6

Introduce los kilómetros del viaje 1: 50
Precio del viaje: 4.06€
Combustible restante: 47.2 litros

Introduce los kilómetros del viaje 2: 200
Precio del viaje: 16.24€
Combustible restante: 36 litros

Introduce los kilómetros del viaje 3: 1000
¡Combustible insuficiente!
```

*Solución:*
```javascript
let combustibleDisponible
let precioLitro
let consumo100km

let numeroViaje = 1
let kilometrosViaje
let precioViaje
let combustibleConsumido

combustibleDisponible = prompt("Introduce el combustible inicial:")
combustibleDisponible = Number(combustibleDisponible)

precioLitro = prompt("Precio del litro de combustible:")
precioLitro = Number(precioLitro)

consumo100km = prompt("Consumo cada 100km:")
consumo100km = Number(consumo100km)

while (combustibleDisponible > 0) {

    kilometrosViaje = prompt("Introduce los kilómetros del viaje " + numeroViaje + ":")
    
    combustibleConsumido = kilometrosViaje * consumo100km / 100

    if (combustibleConsumido < combustibleDisponible) {

        precioViaje = combustibleConsumido * precioLitro
        combustibleDisponible -= combustibleConsumido

        alert("Precio del viaje: " + precioViaje)
        alert("Combustible restante: " + combustibleDisponible)

        numeroViaje++

    }else{
        alert("¡Combustible insuficiente!")
    }   
}
```

### 5.- Calculadora de temperatura media. El programa debe pedir la temperatura de la semana (7 días) y calcular la temperatura promedio. Se deben descartar los valores fuera del rango (-20 y 50).
*Ejemplo:*
```
Introduce la temperatura del día 1: 32
Introduce la temperatura del día 2: 31
Introduce la temperatura del día 3: 28.5
Introduce la temperatura del día 4: 82
Introduce la temperatura del día 5: 33
Introduce la temperatura del día 6: 35
Introduce la temperatura del día 7: 65

La temperatura media de la semana es: 31.9
```

*Solución:*
```javascript
let temperaturaDia
let sumatorioTemperaturas = 0
let temperaturaMedia
let numeroTemperaturasCorrectas = 0

for (let i = 1; i <= 7; i++) {
    temperaturaDia = prompt("Introduce la temperatura del día " + i + ":")
    temperaturaDia = Number(temperaturaDia)

    if (temperaturaDia >= -20 && temperaturaDia <= 50) {
        numeroTemperaturasCorrectas++
        sumatorioTemperaturas += temperaturaDia
    }

}

temperaturaMedia = sumatorioTemperaturas / numeroTemperaturasCorrectas

console.log("La temperatura media de la semana es: " + temperaturaMedia)
```

### 6.- Crea una calculadora básica de dos números. El programa pide dos números y luego una operación (+, -, *, /). El programa pide números hasta que el usuario indica "SALIR"
*Ejemplo:*
```
Introduce el primer número: 4
Introduce el segundo número: 5
Introduce la operación: +

El resultado de 4 + 5 es 9

Introduce el primer número: 3
Introduce el segundo número: 4
Introduce la operación: *

El resultado de 3 * 4 es 12

Introduce el primer número: SALIR
```

*Solución:*
```javascript
let numero1
let numero2
let operacionUsuario
let resultadoOperacion

while (numero1 != "SALIR" && numero2 != "SALIR" && operacionUsuario != "SALIR") {
    numero1 = prompt("Introduce el primer número:")

    if (numero1 != "SALIR") {
        numero1 = Number(numero1)

        numero2 = prompt("Introduce el segundo número:")
        if (numero2 != "SALIR") {
            numero2 = Number(numero2)

            operacionUsuario = prompt("Introduce la operación:")

            if (operacionUsuario != "SALIR") {
                
                if (operacionUsuario == "+") {
                    resultadoOperacion = numero1 + numero2
                } else if (operacionUsuario == "-") {
                    resultadoOperacion = numero1 - numero2
                } else if (operacionUsuario == "*") {
                    resultadoOperacion = numero1 * numero2
                } else if (operacionUsuario == "/") {
                    resultadoOperacion = numero1 / numero2
                }

                alert("El resultado de " + numero1 + operacionUsuario + numero2 + " es " + resultadoOperacion)
            }
        }
    }
}
```

### 7.- Crea un programa que pida al usuario un número y un dígito. El programa debe contar cuántas veces aparece dicho dígito en el número indicado. Además, debe validar que el numero sea postivo y el dígito sea una cifra. El programa termina cuando el usuario teclea "SALIR"
*Ejemplo:*
```
Introduce el número: 124548
Introduce el dígito: 4
El dígito 4 aparece 2 veces en el número 124548

Introduce el número: -58
¡Número incorrecto!

Introduce el número: 5855
Introduce el dígito: 58
¡Dígito incorrecto!
Introduce el dígito: 5
El dígito 5 aparece 3 veces en el número 5855

Introduce el número: SALIR
```

*Solución:*
```javascript
let numeroUsuario
let digito
let cifrasRestantes
let resto
let apariciones

while (numeroUsuario != "SALIR" && digito != "SALIR") {

    numeroUsuario = prompt("Introduce un número:")

    if (numeroUsuario != "SALIR") {

        numeroUsuario = Number(numeroUsuario)

        if (numeroUsuario >= 0) {

            digito = -1
            while (digito != "SALIR" && (digito < 0 || digito > 9)) {
                digito = prompt("Introduce un dígito:")

                if (digito != "SALIR") {
                    digito = Number(digito)
                    if (digito < 0 || digito > 9) {
                        alert("¡Dígito incorrecto!")
                    }
                }
            }

            if (digito != "SALIR") {
                apariciones = 0
                cifrasRestantes = numeroUsuario

                while (cifrasRestantes != 0) {
                    resto = cifrasRestantes % 10
                    cifrasRestantes = parseInt(cifrasRestantes / 10)

                    if (resto == digito) {
                        apariciones++
                    }

                }

                alert("El dígito " + digito + " aparece " + apariciones + " veces en el número " + numeroUsuario)
            }
        } else {
            alert("¡Número incorrecto!")
        }
    }
}
```

### 8.- 
  1. **Crea tres Hobbits:** Introduce, solicitándolo por pantalla, el nombre y la edad de tres hobbits.
      - Crea una variable booleana para cada hobbit en la que almacenes *true* o *false* dependiendo de si cada uno de nuestros Hobbits *puedeRepetir* o no. 
  2. **Menor de edad:** ¿Sabéis que los hobbits cumplen la mayoría de edad a los 33 años? Si alguno de nuestros Hobbits tiene menos de 33 años, automáticamente puede repetir. ¡Están en edad de crecimiento!
  3. **Segunda oportunidad:** Suma la edad de los hobbits y decide quién puede repetir:  
      - Si la suma de las edades es múltiplo de 2, el primer hobbit puede repetir. 
      - Si la suma de las edades es múltiplo de 3, el segundo hobbit puede repetir. 
      - Si la suma de las edades es múltiplo de 5, el tercer hobbit puede repetir.
      - **Por ejemplo: Si la suma de las edades fuera 60 años, los tres pueden repetir porque 60 es múltiplo de 2, 3 y 5.**
  4. **¿Qué ha pasado al final?** Escribe por pantalla qué hobbits puede repetir y cuáles no.
  5. **[EXTRA] Repartir:** Solo nos quedan dos platos de comida extra, así que toca decidir:
      - Si los tres hobbits pueden repetir imprime "Toca repartir la comida"
      - Si ningún hobbit puede repetir imprime "Va a sobrar comida"
      - Si pueden uno o dos hobbits imprime  "¡A comer!"

*Solución:*
```javascript
// Declaración de variables
let nombreHobbit1, nombreHobbit2, nombreHobbit3
let edadHobbit1, edadHobbit2, edadHobbit3
let puedeComerHobbit1, puedeComerHobbit2, puedeComerHobbit3
let sumaEdades

// Solicitud de datos
nombreHobbit1 = prompt("Introduzca el nombre del primer Hobbit:")
edadHobbit1 = Number(prompt("Introduzca la edad del primer Hobbit:"))

nombreHobbit2 = prompt("Introduzca el nombre del segundo Hobbit:")
edadHobbit2 = Number(prompt("Introduzca la edad del segundo Hobbit:"))

nombreHobbit3 = prompt("Introduzca el nombre del tercer Hobbit:")
edadHobbit3 = Number(prompt("Introduzca la edad del tercer Hobbit:"))

// Calculo la suma de las edades
sumaEdades = edadHobbit1 + edadHobbit2 + edadHobbit3

/*Calculo qué hobbits pueden repetir. Tened en cuenta que las operaciones
    lógicas devuelven como resultado un booleano. En este caso, el booleano 
    lo estoy almacenando en una variable (booleana)*/
puedeComerHobbit1 = (edadHobbit1 < 33) || (sumaEdades % 2 == 0)
puedeComerHobbit2 = (edadHobbit2 < 33) || (sumaEdades % 3 == 0)
puedeComerHobbit3 = (edadHobbit3 < 33) || (sumaEdades % 5 == 0)

// Imprimo el estado de los hobbits
if (puedeComerHobbit1) {
    console.log("El Hobbit " + nombreHobbit1 + " de " + edadHobbit1 + " años puede repetir")
} else {
    console.log("El Hobbit " + nombreHobbit1 + " de " + edadHobbit1 + " años NO puede repetir")
}

if (puedeComerHobbit2) {
    console.log("El Hobbit " + nombreHobbit2 + " de " + edadHobbit2 + " años puede repetir")
} else {
    console.log("El Hobbit " + nombreHobbit2 + " de " + edadHobbit2 + " años NO puede repetir")
}

if (puedeComerHobbit3) {
    console.log("El Hobbit " + nombreHobbit3 + " de " + edadHobbit3 + " años puede repetir")
} else {
    console.log("El Hobbit " + nombreHobbit3 + " de " + edadHobbit3 + " años NO puede repetir")
}

// Reparto los platos de comida
if(puedeComerHobbit1 && puedeComerHobbit2 && puedeComerHobbit3){
    console.log("Toca repartir la comida")
}else if(!puedeComerHobbit1 && !puedeComerHobbit2 && !puedeComerHobbit3){
    console.log("Va a sobrar comida")
}else{
    console.log("¡A comer!")
}

```