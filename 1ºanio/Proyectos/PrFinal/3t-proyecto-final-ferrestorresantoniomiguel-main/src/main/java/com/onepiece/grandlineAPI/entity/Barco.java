package com.onepiece.grandlineAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * esta clase representa un barco del mundo de one piece
 * cada barco pertenece a una tripulacion y puede estar activo o destruido
 * es una entidad sencilla comparada con pirata o fruta
 */
@Entity
@Table(name = "barcos")
public class Barco {

    /** identificador unico que pone la base de datos automaticamente */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** el nombre del barco por ejemplo thousand sunny o going merry */
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /** indica si el barco sigue en pie o ha sido destruido */
    @Column(name = "estado", nullable = false)
    private Boolean activo;

    /** la url de la imagen del barco */
    @Column(name = "imagen")
    private String imagen;

    /** la tripulacion a la que pertenece este barco */
    @ManyToOne
    @JoinColumn(name = "tripulacion_id")
    @JsonIgnoreProperties({ "piratas", "barcos" })
    private Tripulacion tripulacion;

    /**
     * constructor vacio que necesita jpa para funcionar
     */
    public Barco() {
    }

    /**
     * crea un barco con todos sus datos
     *
     * @param nombre el nombre del barco
     * @param activo si sigue activo o ha sido destruido
     * @param tripulacion la tripulacion duena del barco
     * @param imagen la url de la imagen
     */
    public Barco(String nombre, Boolean activo, Tripulacion tripulacion, String imagen) {
        this.nombre = nombre;
        this.activo = activo;
        this.tripulacion = tripulacion;
        this.imagen = imagen;
    }

    /**
     * devuelve el id del barco
     *
     * @return el id generado por la base de datos
     */
    public Long getId() {
        return id;
    }

    /**
     * cambia el id del barco aunque esto normalmente lo gestiona la base de datos
     *
     * @param id el nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * devuelve el nombre del barco
     *
     * @return el nombre como texto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * cambia el nombre del barco
     *
     * @param nombre el nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * devuelve si el barco sigue activo o no
     *
     * @return true si sigue en pie false si fue destruido
     */
    public Boolean getActivo() {
        return activo;
    }

    /**
     * cambia el estado del barco
     *
     * @param activo true si sigue activo false si fue destruido
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    /**
     * devuelve la tripulacion a la que pertenece este barco
     *
     * @return la tripulacion o null si no tiene ninguna
     */
    public Tripulacion getTripulacion() {
        return tripulacion;
    }

    /**
     * asigna o cambia la tripulacion del barco
     *
     * @param tripulacion la nueva tripulacion duena del barco
     */
    public void setTripulacion(Tripulacion tripulacion) {
        this.tripulacion = tripulacion;
    }

    /**
     * devuelve la url de la imagen del barco
     *
     * @return la imagen o null si no tiene
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * cambia la imagen del barco
     *
     * @param imagen la nueva url de la imagen
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * muestra un resumen del barco con su id nombre estado y tripulacion
     *
     * @return un string con los datos principales
     */
    @Override
    public String toString() {
        return "Barco [id=" + id + ", nombre=" + nombre + ", activo=" + activo + ", tripulacion=" + tripulacion + "]";
    }

}
