import { GobiernoMundialAPI } from "../api/gobiernoMundialAPI.js"
import { PersonajeAPI } from "../api/personajeAPI.js"
import { crearNavbar } from "../components/navbar.js"
import { crearFooter } from "../components/footer.js"


document.addEventListener("DOMContentLoaded", async () => {

  document.getElementById("navbar").innerHTML = crearNavbar()
  document.getElementById("footer").innerHTML = crearFooter()

  const root = document.getElementById("root")
  const params = new URLSearchParams(window.location.search)
  const id = params.get("id")

  const agente = await GobiernoMundialAPI.obtenerPorId(id)
  const frutas = await PersonajeAPI.obtenerFrutas(id)

  const listaFrutas = frutas.length > 0
    ? frutas.map(f => `
        <div class="tarjeta-fruta">
          <div class="cabecera">
            <h3 class="nombre">${f.nombre}</h3>
            <span class="tipo ${f.tipo.toLowerCase()}">${f.tipo}</span>
          </div>
          <p class="descripcion">${f.descripcion || 'Sin descripción'}</p>
          <div class="botones">
            <a class="btn-editar" href="frutaDetalle.html?id=${f.id}">Ver Detalles</a>
          </div>
        </div>`).join("")
    : `<p class="error">Este agente no tiene frutas del diablo</p>`

  root.innerHTML = `
    <div class="detalle-cabecera">
      <img class="detalle-imagen" src="${agente.imagen || 'img/default.jpg'}" alt="${agente.nombreCompleto}">
      <div class="detalle-info">
        <h1 class="nombre">${agente.nombreCompleto}</h1>
        <span class="alias">${agente.alias || ''}</span>
        <span class="estado ${agente.estado ? 'vivo' : 'muerto'}">${agente.estado ? 'Vivo' : 'Muerto'}</span>
        <ul class="atributos">
          <li><span class="etiqueta">Rango</span><span>${agente.rango || '-'}</span></li>
          <li><span class="etiqueta">Edad</span><span>${agente.edad || '-'}</span></li>
          <li><span class="etiqueta">Altura</span><span>${agente.altura ? agente.altura + ' m' : '-'}</span></li>
          <li><span class="etiqueta">Descripción</span><span>${agente.descripcion || '-'}</span></li>
        </ul>
        <a class="btn-arriba" href="gobiernoMundial.html">← Volver a Gobierno Mundial</a>
      </div>
    </div>

    <h2 class="seccion-titulo">Frutas del Diablo</h2>
    <div class="lista-frutas">${listaFrutas}</div>`

})