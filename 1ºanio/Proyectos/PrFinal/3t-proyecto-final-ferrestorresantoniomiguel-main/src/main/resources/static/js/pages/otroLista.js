import { OtroAPI } from "../api/otroAPI.js"
import { PersonajeAPI } from "../api/personajeAPI.js"
import { FrutaAPI } from "../api/frutaAPI.js"
import { crearNavbar } from "../components/navbar.js"
import { crearFooter } from "../components/footer.js"

document.addEventListener('DOMContentLoaded', async () => {
    document.getElementById('navbar').innerHTML = crearNavbar()
    document.getElementById('footer').innerHTML = crearFooter()
    const root = document.getElementById('root')
    let editandoId = null
    let frutasAntes = []

    root.innerHTML = `
    <div>
      <button class="btn-seccion" data-seccion="lista" onclick="verSeccion('lista')">Ver Personajes</button>
      <button class="btn-seccion" data-seccion="filtrarNombre" onclick="verSeccion('filtrarNombre')">Filtrar por Nombre</button>
      <button class="btn-seccion" data-seccion="filtrarLugar" onclick="verSeccion('filtrarLugar')">Filtrar por Lugar</button>
      <button class="btn-seccion" data-seccion="formulario" onclick="verSeccion('formulario')">Formulario</button>
    </div>

    <section id="sec-lista" class="seccion">
      <div id="contenidoLista"></div>
    </section>

    <section id="sec-filtrarNombre" class="seccion">
      <div class="card">
        <h2>Buscar Personaje por Nombre</h2>
        <div>
          <input id="inputFiltrarNombre" placeholder="Ej: Dr. Vegapunk">
        </div>
        <button onclick="filtrarPorNombre()">Buscar</button>
      </div>
      <div id="resultadoFiltrarNombre"></div>
    </section>

    <section id="sec-filtrarLugar" class="seccion">
      <div class="card">
        <h2>Filtrar por Lugar</h2>
        <div>
          <select id="selectLugar">
            <option value="">Selecciona un lugar</option>
            <option value="Alabasta">Alabasta</option>
            <option value="Wano">Wano</option>
            <option value="Punk Hazard">Punk Hazard</option>
            <option value="Egghead">Egghead</option>
            <option value="Skypie">Skypie</option>
            <option value="Dressrosa">Dressrosa</option>
          </select>
        </div>
        <button onclick="filtrarPorLugar()">Ver Personajes</button>
      </div>
      <div id="resultadoFiltrarLugar"></div>
    </section>

    <section id="sec-formulario" class="seccion">
      <div id="cardFormulario" class="card">
        <h2 id="tituloFormu">Nuevo Personaje</h2>
        <p id="mensajeFormu" class="error"></p>
        <div>
          <label>Nombre Completo</label>
          <input id="nombreCompleto" placeholder="Ej: Dr. Vegapunk">
        </div>
        <div>
          <label>Alias</label>
          <input id="alias" placeholder="Ej: El mejor científico">
        </div>
        <div>
          <label>Altura</label>
          <input id="altura" type="number" step="0.01" placeholder="Ej: 2.00">
        </div>
        <div>
          <label>Edad</label>
          <input id="edad" type="number" placeholder="Ej: 65">
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
          <label>Lugar</label>
          <select id="lugar">
            <option value="">Selecciona un lugar</option>
            <option value="Alabasta">Alabasta</option>
            <option value="Wano">Wano</option>
            <option value="Punk Hazard">Punk Hazard</option>
            <option value="Egghead">Egghead</option>
            <option value="Skypie">Skypie</option>
            <option value="Dressrosa">Dressrosa</option>
          </select>
        </div>
        <div>
          <label>Frutas del Diablo</label>
          <div id="checkboxFrutas"></div>
        </div>
        <div>
          <label>Imagen (ruta)</label>
          <input id="imagen" placeholder="Ej: img/otros/vegapunk.jpg">
        </div>
        <div>
          <button onclick="guardar()">Guardar</button>
          <button onclick="limpiarFormulario()">Cancelar</button>
        </div>
      </div>
      <div id="exitoFormu" style="display:none">
        <p id="mensajeExitoFormu" class="exito"></p>
        <button onclick="verSeccion('lista')">Ver lista</button>
        <button onclick="verSeccion('formulario')">Nuevo personaje</button>
      </div>
    </section>`

    document.querySelectorAll(".seccion").forEach(sec => sec.style.display = "none")

    window.verSeccion = (id) => {
        document.getElementById("resultadoFiltrarNombre").innerHTML = ""
        document.getElementById("resultadoFiltrarLugar").innerHTML = ""

        document.querySelectorAll(".seccion").forEach(sec => sec.style.display = "none")
        document.querySelectorAll(".btn-seccion").forEach(btn => btn.classList.remove("activo"))

        const btn = document.querySelector(`.btn-seccion[data-seccion="${id}"]`)
        if (btn) btn.classList.add("activo")

        document.getElementById("sec-" + id).style.display = "block"

        if (id === "lista") mostrarLista()
        else if (id === "formulario") limpiarFormulario()
    }

    async function mostrarLista() {
        const otros = await OtroAPI.obtenerTodos()

        document.getElementById("contenidoLista").innerHTML = `
          <div class="lista-otros">
            ${otros.map(o => crearTarjeta(o, 'lista')).join("")}
          </div>
          <div class="subir">
            <button class="btn-arriba" onclick="window.scrollTo({ top: 0, behavior: 'smooth' })">↑ Volver arriba</button>
          </div>`
    }

    function crearTarjeta(o, seccion = 'lista') {
        return `
        <div class="tarjeta-otro">
          <div class="datos">
            <div class="cabecera">
              <h3 class="nombre">${o.nombreCompleto}</h3>
              <span class="alias">${o.alias || "-"}</span>
              <span class="estado ${o.estado ? 'vivo' : 'muerto'}">${o.estado ? 'Vivo' : 'Muerto'}</span>
            </div>
            <ul class="atributos">
              <li><span class="etiqueta">Lugar</span><span>${o.lugar || '-'}</span></li>
              <li><span class="etiqueta">Edad</span><span>${o.edad || '-'}</span></li>
              <li><span class="etiqueta">Altura</span><span>${o.altura ? o.altura + ' m' : '-'}</span></li>
              <li><span class="etiqueta">Descripción</span><span>${o.descripcion || '-'}</span></li>
            </ul>
            <div class="botones">
              <a class="btn-editar" href="otroDetalle.html?id=${o.id}">Ver Detalles</a>
              <button class="btn-editar" onclick="editarOtro(${o.id})">Editar</button>
              <button class="btn-eliminar" onclick="eliminar(${o.id}, '${seccion}')">Eliminar</button>
            </div>
          </div>
          <img class="imagen" src="${o.imagen || 'img/default.jpg'}" alt="${o.nombreCompleto}">
        </div>`
    }

    async function cargarFrutas(marcados = []) {
        const frutas = await FrutaAPI.obtenerTodos()
        const contenedor = document.getElementById("checkboxFrutas")
        contenedor.innerHTML = frutas.map(f => `
          <label>
            <input type="checkbox" value="${f.id}" ${marcados.includes(f.id) ? 'checked' : ''}>
            ${f.nombre}
          </label>`).join("")
    }

    window.filtrarPorNombre = async () => {
        const nombre = document.getElementById("inputFiltrarNombre").value.trim()
        const resultado = document.getElementById("resultadoFiltrarNombre")
        resultado.innerHTML = ""

        if (!nombre) {
            resultado.innerHTML = `<p class="error">Introduce un nombre</p>`
            return
        }

        const otros = await OtroAPI.obtenerTodos()
        const encontrados = otros.filter(o => o.nombreCompleto.toLowerCase().includes(nombre.toLowerCase()))

        if (encontrados.length === 0) {
            resultado.innerHTML = `<p class="error">No hay ningún personaje con ese nombre</p>`
        } else {
            resultado.innerHTML = `<div class="lista-otros">${encontrados.map(o => crearTarjeta(o, 'filtrarNombre')).join("")}</div>`
        }
    }

    window.filtrarPorLugar = async () => {
        const lugar = document.getElementById("selectLugar").value
        const resultado = document.getElementById("resultadoFiltrarLugar")
        resultado.innerHTML = ""

        if (!lugar) {
            resultado.innerHTML = `<p class="error">Selecciona un lugar</p>`
            return
        }

        const otros = await OtroAPI.obtenerPorLugar(lugar)

        if (!otros || otros.length === 0) {
            resultado.innerHTML = `<p class="error">No hay personajes en ese lugar</p>`
        } else {
            resultado.innerHTML = `<div class="lista-otros">${otros.map(o => crearTarjeta(o, 'filtrarLugar')).join("")}</div>`
        }
    }

    window.eliminar = async (id, seccion) => {
        await OtroAPI.eliminar(id)
        if (seccion === 'filtrarNombre') {
            document.getElementById("resultadoFiltrarNombre").innerHTML = `<p class="exito">Personaje eliminado correctamente</p>`
        } else if (seccion === 'filtrarLugar') {
            document.getElementById("resultadoFiltrarLugar").innerHTML = `<p class="exito">Personaje eliminado correctamente</p>`
        } else {
            mostrarLista()
        }
    }

    function mensajeExito(texto) {
        document.getElementById("cardFormulario").style.display = "none"
        document.getElementById("exitoFormu").style.display = "block"
        document.getElementById("mensajeExitoFormu").textContent = texto
    }

    window.guardar = async () => {
        const nombreCompleto = document.getElementById("nombreCompleto").value.trim()
        const alias = document.getElementById("alias").value.trim()
        const altura = document.getElementById("altura").value
        const edad = document.getElementById("edad").value
        const descripcion = document.getElementById("descripcion").value.trim()
        const estado = document.getElementById("estado").value
        const lugar = document.getElementById("lugar").value
        const imagen = document.getElementById("imagen").value.trim()

        const frutasSeleccionadas = Array.from(
            document.querySelectorAll("#checkboxFrutas input[type='checkbox']:checked")
        ).map(cb => Number(cb.value))

        if (!nombreCompleto || !lugar) {
            document.getElementById("mensajeFormu").innerHTML = !nombreCompleto
                ? "El personaje debe tener nombre"
                : "El personaje debe tener lugar"
            return
        }

        const otro = {
            nombreCompleto, alias,
            altura: altura || null,
            edad: edad || null,
            descripcion,
            estado: estado === "Vivo",
            lugar,
            imagen: imagen || null
        }

        if (editandoId) {
            await OtroAPI.actualizar(editandoId, otro)

            const aEliminar = frutasAntes.filter(id => !frutasSeleccionadas.includes(id))
            const aAnadir = frutasSeleccionadas.filter(id => !frutasAntes.includes(id))

            for (const id of aEliminar) {
                await PersonajeAPI.eliminarFruta(editandoId, id)
            }
            for (const id of aAnadir) {
                await PersonajeAPI.anadirFruta(editandoId, id)
            }

            editandoId = null
            frutasAntes = []
            mensajeExito(`Personaje "${nombreCompleto}" editado correctamente`)
        } else {
            const creado = await OtroAPI.crear(otro)
            for (const id of frutasSeleccionadas) {
                await PersonajeAPI.anadirFruta(creado.id, id)
            }
            mensajeExito(`Personaje "${nombreCompleto}" creado correctamente`)
        }
    }

    async function limpiarFormulario() {
        editandoId = null
        frutasAntes = []

        document.getElementById("cardFormulario").style.display = "block"
        document.getElementById("exitoFormu").style.display = "none"
        document.getElementById("tituloFormu").textContent = "Nuevo Personaje"
        document.getElementById("mensajeFormu").innerHTML = ""

        document.getElementById("nombreCompleto").value = ""
        document.getElementById("alias").value = ""
        document.getElementById("altura").value = ""
        document.getElementById("edad").value = ""
        document.getElementById("descripcion").value = ""
        document.getElementById("estado").selectedIndex = 0
        document.getElementById("lugar").selectedIndex = 0
        document.getElementById("imagen").value = ""

        await cargarFrutas()
    }

    window.editarOtro = async (id) => {
        const otro = await OtroAPI.obtenerPorId(id)
        editandoId = id

        const frutas = await PersonajeAPI.obtenerFrutas(id)
        frutasAntes = frutas.map(f => f.id)

        document.getElementById("tituloFormu").textContent = "Editar Personaje"
        document.getElementById("nombreCompleto").value = otro.nombreCompleto
        document.getElementById("alias").value = otro.alias || ""
        document.getElementById("altura").value = otro.altura || ""
        document.getElementById("edad").value = otro.edad || ""
        document.getElementById("descripcion").value = otro.descripcion || ""
        document.getElementById("estado").value = otro.estado ? "Vivo" : "Muerto"
        document.getElementById("lugar").value = otro.lugar
        document.getElementById("imagen").value = otro.imagen || ""

        await cargarFrutas(frutasAntes)

        document.getElementById("cardFormulario").style.display = "block"
        document.getElementById("exitoFormu").style.display = "none"
        document.querySelectorAll(".seccion").forEach(sec => sec.style.display = "none")
        document.querySelectorAll(".btn-seccion").forEach(btn => btn.classList.remove("activo"))
        document.getElementById("sec-formulario").style.display = "block"
    }
})