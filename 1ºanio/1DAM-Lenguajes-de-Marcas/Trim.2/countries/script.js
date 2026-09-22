const URL_BASE = "https://restcountries.com/v3.1";

const formNombre = document.getElementById("form-nombre");
const inputNombre = document.getElementById("input-nombre");
const resultadoNombre = document.getElementById("resultado-nombre");

const botonesRegion = document.querySelectorAll(".btn-region");
const resultadoRegion = document.getElementById("resultado-region");

const formCapital = document.getElementById("form-capital");
const inputCapital = document.getElementById("input-capital");
const resultadoCapital = document.getElementById("resultado-capital");

const formCodigo = document.getElementById("form-codigo");
const inputCodigo = document.getElementById("input-codigo");
const resultadoCodigo = document.getElementById("resultado-codigo");

const btnTodos = document.getElementById("btn-todos");
const resultadoTodos = document.getElementById("resultado-todos");

// 🔹 UTILIDADES

function limpiarContenedor(contenedor) {
    contenedor.innerHTML = "";
}

function mostrarError(contenedor, mensaje) {
    contenedor.innerHTML = `<p class="mensaje-error">${mensaje}</p>`;
}

async function obtenerDatos(url) {
    let datos = null;

    try {
        const respuesta = await fetch(url);

        if (respuesta.ok) {
            datos = await respuesta.json();
        } else {
            throw new Error("Error");
        }

    } catch (error) {
        console.error(error);
    }

    return datos;
}

// 🔹 EXTRACCIÓN DE DATOS

function obtenerCapital(pais) {
    let resultado = "No disponible";

    if (pais.capital) {
        resultado = pais.capital.join(", ");
    }

    return resultado;
}

function obtenerIdiomas(pais) {
    let resultado = "No disponible";

    if (pais.languages) {
        resultado = Object.values(pais.languages).join(", ");
    }

    return resultado;
}

function obtenerMonedas(pais) {
    let resultado = "No disponible";

    if (pais.currencies) {
        let texto = "";

        Object.values(pais.currencies).forEach(moneda => {
            const nombre = moneda.name || "Sin nombre";
            const simbolo = moneda.symbol ? ` (${moneda.symbol})` : "";
            texto += `${nombre}${simbolo}, `;
        });

        resultado = texto.slice(0, -2);
    }

    return resultado;
}

function obtenerContinentes(pais) {
    let resultado = "No disponible";

    if (pais.continents) {
        resultado = pais.continents.join(", ");
    }

    return resultado;
}

function obtenerZonasHorarias(pais) {
    let resultado = "No disponible";

    if (pais.timezones) {
        resultado = pais.timezones.join(", ");
    }

    return resultado;
}

// 🔹 TARJETAS

function crearTarjetaDetallePais(pais) {
    let html = `
        <article class="tarjeta-detalle">
            <img src="${pais.flags.svg}">
            <div>
                <h3>${pais.name.common}</h3>
                <p><strong>Nombre oficial:</strong> ${pais.name.official}</p>
                <p><strong>Capital:</strong> ${obtenerCapital(pais)}</p>
                <p><strong>Región:</strong> ${pais.region}</p>
                <p><strong>Subregión:</strong> ${pais.subregion || "No disponible"}</p>
                <p><strong>Población:</strong> ${pais.population.toLocaleString()}</p>
                <p><strong>Idiomas:</strong> ${obtenerIdiomas(pais)}</p>
                <p><strong>Monedas:</strong> ${obtenerMonedas(pais)}</p>
            </div>
        </article>
    `;

    return html;
}

function crearTarjetaRegion(pais) {
    let html = `
        <article class="tarjeta">
            <img src="${pais.flags.svg}">
            <h3>${pais.name.common}</h3>
            <p>Capital: ${obtenerCapital(pais)}</p>
            <p>Población: ${pais.population.toLocaleString()}</p>
        </article>
    `;

    return html;
}

function crearTarjetaCapital(pais) {
    let html = `
        <article class="tarjeta-detalle">
            <img src="${pais.flags.svg}">
            <div>
                <h3>${pais.name.common}</h3>
                <p>Capital: ${obtenerCapital(pais)}</p>
                <p>Región: ${pais.region}</p>
                <a href="${pais.maps.googleMaps}" target="_blank">Ver mapa</a>
            </div>
        </article>
    `;

    return html;
}

