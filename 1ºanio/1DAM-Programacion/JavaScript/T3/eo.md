## Flujo de Ejecución (MAIN)

El programa sigue una estructura secuencial dividida en tres fases:

1.  **Inicialización:**
    * Se selecciona aleatoriamente un "décimo secreto" de un array predefinido.
    * Se inicializan los contadores de intentos (6), arrays de historial (aciertos/fallos) y el estado de victoria (false).

2.  **Ciclo Principal (While):**
    * El juego se ejecuta mientras queden intentos y el jugador no haya ganado.
    * **Visualización:** En cada iteración se muestra el progreso del número oculto (ej: `1 _ _ 4 5`) y los intentos restantes.
    * **Validación:** Se sanitiza la entrada del usuario y se verifica que sea un dígito numérico único.
    * **Lógica de Juego:**
        * Si el dígito ya fue usado, se notifica sin penalización.
        * Si es un acierto, se guarda en el historial de adivinados.
        * Si es un fallo, se guarda en errores y se decrementa el contador de intentos.
    * **Condición de Victoria:** Se invoca a `comprobarGanador()` para verificar si todas las cifras han sido descubiertas.

3.  **Finalización:**
    * Al salir del bucle, se comprueba la variable `ganador`.
    * Se muestra un mensaje de éxito o de derrota revelando el número secreto según corresponda.