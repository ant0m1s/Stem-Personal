package com.onepiece.grandlineAPI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * esta clase representa a personajes que no son ni piratas ni del gobierno mundial
 * hereda de personaje porque comparte los mismos datos basicos
 * se usa para personajes como comerciantes doctores o habitantes de islas
 * lo que le diferencia es el lugar o trabajo con el que se le asocia
 */
@Entity
@Table(name = "otros")
public class Otro extends Personaje {

    /** el lugar de origen o el trabajo que tiene este personaje */
    @Column(name = "trabajo", nullable = false)
    private String lugar;

    /**
     * constructor vacio necesario para que jpa pueda funcionar
     */
    public Otro() {
    }

    /**
     * crea un personaje de tipo otro sin imagen por si no tenemos una todavia
     *
     * @param nombreCompleto el nombre completo del personaje
     * @param alias como se le conoce
     * @param altura cuanto mide en metros
     * @param edad cuantos anos tiene
     * @param descripcion un texto explicando quien es
     * @param estado si sigue vivo o no
     * @param lugar su lugar de origen o trabajo
     */
    public Otro(String nombreCompleto, String alias, Double altura, Integer edad, String descripcion,
            Boolean estado, String lugar) {
        super(nombreCompleto, alias, altura, edad, descripcion, estado);
        this.lugar = lugar;
    }

    /**
     * crea un personaje de tipo otro con todos sus datos incluida la imagen
     *
     * @param nombreCompleto el nombre completo del personaje
     * @param alias como se le conoce
     * @param altura cuanto mide en metros
     * @param edad cuantos anos tiene
     * @param descripcion un texto explicando quien es
     * @param estado si sigue vivo o no
     * @param lugar su lugar de origen o trabajo
     * @param imagen la url de su imagen
     */
    public Otro(String nombreCompleto, String alias, Double altura, Integer edad, String descripcion,
            Boolean estado, String lugar, String imagen) {
        super(nombreCompleto, alias, altura, edad, descripcion, estado, imagen);
        this.lugar = lugar;
    }

    /**
     * devuelve el lugar o trabajo asociado a este personaje
     *
     * @return el lugar como texto
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * cambia el lugar o trabajo del personaje
     *
     * @param lugar el nuevo lugar o trabajo
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * muestra un resumen del personaje con su lugar o trabajo
     *
     * @return un string con los datos principales
     */
    @Override
    public String toString() {
        return "Otro [lugar=" + lugar + "]";
    }

}
