package com.onepiece.grandlineAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * esta clase representa a un pirata de la API
 * hereda de personaje porque un pirata sigue siendo persona
 * tiene campos propios como la recompensa que tiene.
 * la funcion que cumple en su tripulacion y la generacion a la que pertenece
 */
@Entity
@Table(name = "piratas")
public class Pirata extends Personaje {

    /** cuanto vale este pirata en berries */
    @Column(name = "recompensa", nullable = false)
    private Double recompensa;

    /** trabajo dentro de tripulacion por ejemplo cocinero o navegante */
    @Column(name = "funcion", nullable = false)
    private String funcion;

    /** a que generacion de piratas pertenece */
    @Column(name = "generacion", nullable = false)
    private String generacion;

    /** la tripulacion a la que pertenece este pirata si es que tiene una */
    @ManyToOne
    @JoinColumn(name = "tripulacion_id")
    @JsonIgnoreProperties({ "piratas", "barcos" })
    private Tripulacion tripulacion;

    /**
     * constructor vacio que necesita jpa para poder funcionar
     */
    public Pirata() {
    }

    /**
     * crea un pirata con todos sus datos pero sin asignarle tripulacion
     * se usa cuando todavia no sabemos a que grupo pertenece
     *
     * @param nombreCompleto el nombre completo del pirata
     * @param alias como se le conoce en el mundo pirata
     * @param altura cuanto mide en metros
     * @param edad cuantos anos tiene
     * @param descripcion un texto explicando quien es
     * @param estado si sigue vivo o no
     * @param recompensa lo que ofrecen por capturarlo en berries
     * @param funcion su rol dentro de la tripulacion
     * @param generacion a que generacion pertenece
     * @param imagen la url de su imagen
     */
    public Pirata(String nombreCompleto, String alias, Double altura, Integer edad, String descripcion,
            Boolean estado, Double recompensa, String funcion, String generacion, String imagen) {
        super(nombreCompleto, alias, altura, edad, descripcion, estado, imagen);
        this.recompensa = recompensa;
        this.funcion = funcion;
        this.generacion = generacion;
    }

    /**
     * crea un pirata y le asigna directamente una tripulacion
     * no incluye imagen por si no tienes una todavia
     *
     * @param nombreCompleto el nombre completo del pirata
     * @param alias como se le conoce en el mundo pirata
     * @param altura cuanto mide en metros
     * @param edad cuantos anos tiene
     * @param descripcion un texto explicando quien es
     * @param estado si sigue vivo o no
     * @param recompensa lo que ofrecen por capturarlo en berries
     * @param funcion su rol dentro de la tripulacion
     * @param generacion a que generacion pertenece
     * @param tripulacion el grupo al que pertenece
     */
    public Pirata(String nombreCompleto, String alias, Double altura, Integer edad, String descripcion,
            Boolean estado, Double recompensa, String funcion, String generacion, Tripulacion tripulacion) {
        super(nombreCompleto, alias, altura, edad, descripcion, estado);
        this.recompensa = recompensa;
        this.funcion = funcion;
        this.generacion = generacion;
        this.tripulacion = tripulacion;
    }

    /**
     * crea un pirata completo con tripulacion e imagen incluidas
     * constructor mas completo de los tres
     *
     * @param nombreCompleto el nombre completo del pirata
     * @param alias como se le conoce en el mundo pirata
     * @param altura cuanto mide en metros
     * @param edad cuantos anos tiene
     * @param descripcion un texto explicando quien es
     * @param estado si sigue vivo o no
     * @param recompensa lo que ofrecen por capturarlo en berries
     * @param funcion su rol dentro de la tripulacion
     * @param generacion a que generacion pertenece
     * @param tripulacion el grupo al que pertenece
     * @param imagen la url de su imagen
     */
    public Pirata(String nombreCompleto, String alias, Double altura, Integer edad, String descripcion,
            Boolean estado, Double recompensa, String funcion, String generacion, Tripulacion tripulacion, String imagen) {
        super(nombreCompleto, alias, altura, edad, descripcion, estado, imagen);
        this.recompensa = recompensa;
        this.funcion = funcion;
        this.generacion = generacion;
        this.tripulacion = tripulacion;
    }

    /**
     * devuelve la recompensa del pirata en berries
     *
     * @return el valor de la recompensa
     */
    public Double getRecompensa() {
        return recompensa;
    }

    /**
     * cambia la recompensa del pirata
     *
     * @param recompensa el nuevo valor en berries
     */
    public void setRecompensa(Double recompensa) {
        this.recompensa = recompensa;
    }

    /**
     * devuelve la funcion que tiene el pirata en su tripulacion
     *
     * @return su funcion por ejemplo capitan o medico
     */
    public String getFuncion() {
        return funcion;
    }

    /**
     * cambia la funcion del pirata dentro de su tripulacion
     *
     * @param funcion la nueva funcion que va a tener
     */
    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    /**
     * devuelve la tripulacion a la que pertenece este pirata
     *
     * @return la tripulacion o null si no tiene ninguna
     */
    public Tripulacion getTripulacion() {
        return tripulacion;
    }

    /**
     * asigna o cambia la tripulacion del pirata
     *
     * @param tripulacion la tripulacion a la que se une
     */
    public void setTripulacion(Tripulacion tripulacion) {
        this.tripulacion = tripulacion;
    }

    /**
     * devuelve la generacion a la que pertenece el pirata
     *
     * @return la generacion como texto
     */
    public String getGeneracion() {
        return generacion;
    }

    /**
     * cambia la generacion del pirata
     *
     * @param generacion la nueva generacion
     */
    public void setGeneracion(String generacion) {
        this.generacion = generacion;
    }

    /**
     * muestra un resumen basico del pirata con su recompensa y funcion
     *
     * @return un string con los datos principales
     */
    @Override
    public String toString() {
        return "Pirata [recompensa=" + recompensa + ", funcion=" + funcion + "]";
    }
}
