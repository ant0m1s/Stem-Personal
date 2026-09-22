import { OtroAPI } from "../api/otroAPI.js"
import { PersonajeAPI } from "../api/personajeAPI.js"
import { crearNavbar } from "../components/navbar.js"
import { crearFooter } from "../components/footer.js"


document.addEventListener("DOMContentLoaded", async () => {

  document.getElementById("navbar").innerHTML = crearNavbar()
  document.getElementById("footer").innerHTML = crearFooter()

  const root = document.getElementById("root")
  const params = new URLSearchParams(window.location.search)
  const id = params.get("id")

  const [otro, frutas] = await Promise.all([
    OtroAPI.obtenerPorId(id),
    PersonajeAPI.obtenerFrutas(id)
  ])

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
    : `<p class="error">Este personaje no tiene frutas del diablo</p>`

  root.innerHTML = `
    <div class="detalle-cabecera">
      <img class="detalle-imagen" src="${otro.imagen || 'img/default.jpg'}" alt="${otro.nombreCompleto}" onerror="this.src='img/default.jpg'">
      <div class="detalle-info">
        <h1 class="nombre">${otro.nombreCompleto}</h1>
        <span class="alias">${otro.alias || ''}</span>
        <span class="estado ${otro.estado ? 'vivo' : 'muerto'}">${otro.estado ? 'Vivo' : 'Muerto'}</span>
        <ul class="atributos">
          <li><span class="etiqueta">Oficio</span><span>${otro.oficio || '-'}</span></li>
          <li><span class="etiqueta">Edad</span><span>${otro.edad || '-'}</span></li>
          <li><span class="etiqueta">Altura</span><span>${otro.altura ? otro.altura + ' m' : '-'}</span></li>
          <li><span class="etiqueta">Descripción</span><span>${otro.descripcion || '-'}</span></li>
        </ul>
        <a class="btn-arriba" href="otros.html">← Volver a Otros Personajes</a>
      </div>
    </div>

    <h2 class="seccion-titulo">Frutas del Diablo</h2>
    <div class="lista-frutas">${listaFrutas}</div>`

})