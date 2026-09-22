## Ejercicio 1 — Pokémon básico con DTD interna
**Objetivo:** Practicar elementos obligatorios y opcionales.  

📌 Instrucciones:  
1. Crea un XML con DTD interna que represente un **Pokémon**.  
2. El elemento raíz será `<pokemon>`.  
3. Reglas de DTD:  
   - `pokemon` contiene: `nombre`, `tipo`, `nivel`  
   - `nivel` es opcional.  
   - Todos los demás elementos son de texto (`#PCDATA`).  

---

## Ejercicio 2 — Equipo Pokémon (cardinalidades)
**Objetivo:** Usar `+` y `*`.  

📌 Instrucciones:  
1. Crea un XML con DTD interna que represente un **equipo Pokémon**.  
2. El elemento raíz será `<equipo>`.  
3. Reglas de DTD:  
   - `equipo` contiene **uno o más** `pokemon` 
   - `pokemon` contiene: `nombre`, `tipo`, `nivel`.  
   - `tipo` puede repetirse **una o más veces** (`tipo`) porque un Pokémon puede tener varios tipos (ej. Agua + Volador).  

---

## Ejercicio 3 — Liga Pokémon con DTD externa
**Objetivo:** Separar en archivo externo y practicar estructuras más grandes.  

📌 Instrucciones:  
1. Crea un archivo `liga.xml` que represente una **Liga Pokémon**.  
2. El XML debe usar una DTD externa llamada `liga.dtd`.  
3. Reglas de DTD:  
   - El elemento raíz es `liga`.  
   - `liga` contiene: `entrenador`.  
   - `entrenador` contiene: `nombre`, `pokemon`.  
   - `pokemon` contiene: `nombre`, `tipo`, `nivel`.  

📌 Tarea:  
- Escribe `liga.xml` con al menos 2 entrenadores.  
- Cada entrenador debe tener mínimo 2 Pokémon.  
- Escribe también `liga.dtd` con las reglas.  