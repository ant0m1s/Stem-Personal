// =========================
// RELACIÓN 1 — PLANTILLA CON PSEUDOCÓDIGO
// (Deja las constantes y completa las funciones)
// =========================

let jugadorSeleccionado = null;

// ====== CONSTANTES (NO TOCAR) ======
const listaJugadores = document.getElementById("listaJugadores");

const detalleNombre = document.getElementById("detalleNombre");
const detallePosicion = document.getElementById("detallePosicion");
const detalleGoles = document.getElementById("detalleGoles");

const btnSumarGol = document.getElementById("btnSumarGol");
const btnBorrar = document.getElementById("btnBorrar");
const filtroPosicion = document.getElementById("filtroPosicion");

const formNuevoJugador = document.getElementById("formNuevoJugador");
const mensajeForm = document.getElementById("mensajeForm");

// ====== FUNCIONES A IMPLEMENTAR (las más importantes) ======

function quitarSeleccionATodos() {
  /*
  PSEUDOCÓDIGO:
  1) Obtener todas las tarjetas con ".jugador"
  2) Recorrerlas
  3) A cada una, quitarle la clase "seleccionado"
  */

  const tarjetas = document.querySelectorAll(".jugador");
  tarjetas.forEach((j) => j.classList.remove("seleccionado"));

}

function actualizarPanel(jugador) {
  /*
  PSEUDOCÓDIGO:
  SI jugador existe:
    1) Leer el nombre desde el DOM (h3.nombre dentro de la tarjeta)
    2) Leer dorsal, posición y goles desde jugador.dataset
       - dorsal: jugador.dataset.dorsal
       - posicion: jugador.dataset.posicion
       - goles: jugador.dataset.goles
    3) Pintar en el panel:
       - detalleNombre: "Nombre (#dorsal)" (o solo nombre, como prefieras)
       - detallePosicion: posicion
       - detalleGoles: goles
  SI NO existe jugador:
    4) Poner "—" en los 3 campos del panel
  */
   if (jugador != null) {
    const nombre = jugador.querySelector(".nombre").textContent;
    const posicion = jugador.dataset.posicion;
    const goles = jugador.dataset.goles;

    detalleNombre.textContent = `${nombre} (#${jugador.dataset.dorsal})`;
    detallePosicion.textContent = posicion;
    detalleGoles.textContent = goles;
  } else {
    detalleNombre.textContent = "—";
    detallePosicion.textContent = "—";
    detalleGoles.textContent = "—";
  }
}

function seleccionarJugador(jugador) {

  if (jugador) {
    quitarSeleccionATodos();
    jugador.classList.add("seleccionado");
    jugadorSeleccionado = jugador;
    actualizarPanel(jugador)
  }
  /*
  PSEUDOCÓDIGO:
  1) Si jugador es null, no hacer nada (o actualizarPanel(null))
  2) Llamar a quitarSeleccionATodos()
  3) Añadir clase "seleccionado" al jugador clicado
  4) Guardarlo en la variable global jugadorSeleccionado
  5) Llamar a actualizarPanel(jugadorSeleccionado)
  */
}

function engancharClickTarjeta(tarjeta) {
  tarjeta.addEventListener("click", function () {
    seleccionarJugador(this);
  });
  /*
  PSEUDOCÓDIGO:
  1) Añadir un addEventListener("click", ...)
  2) Dentro del handler, llamar a seleccionarJugador(tarjeta)
     (ojo: si usas function() puedes usar this)
  */
}

function engancharClicksJugadoresExistentes() {
  /*
  PSEUDOCÓDIGO:
  1) Seleccionar todas las tarjetas ".jugador"
  2) Recorrerlas
  3) A cada una, llamarle a engancharClickTarjeta(tarjeta)
  */
  const tarjetas = document.querySelectorAll(".jugador");
  tarjetas.forEach((tarjeta) => engancharClickTarjeta(tarjeta));
}

function sumarGolSeleccionado() {
  /*
  PSEUDOCÓDIGO (dataset + DOM):
  1) Si no hay jugadorSeleccionado -> no hacer nada
  2) Leer goles actuales desde jugadorSeleccionado.dataset.goles
     - Convertir a número (Number(...))
  3) Calcular nuevosGoles = golesActuales + 1
  4) MODIFICAR dataset:
     - jugadorSeleccionado.dataset.goles = String(nuevosGoles)
  5) Actualizar texto visible en:
     - El <strong class="goles"> dentro de la tarjeta
     - detalleGoles en el panel
  */
   if (jugadorSeleccionado) {
    const golesActuales = Number(jugadorSeleccionado.dataset.goles);
    const nuevosGoles = golesActuales + 1;

    // Modificar dataset
    jugadorSeleccionado.dataset.goles = String(nuevosGoles);

    // Actualizar texto visible
    jugadorSeleccionado.querySelector(".goles").textContent = String(nuevosGoles);
    detalleGoles.textContent = String(nuevosGoles);
  }
}

function filtrarPorPosicion(valorFiltro) {
  /*
  PSEUDOCÓDIGO:
  1) Obtener todas las tarjetas ".jugador"
  2) Para cada tarjeta:
     - Leer posicionTarjeta = tarjeta.dataset.posicion
     - Si valorFiltro === "todas" -> mostrar
     - Si no:
         - Si posicionTarjeta === valorFiltro -> mostrar
         - Si no -> ocultar
  3) Para ocultar/mostrar:
     - style.display = "block" / "none"
     (o "grid"/"flex" según tu CSS, pero en el enunciado vale block)
  */

  const tarjetas = document.querySelectorAll(".jugador");

  tarjetas.forEach((jugador) => {
    const posicion = jugador.dataset.posicion;
    const cumpleFiltro = (valorFiltro === "todas" || posicion === valorFiltro);

    jugador.style.display = cumpleFiltro ? "block" : "none";
  });
}

