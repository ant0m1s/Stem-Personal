package com.onepiece.grandlineAPI.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * esta clase representa una tripulacion del mundo de one piece
 * una tripulacion agrupa a varios piratas y tiene uno o mas barcos
 * guarda la recompensa total sumando la de todos sus miembros
 * si se borra una tripulacion se borran tambien sus piratas y barcos por el cascade
 */
@Entity
@Table(name = "tripulaciones")
public class Tripulacion {

    /** identificador unico generado por la base de datos */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** el nombre de la tripulacion por ejemplo sombrero de paja */
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /** cuantos miembros tiene la tripulacion */
    @Column(name = "numeroMiembros", nullable = false)
    private Integer numeroMiembros;

    /** una descripcion explicando de que va esta tripulacion */
    @Column(name = "descripcion")
    private String descripcion;

    /** la suma de todas las recompensas de sus miembros en berries */
    @Column(name = "recompensaTotal", nullable = false)
    private Double recompensaTotal;

    /** a que generacion de piratas pertenece esta tripulacion */
    @Column(name = "generacion", nullable = false)
    private String generacion;

    /** la url de la imagen o bandera de la tripulacion */
    @Column(name = "imagen")
    private String imagen;

    /** lista de piratas que forman parte de esta tripulacion */
    @OneToMany(mappedBy = "tripulacion", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Pirata> piratas = new ArrayList<>();

    /** lista de barcos que tiene esta tripulacion */
    @OneToMany(mappedBy = "tripulacion", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Barco> barcos = new ArrayList<>();

    /**
     * constructor vacio necesario para que jpa pueda funcionar
     */
    public Tripulacion() {
    }

    /**
     * crea una tripulacion sin imagen por si no tenemos una todavia
     *
     * @param nombre el nombre de la tripulacion
     * @param numeroMiembros cuantos miembros tiene
     * @param descripcion un texto explicando la tripulacion
     * @param recompensaTotal la suma de recompensas de todos sus miembros
     * @param generacion a que generacion pertenece
     */
    public Tripulacion(String nombre, Integer numeroMiembros, String descripcion, Double recompensaTotal, String generacion) {
        this.nombre = nombre;
        this.numeroMiembros = numeroMiembros;
        this.descripcion = descripcion;
        this.recompensaTotal = recompensaTotal;
        this.generacion = generacion;
    }

    /**
     * crea una tripulacion con todos sus datos incluida la imagen
     *
     * @param nombre el nombre de la tripulacion
     * @param numeroMiembros cuantos miembros tiene
     * @param descripcion un texto explicando la tripulacion
     * @param recompensaTotal la suma de recompensas de todos sus miembros
     * @param generacion a que generacion pertenece
     * @param imagen la url de la imagen o bandera
     */
    public Tripulacion(String nombre, Integer numeroMiembros, String descripcion, Double recompensaTotal, String generacion, String imagen) {
        this.nombre = nombre;
        this.numeroMiembros = numeroMiembros;
        this.descripcion = descripcion;
        this.recompensaTotal = recompensaTotal;
        this.generacion = generacion;
        this.imagen = imagen;
    }

    /**
     * devuelve el id de la tripulacion
     *
     * @return el id generado por la base de datos
     */
    public Long getId() {
        return id;
    }

    /**
     * cambia el id de la tripulacion aunque esto lo gestiona la base de datos
     *
     * @param id el nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * devuelve el nombre de la tripulacion
     *
     * @return el nombre como texto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * cambia el nombre de la tripulacion
     *
     * @param nombre el nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * devuelve el numero de miembros de la tripulacion
     *
     * @return la cantidad de miembros
     */
    public Integer getNumeroMiembros() {
        return numeroMiembros;
    }

    /**
     * cambia el numero de miembros de la tripulacion
     *
     * @param numeroMiembros el nuevo numero de miembros
     */
    public void setNumeroMiembros(Integer numeroMiembros) {
        this.numeroMiembros = numeroMiembros;
    }

    /**
     * devuelve la descripcion de la tripulacion
     *
     * @return el texto descriptivo o null si no tiene
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * cambia la descripcion de la tripulacion
     *
     * @param descripcion el nuevo texto descriptivo
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * devuelve la recompensa total sumando la de todos sus miembros
     *
     * @return la recompensa total en berries
     */
    public Double getRecompensaTotal() {
        return recompensaTotal;
    }

    /**
     * cambia la recompensa total de la tripulacion
     *
     * @param recompensaTotal el nuevo valor en berries
     */
    public void setRecompensaTotal(Double recompensaTotal) {
        this.recompensaTotal = recompensaTotal;
    }

    /**
     * devuelve la lista de piratas que pertenecen a esta tripulacion
     *
     * @return la lista de piratas puede estar vacia
     */
    public List<Pirata> getPiratas() {
        return piratas;
    }

    /**
     * cambia la lista de piratas de la tripulacion
     *
     * @param piratas la nueva lista de piratas
     */
    public void setPiratas(List<Pirata> piratas) {
        this.piratas = piratas;
    }

    /**
     * devuelve la lista de barcos que tiene esta tripulacion
     *
     * @return la lista de barcos puede estar vacia
     */
    public List<Barco> getBarcos() {
        return barcos;
    }

    /**
     * cambia la lista de barcos de la tripulacion
     *
     * @param barcos la nueva lista de barcos
     */
    public void setBarcos(List<Barco> barcos) {
        this.barcos = barcos;
    }

    /**
     * devuelve la generacion a la que pertenece esta tripulacion
     *
     * @return la generacion como texto
     */
    public String getGeneracion() {
        return generacion;
    }

    /**
     * cambia la generacion de la tripulacion
     *
     * @param generacion la nueva generacion
     */
    public void setGeneracion(String generacion) {
        this.generacion = generacion;
    }

    /**
     * devuelve la url de la imagen de la tripulacion
     *
     * @return la imagen o null si no tiene
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * cambia la imagen de la tripulacion
     *
     * @param imagen la nueva url de la imagen
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * muestra un resumen de la tripulacion con sus datos principales
     *
     * @return un string con los datos mas importantes
     */
    @Override
    public String toString() {
        return "Tripulacion [id=" + id + ", nombre=" + nombre + ", numeroMiembros=" + numeroMiembros + ", descripcion="
                + descripcion + ", recompensaTotal=" + recompensaTotal + ", piratas=" + piratas + "]";
    }

}