function crearTarjetaCodigo(pais) {
    let html = `
        <article class="tarjeta-detalle">
            <img src="${pais.flags.svg}">
            <div>
                <h3>${pais.name.common}</h3>
                <p>CCA2: ${pais.cca2}</p>
                <p>CCA3: ${pais.cca3}</p>
                <p>Continentes: ${obtenerContinentes(pais)}</p>
                <p>Zonas horarias: ${obtenerZonasHorarias(pais)}</p>
            </div>
        </article>
    `;

    return html;
}

function crearTarjetaListaRapida(pais) {
    let html = `
        <article class="tarjeta">
            <img src="${pais.flags.svg}">
            <h3>${pais.name.common}</h3>
            <p>Capital: ${obtenerCapital(pais)}</p>
            <p>Población: ${pais.population.toLocaleString()}</p>
        </article>
    `;

    return html;
}

// 🔹 FUNCIONES PRINCIPALES

async function buscarPaisPorNombre(nombre) {
    limpiarContenedor(resultadoNombre);

    if (!nombre) {
        mostrarError(resultadoNombre, "Introduce un país");
    } else {
        const datos = await obtenerDatos(`${URL_BASE}/name/${nombre}`);

        if (datos) {
            resultadoNombre.innerHTML = crearTarjetaDetallePais(datos[0]);
        } else {
            mostrarError(resultadoNombre, "País no encontrado");
        }
    }
}

async function buscarPorRegion(region) {
    limpiarContenedor(resultadoRegion);

    const datos = await obtenerDatos(`${URL_BASE}/region/${region}`);

    if (datos) {
        let html = "";

        datos.slice(0, 12).forEach(pais => {
            html += crearTarjetaRegion(pais);
        });

        resultadoRegion.innerHTML = html;
    } else {
        mostrarError(resultadoRegion, "Error al cargar región");
    }
}

async function buscarPorCapital(capital) {
    limpiarContenedor(resultadoCapital);

    if (!capital) {
        mostrarError(resultadoCapital, "Introduce una capital");
    } else {
        const datos = await obtenerDatos(`${URL_BASE}/capital/${capital}`);

        if (datos) {
            resultadoCapital.innerHTML = crearTarjetaCapital(datos[0]);
        } else {
            mostrarError(resultadoCapital, "Capital no encontrada");
        }
    }
}

async function buscarPorCodigo(codigo) {
    limpiarContenedor(resultadoCodigo);

    if (!codigo) {
        mostrarError(resultadoCodigo, "Introduce un código");
    } else {
        const datos = await obtenerDatos(`${URL_BASE}/alpha/${codigo}`);

        if (datos) {
            const pais = Array.isArray(datos) ? datos[0] : datos;
            resultadoCodigo.innerHTML = crearTarjetaCodigo(pais);
        } else {
            mostrarError(resultadoCodigo, "Código no válido");
        }
    }
}

async function cargarTodos() {
    limpiarContenedor(resultadoTodos);

    const datos = await obtenerDatos(`${URL_BASE}/all?fields=name,flags,capital,population`);

    if (datos) {
        let html = "";

        datos.slice(0, 12).forEach(pais => {
            html += crearTarjetaListaRapida(pais);
        });

        resultadoTodos.innerHTML = html;
    } else {
        mostrarError(resultadoTodos, "Error al cargar países");
    }
}

// 🔹 EVENTOS

formNombre.addEventListener("submit", e => {
    e.preventDefault();
    buscarPaisPorNombre(inputNombre.value.trim().toLowerCase());
});

botonesRegion.forEach(boton => {
    boton.addEventListener("click", () => {
        buscarPorRegion(boton.dataset.region);
    });
});

formCapital.addEventListener("submit", e => {
    e.preventDefault();
    buscarPorCapital(inputCapital.value.trim().toLowerCase());
});

formCodigo.addEventListener("submit", e => {
    e.preventDefault();
    buscarPorCodigo(inputCodigo.value.trim().toLowerCase());
});

btnTodos.addEventListener("click", cargarTodos);