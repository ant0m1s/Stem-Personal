//Funciones
/** * 
La función basicamente lo que hace es darnos un número aleatorio entre 1 y 3(máximo OPCIONES) 
y nos devuelve una palabra aleatoria
* @function
* @returns {string} - La palabra (opción) seleccionada aleatoriamente. 
*/

function eleccionMaquina() {
  //Creamos un número random para que no sobrepase el nº de opciones
  let indiceRandom = Math.floor(Math.random() * OPCIONES.length);
  //Devolvemos la opción
  return OPCIONES[indiceRandom];
}


/**
 * 
 * jdgjkblxjngñlm
 * 
 * @param {null} jugadorEleccion 
 * @param {*} maquinaEleccion uzhfbho
 * @returns kcdkdkjvmv
 */

function determinarGanador(jugadorEleccion, maquinaEleccion) {
  let resultadoRonda;

  //Ambos sacan la misma elección
  if (jugadorEleccion === maquinaEleccion) {
    resultadoRonda = "Empate entre los jugadores";
  } //Jugador gana
  else if (
    (jugadorEleccion === "piedra" && maquinaEleccion === "tijera") ||
    (jugadorEleccion === "papel" && maquinaEleccion === "piedra") ||
    (jugadorEleccion === "tijera" && maquinaEleccion === "papel")
  ) {
    jugadorVictoria++;
    resultadoRonda = "Has ganado";
  } else {
    //Maquina Gana
    maquinaVictoria++;
    resultadoRonda = "Gana la consola";
  } //Devuelve el resultado de esa ronda
  return resultadoRonda;
}

//-------------Main------------------------

//Principales variables o variables globales
let jugadorVictoria = 0;
let maquinaVictoria = 0;
const OPCIONES = ["piedra", "papel", "tijera"];

// Este es el juego
function juego(eleccionJugador) {
  //Llamamos a la función y le damos un valor a la elección de la máquina
  let eleccionMaquinaActual = eleccionMaquina();
  //Llamamos a la función y le determinamos quien ha ganado
  let resultado = determinarGanador(eleccionJugador, eleccionMaquinaActual);

  //Actualiza las elecciones de los jugadores
  document.getElementById("user-choice").innerText =
    "Tu elección: " + eleccionJugador.toUpperCase();
  document.getElementById("computer-choice").innerText =
    "Elección de la consola: " + eleccionMaquinaActual.toUpperCase();

    //Actualiza el resultado de la ronda actual
  document.getElementById("winner").innerText = "Resultado: " + resultado;

  //Actualiza el marcador de las victorias de cada jugador
  document.getElementById("user-wins").innerText =
    "Victorias del jugador: " + jugadorVictoria;
  document.getElementById("computer-wins").innerText =
    "Victorias de la computadora: " + maquinaVictoria;
}
