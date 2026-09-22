import { fetchApi } from "../utils/apiUtils.js";

export const PersonajeAPI = {
    obtenerTodos: () => fetchApi('GET', `/personajes`),
    obtenerPorId: (id) => fetchApi('GET', `/personajes/${id}`),
    obtenerFrutas: (id) => fetchApi('GET', `/personajes/${id}/frutas`),
    anadirFruta: (id, frutaId) => fetchApi('POST', `/personajes/${id}/frutas/${frutaId}`),
    eliminarFruta: (id, frutaId) => fetchApi('DELETE', `/personajes/${id}/frutas/${frutaId}`),
    eliminar: (id) => fetchApi('DELETE', `/personajes/${id}`)
}