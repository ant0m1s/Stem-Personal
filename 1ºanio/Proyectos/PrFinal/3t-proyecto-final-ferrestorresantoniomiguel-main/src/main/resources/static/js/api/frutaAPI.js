import { fetchApi } from "../utils/apiUtils.js";

export const FrutaAPI = {
    obtenerTodos: () => fetchApi('GET', `/frutas`),
    obtenerPorId: (id) => fetchApi('GET', `/frutas/${id}`),
    obtenerPorNombre: (nombre) => fetchApi('GET', `/frutas/nombre/${nombre}`),
    crear: (fruta) => fetchApi('POST', `/frutas`, fruta),
    actualizar: (id, fruta) => fetchApi('PUT', `/frutas/${id}`, fruta),
    eliminar: (id) => fetchApi('DELETE', `/frutas/${id}`)
}