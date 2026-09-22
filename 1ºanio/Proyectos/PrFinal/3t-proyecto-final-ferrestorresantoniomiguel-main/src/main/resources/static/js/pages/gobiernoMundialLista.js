import { GobiernoMundialAPI } from "../api/gobiernoMundialAPI.js"
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
            <button class="btn-seccion" data-seccion="lista" onclick="verSeccion('lista')">Ver Agentes</button>
            <button class="btn-seccion" data-seccion="filtrarNombre" onclick="verSeccion('filtrarNombre')">Filtrar por Nombre</button>
            <button class="btn-seccion" data-seccion="filtrarRango" onclick="verSeccion('filtrarRango')">Filtrar por Rango</button>
            <button class="btn-seccion" data-seccion="formulario" onclick="verSeccion('formulario')">Formulario</button>
        </div>

        <section id="sec-lista" class="seccion">
            <div id="contenidoLista"></div>
        </section>

        <section id="sec-filtrarNombre" class="seccion">
            <div class="card">
                <h2>Buscar Agente por Nombre</h2>
                <div>
                    <input id="inputFiltrarNombre" placeholder="Ej: Sakazuki">
                </div>
                <button onclick="filtrarPorNombre()">Buscar</button>
            </div>
            <div id="resultadoFiltrarNombre"></div>
        </section>

        <section id="sec-filtrarRango" class="seccion">
            <div class="card">
                <h2>Filtrar por Rango</h2>
                <div>
                    <select id="selectRango">
                        <option value="">Selecciona un rango</option>
                        <option value="Cinco Ancianos">Cinco Ancianos</option>
                        <option value="Almirante de Flota">Almirante de Flota</option>
                        <option value="Almirante">Almirante</option>
                        <option value="Vicealmirante">Vicealmirante</option>
                        <option value="Agente CP0">Agente CP0</option>
                        <option value="Ex-Almirante de Flota">Ex-Almirante de Flota</option>
                        <option value="Ex-Almirante">Ex-Almirante</option>
                        <option value="Ex-Cinco Ancianos">Ex-Cinco Ancianos</option>
                        <option value="Otro">Otro</option>
                    </select>
                </div>
                <button onclick="filtrarPorRango()">Ver Agentes</button>
            </div>
            <div id="resultadoFiltrarRango"></div>
        </section>

        <section id="sec-formulario" class="seccion">
          <div id="cardFormulario" class="card">
            <h2 id="tituloFormu">Nuevo Agente</h2>
            <p id="mensajeFormu" class="error"></p>
            <div>
              <label>Nombre Completo</label>
              <input id="nombreCompleto" placeholder="Ej: Sakazuki">
            </div>
            <div>
              <label>Alias</label>
              <input id="alias" placeholder="Ej: Akainu">
            </div>
            <div>
              <label>Altura</label>
              <input id="altura" type="number" step="0.01" placeholder="Ej: 3.06">
            </div>
            <div>
              <label>Edad</label>
              <input id="edad" type="number" placeholder="Ej: 55">
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
              <label>Rango</label>
              <select id="rango">
                <option value="Cinco Ancianos">Cinco Ancianos</option>
                <option value="Almirante de Flota">Almirante de Flota</option>
                <option value="Almirante">Almirante</option>
                <option value="Vicealmirante">Vicealmirante</option>
                <option value="Agente CP0">Agente CP0</option>
                <option value="Ex-Almirante de Flota">Ex-Almirante de Flota</option>
                <option value="Ex-Almirante">Ex-Almirante</option>
                <option value="Ex-Cinco Ancianos">Ex-Cinco Ancianos</option>
                <option value="Otro">Otro</option>
              </select>
            </div>
            <div>
              <label>Imagen (ruta)</label>
              <input id="imagen" placeholder="Ej: img/gobierno/akainu.jpg">
            </div>
            <div>
              <label>Frutas del Diablo</label>
              <div id="checkboxFrutas"></div>
            </div>
            <div>
              <button onclick="guardar()">Guardar</button>
              <button onclick="limpiarFormulario()">Cancelar</button>
            </div>
          </div>
          <div id="exitoFormu" style="display:none">
            <p id="mensajeExitoFormu" class="exito"></p>
            <button onclick="verSeccion('lista')">Ver lista</button>
            <button onclick="verSeccion('formulario')">Nuevo agente</button>
          </div>
        </section>`

    document.querySelectorAll(".seccion").forEach(sec => sec.style.display = "none")

    window.verSeccion = (id) => {
        document.getElementById("resultadoFiltrarNombre").innerHTML = ""
        document.getElementById("resultadoFiltrarRango").innerHTML = ""

        document.querySelectorAll(".seccion").forEach(sec => sec.style.display = "none")
        document.querySelectorAll(".btn-seccion").forEach(btn => btn.classList.remove("activo"))

        const btn = document.querySelector(`.btn-seccion[data-seccion="${id}"]`)
        if (btn) btn.classList.add("activo")

        document.getElementById("sec-" + id).style.display = "block"

        if (id === "lista") mostrarLista()
        else if (id === "formulario") limpiarFormulario()
    }

    async function mostrarLista() {
        const agentes = await GobiernoMundialAPI.obtenerTodos()

        document.getElementById("contenidoLista").innerHTML = `
          <div class="lista-agentes">
            ${agentes.map(a => crearTarjeta(a, 'lista')).join("")}
          </div>
          <div class="subir">
            <button class="btn-arriba" onclick="window.scrollTo({ top: 0, behavior: 'smooth' })">↑ Volver arriba</button>
          </div>`
    }

    function crearTarjeta(a, seccion = 'lista') {
        return `
        <div class="tarjeta-agente">
          <div class="datos">
            <div class="cabecera">
              <h3 class="nombre">${a.nombreCompleto}</h3>
              <span class="alias">${a.alias || "-"}</span>
              <span class="estado ${a.estado ? 'vivo' : 'muerto'}">${a.estado ? 'Vivo' : 'Muerto'}</span>
            </div>
            <ul class="atributos">
              <li><span class="etiqueta">Rango</span><span>${a.rango || '-'}</span></li>
              <li><span class="etiqueta">Edad</span><span>${a.edad || '-'}</span></li>
              <li><span class="etiqueta">Altura</span><span>${a.altura ? a.altura + ' m' : '-'}</span></li>
              <li><span class="etiqueta">Descripción</span><span>${a.descripcion || '-'}</span></li>
            </ul>
            <div class="botones">
              <a class="btn-editar" href="gobiernoMundialDetalle.html?id=${a.id}">Ver Detalles</a>
              <button class="btn-editar" onclick="editarAgente(${a.id})">Editar</button>
              <button class="btn-eliminar" onclick="eliminar(${a.id}, '${seccion}')">Eliminar</button>
            </div>
          </div>
          <img class="imagen" src="${a.imagen || 'img/default.jpg'}" alt="${a.nombreCompleto}">
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

        const agentes = await GobiernoMundialAPI.obtenerTodos()
        const encontrados = agentes.filter(a => a.nombreCompleto.toLowerCase().includes(nombre.toLowerCase()))

        if (encontrados.length === 0) {
            resultado.innerHTML = `<p class="error">No hay ningún agente con ese nombre</p>`
        } else {
            resultado.innerHTML = `<div class="lista-agentes">${encontrados.map(a => crearTarjeta(a, 'filtrarNombre')).join("")}</div>`
        }
    }

    window.filtrarPorRango = async () => {
        const rango = document.getElementById("selectRango").value
        const resultado = document.getElementById("resultadoFiltrarRango")

        resultado.innerHTML = ""

        if (!rango) {
            resultado.innerHTML = `<p class="error">Selecciona un rango</p>`
            return
        }

        const agentes = await GobiernoMundialAPI.obtenerPorRango(rango)

        if (!agentes || agentes.length === 0) {
            resultado.innerHTML = `<p class="error">No hay agentes con ese rango</p>`
        } else {
            resultado.innerHTML = `<div class="lista-agentes">${agentes.map(a => crearTarjeta(a, 'filtrarRango')).join("")}</div>`
        }
    }

    async function cargarFrutas(marcados = []) {
        const frutas = await FrutaAPI.obtenerTodos()
        document.getElementById("checkboxFrutas").innerHTML = frutas.map(f => `
        <label class="checkbox-item">
          <input type="checkbox" value="${f.id}" ${marcados.includes(String(f.id)) ? "checked" : ""}>
          <div class="checkbox-info">
            <span class="checkbox-nombre">${f.nombre}</span>
            <span class="checkbox-alias">${f.tipo}</span>
          </div>
        </label>`).join("")
    }

    window.guardar = async () => {
        const nombreCompleto = document.getElementById("nombreCompleto").value.trim()
        const alias = document.getElementById("alias").value.trim()
        const altura = document.getElementById("altura").value
        const edad = document.getElementById("edad").value
        const descripcion = document.getElementById("descripcion").value.trim()
        const estado = document.getElementById("estado").value
        const rango = document.getElementById("rango").value
        const imagen = document.getElementById("imagen").value.trim()

        const marcados = document.querySelectorAll("#checkboxFrutas input:checked")
        const nuevosIds = Array.from(marcados).map(cb => cb.value)

        if (!nombreCompleto) {
            document.getElementById("mensajeFormu").innerHTML = "El agente debe tener nombre"
            return
        }

        const agente = {
            nombreCompleto, alias,
            altura: altura || null,
            edad: edad || null,
            descripcion,
            estado: estado === "Vivo",
            rango,
            imagen: imagen || null
        }

        if (editandoId) {
            await GobiernoMundialAPI.actualizar(editandoId, agente)

            for (const id of frutasAntes) {
                if (!nuevosIds.includes(id)) {
                    await PersonajeAPI.eliminarFruta(editandoId, id)
                }
            }
            for (const id of nuevosIds) {
                if (!frutasAntes.includes(id)) {
                    await PersonajeAPI.anadirFruta(editandoId, id)
                }
            }

            editandoId = null
            frutasAntes = []
            mensajeExito(`Agente "${nombreCompleto}" editado correctamente`)
        } else {
            const nuevo = await GobiernoMundialAPI.crear(agente)

            for (const id of nuevosIds) {
                await PersonajeAPI.anadirFruta(nuevo.id, id)
            }

            mensajeExito(`Agente "${nombreCompleto}" creado correctamente`)
        }
    }

    window.eliminar = async (id, seccion) => {
        await GobiernoMundialAPI.eliminar(id)
        if (seccion === 'filtrarNombre') {
            document.getElementById("resultadoFiltrarNombre").innerHTML = `<p class="exito">Agente eliminado correctamente</p>`
        } else if (seccion === 'filtrarRango') {
            document.getElementById("resultadoFiltrarRango").innerHTML = `<p class="exito">Agente eliminado correctamente</p>`
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
        document.getElementById("tituloFormu").textContent = "Nuevo Agente"
        document.getElementById("mensajeFormu").innerHTML = ""

        document.getElementById("nombreCompleto").value = ""
        document.getElementById("alias").value = ""
        document.getElementById("altura").value = ""
        document.getElementById("edad").value = ""
        document.getElementById("descripcion").value = ""
        document.getElementById("estado").value = "Vivo"
        document.getElementById("rango").selectedIndex = 0
        document.getElementById("imagen").value = ""

        cargarFrutas()
    }

    window.editarAgente = async (id) => {
        const agente = await GobiernoMundialAPI.obtenerPorId(id)
        const frutas = await PersonajeAPI.obtenerFrutas(id)
        editandoId = id
        frutasAntes = frutas.map(f => String(f.id))

        document.getElementById("tituloFormu").textContent = "Editar Agente"
        document.getElementById("nombreCompleto").value = agente.nombreCompleto
        document.getElementById("alias").value = agente.alias || ""
        document.getElementById("altura").value = agente.altura || ""
        document.getElementById("edad").value = agente.edad || ""
        document.getElementById("descripcion").value = agente.descripcion || ""
        document.getElementById("estado").value = agente.estado ? "Vivo" : "Muerto"
        document.getElementById("rango").value = agente.rango
        document.getElementById("imagen").value = agente.imagen || ""
        document.getElementById("mensajeFormu").innerHTML = ""

        await cargarFrutas(frutasAntes)

        document.getElementById("cardFormulario").style.display = "block"
        document.getElementById("exitoFormu").style.display = "none"
        document.querySelectorAll(".seccion").forEach(sec => sec.style.display = "none")
        document.querySelectorAll(".btn-seccion").forEach(btn => btn.classList.remove("activo"))
        document.getElementById("sec-formulario").style.display = "block"
    }
})