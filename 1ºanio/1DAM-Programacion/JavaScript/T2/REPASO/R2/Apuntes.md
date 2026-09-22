## Arrays

(Para sacar el número más bajo o más alto)
Infinito: Infinity
Infinito negativo: Number.NEGATIVE_INFINITY

EJEMPLO DEL Nº MÁS BAJO/ALTO
else if (opcion == "3") {
                gastomasAlto = Number.NEGATIVE_INFINITY
                gastoMasBajo = Infinity
                for (let i = 0; i < gastosNumero.length; i++) {
                    if (gastosNumero[i] > gastomasAlto) {
                        gastomasAlto = gastosNumero[i]
                    }
                    if (gastosNumero[i] < gastoMasBajo) {
                        gastoMasBajo = gastosNumero[i]
                    }
                }
                alert("Gasto más alto: " + gastomasAlto + "\nGasto más bajo: " + gastoMasBajo)

            }

(Para borrar cualquier posición dentro de un array, se utiliza .splice(i, 1))
for (let i = 0; i < reservasNombres.length; i++) {
                    if (reservasNombres[i].toLowerCase() === cancelarNombre.toLowerCase()) {
                        reservasNombres.splice(i, 1)
                        reservasPersonas.splice(i, 1)
                        nombreEncontrado = true
                    }

                }

AÑADIR
(Si queremos añadir algo al final de un array:).push
(Si queremos añadir algo al principio de un array:).unshift

BORRAR
(Si queremos eliminar algo al final de un array:).pop
(Si queremos eliminar algo al principio de un array:).shift