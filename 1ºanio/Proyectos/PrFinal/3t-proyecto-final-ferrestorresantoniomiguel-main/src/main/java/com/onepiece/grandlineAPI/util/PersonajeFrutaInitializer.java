package com.onepiece.grandlineAPI.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onepiece.grandlineAPI.entity.Fruta;
import com.onepiece.grandlineAPI.entity.Personaje;
import com.onepiece.grandlineAPI.service.FrutaService;
import com.onepiece.grandlineAPI.service.PersonajeService;

@Component
public class PersonajeFrutaInitializer {

    @Autowired
    private PersonajeService personajeService;

    @Autowired
    private FrutaService frutaService;

    public void asignarFrutas() {
        asignar("Monkey D. Luffy", "Gomu Gomu no Mi (Modelo Nika)");
        asignar("Nico Robin", "Bari Bari no Mi");
        asignar("Kuzan (pre-timeskip)", "Hie Hie no Mi (pre-timeskip)");
        asignar("Kuzan", "Hie Hie no Mi");
        asignar("Trafalgar D. Water Law", "Ope Ope no Mi");
        asignar("Portgas D. Ace", "Mera Mera no Mi (Ace)");
        asignar("Borsalino", "Pika Pika no Mi");
        asignar("Marshall D. Teach", "Yami Yami no Mi");
        asignar("Marshall D. Teach", "Gura Gura no Mi (Teach)");
        asignar("Edward Newgate", "Gura Gura no Mi (Edward)");
        asignar("Sakazuki", "Magu Magu no Mi");
        asignar("Tony Tony Chopper", "Hito Hito no Mi (Modelo Humano-Humano)");
        asignar("Brook", "Yomi Yomi no Mi");
        asignar("Sabo", "Mera Mera no Mi");
        asignar("Sengoku", "Hito Hito no Mi (Modelo Daibutsu)");
        asignar("Marco", "Tori Tori no Mi (Modelo: Fénix)");
        asignar("Jesus Burgess", "Riki Riki no Mi");
        asignar("Van Augur", "Wapu Wapu no Mi");
        asignar("Shiryu", "Suke Suke no Mi");
        asignar("Avalo Pizarro", "Shima Shima no Mi");
        asignar("Charlotte Smoothie", "Shibo Shibo no Mi");
        asignar("Charlotte Katakuri", "Mochi Mochi no Mi");
        asignar("Charlotte Cracker", "Bisu Bisu no Mi");
        asignar("Charlotte Perospero", "Pero Pero no Mi");
        asignar("Jozu", "Kira Kira no Mi");
        asignar("Emporio Ivankov", "Horu Horu no Mi");
        asignar("Belo Betty", "Kobu Kobu no Mi");
        asignar("Donquijote Doflamingo", "Ito Ito no Mi");
        asignar("Trebol", "Beta Beta no Mi");
        asignar("Pica", "Ishi Ishi no Mi");
        asignar("Diamante", "Hira Hira no Mi");
        asignar("Monet", "Yuki Yuki no Mi");
        asignar("Sugar", "Hobi Hobi no Mi");
    }

    public void asignar(String nombrePersonaje, String nombreFruta) {
        Personaje personaje = personajeService.findByNombreCompleto(nombrePersonaje);
        Fruta fruta = frutaService.findByNombre(nombreFruta);
        personajeService.anadirFruta(personaje.getId(), fruta.getId());
    }
}
