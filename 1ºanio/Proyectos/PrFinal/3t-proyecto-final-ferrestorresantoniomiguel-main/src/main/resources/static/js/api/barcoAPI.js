import { fetchApi } from "../utils/apiUtils.js";

export const BarcoAPI = {
    obtenerTodos: () => fetchApi('GET', `/barcos`),
    obtenerPorId: (id) => fetchApi('GET', `/barcos/${id}`),
    crear: (barco) => fetchApi('POST', `/barcos`, barco),
    actualizar: (id, barco) => fetchApi('PUT', `/barcos/${id}`, barco),
    eliminar: (id) => fetchApi('DELETE', `/barcos/${id}`)
}