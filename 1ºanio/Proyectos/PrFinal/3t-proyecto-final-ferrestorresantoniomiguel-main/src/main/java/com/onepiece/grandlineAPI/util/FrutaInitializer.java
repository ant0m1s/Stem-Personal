package com.onepiece.grandlineAPI.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onepiece.grandlineAPI.entity.Fruta;
import com.onepiece.grandlineAPI.service.FrutaService;

@Component
public class FrutaInitializer {

    @Autowired
    private FrutaService frutaService;

    public void inicializarFrutas() {
        frutaService.save(new Fruta("Gomu Gomu no Mi (Modelo Nika)", "Convierte el cuerpo del usuario en goma, dandole los atributos de la goma. Se dice que es una Fruta Legendaría", "Paramecia", "/img/frutas/gomuGomu.png"));
        frutaService.save(new Fruta("Mera Mera no Mi (Ace)", "Permite crear, controlar y transformarse en fuego. Portada por Ace", "Logia", "/img/frutas/meraMera.jpg"));
        frutaService.save(new Fruta("Mera Mera no Mi", "Permite crear, controlar y transformarse en fuego. Portada por Sabo", "Logia", "/img/frutas/meraMera.jpg"));
        frutaService.save(new Fruta("Ito Ito no Mi", "Permite crear y controlar hilos de acero irrompibles", "Paramecia", "/img/frutas/itoIto.jpg"));
        frutaService.save(new Fruta("Ope Ope no Mi", "Permite crear una 'sala de operaciones' en la que el usuario tiene control total", "Paramecia", "/img/frutas/opeOpe.jpg"));
        frutaService.save(new Fruta("Yami Yami no Mi", "El usuario controla la oscuridad y anular poderes de otra frutas", "Logia", "/img/frutas/yamiYami.jpg"));
        frutaService.save(new Fruta("Tori Tori no Mi", "Permite a su usuario transformarse en un halcón", "Zoan", "/img/frutas/toriTori.jpg"));
        frutaService.save(new Fruta("Bari Bari no Mi", "El usuario puede crear barreras irrompibles", "Paramecia", "/img/frutas/bariBari.jpg"));
        frutaService.save(new Fruta("Hie Hie no Mi (pre-timeskip)", "Permite crear, controlar y transformarse en hielo", "Logia", "/img/frutas/hieHie.jpg"));
        frutaService.save(new Fruta("Hie Hie no Mi", "Permite crear, controlar y transformarse en hielo", "Logia", "/img/frutas/hieHie.jpg"));
        frutaService.save(new Fruta("Pika Pika no Mi", "Permite al usuario crear, controlar y transformarse en luz pura", "Logia", "/img/frutas/pikaPika.jpg"));
        frutaService.save(new Fruta("Gura Gura no Mi (Teach)", "Permite crear vibraciones sísmicas devastadoras. Portada por Teach", "Paramecia", "/img/frutas/guraGura.jpg"));
        frutaService.save(new Fruta("Gura Gura no Mi (Edward)", "Permite crear vibraciones sísmicas devastadoras. Portada por Barba Blanca", "Paramecia", "/img/frutas/guraGura.jpg"));
        frutaService.save(new Fruta("Magu Magu no Mi", "Permite al usuario crear, controlar y transformarse en Magma", "Logia", "/img/frutas/maguMagu.jpg"));
        frutaService.save(new Fruta("Hito Hito no Mi (Modelo Humano-Humano)", "Permite al usuario transformarse en un humano", "Zoan", "/img/frutas/hitoHitoHumano.jpg"));
        frutaService.save(new Fruta("Hito Hito no Mi (Modelo Daibutsu)", "Permite al usuario transformarse en un Buda gigante", "Zoan", "/img/frutas/hitoHitoDai.png"));
        frutaService.save(new Fruta("Yomi Yomi no Mi", "Permite revivir una vez tras la muerte y poder controlar su alma", "Paramecia", "/img/frutas/yomiYomi.jpg"));
        frutaService.save(new Fruta("Tori Tori no Mi (Modelo: Fénix)", "Permite transformarse en un fénix mítico con llamas regenerativas", "Zoan", "/img/frutas/sinImagen.jpg"));
        frutaService.save(new Fruta("Riki Riki no Mi", "Da una fuerza física extraordinaria", "Paramecia", "/img/frutas/rikiRiki.jpg"));
        frutaService.save(new Fruta("Wapu Wapu no Mi", "Permite teletransporse a sí mismo y a otros", "Paramecia", "/img/frutas/wapuWapu.jpg"));
        frutaService.save(new Fruta("Suke Suke no Mi", "Vuelve invisible a su portador", "Paramecia", "/img/frutas/sukeSuke.jpg"));
        frutaService.save(new Fruta("Shima Shima no Mi", "Permite a su usuario controlar y transformarse en una isla entera", "Paramecia", "/img/frutas/shimaShima.jpg"));
        frutaService.save(new Fruta("Shibo Shibo no Mi", "Da el poder de exprimir cualquier cosa como si fuera una esponja", "Paramecia", "/img/frutas/sinImagen.jpg"));
        frutaService.save(new Fruta("Mochi Mochi no Mi", "Permite a su portador crear y transformarse en mochi", "Paramecia", "/img/frutas/mochiMochi.jpg"));
        frutaService.save(new Fruta("Bisu Bisu no Mi", "Da la posibilidad de crear y controlar galletas de una gran dureza", "Paramecia", "/img/frutas/sinImagen.jpg"));
        frutaService.save(new Fruta("Pero Pero no Mi", "Permite al usuario crear y controlar caramelo", "Paramecia", "/img/frutas/sinImagen.jpg"));
        frutaService.save(new Fruta("Kira Kira no Mi", "El usuario puede transformar partes de su cuerpo en diamante", "Paramecia", "/img/frutas/sinImagen.jpg"));
        frutaService.save(new Fruta("Horu Horu no Mi", "Permite al usuario crear y controlar hormonas que alteran el cuerpo", "Paramecia", "/img/frutas/sinImagen.jpg"));
        frutaService.save(new Fruta("Kobu Kobu no Mi", "Permite al usuario motivar el espíritu de combate a otros", "Paramecia", "/img/frutas/sinImagen.jpg"));
        frutaService.save(new Fruta("Beta Beta no Mi", "Permite crear y controlar moco pegajoso con el que atrapar y moverse", "Paramecia", "/img/frutas/sinImagen.jpg"));
        frutaService.save(new Fruta("Ishi Ishi no Mi", "Permite al usuario fundirse con la piedra y controlar estructuras rocosas enormes", "Paramecia", "/img/frutas/sinImagen.jpg"));
        frutaService.save(new Fruta("Hira Hira no Mi", "Hace que el cuerpo del usuario ondule como una bandera pudiendo deflectar ataques", "Paramecia", "/img/frutas/sinImagen.jpg"));
        frutaService.save(new Fruta("Yuki Yuki no Mi", "Permite crear controlar y transformarse en nieve", "Logia", "/img/frutas/sinImagen.jpg"));
        frutaService.save(new Fruta("Hobi Hobi no Mi", "Convierte a cualquier persona que toque al usuario en un juguete borrando su recuerdo de todos", "Paramecia", "/img/frutas/sinImagen.jpg"));
    }
}
