import { BarcoAPI } from "../api/barcoAPI.js"
import { crearNavbar } from "../components/navbar.js"
import { crearFooter } from "../components/footer.js"


document.addEventListener("DOMContentLoaded", async () => {

  document.getElementById("navbar").innerHTML = crearNavbar()
  document.getElementById("footer").innerHTML = crearFooter()

  const root = document.getElementById("root")
  const params = new URLSearchParams(window.location.search)
  const id = params.get("id")

  const barco = await BarcoAPI.obtenerPorId(id)

  const infoTripulacion = barco.tripulacion
    ? `
      <div class="tarjeta-tripulacion">
        <div class="datos">
          <div class="cabecera">
            <h3 class="nombre">${barco.tripulacion.nombre}</h3>
            <span class="alias">${barco.tripulacion.generacion || ''}</span>
          </div>
          <ul class="atributos">
            <li><span class="etiqueta">Miembros</span><span>${barco.tripulacion.numeroMiembros || '-'}</span></li>
            <li><span class="etiqueta">Recompensa total</span><span>${barco.tripulacion.recompensaTotal ? Number(barco.tripulacion.recompensaTotal).toLocaleString() + ' B' : '-'}</span></li>
          </ul>
          <div class="botones">
            <a class="btn-editar" href="tripulacionDetalle.html?id=${barco.tripulacion.id}">Ver Tripulación</a>
          </div>
        </div>
        <img class="imagen" src="${barco.tripulacion.imagen || 'img/default.jpg'}" alt="${barco.tripulacion.nombre}">
      </div>`
    : `<p class="error">Este barco no tiene tripulación asignada</p>`

  root.innerHTML = `
    <div class="detalle-cabecera">
      <img class="detalle-imagen" src="${barco.imagen || 'img/default.jpg'}" alt="${barco.nombre}">
      <div class="detalle-info">
        <h1 class="nombre">${barco.nombre}</h1>
        <span class="estado ${barco.activo ? 'vivo' : 'muerto'}">${barco.activo ? 'Activo' : 'Destruido'}</span>
        <a class="btn-arriba" href="barcos.html">← Volver a Barcos</a>
      </div>
    </div>

    <h2 class="seccion-titulo">Tripulación</h2>
    <div class="lista-tripulaciones">${infoTripulacion}</div>`

})