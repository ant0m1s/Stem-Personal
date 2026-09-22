import { fetchApi } from "../utils/apiUtils.js";

export const GobiernoMundialAPI = {
    obtenerTodos: () => fetchApi('GET', `/gobierno-mundial`),
    obtenerPorId: (id) => fetchApi('GET', `/gobierno-mundial/${id}`),
    obtenerPorRango: (rango) => fetchApi('GET', `/gobierno-mundial/rango/${rango}`),
    crear: (agente) => fetchApi('POST', `/gobierno-mundial`, agente),
    actualizar: (id, agente) => fetchApi('PUT', `/gobierno-mundial/${id}`, agente),
    eliminar: (id) => fetchApi('DELETE', `/gobierno-mundial/${id}`)
}