import { FrutaAPI } from "../api/frutaAPI.js"
import { crearNavbar } from "../components/navbar.js"
import { crearFooter } from "../components/footer.js"


document.addEventListener("DOMContentLoaded", async () => {

  document.getElementById("navbar").innerHTML = crearNavbar()
  document.getElementById("footer").innerHTML = crearFooter()

  const root = document.getElementById("root")
  const params = new URLSearchParams(window.location.search)
  const id = params.get("id")

  const fruta = await FrutaAPI.obtenerPorId(id)

  const listaPortadores = fruta.personajes && fruta.personajes.length > 0
    ? fruta.personajes.map(p => `
        <div class="tarjeta-pirata">
          <div class="datos">
            <div class="cabecera">
              <h3 class="nombre">${p.nombreCompleto}</h3>
              <span class="alias">${p.alias || '-'}</span>
              <span class="estado ${p.estado ? 'vivo' : 'muerto'}">${p.estado ? 'Vivo' : 'Muerto'}</span>
            </div>
            <div class="botones">
              <a class="btn-editar" href="pirataDetalle.html?id=${p.id}">Ver Detalle</a>
            </div>
          </div>
          <img class="imagen" src="${p.imagen || 'img/default.jpg'}" alt="${p.nombreCompleto}" onerror="this.src='img/default.jpg'">
        </div>`).join("")
    : `<p class="error">Esta fruta no tiene portadores registrados</p>`

  root.innerHTML = `
    <div class="detalle-cabecera">
      <img class="detalle-imagen" src="${fruta.imagen || 'img/default.jpg'}" alt="${fruta.nombre}" onerror="this.src='img/default.jpg'">
      <div class="detalle-info">
        <h1 class="nombre">${fruta.nombre}</h1>
        <span class="tipo ${fruta.tipo.toLowerCase()}">${fruta.tipo}</span>
        <ul class="atributos">
          <li><span class="etiqueta">Descripción</span><span>${fruta.descripcion || '-'}</span></li>
        </ul>
        <a class="btn-arriba" href="frutas.html">← Volver a Frutas</a>
      </div>
    </div>

    <h2 class="seccion-titulo">Portadores</h2>
    <div class="lista-piratas">${listaPortadores}</div>`

})