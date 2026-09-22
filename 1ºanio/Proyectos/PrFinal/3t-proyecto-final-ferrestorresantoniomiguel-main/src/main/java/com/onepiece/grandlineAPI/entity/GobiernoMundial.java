package com.onepiece.grandlineAPI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * esta clase representa a un miembro del gobierno mundial de one piece
 * hereda de personaje porque al final tambien es una persona
 * el gobierno mundial es la organizacion que controla el mundo en la historia
 * lo unico que le diferencia de otros personajes es que tiene un rango
 */
@Entity
@Table(name = "gobierno_mundial")
public class GobiernoMundial extends Personaje {

    /** el rango que tiene dentro del gobierno mundial por ejemplo almirante o celestial */
    @Column(name = "rango", nullable = false)
    private String rango;

    /**
     * constructor vacio que necesita jpa para poder crear objetos de esta clase
     */
    public GobiernoMundial() {
    }

    /**
     * crea un miembro del gobierno mundial con todos sus datos incluida la imagen
     *
     * @param nombreCompleto el nombre completo del personaje
     * @param alias como se le conoce popularmente
     * @param altura cuanto mide en metros
     * @param edad cuantos anos tiene
     * @param descripcion un texto explicando quien es
     * @param estado si sigue vivo o no
     * @param rango su posicion dentro del gobierno mundial
     * @param imagen la url de su imagen
     */
    public GobiernoMundial(String nombreCompleto, String alias, Double altura, Integer edad, String descripcion,
            Boolean estado, String rango, String imagen) {
        super(nombreCompleto, alias, altura, edad, descripcion, estado, imagen);
        this.rango = rango;
    }

    /**
     * crea un miembro del gobierno mundial sin imagen por si no tenemos una todavia
     *
     * @param nombreCompleto el nombre completo del personaje
     * @param alias como se le conoce popularmente
     * @param altura cuanto mide en metros
     * @param edad cuantos anos tiene
     * @param descripcion un texto explicando quien es
     * @param estado si sigue vivo o no
     * @param rango su posicion dentro del gobierno mundial
     */
    public GobiernoMundial(String nombreCompleto, String alias, Double altura, Integer edad, String descripcion,
            Boolean estado, String rango) {
        super(nombreCompleto, alias, altura, edad, descripcion, estado);
        this.rango = rango;
    }

    /**
     * devuelve el rango que tiene este personaje en el gobierno mundial
     *
     * @return el rango como texto
     */
    public String getRango() {
        return rango;
    }

    /**
     * cambia el rango del personaje dentro del gobierno mundial
     *
     * @param rango el nuevo rango
     */
    public void setRango(String rango) {
        this.rango = rango;
    }

    /**
     * muestra un resumen del personaje con su rango
     *
     * @return un string con los datos principales
     */
    @Override
    public String toString() {
        return "GobiernoMundial [rango=" + rango + "]";
    }

}