function crearTarjetaJugador(nombre, dorsal, posicion, goles) {
  /*
  PSEUDOCÓDIGO (crear nodos + dataset):
  1) Crear <article>
  2) Añadir clase "jugador"
  3) (opcional) poner tabIndex = 0
  4) Asignar dataset:
     - tarjeta.dataset.dorsal = dorsal
     - tarjeta.dataset.posicion = posicion
     - tarjeta.dataset.goles = goles
  5) Crear la estructura interna (puedes usar innerHTML):
     - h3.nombre con el nombre
     - p.meta con dorsal y posicion
     - p.stats con <strong class="goles">goles</strong>
  6) Engancharle click (engancharClickTarjeta)
  7) Devolver la tarjeta creada
  */
   const tarjeta = document.createElement("article");
  tarjeta.classList.add("jugador");
  tarjeta.tabIndex = 0;

  tarjeta.dataset.dorsal = String(dorsal);
  tarjeta.dataset.posicion = posicion;
  tarjeta.dataset.goles = String(goles);

  tarjeta.innerHTML = `
    <h3 class="nombre">${nombre}</h3>
    <p class="meta">Dorsal <strong class="dorsal">${dorsal}</strong> · <span class="posicion">${posicion}</span></p>
    <p class="stats">Goles: <strong class="goles">${goles}</strong></p>
  `;

  engancharClickTarjeta(tarjeta);

  return tarjeta; 
}

function validarNuevoJugador(nombre, dorsal, goles) {
  /*
  PSEUDOCÓDIGO:
  1) Crear boolean ok = true
  2) Si nombre vacío -> ok = false
  3) Si dorsal < 1 o NaN -> ok = false
  4) Si goles < 0 o NaN -> ok = false
  5) Devuelve ok (1 return como máximo)
  */
}

function mostrarMensajeFormulario(texto) {
  mensajeForm.textContent = texto;

}

function anadirJugadorDesdeFormulario() {
  /*
  PSEUDOCÓDIGO (submit):
  1) Leer valores:
     - nombre (trim)
     - dorsal (Number)
     - posicion (value del select)
     - goles (Number)
  2) Validar con validarNuevoJugador(...)
  3) Si OK:
     - crear tarjeta con crearTarjetaJugador(...)
     - appendChild a listaJugadores
     - reset del form
     - mensaje "ok"
  4) Si NO OK:
     - mensaje de error
  */
 const nombre = document.getElementById("nuevoNombre").value.trim();
  const dorsal = Number(document.getElementById("nuevoDorsal").value);
  const posicion = document.getElementById("nuevaPosicion").value;
  const goles = Number(document.getElementById("nuevosGoles").value);

  let datosCorrectos = true;

  if (nombre === "") datosCorrectos = false;
  if (!(dorsal >= 1)) datosCorrectos = false;
  if (!(goles >= 0)) datosCorrectos = false;

  if (datosCorrectos) {
    const tarjeta = crearTarjetaJugador(nombre, dorsal, posicion, goles);
    listaJugadores.appendChild(tarjeta);

    formNuevoJugador.reset();
    mostrarMensajeFormulario("Jugador añadido correctamente ✅");
  } else {
    mostrarMensajeFormulario("Error: revisa nombre, dorsal (>=1) y goles (>=0).");
  }
}

function borrarSeleccionado() {
  /*
  PSEUDOCÓDIGO:
  1) Si no hay jugadorSeleccionado -> no hacer nada
  2) Guardar referencia: aBorrar = jugadorSeleccionado
  3) Poner jugadorSeleccionado = null (para evitar líos)
  4) aBorrar.remove()
  5) Buscar el primer ".jugador" que quede
     - Si existe: seleccionarJugador(primero)
     - Si no: actualizarPanel(null)
  */
 if (jugadorSeleccionado) {
    const aBorrar = jugadorSeleccionado;

    // Quitamos referencia antes de borrar
    jugadorSeleccionado = null;

    aBorrar.remove();

    // Seleccionar el primero que quede (si existe)
    const primero = document.querySelector(".jugador");
    if (primero) {
      seleccionarJugador(primero);
    } else {
      actualizarPanel(null);
    }
  }else{
    alert("Ningun jugador seleccionado")
  }

}

// ====== LISTENERS (NO TOCAR estructura, solo llamar a tus funciones) ======
btnSumarGol.addEventListener("click", function () {
  sumarGolSeleccionado();
});

btnBorrar.addEventListener("click", function () {
  borrarSeleccionado();
});

filtroPosicion.addEventListener("change", function () {
  filtrarPorPosicion(this.value);
});

formNuevoJugador.addEventListener("submit", function (event) {
  event.preventDefault();
  anadirJugadorDesdeFormulario();
});

// ====== INICIO ======
engancharClicksJugadoresExistentes();

// Selección inicial del primer jugador (si existe)
const primerJugador = document.querySelector(".jugador");
if (primerJugador) {
  seleccionarJugador(primerJugador);
} else {
  actualizarPanel(null);
}