[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/fgfGl4k_)

# 🏴‍☠️ GrandLine API🏴‍☠️

Proyecto final de curso — Aplicación web full stack temática de **One Piece** desarrollada con **Spring Boot** (backend) y **JavaScript Vanilla** (frontend).

---

## Video 🤠

Enlace a video: "https://youtu.be/jop8XFCAg-U"

En el video hago una pequeña introducción, explicación de la estructura, comprobación en Postman y una poco de front-end

---

## Descripción 🤠

GrandLine API es una aplicación REST que permite gestionar el universo de la serie One Piece: piratas, tripulaciones, frutas del diablo, barcos, miembros del gobierno mundial y otros personajes. Desde el frontend se pueden listar, crear, editar, eliminar y filtrar todas las entidades. La API devuelve los datos en formato JSON y el frontend los consume mediante `fetch`.

---

## Estructura del proyecto 🤠

```
src/
├── main/
│   ├── java/com/onepiece/grandlineAPI/
│   │   ├── entity/           → Entidades JPA (Personaje, Pirata, Fruta, Tripulacion, Barco, GobiernoMundial, Otro)
│   │   ├── repository/       → Interfaces JpaRepository con filtros personalizados
│   │   ├── service/          → Interfaces de servicio
│   │   │   └── impl/         → Implementaciones con lógica de negocio
│   │   ├── controller/       → Controllers REST bajo /api/v1/
│   │   └── util/             → Inicializadores de datos (DataLoader)
│   └── resources/
│       ├── application.properties
│       └── static/           → Frontend (HTML, CSS, JS, imágenes)
│           ├── js/
│           │   ├── api/      → Archivos *API.js (puente frontend-backend)
│           │   ├── components/ → navbar.js, footer.js
│           │   ├── config/   → apiConfig.js
│           │   ├── pages/    → Lógica de cada página (*Lista.js, *Detalle.js)
│           │   └── utils/    → apiUtils.js
│           └── img/          → Imágenes de personajes, frutas, barcos y tripulaciones
└── test/
    └── java/com/onepiece/grandlineAPI/
        └── service/          → Tests unitarios de todos los ServiceImpl (JUnit 5 + Mockito)
```

---

## Modelo de datos 🤠

### Herencia JOINED

`Personaje` es una clase abstracta de la que heredan tres subtipos mediante `@Inheritance(strategy = InheritanceType.JOINED)`. Cada subclase tiene su propia tabla en base de datos además de la tabla común `personajes`.

```
Personaje (abstracta)
├── Pirata          → recompensa, funcion, generacion, tripulacion
├── GobiernoMundial → rango
└── Otro            → lugar
```

### Relaciones

| Relación | Tipo | Detalle |
|---|---|---|
| `Personaje` ↔ `Fruta` | `@ManyToMany` | Tabla intermedia `personaje_fruta`. Propietario: Personaje |
| `Pirata` → `Tripulacion` | `@ManyToOne` | Un pirata pertenece a una tripulación |
| `Barco` → `Tripulacion` | `@ManyToOne` | Un barco pertenece a una tripulación |
| `Tripulacion` → `Pirata` | `@OneToMany` | Una tripulación tiene varios piratas (CascadeType.ALL) |
| `Tripulacion` → `Barco` | `@OneToMany` | Una tripulación tiene varios barcos (CascadeType.ALL) |

---

## Endpoints de la API 🤠

Base URL: `http://localhost:8080/api/v1`

### Piratas `/piratas`
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/piratas` | Listar todos |
| GET | `/piratas/{id}` | Buscar por ID |
| GET | `/piratas/generacion/{generacion}` | Filtrar por generación |
| POST | `/piratas` | Crear nuevo |
| PUT | `/piratas/{id}` | Editar existente |
| DELETE | `/piratas/{id}` | Eliminar |

### Frutas `/frutas`
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/frutas` | Listar todas |
| GET | `/frutas/{id}` | Buscar por ID |
| GET | `/frutas/nombre/{nombre}` | Filtrar por nombre |
| POST | `/frutas` | Crear nueva |
| PUT | `/frutas/{id}` | Editar existente |
| DELETE | `/frutas/{id}` | Eliminar (desasocia de personajes automáticamente) |

### Tripulaciones `/tripulaciones`
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/tripulaciones` | Listar todas |
| GET | `/tripulaciones/{id}` | Buscar por ID |
| GET | `/tripulaciones/nombre/{nombre}` | Filtrar por nombre |
| GET | `/tripulaciones/generacion/{generacion}` | Filtrar por generación |
| GET | `/tripulaciones/{id}/piratas` | Piratas de una tripulación |
| POST | `/tripulaciones` | Crear nueva |
| PUT | `/tripulaciones/{id}` | Editar existente |
| DELETE | `/tripulaciones/{id}` | Eliminar |

### Barcos `/barcos`
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/barcos` | Listar todos |
| GET | `/barcos/{id}` | Buscar por ID |
| GET | `/barcos/tripulacion/{id}` | Barcos de una tripulación |
| POST | `/barcos` | Crear nuevo |
| PUT | `/barcos/{id}` | Editar existente |
| DELETE | `/barcos/{id}` | Eliminar |

