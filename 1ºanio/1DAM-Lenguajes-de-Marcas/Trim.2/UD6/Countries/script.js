// script.js

// URL base de la API
const apiUrl = "https://restcountries.com/v3.1/";

// Función para obtener datos país por su nombre
async function obtenerPais(nombreOId) {
    try {
        const respuesta = await fetch(`${apiUrl}name/${nombreOId}`);
        if (!respuesta.ok) {
            throw new Error("No se encontró el País");
        }
        const datos = await respuesta.json();
        mostrarPaís(datos);
    } catch (error) {
        console.error("Error al obtener el País:", error);
    }
}

// Función para mostrar los datos del Pokémon en la página
function mostrarPaís(datos) {
    const contenedor = document.getElementById("pais-info");

    contenedor.innerHTML = `
        <h2>${datos.name.official.toUpperCase()}</h2>
        <p><strong>Región: </strong> ${datos.region}</p>
        <p><strong>Capital: </strong> ${datos.capital}</p>
        <p><strong>Subregión: </strong> ${datos.subregion}</p>
        <p><strong>Nombre (común): </strong> ${datos.name.common}</p>

    `
}

// Manejo del formulario de búsqueda
document.getElementById("form-nombre").addEventListener("submit", function (event) {
    event.preventDefault();
    const input = document.getElementById("input-nombre").value.trim().toLowerCase();
    if (input) {
        obtenerPais(input);
    }
});