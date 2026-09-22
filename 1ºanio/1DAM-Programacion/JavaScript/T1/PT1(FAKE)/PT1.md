# Prueba Técnica 1: Calculadora de consumo Eléctrico

## [Enlace Github](https://classroom.github.com/a/Ovw4ZyHL)

### **Instrucciones Generales**

1. **Lee todo el examen** detenidamente antes de empezar a programar. Es importante que comprendas bien el problema antes de comenzar a escribir código.
2. La prueba se realizará usando **GitHub Classroom**, por lo que debes estar familiarizado con su uso. Mas abajo, se explica detalladamente cómo funciona.
3. Solo están permitidos los **apuntes disponibles en GitHub**, ya sean los proporcionados por el profesor o tus propios apuntes. 
4. **No está permitido buscar en Internet** ni utilizar **IA generativa** para resolver el examen.
5. **No se permite la comunicación entre alumnos** durante la prueba. Tener abiertas aplicaciones de mensajería como **Discord, Telegram, WhatsApp, etc.**, se considerará intento de copia, lo que resultará en un **0 automático**.
6. **Solo se corregirán programas que se puedan ejecutar correctamente**. Es preferible entregar un código que funcione aunque falten algunas funcionalidades, a presentar un programa con errores de sintaxis o código que no tiene sentido. Si tu programa **no se ejecuta** o tiene **errores de sintaxis**, se calificará con un **0**.
7. Se valorará la calidad y limpieza del código. Asegúrate de usar **nombres de variables descriptivos** y el estilo de nombres **camelCase** para mantener una estructura clara y fácil de leer.
8. Están prohibidas las palabras reservadas **`break`** y **`continue`** debido a la regla de **Single Exit Rule**. Esta regla establece que una función debe tener **un solo punto de salida**, lo que facilita la lectura y depuración del código. El uso de estas palabras puede generar múltiples puntos de salida en bucles o funciones, haciendo el código más difícil de entender y mantener.
9. **Incumplir cualquiera de estas normas** implicará una calificación de **0** en el examen.

---

## **Prueba Técnica: Calculadora de consumo Eléctrico**

### **Instrucciones del programa:**

1. **Solicitar información inicial:**
  - Pide al usuario el número de electrodomésticos que desea registrar.
  - Solicita el precio por kilovatio-hora (kWh) para calcular el coste del consumo eléctrico.
  - Solicita al usuario la potencia total contratada (en vatios W).

2. **Registrar datos de cada electrodoméstico:**
   - Para cada electrodoméstico, el programa debe solicitar:
     - La potencia del electrodoméstico en vatios (W).
     - El número de horas que se utiliza cada día.

3. **Cálculos de consumo:**
  - El programa debe calcular el consumo de cada electrodoméstico en kilovatios-hora (kWh) diarios y mensuales, utilizando la fórmula:
  ```javascript
  Consumo diario en kWh = (Potencia en vatios / 1000) * Número de horas de uso diario
  Consumo mensual en kWh = Consumo diario en kWh * 30
  ```
  - También debe calcular el coste mensual en base al consumo mensual y el precio por kWh proporcionado por el usuario.
  - El programa debe verificar si el **consumo del electrodoméstico** supera la potencia contratada. Si esto ocurre, el programa se debe detener y mostrar un mensaje de alerta:
  ```
  ¡Alerta! Has superado la potencia de [potencia contratada] W.
  ```

4. **Mostrar resultados:**
  Al final del programa, muestra los siguientes resultados:
  - El consumo mensual total en kWh para todos los electrodomésticos.
  - El coste total mensual en euros.

---

### **Ejemplo de ejecución correcta:**
```yaml
Introduce el número de electrodomésticos a registrar: 2
Introduce el precio por kilovatio-hora (kWh): 0.15
Introduce la potencia total contratada (en vatios): 5000

Introduce la potencia del electrodoméstico 1 (en vatios): 1000
Introduce el número de horas de uso diario: 4

Introduce la potencia del electrodoméstico 2 (en vatios): 800
Introduce el número de horas de uso diario: 6

Consumo total mensual: 264 kWh
Coste total mensual: 39.60 €
```

### **Ejemplo de ejecución con superación de la potencia contratada:**

```yaml
Introduce el número de electrodomésticos a registrar: 3
Introduce el precio por kilovatio-hora (kWh): 0.15
Introduce la potencia total contratada (en vatios): 3000

Introduce la potencia del electrodoméstico 1 (en vatios): 1500
Introduce el número de horas de uso diario: 3

Introduce la potencia del electrodoméstico 2 (en vatios): 4000

¡Alerta! Has superado la potencia de 3000 W.
```