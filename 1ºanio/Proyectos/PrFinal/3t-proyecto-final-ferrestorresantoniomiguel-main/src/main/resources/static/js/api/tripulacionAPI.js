import { fetchApi } from "../utils/apiUtils.js";

export const TripulacionAPI = {
    obtenerTodos: () => fetchApi('GET', `/tripulaciones`),
    obtenerPorId: (id) => fetchApi('GET', `/tripulaciones/${id}`),
    obtenerPorGeneracion: (generacion) => fetchApi('GET', `/tripulaciones/generacion/${generacion}`),
    obtenerPiratas: (id) => fetchApi('GET', `/tripulaciones/${id}/piratas`),
    obtenerBarcos: (id) => fetchApi('GET', `/tripulaciones/${id}/barcos`),
    crear: (tripulacion) => fetchApi('POST', `/tripulaciones`, tripulacion),
    actualizar: (id, tripulacion) => fetchApi('PUT', `/tripulaciones/${id}`, tripulacion),
    eliminar: (id) => fetchApi('DELETE', `/tripulaciones/${id}`)
}