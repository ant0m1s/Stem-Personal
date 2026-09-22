import { PirataAPI } from "../api/pirataAPI.js"
import { TripulacionAPI } from "../api/tripulacionAPI.js"
import { crearNavbar } from "../components/navbar.js"
import { crearFooter } from "../components/footer.js"
import { FrutaAPI } from "../api/frutaAPI.js"
import { PersonajeAPI } from "../api/personajeAPI.js"


document.addEventListener("DOMContentLoaded", async () => {

  document.getElementById("navbar").innerHTML = crearNavbar()
  document.getElementById("footer").innerHTML = crearFooter()

  const root = document.getElementById("root")
  let editandoId = null
  let generacion = null
  let frutasAntes = []

  root.innerHTML = `
    <div>
      <button class="btn-generacion" data-gen="Generación Antigua" onclick="elegirGeneracion('Generación Antigua')">Generación Antigua</button>
      <button class="btn-generacion" data-gen="Generación de Oro" onclick="elegirGeneracion('Generación de Oro')">Generación de Oro</button>
      <button class="btn-generacion" data-gen="Peor Generación" onclick="elegirGeneracion('Peor Generación')">Peor Generación</button>
    </div>

    <div id="contenido-generacion" style="display:none">
      <div>
        <button class="btn-seccion" data-seccion="lista" onclick="verSeccion('lista')">Ver Piratas</button>
        <button class="btn-seccion" data-seccion="filtrarNombre" onclick="verSeccion('filtrarNombre')">Filtrar por Nombre</button>
        <button class="btn-seccion" data-seccion="filtrarTripulacion" onclick="verSeccion('filtrarTripulacion')">Filtrar por Tripulación</button>
        <button class="btn-seccion" data-seccion="formulario" onclick="verSeccion('formulario')">Formulario</button>
        <button class="btn-seccion">Minijuego</button>
      </div>

      <section id="sec-lista" class="seccion">
        <div id="contenidoLista"></div>
      </section>

      <section id="sec-filtrarNombre" class="seccion">
        <div class="card">
          <h2>Buscar Pirata por Nombre</h2>
          <div>
            <input id="inputFiltrarNombre" placeholder="Ej: Monkey D. Luffy">
          </div>
          <button onclick="filtrarPorNombre()">Buscar</button>
        </div>
        <div id="resultadoFiltrarNombre"></div>
      </section>

      <section id="sec-filtrarTripulacion" class="seccion">
        <div class="card">
          <h2>Filtrar por Tripulación</h2>
          <div>
            <select id="selectTripulacion">
              <option value="">Seleccina tripulación</option>
            </select>
          </div>
          <button onclick="filtrarPorTripulacion()">Ver piratas</button>
        </div>
        <div id="resultadoFiltrarTripulacion"></div>
      </section>

      <section id="sec-formulario" class="seccion">
        <div id="cardFormulario" class="card">
          <h2 id="tituloFormu">Nuevo Pirata</h2>
          <p id="mensajeFormu" class="error"></p>
          <div>
            <label>Nombre completo</label>
            <input id="nombreCompleto" placeholder="Ej: Monkey D. Luffy" required>
          </div>
          <div>
            <label>Alias</label>
            <input id="alias" placeholder="Ej: Sombrero de Paja">
          </div>
          <div>
            <label>Altura</label>
            <input id="altura" placeholder="Ej: 1.74">
          </div>
          <div>
            <label>Edad</label>
            <input id="edad" type="number" placeholder="Ej: 19">
          </div>
          <div>
            <label>Descripción</label>
            <input id="descripcion" placeholder="Breve descripción">
          </div>
          <div>
            <label>Estado</label>
            <select id="estado">
              <option value="Vivo">Vivo</option>
              <option value="Muerto">Muerto</option>
            </select>
          </div>
          <div>
            <label>Recompensa (Berry)</label>
            <input id="recompensa" type="number" placeholder="Ej: 3000000000">
          </div>
          <div>
            <label>Función</label>
            <input id="funcion" placeholder="Ej: Capitán">
          </div>
          <div>
            <label>Generación</label>
            <select id="generacion">
              <option value="Generación Antigua">Generación Antigua</option>
              <option value="Generación de Oro">Generación de Oro</option>
              <option value="Peor Generación">Peor Generación</option>
            </select>
          </div>
          <div>
            <label>Imagen (optional)</label>
            <input id="imagen" placeholder="Placeholder">
          </div>
          <div>
            <label>Tripulación</label>
            <select id="tripulacion">
              <option>Sin tripulación</option>
            </select>
          </div>
          <div>
            <label>Frutas del Diablo</label>
            <div id="checkboxFrutas"></div>
          </div>
          <div>
            <button onclick="guardar()">Guardar</button>
            <button onclick="cancelar()">Cancelar</button>
          </div>
        </div>
        <div id="exitoFormu" style="display:none">
          <p id="mensajeExitoFormu" class="exito"></p>
          <button onclick="verSeccion('lista')">Ver lista</button>
          <button onclick="verSeccion('formulario')">Nuevo pirata</button>
        </div>
      </section>
    </div>`


  document.querySelectorAll(".seccion").forEach(sec => sec.style.display = "none")

  // botones de generacion
  window.elegirGeneracion = (gen) => {
      generacion = gen

      document.querySelectorAll(".btn-generacion").forEach(btn => btn.classList.remove("activo"))
      document.querySelector(`.btn-generacion[data-gen="${gen}"]`).classList.add("activo")

      document.getElementById("contenido-generacion").style.display = "block"
      document.querySelectorAll(".seccion").forEach(sec => sec.style.display = "none")
      document.querySelectorAll(".btn-seccion").forEach(btn => btn.classList.remove("activo"))
  }

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
    } else if (id === "filtrarTripulacion") {
        cargarTripulacionesFiltro()
    } else if (id === "formulario") {
        limpiarFormulario()
        await cargarTripulaciones(generacion)
        await cargarFrutas()
    }
  }


  // carga la lista de piratas segun la generacion
  async function mostrarLista() {
      const piratas = await PirataAPI.obtenerPorGeneracion(generacion)

      document.getElementById("contenidoLista").innerHTML = `
      <div class="lista-piratas">
        ${piratas.map(p => crearTarjeta(p)).join("")}
      </div>
      <div class="subir">
        <button class="btn-arriba" onclick="window.scrollTo({ top: 0, behavior: 'smooth' })">↑ Volver arriba</button>
      </div>`
  }

  function crearTarjeta(p, seccion = 'lista') {
    return `
    <div class="tarjeta-pirata">
      <div class="datos">
        <div class="cabecera">
          <h3 class="nombre">${p.nombreCompleto}</h3>
          <span class="alias">${p.alias || "-"}</span>
          <span class="estado ${p.estado ? 'vivo' : 'muerto'}">${p.estado ? 'Vivo' : 'Muerto'}</span>
        </div>
        <ul class="atributos">
          <li><span class="etiqueta">Función</span><span>${p.funcion || '-'}</span></li>
          <li><span class="etiqueta">Tripulación</span><span>${p.tripulacion ? p.tripulacion.nombre : 'Sin tripulación'}</span></li>
          <li><span class="etiqueta">Recompensa</span><span>${p.recompensa ? Number(p.recompensa).toLocaleString() + ' B' : 'Sin recompensa'}</span></li>
          <li><span class="etiqueta">Edad</span><span>${p.edad || '-'}</span></li>
          <li><span class="etiqueta">Altura</span><span>${p.altura ? p.altura + ' m' : '-'}</span></li>
        </ul>
        <div class="botones">
          <a class="btn-editar" href="pirataDetalle.html?id=${p.id}">Ver Detalles</a>
          <button class="btn-editar" onclick="editarPirata(${p.id})">Editar</button>
          <button class="btn-eliminar" onclick="eliminar(${p.id}, '${seccion}')">Eliminar</button>
        </div>
      </div>
      <img class="imagen" src="${p.imagen || "img/default.jpg"}" alt="${p.nombreCompleto}">
    </div>`
  }


  window.filtrarPorNombre = async () => {
      const nombre = document.getElementById("inputFiltrarNombre").value.trim()
      const resultado = document.getElementById("resultadoFiltrarNombre")

      resultado.innerHTML = ""
      if (!nombre) {
        resultado.innerHTML = `<p class="error">Introduce un nombre</p>`
        return
      }

      const piratas = await PirataAPI.obtenerPorGeneracion(generacion)
      const encontrados = piratas.filter(p => p.nombreCompleto.toLowerCase().includes(nombre.toLowerCase()))

      if (encontrados.length === 0) {
        resultado.innerHTML = `<p class="error">No hay piratas con ese nombre</p>`
      } else {
        resultado.innerHTML = `<div>${encontrados.map(p => crearTarjeta(p, 'filtrarNombre')).join("")}</div>`
      }
  }

  async function cargarTripulacionesFiltro() {
    const tripulaciones = await TripulacionAPI.obtenerPorGeneracion(generacion)
    document.getElementById("selectTripulacion").innerHTML =
        `<option value="">Selecciona una tripulación</option>` +
        tripulaciones.map(t => `<option value="${t.id}">${t.nombre}</option>`).join("")
  }

  window.filtrarPorTripulacion = async () => {
      const tripulacionID = document.getElementById("selectTripulacion").value
      const resultado = document.getElementById("resultadoFiltrarTripulacion")

      resultado.innertHTML = ""
      if (!tripulacionID) {
        resultado.innerHTML = `<p class="error">Selecciona una tripulación</p>`
        return
      }

      const piratas = await TripulacionAPI.obtenerPiratas(tripulacionID)
      if (!piratas || piratas.length === 0) {
        resultado.innerHTML = `<p class="error">Esta tripulación no tiene piratas</p>`
      } else {
        resultado.innerHTML = `<div>${piratas.map(p => crearTarjeta(p, 'filtrarTripulacion')).join("")}</div>`
      }
  }


  // guardar pirata nuevo o editado
  window.guardar = async () => {

    const nombreCompleto = document.getElementById("nombreCompleto").value.trim()
    const alias = document.getElementById("alias").value.trim()
    const altura = document.getElementById("altura").value
    const edad = document.getElementById("edad").value
    const descripcion = document.getElementById("descripcion").value.trim()
    const estado = document.getElementById("estado").value
    const recompensa = document.getElementById("recompensa").value
    const funcion = document.getElementById("funcion").value.trim()
    const gen = document.getElementById("generacion").value
    const imagen = document.getElementById("imagen").value.trim()
    const tripulacionID = document.getElementById("tripulacion").value

    const frutasMarcadas = Array.from(document.querySelectorAll("#checkboxFrutas input:checked"))
        .map(cb => parseInt(cb.value))

    if (!nombreCompleto || !funcion) {
        document.getElementById("mensajeFormu").innerHTML = !nombreCompleto
            ? "El personaje debe tener nombre"
            : "El personaje debe tener función"
        return
    }

    const pirata = {
        nombreCompleto,
        alias,
        altura: altura || null,
        fechaNac: edad,
        descripcion,
        estado: estado === "Vivo",
        recompensa: recompensa || null,
        funcion,
        generacion: gen,
        imagen: imagen || null,
        tripulacion: tripulacionID ? { id: tripulacionID } : null
    }

    if (editandoId) {
        await PirataAPI.actualizar(editandoId, pirata)

        const quitar = frutasAntes.filter(id => !frutasMarcadas.includes(id))
        const agregar = frutasMarcadas.filter(id => !frutasAntes.includes(id))

        for (const id of quitar) await PersonajeAPI.eliminarFruta(editandoId, id)
        for (const id of agregar) await PersonajeAPI.anadirFruta(editandoId, id)

        editandoId = null
        frutasAntes = []
        mensajeExito(`Pirata "${nombreCompleto}" editado correctamente`)

    } else {
        const nuevo = await PirataAPI.crear(pirata)
        for (const id of frutasMarcadas) {
            await PersonajeAPI.anadirFruta(nuevo.id, id)
        }
        mensajeExito(`Pirata "${nombreCompleto}" creado correctamente`)
    }
  }


  window.eliminar = async (id, seccion) => {
    await PirataAPI.eliminar(id)
    if (seccion === 'filtrarNombre') {
      document.getElementById("resultadoFiltrarNombre").innerHTML = `<p class="exito">Pirata eliminado correctamente</p>`
    } else if (seccion === 'filtrarTripulacion') {
      document.getElementById("resultadoFiltrarTripulacion").innerHTML = `<p class="exito">Pirata eliminado correctamente</p>`
    } else {
      mostrarLista()
    }
  }

  function mensajeExito(texto) {
      document.getElementById("cardFormulario").style.display = "none"
      document.getElementById("exitoFormu").style.display = "block"
      document.getElementById("mensajeExitoFormu").textContent = texto
  }

  function limpiarFormulario() {
    editandoId = null
    frutasAntes = []

    document.getElementById("cardFormulario").style.display = "block"
    document.getElementById("exitoFormu").style.display = "none"
    document.getElementById("tituloFormu").textContent = "Nuevo Pirata"
    document.getElementById("mensajeFormu").innerHTML = ""

    document.getElementById("nombreCompleto").value = ""
    document.getElementById("alias").value = ""
    document.getElementById("altura").value = ""
    document.getElementById("edad").value = ""
    document.getElementById("descripcion").value = ""
    document.getElementById("estado").value = "Vivo"
    document.getElementById("recompensa").value = ""
    document.getElementById("funcion").value = ""
    document.getElementById("imagen").value = ""
    document.getElementById("tripulacion").value = ""
    document.getElementById("checkboxFrutas").innerHTML = ""
  }

  window.cancelar = () => {
      limpiarFormulario()
      verSeccion("lista")
  }


  // editar pirata existente
  window.editarPirata = async (id) => {
    const pirata = await PirataAPI.obtenerPorId(id)
    editandoId = id
    frutasAntes = (pirata.frutas || []).map(f => f.id)

    document.getElementById("tituloFormu").textContent = "Editar Pirata"
    document.getElementById("nombreCompleto").value = pirata.nombreCompleto
    document.getElementById("alias").value = pirata.alias || ""
    document.getElementById("altura").value = pirata.altura || ""
    document.getElementById("edad").value = pirata.edad || ""
    document.getElementById("descripcion").value = pirata.descripcion || ""
    document.getElementById("estado").value = pirata.estado ? "Vivo" : "Muerto"
    document.getElementById("recompensa").value = pirata.recompensa || ""
    document.getElementById("funcion").value = pirata.funcion || ""
    document.getElementById("generacion").value = pirata.generacion
    document.getElementById("imagen").value = pirata.imagen || ""

    await cargarTripulaciones(pirata.generacion)
    document.getElementById("tripulacion").value = pirata.tripulacion ? pirata.tripulacion.id : ""
    await cargarFrutas(frutasAntes)

    document.getElementById("cardFormulario").style.display = "block"
    document.getElementById("exitoFormu").style.display = "none"
    document.querySelectorAll(".seccion").forEach(sec => sec.style.display = "none")
    document.querySelectorAll(".btn-seccion").forEach(btn => btn.classList.remove("activo"))
    document.getElementById("sec-formulario").style.display = "block"
  }

  async function cargarTripulaciones(gen) {
      const tripulaciones = await TripulacionAPI.obtenerPorGeneracion(gen)
      document.getElementById("tripulacion").innerHTML =
        `<option value="">Sin tripulación</option>` +
        tripulaciones.map(t => `<option value="${t.id}">${t.nombre}</option>`).join("")
  }

  async function cargarFrutas(marcadas = []) {
    const marcadasStr = marcadas.map(String)
    const frutas = await FrutaAPI.obtenerTodos()
    document.getElementById("checkboxFrutas").innerHTML = frutas.map(f => `
      <label class="checkbox-item">
        <input type="checkbox" value="${f.id}" ${marcadasStr.includes(String(f.id)) ? "checked" : ""}>
        <img class="checkbox-img" src="${f.imagen || 'img/frutas/sinImagen.jpg'}" alt="${f.nombre}" onerror="this.src='img/frutas/sinImagen.jpg'">
        <div class="checkbox-info">
          <span class="checkbox-nombre">${f.nombre}</span>
          <span class="tipo ${f.tipo.toLowerCase()}">${f.tipo}</span>
        </div>
      </label>`).join("")
  }

})
