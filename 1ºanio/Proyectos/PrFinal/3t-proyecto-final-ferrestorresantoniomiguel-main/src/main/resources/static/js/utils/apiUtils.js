import { API_CONFIG } from "../config/apiConfig.js";

export async function fetchApi(method = 'GET', path, body = null) {
    let respuesta
    const url = `${API_CONFIG.baseURL}${path}`

    const options = { method }

    if (method == 'POST' || method == 'PUT') {
        options.headers = {
            'Content-Type': 'application/json'
        }
        options.body = JSON.stringify(body)
    }

    const response = await fetch(url, options)

    if (response.status === 204 || method === 'DELETE') {
        respuesta = null
    } else {
        respuesta = await response.json()
    }
    return respuesta
}