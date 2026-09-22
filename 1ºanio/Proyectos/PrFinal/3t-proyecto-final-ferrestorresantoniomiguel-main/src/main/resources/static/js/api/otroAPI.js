import { fetchApi } from "../utils/apiUtils.js";

export const OtroAPI = {
    obtenerTodos: () => fetchApi('GET', `/otros`),
    obtenerPorId: (id) => fetchApi('GET', `/otros/${id}`),
    obtenerPorLugar: (lugar) => fetchApi('GET', `/otros/lugar/${lugar}`),
    crear: (otro) => fetchApi('POST', `/otros`, otro),
    actualizar: (id, otro) => fetchApi('PUT', `/otros/${id}`, otro),
    eliminar: (id) => fetchApi('DELETE', `/otros/${id}`)
}