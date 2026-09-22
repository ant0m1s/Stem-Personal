import { crearNavbar } from "../components/navbar.js"
import { crearFooter } from "../components/footer.js"

document.addEventListener('DOMContentLoaded', async () => {
    document.getElementById('navbar').innerHTML = crearNavbar()
    document.getElementById('footer').innerHTML = crearFooter()
    const root = document.getElementById('root')

})