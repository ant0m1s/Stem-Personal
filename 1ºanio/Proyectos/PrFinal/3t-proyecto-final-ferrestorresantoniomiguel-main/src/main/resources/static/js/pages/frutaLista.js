import { FrutaAPI } from "../api/frutaAPI.js"
import { PersonajeAPI } from "../api/personajeAPI.js"
import { crearNavbar } from "../components/navbar.js"
import { crearFooter } from "../components/footer.js"


document.addEventListener("DOMContentLoaded", async () => {

  document.getElementById("navbar").innerHTML = crearNavbar()
  document.getElementById('footer').innerHTML = crearFooter()

  const root = document.getElementById("root")
  let editandoId = null
  let portadoresAntes = []

  root.innerHTML = `
          <div class="section-buttons">
          <button class="btn-seccion" data-seccion="listar" onclick="verSeccion('listar')">Listar Frutas</button>
            <button class="btn-seccion" data-seccion="filtrar" onclick="verSeccion('filtrar')">Filtrar por Nombre</button>
            <button class="btn-seccion" data-seccion="filtrarPortador" onclick="verSeccion('filtrarPortador')">Filtrar por Portador</button>
            <button class="btn-seccion" data-seccion="formulario" onclick="verSeccion('formulario')">Formulario</button>
            <button class="btn-seccion">Minijuego</button>
          </div>
          
          <section id="sec-listar" class="seccion">
            <div id="contenidoListar"></div>
          </section>

          <section id="sec-filtrar" class="seccion">
            <div class="card">
              <h2>Buscar Fruta por Nombre</h2>
              <div>
                <input id="inputFiltrar" placeholder="Ej: Gomu Gomu no Mi (Modelo Nika)">
              </div>
              <button onclick="filtrarPorNombre()">Buscar</button>
            </div>
            <div id="resultadoFiltrar"></div>
          </section>

          <section id="sec-filtrarPortador" class="seccion">
            <div class="card">
              <h2>Filtrar por Portador</h2>
              <div>
                <select id="selectPortador">
                  <option value="">Selecciona un personaje</option>
                </select>
              </div>
              <button onclick="filtrarPorPortador()">Ver Frutas</button>
            </div>
            <div id="resultadoFiltrarPortador"></div>
          </section>

          <section id="sec-formulario" class="seccion">
            <div id="cardFormulario" class="card">
              <h2 id="tituloFormu">Nueva Fruta</h2>
              <p id="mensajeFormu" class="error"></p>
              <div>
                <label>Nombre</label>
                <input id="nombreFruta" placeholder="Ej: Gomu Gomu no Mi (Modelo Nika)">
              </div>
              <div>
                <label>Descripción</label>
                <input id="descripcionFruta" placeholder="Breve Descripción">
              </div>
              <div>
                <label>Tipo</label>
                <select id="tipoFruta">
                  <option value="Paramecia">Paramecia</option>
                  <option value="Logia">Logia</option>
                  <option value="Zoan">Zoan</option>
                </select>
              </div>
              <div>
                <label>Imagen (ruta)</label>
                <input id="imagenFruta" placeholder="Ej: img/frutas/gomuGomu.jpg">
              </div>
              <div>
                <label>Personajes Portadores</label>
                <div id="checkboxPersonajes"></div>
              </div>
              <div>
                <button onclick="guardar()">Guardar</button>
                <button onclick="limpiarFormulario()">Cancelar</button>
              </div>
            </div>
            <div id="exitoFormu" style="display:none">
              <p id="mensajeExitoFormu" class="exito"></p>
              <button onclick="limpiarFormulario()">Volver al formulario</button>
            </div>
          </section>`


  document.querySelectorAll(".seccion").forEach(sec => sec.style.display = "none")

  window.verSeccion = (id) => {
      document.querySelectorAll(".seccion").forEach(sec => sec.style.display = "none")
      document.querySelectorAll(".btn-seccion").forEach(btn => btn.classList.remove("activo"))

      document.getElementById("sec-" + id).style.display = "block"
      document.querySelector(`.btn-seccion[data-seccion="${id}"]`).classList.add("activo")

      if (id === "listar") {
        mostrarLista()
      } else if (id === "formulario") {
        limpiarFormulario()
      } else if (id === "filtrarPortador") {
        cargarPortadoresFiltrar()
      }
  }


  async function mostrarLista() {
    const frutas = await FrutaAPI.obtenerTodos()

    const tarjetas = frutas.map(f => `
      <div class="tarjeta-fruta">
        <img class="imagen" src="${f.imagen || 'img/frutas/sinImagen.jpg'}" alt="${f.nombre}">
        <div class="cabecera">
          <h3 class="nombre">${f.nombre}</h3>
          <span class="tipo ${f.tipo.toLowerCase()}">${f.tipo}</span>
        </div>
        <p class="descripcion">${f.descripcion || 'Sin descripción'}</p>
        <div class="botones">
          <a class="btn-editar" href="frutaDetalle.html?id=${f.id}">Ver Detalle</a>
          <button class="btn-editar" onclick="editarFruta(${f.id})">Editar</button>
          <button class="btn-eliminar" onclick="eliminar(${f.id}, 'listar')">Eliminar</button>
        </div>
      </div>`).join("")

    document.getElementById("contenidoListar").innerHTML = `
      <div class="lista-frutas">${tarjetas}</div>
      <div class="subir">
        <button class="btn-arriba" onclick="window.scrollTo({ top: 0, behavior: 'smooth' })">↑ Volver arriba</button>
      </div>`
  }


  // guardar fruta nueva o editada
  window.guardar = async () => {

    const nombre = document.getElementById("nombreFruta").value.trim()
    const descripcion = document.getElementById("descripcionFruta").value.trim()
    const tipo = document.getElementById("tipoFruta").value
    const imagen = document.getElementById("imagenFruta").value.trim()

    const marcados = document.querySelectorAll("#checkboxPersonajes input:checked")
    const nuevosIds = Array.from(marcados).map(cb => cb.value)

    if (!nombre) {
        document.getElementById("mensajeFormu").innerHTML = "Nombre vacío"
        return
    }

    if (editandoId) {
      const fruta = {nombre, descripcion, tipo, imagen: imagen || null}
      await FrutaAPI.actualizar(editandoId, fruta)

      for (const id of portadoresAntes) {
          if (!nuevosIds.includes(id)) {
            await PersonajeAPI.eliminarFruta(id, editandoId)
          }
      }

      for (const id of nuevosIds) {
        if (!portadoresAntes.includes(id)) {
            await PersonajeAPI.anadirFruta(id, editandoId)
        }
      }

      editandoId = null
      portadoresAntes = []
      mensajeExito("Fruta editada correctamente")

    } else {
        const fruta = {nombre, descripcion, tipo, imagen: imagen || null}
        const nueva = await FrutaAPI.crear(fruta)

        for (const id of nuevosIds) {
          await PersonajeAPI.anadirFruta(id, nueva.id)
        }

        mensajeExito("Fruta agregada correctamente")
    }
  }

  function mensajeExito(texto) {
    document.getElementById("cardFormulario").style.display = "none"
    document.getElementById("exitoFormu").style.display = "block"
    document.getElementById("mensajeExitoFormu").textContent = texto
  }

  async function buscar(inputId, divId) {
      const nombre = document.getElementById(inputId).value.trim()
      const resultado = document.getElementById(divId)
      let fruta = null

      if (!nombre) {
        resultado.innerHTML = `<p class="error">Introduce un nombre</p>`
      } else {
          fruta = await FrutaAPI.obtenerPorNombre(nombre)
          if (!fruta) {
            resultado.innerHTML = `<p class="error">No hay ninguna fruta con ese nombre</p>`
          }
      }

      return fruta
  }

  window.filtrarPorNombre = async () => {
    const fruta = await buscar("inputFiltrar", "resultadoFiltrar")

    if (fruta) {
      let portadores = `<p class="descripcion">Sin portadores</p>`

      if (fruta.personajes && fruta.personajes.length > 0) {
          portadores = fruta.personajes.map(p => {
            const url = p.rango ? `gobiernoMundialDetalle.html?id=${p.id}`
                      : p.oficio ? `otroDetalle.html?id=${p.id}`
                      : `pirataDetalle.html?id=${p.id}`
            return `
          <div class="tarjeta-portador">
            <img class="imagen-portador" src="${p.imagen || 'img/default.jpg'}" alt="${p.nombreCompleto}" onerror="this.src='img/default.jpg'">
            <div class="info-portador">
              <span class="nombre">${p.nombreCompleto}</span>
              <span class="alias">${p.alias || ''}</span>
              <span class="estado ${p.estado ? 'vivo' : 'muerto'}">${p.estado ? 'Vivo' : 'Muerto'}</span>
              <a class="btn-editar" href="${url}">Ver Detalles</a>
            </div>
          </div>`
          }).join("")
      }

      document.getElementById("resultadoFiltrar").innerHTML = `
        <div class="tarjeta-fruta">
          <img class="imagen" src="${fruta.imagen || 'img/frutas/sinImagen.jpg'}" alt="${fruta.nombre}" onerror="this.src='img/frutas/sinImagen.jpg'">
          <div class="cabecera">
            <h3 class="nombre">${fruta.nombre}</h3>
            <span class="tipo ${fruta.tipo.toLowerCase()}">${fruta.tipo}</span>
          </div>
          <p class="descripcion">${fruta.descripcion || 'Sin descripción'}</p>
          <div class="portadores">
            <span class="etiqueta">Portadores</span>
            ${portadores}
          </div>
          <div class="botones">
            <a class="btn-editar" href="frutaDetalle.html?id=${fruta.id}">Ver Detalle</a>
            <button class="btn-editar" onclick="editarFruta(${fruta.id})">Editar</button>
            <button class="btn-eliminar" onclick="eliminar(${fruta.id}, 'filtrar')">Eliminar</button>
          </div>
        </div>`
    }
  }

  async function cargarPersonajes(marcados = []) {
      const personajes = await PersonajeAPI.obtenerTodos()
      document.getElementById("checkboxPersonajes").innerHTML = personajes.map(p => `
      <label class="checkbox-item">
        <input type="checkbox" value="${p.id}" ${marcados.includes(String(p.id)) ? "checked" : ""}>
        <img class="checkbox-img" src="${p.imagen || 'img/default.jpg'}" alt="${p.nombreCompleto}">
        <div class="checkbox-info">
          <span class="checkbox-nombre">${p.nombreCompleto}</span>
          <span class="checkbox-alias">${p.alias || ''}</span>
        </div>
      </label>`).join("")
  }

  async function cargarPortadoresFiltrar() {
    const personajes = await PersonajeAPI.obtenerTodos()
    document.getElementById("selectPortador").innerHTML =
      `<option value="">Selecciona un personaje</option>` +
      personajes.map(p => `<option value="${p.id}">${p.nombreCompleto}</option>`).join("")
  }

  window.filtrarPorPortador = async () => {
    const portadorId = document.getElementById("selectPortador").value
    const resultado = document.getElementById("resultadoFiltrarPortador")

    if (!portadorId) {
      resultado.innerHTML = `<p class="error">Selecciona un personaje</p>`
      return
    }

    const frutas = await PersonajeAPI.obtenerFrutas(portadorId)

    if (!frutas || frutas.length === 0) {
      resultado.innerHTML = `<p class="error">Este personaje no tiene frutas del diablo</p>`
    } else {
      resultado.innerHTML = `<div class="lista-frutas">${frutas.map(f => `
        <div class="tarjeta-fruta">
          ${f.imagen ? `<img class="fruta-img" src="${f.imagen}" alt="${f.nombre}" onerror="this.src='img/default.jpg'">` : ''}
          <div class="cabecera">
            <h3 class="nombre">${f.nombre}</h3>
            <span class="tipo ${f.tipo.toLowerCase()}">${f.tipo}</span>
          </div>
          <p class="descripcion">${f.descripcion || 'Sin descripción'}</p>
          <div class="botones">
            <a class="btn-editar" href="frutaDetalle.html?id=${f.id}">Ver Detalles</a>
          </div>
        </div>`).join("")}</div>`
    }
  }

  window.editarFruta = async (id) => {
    const fruta = await FrutaAPI.obtenerPorId(id)
    verSeccion("formulario")

    editandoId = id
    portadoresAntes = fruta.personajes ? fruta.personajes.map(p => String(p.id)) : []

    document.getElementById("tituloFormu").textContent = "Editar Fruta"
    document.getElementById("nombreFruta").value = fruta.nombre
    document.getElementById("descripcionFruta").value = fruta.descripcion
    document.getElementById("tipoFruta").value = fruta.tipo
    document.getElementById("mensajeFormu").innerHTML = ""
    document.getElementById("imagenFruta").value = fruta.imagen || ""

    await cargarPersonajes(portadoresAntes)
  }

  window.eliminar = async (id, seccion) => {
      await FrutaAPI.eliminar(id)
      if (seccion === "filtrar") {
        document.getElementById("resultadoFiltrar").innerHTML = `<p class="exito">Fruta eliminada correctamente</p>`
      }
      if (seccion === "listar") mostrarLista()
  }

  window.limpiarFormulario = () => {
    editandoId = null
    portadoresAntes = []

    document.getElementById("cardFormulario").style.display = "block"
    document.getElementById("exitoFormu").style.display = "none"
    document.getElementById("tituloFormu").textContent = "Nueva Fruta"
    document.getElementById("nombreFruta").value = ""
    document.getElementById("descripcionFruta").value = ""
    document.getElementById("tipoFruta").value = "Paramecia"
    document.getElementById("mensajeFormu").innerHTML = ""
    document.getElementById("imagenFruta").value = ""

    cargarPersonajes()
  }

})
