# Pro-tips HTML

    ACORDARME DE CAMBIAR EL IDIOMA, AL INICIAR EL HTML


# Cuerpo del HTML

--------------------------------------------------------------


<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="Una breve descripción de mi página web, enfocada en servicios y productos de calidad."/>
    <meta name="author" content="Eduardo" />
    <meta name="keywords" content="servicios, productos, agua, domicilio" />
    <title>Aquaexpress</title>
  </head>

  <body>
    <header></header>
    <main></main>
    <footer></footer>
  </body>
</html>

# Dentro de BODY

# Insertar header (dentro de body)

Normalmente en el header vamos a meter al principio un título y/o imagen, y al final, pues tendremos que poner un pequeño "nav"

    <header>
        <img src="img/logotipo.png" alt="Logotipo para BioChefLab" width="100">
        <h1>BioChefLab</h1>
        <nav>
            <ul>
                <li><a href="index.html">Inicio</a></li>
                <li><a href="recetas.html">Recetas</a></li>
                <li><a href="laboratorio.html">Laboratorio</a></li>
                <li><a href="opiniones.html">Opiniones</a></li>
                <li><a href="#contacto">Contacto</a></li>
            </ul>
        </nav>
    </header>

# Insertar main

Normalmente dentro del main, vamos a poner "secciones", estas si que pueden tener varias cosas como:

## section

Se suele poner con "id" y hasta sin "id

(CON ID)

    <main>
        <section id="Inicio">
            <h2>Bienvenidos a BioChefLab</h2>
            <p>Nos basamos en ofrecer lo mejor de lo mejor para los nuestros...</p>
        </section>
    </main>

(SIN ID)

    <main>
        <section>
            <h2>Bienvenidos a BioChefLab</h2>
            <p>Nos basamos en ofrecer lo mejor de lo mejor para los nuestros...</p>
        </section>
    </main>

## agregar Citas

Se utiliza el blockquote, que funcionapara añadir un texto y citarlo, como si lo hubiera dicho alguien

    <section>
        <h2>Opiniones de jugadores</h2>
        <blockquote><i>Nunca pensé que la geometría pudiera ser tan divertida</i></blockquote>
        <blockquote><i>Nunca pensé que la geometría pudiera ser tan jodidamente top</i></blockquote>
        <blockquote><i>Nunca pensé que la geometría pudiera ser tan rara de entender</i></blockquote>

    </section>

## agregar articulos (article)

Son parecidos al section, pero se utiliza para encapsular contenido independiente en una página

    <article id="tartaQueso">
        <h2>Tarta de Queso</h2>
        <figure>
            <figcaption>Postre de queso preparado con nitrógeno líquido</figcaption>
            <img src="img/TartaQueso.png" alt="Tarta de Queso" width="300">

        </figure>
        <ol>
            <h3>Receta - Paso a paso</h3>
            <li>Comprar ingredientes</li>
            <li>Hacer molde de Tarta</li>
            <li>Hacer Tarta</li>
            <li>Hornearla</li>
        </ol>
    </article>

## lista "ul" (NO NUMERADAS)

    <ul>
        <h3>Receta - Paso a paso</h3>
        <li>Comprar ingredientes</li>
        <li>Hacer molde de Tarta</li>
        <li>Hacer Tarta</li>
        <li>Hornearla</li>
    </ul

## lista ordenada "ol" (NUMERADAS)

    <ol>
        <h3>Receta - Paso a paso</h3>
        <li>Comprar ingredientes</li>
        <li>Hacer molde de Tarta</li>
        <li>Hacer Tarta</li>
        <li>Hornearla</li>
    </ol>

---

# Insertar elementos

## Enlaces

### Descargar PDFs

Cuando nos piden un enlace para descargar un PDF deberemos hacer:

<p>: sera para comentar
a: para ingresar lo que queremos descargar y para poner su nombre

    <p>Guía completa en PDF: <a href="manual_biocheflab.pdf" download="manual_biocheflab.pdf">DESCARGAR</a></p>

### Añadir ubicación real

    <a href="URL_DEL_MAPA_AQUI" target="_blank"

## Imagenes

### Sin "Figure"

Cuando va sin figure es porque no nos piden figcaption

    <img src="img/logotipo.png" alt="Logotipo para BioChefLab" width="100">

### Con "Figure"

Tenemos que poner figure cuando nos piden algún comentario o escribirle algo (como una leyenda).

    <figure>
    <img src="img/TartaQueso.png" alt="Tarta de Queso" width="300">
    <figcaption>Postre de queso preparado con nitrógeno líquido</figcaption>
    </figure>

## Video LOCAL

### Con controls _Gran mayoría de veces_

Esta opción es cuando nos piden <video controls>

    <section>
        <h2>Vídeo explicando como se hace la "Tarta de Queso" </h2>
        <video controls width="400">
            <source src="video/puchaina.mp4" type="video/mp4">
        </video>
    </section>

## Video INTERNET

    <section>
        <iframe
            width="943"
            height="539"
            src="https://www.youtube.com/embed/ibJpn-RDg8U?list=RDibJpn-RDg8U"
            title="Sailin Da South (Screwed)"
            frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            referrerpolicy="strict-origin-when-cross-origin"
            allowfullscreen
        </iframe>
    </section>


## Audios

### Con controls _Gran mayoría de veces_

Esta opción es cuando nos piden <audio controls>

    <section>
            <h3>Explicación del científico </h3>
            <audio controls src="audio/arbolada.mp3"></audio>
        </section>


# Dentro de FOOTER

## Información de contacto

    <footer>
        <section id="contacto">
            <p>Contacto: <a href="mailto:amadeoKteveo@gmail.com">amadeoKteveo@gmail.com</a></p>
        </section>
        <p>Derechos reservados &copy; 2025 BioChefLab</p>

    </footer>

### Añadir redes sociales

Cuando nos piden enlazar enlaces a redes sociales

    <footer>
        <h3>Nuestras redes sociales</h3>
        <a href="https://x.com/home">#Twitter</a> |
        <a href="https://www.instagram.com/">#Instagram</a> |
        <a href="https://www.facebook.com/">#Facebook</a>
    </footer>


## Correo electrónico (mailto:)

    <a href="mailto:ejemplo@ejemplo.com">Contáctanos por correo</a>

## Derechos reservados
1º EJEMPLO

    <footer>
        <p>Derechos reservados &copy; 2025 BioChefLab</p>
    </footer>

2º EJEMPLO - CHATGPT

    <footer>
        <p>&copy; 2025 BioChefLab | Contacto: <a href="mailto:info@biocheflab.com">info@biocheflab.com</a></p>
    </footer>


## Esta bien saberlo
- Normalmente el h2, funciona como "encabezado" y "subtítulo"
- Normalmente el h3, funciona como "subtítulo"
Párrafos: <p></p>
Enlaces: <a></a>
Texto en Negrita: <b>, <strong></strong>
Texto en Cursiva: <i>, <em></em>
Citas en Bloque: <blockquote></blockquote>
Saltos de Línea: <br>
Líneas Horizontales: <hr>