### Gobierno Mundial `/gobiernoMundial`
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/gobiernoMundial` | Listar todos |
| GET | `/gobiernoMundial/{id}` | Buscar por ID |
| GET | `/gobiernoMundial/rango/{rango}` | Filtrar por rango |
| POST | `/gobiernoMundial` | Crear nuevo |
| PUT | `/gobiernoMundial/{id}` | Editar existente |
| DELETE | `/gobiernoMundial/{id}` | Eliminar |

### Otros `/otros`
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/otros` | Listar todos |
| GET | `/otros/{id}` | Buscar por ID |
| GET | `/otros/lugar/{lugar}` | Filtrar por lugar |
| POST | `/otros` | Crear nuevo |
| PUT | `/otros/{id}` | Editar existente |
| DELETE | `/otros/{id}` | Eliminar |

### Personajes — Frutas del diablo `/personajes`
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/personajes/{id}/frutas` | Frutas de un personaje |
| POST | `/personajes/{personajeId}/frutas/{frutaId}` | Añadir fruta a personaje |
| DELETE | `/personajes/{personajeId}/frutas/{frutaId}` | Quitar fruta de personaje |

---

## Cómo ejecutar el proyecto 🤠

### Requisitos previos
- Java 25
- Maven

### Pasos

```bash
# 1. Clonar el repositorio
git clone https://github.com/DAM-3T-24-25/3t-proyecto-final-ferrestorresantoniomiguel.git

# 2. Entrar en el directorio
cd 3t-proyecto-final-ferrestorresantoniomiguel

# 3. Ejecutar con Maven
mvn spring-boot:run
```

La aplicación arranca en `http://localhost:8080`. Al iniciarse carga automáticamente todos los datos de ejemplo.

### Consola H2

Disponible en `http://localhost:8080/h2-console`

| Campo | Valor |
|---|---|
| JDBC URL | `jdbc:h2:mem:grandlinedb` |
| Usuario | `root` |
| Contraseña | *(vacía)* |

### Ejecutar los tests

```bash
mvn test
```

---

## Tests unitarios 🤠

Se han implementado **7 clases de test** con JUnit 5 y Mockito, una por cada ServiceImpl:

| Clase de test | Métodos cubiertos |
|---|---|
| `PirataServiceImplTest` | findAll, findById, save, deleteById, findByTripulacion_Id, findByGeneracion |
| `FrutaServiceImplTest` | findAll, findById, save, deleteById, findByNombre |
| `TripulacionServiceImplTest` | findAll, findById, save, deleteById, findByNombre, findByGeneracion |
| `BarcoServiceImplTest` | findAll, findById, save, deleteById, findByTripulacionId |
| `GobiernoMundialServiceImplTest` | findAll, findById, save, deleteById, findByRango |
| `OtroServiceImplTest` | findAll, findById, save, deleteById, findByLugar |
| `PersonajeServiceImplTest` | findAll, findById, deleteById, anadirFruta, borrarFruta, getFrutasByPersonajeId, findByNombreCompleto |

Los tests siguen el **patrón AAA** (Arrange / Act / Assert) y usan `@ParameterizedTest` con `@ValueSource` para probar los métodos de filtro con distintos valores de entrada. La nomenclatura sigue el convenio `comportamiento_resultado_condicion`.

---

## Diario de desarrollo 🤠

| Fecha | Actividad |
|---|---|
| **15 may** | Creación del diagrama UML y diseño de la lógica del proyecto. Configuración inicial de Spring Boot y repositorio. |
| **16–17 may** | Creación de todas las entidades JPA: `Personaje` (abstracta), `Pirata`, `Fruta`, `GobiernoMundial`, `Otro`, `Tripulacion` y `Barco`. Configuración de herencia JOINED y relaciones ManyToMany / ManyToOne. Creación de los repositorios con filtros personalizados. Problema resuelto: configuración de la herencia JPA y arranque de H2. |
| **18 may** | Implementación de todas las interfaces de servicio y sus implementaciones en `impl/`. Creación de los 7 controllers REST. Subida del UML al repositorio. |
| **19 may** | Inicio del frontend. Creación de los archivos `*API.js` como puente entre el frontend y la API. Carga masiva de datos en los inicializadores: piratas, frutas y relaciones Personaje–Fruta. |
| **20 may** | Creación de las páginas HTML base para cada entidad. Implementación de los archivos `*Lista.js` para listar información accediendo a cada API. Navbar provisional e index. |
| **22 may** | CRUD completo para Fruta (crear, editar, eliminar con desasociación automática de personajes). Inicio del CRUD para Pirata. |
| **25 may** | CRUD completo para Pirata, GobiernoMundial, Tripulación y Otro. |
| **26 may** | CRUD completo para Barco. Añadido atributo `nombre` a Fruta con nuevo endpoint de filtrado. Footer y CSS provisional aplicados a todas las páginas. |
| **27 may** | Mejoras en Fruta: filtrar por nombre, checkboxes de personajes en formulario, corrección de bugs al editar y eliminar. Añadidos atributos `imagen` y `generacion` a Pirata. |
| **30 may** | Actualización del backend: `generacion` e `imagen` para Pirata y Tripulación, nuevos endpoints. Frontend de piratas con filtro por nombre y por tripulación. |
| **31 may** | Páginas de detalle para todas las entidades. Tests unitarios para los 7 servicios. JavaDoc en todas las clases. Botón "Volver arriba". Refactorización y mejoras visuales finales. |

---

## Autor

**Antonio Miguel Ferres Torres**  
Proyecto Final — Curso DAM 3T 2024/2025
