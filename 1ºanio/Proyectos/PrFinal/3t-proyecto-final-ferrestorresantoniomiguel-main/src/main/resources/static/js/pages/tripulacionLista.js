import { TripulacionAPI } from "../api/tripulacionAPI.js"
import { crearNavbar } from "../components/navbar.js"
import { crearFooter } from "../components/footer.js"

document.addEventListener("DOMContentLoaded", async () => {
  document.getElementById("navbar").innerHTML = crearNavbar()
  document.getElementById('footer').innerHTML = crearFooter()
  const root = document.getElementById("root")
  let editandoId = null
  root.innerHTML = `
    <div>
      <button class="btn-seccion" data-seccion="lista" onclick="verSeccion('lista')">Ver Tripulaciones</button>
      <button class="btn-seccion" data-seccion="filtrarNombre" onclick="verSeccion('filtrarNombre')">Filtrar por Nombre</button>
      <button class="btn-seccion" data-seccion="filtrarGeneracion" onclick="verSeccion('filtrarGeneracion')">Filtrar por Generación</button>
      <button class="btn-seccion" data-seccion="formulario" onclick="verSeccion('formulario')">Formulario</button>
    </div>

    <section id="sec-lista" class="seccion">
      <div id="contenidoLista"></div>
    </section>

    <section id="sec-filtrarNombre" class="seccion">
      <div class="card">
        <h2>Buscar Tripulación por Nombre</h2>
        <div>
          <input id="inputFiltrarNombre" placeholder="Ej: Piratas del Sombrero de Paja">
        </div>
        <button onclick="filtrarPorNombre()">Buscar</button>
      </div>
      <div id="resultadoFiltrarNombre"></div>
    </section>

    <section id="sec-filtrarGeneracion" class="seccion">
      <div class="card">
        <h2>Filtrar por Generación</h2>
        <div>
          <select id="selectGeneracion">
            <option value="">Selecciona una generación</option>
            <option value="Peor Generación">Peor Generación</option>
            <option value="Generación de Oro">Generación de Oro</option>
            <option value="Generación Antigua">Generación Antigua</option>
          </select>
        </div>
        <button onclick="filtrarPorGeneracion()">Ver Tripulaciones</button>
      </div>
      <div id="resultadoFiltrarGeneracion"></div>
    </section>

    <section id="sec-formulario" class="seccion">
      <div id="cardFormulario" class="card">
        <h2 id="tituloFormu">Nueva Tripulación</h2>
        <p id="mensajeFormu" class="error"></p>
        <div>
          <label>Nombre</label>
          <input id="nombre" placeholder="Ej: Piratas del Sombrero de Paja">
        </div>
        <div>
          <label>Nº de Miembros</label>
          <input id="numeroMiembros" type="number" placeholder="Ej: 10">
        </div>
        <div>
          <label>Descripción</label>
          <input id="descripcion" placeholder="Breve descripción">
        </div>
        <div>
          <label>Recompensa Total (Berry)</label>
          <input id="recompensaTotal" type="number" placeholder="Ej: 3131000000">
        </div>
        <div>
          <label>Generación</label>
          <select id="generacion">
            <option value="Peor Generación">Peor Generación</option>
            <option value="Generación de Oro">Generación de Oro</option>
            <option value="Generación Antigua">Generación Antigua</option>
          </select>
        </div>
        <div>
          <label>Imagen (ruta)</label>
          <input id="imagen" placeholder="Ej: img/tripulaciones/sombreroPaja.jpg">
        </div>
        <div>
          <button onclick="guardar()">Guardar</button>
          <button onclick="limpiarFormulario()">Cancelar</button>
        </div>
      </div>
      <div id="exitoFormu" style="display:none">
        <p id="mensajeExitoFormu" class="exito"></p>
        <button onclick="verSeccion('lista')">Ver lista</button>
        <button onclick="verSeccion('formulario')">Nueva tripulación</button>
      </div>
    </section>`

  document.querySelectorAll(".seccion").forEach(sec => sec.style.display = "none")

  window.verSeccion = (id) => {
    document.getElementById("resultadoFiltrarNombre").innerHTML = ""
    document.getElementById("resultadoFiltrarGeneracion").innerHTML = ""

    document.querySelectorAll(".seccion").forEach(sec => sec.style.display = "none")
    document.querySelectorAll(".btn-seccion").forEach(btn => btn.classList.remove("activo"))

    const btn = document.querySelector(`.btn-seccion[data-seccion="${id}"]`)
    if (btn) btn.classList.add("activo")

    document.getElementById("sec-" + id).style.display = "block"

    if (id === "lista") mostrarLista()
    else if (id === "formulario") limpiarFormulario()
  }

  // carga la lista de todas las tripulaciones
  async function mostrarLista() {
    const tripulaciones = await TripulacionAPI.obtenerTodos()

    document.getElementById("contenidoLista").innerHTML = `
      <div class="lista-tripulaciones">
        ${tripulaciones.map(t => crearTarjeta(t)).join("")}
      </div>
      <div class="subir">
        <button class="btn-arriba" onclick="window.scrollTo({ top: 0, behavior: 'smooth' })">↑ Volver arriba</button>
      </div>`
  }

  function crearTarjeta(t) {
    return `
    <div class="tarjeta-tripulacion">
      <div class="datos">
        <div class="cabecera">
          <h3 class="nombre">${t.nombre}</h3>
          <span class="alias">${t.generacion || "-"}</span>
        </div>
        <ul class="atributos">
          <li><span class="etiqueta">Miembros</span><span>${t.numeroMiembros || '-'}</span></li>
          <li><span class="etiqueta">Recompensa</span><span>${t.recompensaTotal ? Number(t.recompensaTotal).toLocaleString() + ' B' : '-'}</span></li>
          <li><span class="etiqueta">Descripción</span><span>${t.descripcion || '-'}</span></li>
        </ul>
        <div class="botones">
          <a class="btn-editar" href="tripulacionDetalle.html?id=${t.id}">Ver Detalles</a>
          <button class="btn-editar" onclick="editarTripulacion(${t.id})">Editar</button>
          <button class="btn-eliminar" onclick="eliminar(${t.id})">Eliminar</button>
        </div>
      </div>
      <img class="imagen" src="${t.imagen || 'img/default.jpg'}" alt="${t.nombre}" onerror="this.src='img/default.jpg'">
    </div>`
  }

  window.filtrarPorNombre = async () => {
    const nombre = document.getElementById("inputFiltrarNombre").value.trim()
    const resultado = document.getElementById("resultadoFiltrarNombre")

    if (!nombre) {
      resultado.innerHTML = `<p class="error">Introduce un nombre</p>`
      return
    }

    const tripulaciones = await TripulacionAPI.obtenerTodos()
    const encontradas = tripulaciones.filter(t => t.nombre.toLowerCase().includes(nombre.toLowerCase()))

    if (encontradas.length === 0) {
      resultado.innerHTML = `<p class="error">No hay ninguna tripulación con ese nombre</p>`
    } else {
      resultado.innerHTML = `<div class="lista-tripulaciones">${encontradas.map(t => crearTarjeta(t)).join("")}</div>`
    }
  }

  window.filtrarPorGeneracion = async () => {
    const generacion = document.getElementById("selectGeneracion").value
    const resultado = document.getElementById("resultadoFiltrarGeneracion")

    if (!generacion) {
      resultado.innerHTML = `<p class="error">Selecciona una generación</p>`
      return
    }

    const tripulaciones = await TripulacionAPI.obtenerPorGeneracion(generacion)

    if (!tripulaciones || tripulaciones.length === 0) {
      resultado.innerHTML = `<p class="error">No hay tripulaciones en esa generación</p>`
    } else {
      resultado.innerHTML = `<div class="lista-tripulaciones">${tripulaciones.map(t => crearTarjeta(t)).join("")}</div>`
    }
  }

  // guardar tripulación nueva o editada
  window.guardar = async () => {
    const nombre = document.getElementById("nombre").value.trim()
    const numeroMiembros = document.getElementById("numeroMiembros").value
    const descripcion = document.getElementById("descripcion").value.trim()
    const recompensaTotal = document.getElementById("recompensaTotal").value
    const generacion = document.getElementById("generacion").value
    const imagen = document.getElementById("imagen").value.trim()

    if (!nombre) {
      document.getElementById("mensajeFormu").innerHTML = "La tripulación debe tener nombre"
      return
    }

    const tripulacion = {
      nombre,
      numeroMiembros: numeroMiembros || null,
      descripcion,
      recompensaTotal: recompensaTotal || null,
      generacion,
      imagen: imagen || null
    }

    if (editandoId) {
      await TripulacionAPI.actualizar(editandoId, tripulacion)
      editandoId = null
      mensajeExito(`Tripulación "${nombre}" editada correctamente`)
    } else {
      await TripulacionAPI.crear(tripulacion)
      mensajeExito(`Tripulación "${nombre}" creada correctamente`)
    }
  }

  window.eliminar = async (id) => {
    await TripulacionAPI.eliminar(id)
    mostrarLista()
  }

  function mensajeExito(texto) {
    document.getElementById("cardFormulario").style.display = "none"
    document.getElementById("exitoFormu").style.display = "block"
    document.getElementById("mensajeExitoFormu").textContent = texto
  }

  function limpiarFormulario() {
    editandoId = null

    document.getElementById("cardFormulario").style.display = "block"
    document.getElementById("exitoFormu").style.display = "none"
    document.getElementById("tituloFormu").textContent = "Nueva Tripulación"
    document.getElementById("mensajeFormu").innerHTML = ""

    document.getElementById("nombre").value = ""
    document.getElementById("numeroMiembros").value = ""
    document.getElementById("descripcion").value = ""
    document.getElementById("recompensaTotal").value = ""
    document.getElementById("generacion").value = "Peor Generación"
    document.getElementById("imagen").value = ""
  }

  // editar tripulación existente
  window.editarTripulacion = async (id) => {
    const t = await TripulacionAPI.obtenerPorId(id)
    editandoId = id

    document.getElementById("tituloFormu").textContent = "Editar Tripulación"
    document.getElementById("nombre").value = t.nombre
    document.getElementById("numeroMiembros").value = t.numeroMiembros || ""
    document.getElementById("descripcion").value = t.descripcion || ""
    document.getElementById("recompensaTotal").value = t.recompensaTotal || ""
    document.getElementById("generacion").value = t.generacion
    document.getElementById("imagen").value = t.imagen || ""

    document.getElementById("cardFormulario").style.display = "block"
    document.getElementById("exitoFormu").style.display = "none"
    document.querySelectorAll(".seccion").forEach(sec => sec.style.display = "none")
    document.querySelectorAll(".btn-seccion").forEach(btn => btn.classList.remove("activo"))
    document.getElementById("sec-formulario").style.display = "block"
  }
})
