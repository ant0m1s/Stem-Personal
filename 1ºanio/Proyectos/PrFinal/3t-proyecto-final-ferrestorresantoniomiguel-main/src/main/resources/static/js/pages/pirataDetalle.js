import { PirataAPI } from "../api/pirataAPI.js"
import { crearNavbar } from "../components/navbar.js"
import { crearFooter } from "../components/footer.js"

document.addEventListener("DOMContentLoaded", async () => {

  document.getElementById("navbar").innerHTML = crearNavbar()
  document.getElementById("footer").innerHTML = crearFooter()

  const root = document.getElementById("root")
  const params = new URLSearchParams(window.location.search)
  const id = params.get("id")

  const pirata = await PirataAPI.obtenerPorId(id)

  const listaFrutas = pirata.frutas && pirata.frutas.length > 0
    ? pirata.frutas.map(f => `
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
    : `<p class="error">Este pirata no tiene frutas del diablo</p>`

  root.innerHTML = `
    <div class="detalle-cabecera">
      <img class="detalle-imagen" src="${pirata.imagen || 'img/default.jpg'}" alt="${pirata.nombreCompleto}" onerror="this.src='img/default.jpg'">
      <div class="detalle-info">
        <h1 class="nombre">${pirata.nombreCompleto}</h1>
        <span class="alias">${pirata.alias || ''}</span>
        <span class="estado ${pirata.estado ? 'vivo' : 'muerto'}">${pirata.estado ? 'Vivo' : 'Muerto'}</span>
        <ul class="atributos">
          <li><span class="etiqueta">Función</span><span>${pirata.funcion || '-'}</span></li>
          <li><span class="etiqueta">Generación</span><span>${pirata.generacion || '-'}</span></li>
          <li><span class="etiqueta">Tripulación</span><span>${pirata.tripulacion ? pirata.tripulacion.nombre : 'Sin tripulación'}</span></li>
          <li><span class="etiqueta">Recompensa</span><span>${pirata.recompensa ? Number(pirata.recompensa).toLocaleString() + ' B' : 'Sin recompensa'}</span></li>
          <li><span class="etiqueta">Edad</span><span>${pirata.edad || '-'}</span></li>
          <li><span class="etiqueta">Altura</span><span>${pirata.altura ? pirata.altura + ' m' : '-'}</span></li>
          <li><span class="etiqueta">Descripción</span><span>${pirata.descripcion || '-'}</span></li>
        </ul>
        <a class="btn-arriba" href="piratas.html">← Volver a Piratas</a>
      </div>
    </div>

    <h2 class="seccion-titulo">Frutas del Diablo</h2>
    <div class="lista-frutas">${listaFrutas}</div>`

})