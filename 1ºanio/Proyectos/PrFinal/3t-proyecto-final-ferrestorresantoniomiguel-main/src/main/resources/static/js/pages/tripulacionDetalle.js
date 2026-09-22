import { TripulacionAPI} from "../api/tripulacionAPI.js"
import { crearNavbar } from "../components/navbar.js"
import { crearFooter } from "../components/footer.js"

document.addEventListener('DOMContentLoaded', async () => {
    document.getElementById('navbar').innerHTML = crearNavbar()
    document.getElementById('footer').innerHTML = crearFooter()
    const root = document.getElementById('root')

    const params = new URLSearchParams(window.location.search)
    const id = params.get('id')

    const tripulacion = await TripulacionAPI.obtenerPorId(id)
    const piratas = await TripulacionAPI.obtenerPiratas(id)
    const barcos = await TripulacionAPI.obtenerBarcos(id)

    let listaPiratas = piratas.length > 0
    ? piratas.map(p => `
        <div class="tarjeta-pirata">
          <div class="datos">
            <div class="cabecera">
              <h3 class="nombre">${p.nombreCompleto}</h3>
              <span class="alias">${p.alias || "-"}</span>
              <span class="estado ${p.estado ? 'vivo' : 'muerto'}">${p.estado ? 'Vivo' : 'Muerto'}</span>
            </div>
            <ul class="atributos">
              <li><span class="etiqueta">Función</span><span>${p.funcion || '-'}</span></li>
              <li><span class="etiqueta">Recompensa</span><span>${p.recompensa ? Number(p.recompensa).toLocaleString() + ' B' : 'Sin recompensa'}</span></li>
              <li><span class="etiqueta">Edad</span><span>${p.edad || '-'}</span></li>
              <li><span class="etiqueta">Altura</span><span>${p.altura ? p.altura + ' m' : '-'}</span></li>
            </ul>
            <div class="botones">
                <a class="btn-editar" href="pirataDetalle.html?id=${p.id}">Ver Detalles</a>
            </div>
          </div>
          <img class="imagen" src="${p.imagen || 'img/default.jpg'}" alt="${p.nombreCompleto}" onerror="this.src='img/default.jpg'">
        </div>`).join("")
    : `<p class="error">Esta tripulación no tiene piratas registrados</p>`

    let listaBarcos = barcos.length > 0
    ? barcos.map(b => `
        <div class="tarjeta-barco">
          <h3 class="nombre">${b.nombre}</h3>
          <span class="estado ${b.activo ? 'vivo' : 'muerto'}">${b.activo ? 'Activo' : 'Destruido'}</span>
          <a class="btn-editar" href="barcoDetalle.html?id=${b.id}">Ver Detalles</a>
        </div>`).join("")
    : `<p class="error">Esta tripulación no tiene barcos registrados</p>`

    root.innerHTML = `
    <div class="detalle-cabecera">
      <img class="detalle-imagen" src="${tripulacion.imagen || 'img/default.jpg'}" alt="${tripulacion.nombre}" onerror="this.src='img/default.jpg'">
      <div class="detalle-info">
        <h1 class="nombre">${tripulacion.nombre}</h1>
        <span class="alias">${tripulacion.generacion || ''}</span>
        <ul class="atributos">
          <li><span class="etiqueta">Miembros</span><span>${tripulacion.numeroMiembros || '-'}</span></li>
          <li><span class="etiqueta">Recompensa total</span><span>${tripulacion.recompensaTotal ? Number(tripulacion.recompensaTotal).toLocaleString() + ' B' : '-'}</span></li>
          <li><span class="etiqueta">Descripción</span><span>${tripulacion.descripcion || '-'}</span></li>
        </ul>
        <a class="btn-arriba" href="tripulaciones.html">← Volver a Tripulaciones</a>
      </div>
    </div>

    <h2 class="seccion-titulo">Piratas</h2>
    <div class="lista-piratas">${listaPiratas}</div>

    <h2 class="seccion-titulo">Barcos</h2>
    <div class="lista-barcos">${listaBarcos}</div>`
})