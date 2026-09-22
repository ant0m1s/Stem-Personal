import { BarcoAPI} from "../api/barcoAPI.js"
import {TripulacionAPI } from "../api/tripulacionAPI.js"
import { crearNavbar } from "../components/navbar.js"
import { crearFooter } from "../components/footer.js"

document.addEventListener('DOMContentLoaded', async () => {
    document.getElementById('navbar').innerHTML = crearNavbar()
    document.getElementById('footer').innerHTML = crearFooter()
    const root = document.getElementById('root')
    let editandoId = null

    
    root.innerHTML = `
    <div>
      <button class="btn-seccion" data-seccion="lista" onclick="verSeccion('lista')">Ver Barcos</button>
      <button class="btn-seccion" data-seccion="filtrarNombre" onclick="verSeccion('filtrarNombre')">Filtrar por Nombre</button>
      <button class="btn-seccion" data-seccion="filtrarTripulacion" onclick="verSeccion('filtrarTripulacion')">Filtrar por Tripulación</button>
      <button class="btn-seccion" data-seccion="formulario" onclick="verSeccion('formulario')">Formulario</button>
    </div>

    <section id="sec-lista" class="seccion">
      <div id="contenidoLista"></div>
    </section>

    <section id="sec-filtrarNombre" class="seccion">
      <div class="card">
        <h2>Buscar Barco por Nombre</h2>
        <div>
          <input id="inputFiltrarNombre" placeholder="Ej: Thousand Sunny">
        </div>
        <button onclick="filtrarPorNombre()">Buscar</button>
      </div>
      <div id="resultadoFiltrarNombre"></div>
    </section>

    <section id="sec-filtrarTripulacion" class="seccion">
        <div class="card">
            <h2>Filtrar por Tripulación</h2>
            <div>
                <select id="selectTripulacionFiltrar"></select>
            </div>
            <button onclick="filtrarPorTripulacion()">Ver Barcos</button>
        </div>
        <div id="resultadoFiltrarTripulacion"></div>
    </section>

    <section id="sec-formulario" class="seccion">
      <div id="cardFormulario" class="card">
        <h2 id="tituloFormu">Nuevo Barco</h2>
        <p id="mensajeFormu" class="error"></p>
        <div>
          <label>Nombre</label>
          <input id="nombre" placeholder="Ej: Thousand Sunny">
        </div>
        <div>
          <label>Estado</label>
          <select id="activo">
            <option value="Activo">Activo</option>
            <option value="Destruido">Destruido</option>
          </select>
        </div>
        <div>
          <label>Tripulación</label>
          <select id="tripulacion"></select>
        </div>
        <div>
          <label>Imagen (ruta)</label>
          <input id="imagen" placeholder="Ej: img/barcos/thousandSunny.jpg">
        </div>
        <div>
          <button onclick="guardar()">Guardar</button>
          <button onclick="limpiarFormulario()">Cancelar</button>
        </div>
      </div>
      <div id="exitoFormu" style="display:none">
        <p id="mensajeExitoFormu" class="exito"></p>
        <button onclick="verSeccion('lista')">Ver lista</button>
        <button onclick="verSeccion('formulario')">Nuevo barco</button>
      </div>
    </section>`

    document.querySelectorAll(".seccion").forEach(sec => sec.style.display = "none")

    window.verSeccion = async (id) => {
        document.getElementById("resultadoFiltrarNombre").innerHTML = ""
        document.getElementById("resultadoFiltrarTripulacion").innerHTML = ""

        document.querySelectorAll(".seccion").forEach(sec => sec.style.display = "none")
        document.querySelectorAll(".btn-seccion").forEach(btn => btn.classList.remove("activo"))

        const btn = document.querySelector(`.btn-seccion[data-seccion="${id}"]`)
        if (btn) btn.classList.add("activo")

        document.getElementById("sec-" + id).style.display = "block"

        if (id === "lista") {
            mostrarLista()
        } else if(id === "filtrarTripulacion") {
            cargarTripulacionesFiltrar()
        }else if (id === "formulario") {
            limpiarFormulario()
            await cargarTripulaciones()
        }
    }

    // carga la lista de todos los barcos
    async function mostrarLista() {
        const barcos = await BarcoAPI.obtenerTodos()

        document.getElementById("contenidoLista").innerHTML = `
            <div class="lista-barcos">
                ${barcos.map(b => crearTarjeta(b, 'lista')).join("")}
            </div>
            <div class="subir">
                <button class="btn-arriba" onclick="window.scrollTo({ top: 0, behavior: 'smooth' })">↑ Volver arriba</button>
            </div>`
    }

    function crearTarjeta(b, seccion = 'lista') {
    return `
    <div class="tarjeta-barco">
      <div class="datos">
        <div class="cabecera">
          <h3 class="nombre">${b.nombre}</h3>
          <span class="estado ${b.activo ? 'vivo' : 'muerto'}">${b.activo ? 'Activo' : 'Destruido'}</span>
        </div>
        <ul class="atributos">
          <li><span class="etiqueta">Tripulación</span><span>${b.tripulacion ? b.tripulacion.nombre : 'Sin tripulación'}</span></li>
        </ul>
        <div class="botones">
          <a class="btn-editar" href="barcoDetalle.html?id=${b.id}">Ver Detalle</a>
          <button class="btn-editar" onclick="editarBarco(${b.id})">Editar</button>
          <button class="btn-eliminar" onclick="eliminar(${b.id}, '${seccion}')">Eliminar</button>
        </div>
      </div>
      <img class="imagen" src="${b.imagen || 'img/default.jpg'}" alt="${b.nombre}" onerror="this.src='img/default.jpg'">
    </div>`
    }

    //Filtrar nombre
    window.filtrarPorNombre = async () => {
        const nombre = document.getElementById("inputFiltrarNombre").value.trim()
        const resultado = document.getElementById("resultadoFiltrarNombre")

        if (!nombre) {
            resultado.innerHTML = `<p class="error">Introduce un nombre</p>`
            return
        }

        const barcos = await BarcoAPI.obtenerTodos()
        const encontrados = barcos.filter(b => b.nombre.toLowerCase().includes(nombre.toLowerCase()))

        if (encontrados.length === 0) {
            resultado.innerHTML = `<p class="error">No hay ningún barco con ese nombre</p>`
        } else {
            resultado.innerHTML = `<div class="lista-barcos">${encontrados.map(b => crearTarjeta(b, 'filtrarNombre')).join("")}</div>`
        }
    }

    //Filtrar tripulacion
    async function cargarTripulacionesFiltrar() {
        const tripulaciones = await TripulacionAPI.obtenerTodos()
        document.getElementById("selectTripulacionFiltrar").innerHTML =
            `<option value="">Selecciona una tripulación</option>` +
        tripulaciones.map(t => `<option value="${t.id}">${t.nombre}</option>`).join("")
    }

    window.filtrarPorTripulacion = async () => {
        const tripulacionId = document.getElementById("selectTripulacionFiltrar").value
        const resultado = document.getElementById("resultadoFiltrarTripulacion")

        if (!tripulacionId) {
            resultado.innerHTML = `<p class="error">Selecciona una tripulación</p>`
            return
        }

        const barcos = await BarcoAPI.obtenerTodos()
        const filtrados = barcos.filter(b => b.tripulacion && String(b.tripulacion.id) === tripulacionId)

        if (filtrados.length === 0) {
            resultado.innerHTML = `<p class="error">Esta tripulación no tiene barcos registrados</p>`
        } else {
            resultado.innerHTML = `<div class="lista-barcos">${filtrados.map(b => crearTarjeta(b, 'filtrarTripulacion')).join("")}</div>`
        }
    }

    window.eliminar = async (id, seccion) => {
        await BarcoAPI.eliminar(id)
        if (seccion === 'filtrarNombre') {
            document.getElementById("resultadoFiltrarNombre").innerHTML = `<p class="exito">Barco eliminado correctamente</p>`
        } else if (seccion === 'filtrarTripulacion') {
            document.getElementById("resultadoFiltrarTripulacion").innerHTML = `<p class="exito">Barco eliminado correctamente</p>`
        } else {
            mostrarLista()
        }
    }

    // guardar barco nuevo o editado
    window.guardar = async () => {
        const nombre = document.getElementById("nombre").value.trim()
        const activo = document.getElementById("activo").value
        const tripulacionId = document.getElementById("tripulacion").value
        const imagen = document.getElementById("imagen").value.trim()

        if (!nombre) {
            document.getElementById("mensajeFormu").innerHTML = "El barco debe tener un nombre"
            return
        }

        const barco = {
        nombre,
        activo: activo === "Activo",
        tripulacion: tripulacionId ? { id: tripulacionId } : null,
        imagen: imagen || null
        }

        if (editandoId) {
            await BarcoAPI.actualizar(editandoId, barco)
            editandoId = null
            mensajeExito(`Barco "${nombre}" editado correctamente`)
        } else {
            await BarcoAPI.crear(barco)
            mensajeExito(`Barco "${nombre}" creado correctamente`)
        }
    }

    function mensajeExito(texto) {
        document.getElementById("cardFormulario").style.display = "none"
        document.getElementById("exitoFormu").style.display = "block"
        document.getElementById("mensajeExitoFormu").textContent = texto
    }

    async function cargarTripulaciones() {
        const tripulaciones = await TripulacionAPI.obtenerTodos()
        document.getElementById("tripulacion").innerHTML =
            `<option value="">Sin tripulación</option>` +
        tripulaciones.map(t => `<option value="${t.id}">${t.nombre}</option>`).join("")
    }

    // editar barco existente
    window.editarBarco = async (id) => {
        const barco = await BarcoAPI.obtenerPorId(id)
        editandoId = id

        await cargarTripulaciones()

        document.getElementById("tituloFormu").textContent = "Editar Barco"
        document.getElementById("nombre").value = barco.nombre
        document.getElementById("activo").value = barco.activo ? "Activo" : "Destruido"
        document.getElementById("tripulacion").value = barco.tripulacion ? barco.tripulacion.id : ""
        document.getElementById("imagen").value = barco.imagen || ""

        document.getElementById("cardFormulario").style.display = "block"
        document.getElementById("exitoFormu").style.display = "none"
        document.querySelectorAll(".seccion").forEach(sec => sec.style.display = "none")
        document.querySelectorAll(".btn-seccion").forEach(btn => btn.classList.remove("activo"))
        document.getElementById("sec-formulario").style.display = "block"
    }

    function limpiarFormulario() {
        editandoId = null

        document.getElementById("cardFormulario").style.display = "block"
        document.getElementById("exitoFormu").style.display = "none"
        document.getElementById("tituloFormu").textContent = "Nuevo Barco"
        document.getElementById("mensajeFormu").innerHTML = ""

        document.getElementById("nombre").value = ""
        document.getElementById("activo").value = "Activo"
        document.getElementById("tripulacion").value = ""
        document.getElementById("imagen").value = ""
    }
})