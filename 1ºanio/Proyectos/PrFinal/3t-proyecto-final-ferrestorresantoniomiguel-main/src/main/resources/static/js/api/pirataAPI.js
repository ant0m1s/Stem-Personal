import { fetchApi } from "../utils/apiUtils.js";

export const PirataAPI = {
    obtenerTodos: () => fetchApi('GET', `/piratas`),
    obtenerPorId: (id) => fetchApi('GET', `/piratas/${id}`),
    obtenerPorGeneracion: (generacion) => fetchApi('GET', `/piratas/generacion/${generacion}`),
    crear: (pirata) => fetchApi('POST', `/piratas`, pirata),
    actualizar: (id, pirata) => fetchApi('PUT', `/piratas/${id}`, pirata),
    eliminar: (id) => fetchApi('DELETE', `/piratas/${id}`)
}