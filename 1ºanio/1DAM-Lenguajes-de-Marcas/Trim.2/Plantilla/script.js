// ==========================================
// ⚙️ 1. CONFIGURACIÓN (El Proveedor)
// ==========================================
// Reemplaza esto con la URL principal de la nueva API
const URL_BASE = "https://api-ejemplo.com/v1";

// ==========================================
// 🛠️ 2. CONEXIÓN CON HTML (Los utensilios del comedor)
// ==========================================

// Búsqueda principal (Ej: por nombre, por título)
const formFiltro1 = document.getElementById("form-filtro1");
const inputFiltro1 = document.getElementById("input-filtro1");
const resultadoFiltro1 = document.getElementById("resultado-filtro1");

// Búsqueda por categorías (Ej: botones de regiones, géneros, tipos)
const botonesCategoria = document.querySelectorAll(".btn-categoria");
const resultadoCategoria = document.getElementById("resultado-categoria");

// Búsqueda secundaria (Ej: por capital, por director, por ciudad)
const formFiltro2 = document.getElementById("form-filtro2");
const inputFiltro2 = document.getElementById("input-filtro2");
const resultadoFiltro2 = document.getElementById("resultado-filtro2");

// Búsqueda exacta (Ej: por código postal, por ID, por DNI)
const formFiltroExacto = document.getElementById("form-filtro-exacto");
const inputFiltroExacto = document.getElementById("input-filtro-exacto");
const resultadoFiltroExacto = document.getElementById("resultado-filtro-exacto");

// Botón para traer todo
const btnTodos = document.getElementById("btn-todos");
const resultadoTodos = document.getElementById("resultado-todos");

// ==========================================
// 🧹 3. UTILIDADES (Mantenimiento y Recadero)
// ==========================================

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
            throw new Error("Error en la petición");
        }

    } catch (error) {
        console.error("Fallo al obtener datos:", error);
    }

    return datos; // Devuelve los datos o null si falló
}

// ==========================================
// 🔪 4. EXTRACCIÓN DE DATOS (Preparar ingredientes)
// ==========================================
// Adapta estas funciones según la estructura del JSON que recibas.
// Son útiles cuando un dato viene anidado, es un array o puede no existir.

function obtenerDatoComplejoA(item) {
    let resultado = "No disponible";
    // Ejemplo: si item.propiedad existe, la unimos con comas
    if (item.propiedad_array) {
        resultado = item.propiedad_array.join(", ");
    }
    return resultado;
}

function obtenerDatoComplejoB(item) {
    let resultado = "No disponible";
    // Ejemplo: si es un objeto con claves dinámicas
    if (item.propiedad_objeto) {
        resultado = Object.values(item.propiedad_objeto).join(", ");
    }
    return resultado;
}

// ==========================================
// 🎨 5. TARJETAS (El emplatado)
// ==========================================
// Aquí decides cómo se inyectan los datos en el HTML

function crearTarjetaDetalle(item) {
    // Reemplaza item.imagen, item.titulo, etc., por las propiedades reales de tu API
    return `
        <article class="tarjeta-detalle">
            <img src="${item.imagen || 'ruta-por-defecto.jpg'}" alt="Imagen">
            <div>
                <h3>${item.titulo}</h3>
                <p><strong>Dato 1:</strong> ${item.dato1}</p>
                <p><strong>Dato procesado:</strong> ${obtenerDatoComplejoA(item)}</p>
            </div>
        </article>
    `;
}

function crearTarjetaResumen(item) {
    return `
        <article class="tarjeta">
            <h3>${item.titulo}</h3>
            <p>Dato breve: ${item.dato_breve}</p>
        </article>
    `;
}

// ==========================================
// 👨‍🍳 6. FUNCIONES PRINCIPALES (Las recetas del Chef)
// ==========================================

async function buscarPorFiltroPrincipal(termino) {
    limpiarContenedor(resultadoFiltro1);

    if (!termino) {
        mostrarError(resultadoFiltro1, "Introduce un término de búsqueda");
    } else {
        // Adapta la ruta (endpoint) a lo que exija tu API
        const datos = await obtenerDatos(`${URL_BASE}/ruta-busqueda/${termino}`);

        if (datos) {
            // A veces la API devuelve un array, a veces un objeto. Ajusta el índice si es necesario.
            resultadoFiltro1.innerHTML = crearTarjetaDetalle(datos[0]);
        } else {
            mostrarError(resultadoFiltro1, "Elemento no encontrado");
        }
    }
}

async function buscarPorCategoria(categoria) {
    limpiarContenedor(resultadoCategoria);

    const datos = await obtenerDatos(`${URL_BASE}/ruta-categoria/${categoria}`);

    if (datos) {
        let html = "";
        // Si vienen muchos resultados, podemos limitarlos con .slice()
        datos.slice(0, 12).forEach(item => {
            html += crearTarjetaResumen(item);
        });
        resultadoCategoria.innerHTML = html;
    } else {
        mostrarError(resultadoCategoria, "Error al cargar la categoría");
    }
}

async function buscarPorFiltroSecundario(termino) {
    limpiarContenedor(resultadoFiltro2);

    if (!termino) {
        mostrarError(resultadoFiltro2, "Introduce un valor");
    } else {
        const datos = await obtenerDatos(`${URL_BASE}/ruta-filtro-2/${termino}`);

        if (datos) {
            resultadoFiltro2.innerHTML = crearTarjetaDetalle(datos[0]);
        } else {
            mostrarError(resultadoFiltro2, "No encontrado");
        }
    }
}

async function buscarPorFiltroExacto(id_o_codigo) {
    limpiarContenedor(resultadoFiltroExacto);

    if (!id_o_codigo) {
        mostrarError(resultadoFiltroExacto, "Introduce un código válido");
    } else {
        const datos = await obtenerDatos(`${URL_BASE}/ruta-id/${id_o_codigo}`);

        if (datos) {
            const item = Array.isArray(datos) ? datos[0] : datos;
            resultadoFiltroExacto.innerHTML = crearTarjetaDetalle(item);
        } else {
            mostrarError(resultadoFiltroExacto, "Código no válido");
        }
    }
}

async function cargarTodos() {
    limpiarContenedor(resultadoTodos);

    const datos = await obtenerDatos(`${URL_BASE}/ruta-todos`);

    if (datos) {
        let html = "";
        datos.slice(0, 12).forEach(item => {
            html += crearTarjetaResumen(item);
        });
        resultadoTodos.innerHTML = html;
    } else {
        mostrarError(resultadoTodos, "Error al cargar la lista general");
    }
}

// ==========================================
// 🛎️ 7. EVENTOS (Los camareros tomando nota)
// ==========================================

formFiltro1.addEventListener("submit", e => {
    e.preventDefault();
    buscarPorFiltroPrincipal(inputFiltro1.value.trim().toLowerCase());
});

botonesCategoria.forEach(boton => {
    boton.addEventListener("click", () => {
        // Asume que los botones tienen un atributo data-categoria="valor"
        buscarPorCategoria(boton.dataset.categoria);
    });
});

formFiltro2.addEventListener("submit", e => {
    e.preventDefault();
    buscarPorFiltroSecundario(inputFiltro2.value.trim().toLowerCase());
});

formFiltroExacto.addEventListener("submit", e => {
    e.preventDefault();
    buscarPorFiltroExacto(inputFiltroExacto.value.trim().toLowerCase());
});

btnTodos.addEventListener("click", cargarTodos);