package com.onepiece.grandlineAPI.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * esta clase representa una fruta del diablo de one piece
 * las frutas del diablo son objetos especiales que dan poderes a quien las come
 * tiene su nombre su tipo y una lista de personajes que la han comido
 */
@Entity
@Table(name = "frutas")
public class Fruta {

    /** identificador unico que genera la base de datos automaticamente */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** el nombre de la fruta por ejemplo gomu gomu no mi */
    @Column(nullable = false)
    private String nombre;

    /** una explicacion de que poder da esta fruta */
    @Column
    private String descripcion;

    /** el tipo al que pertenece puede ser paramecia zoan o logia */
    @Column(nullable = false)
    private String tipo;

    /** la url de la imagen de la fruta */
    @Column(name = "imagen")
    private String imagen;

    /** lista de personajes que han comido esta fruta normalmente solo uno */
    @ManyToMany(mappedBy = "frutas")
    @JsonIgnoreProperties({"frutas", "tripulacion"})
    private List<Personaje> personajes = new ArrayList<>();

    /**
     * constructor vacio necesario para que jpa pueda crear objetos de esta clase
     */
    public Fruta() {
    }

    /**
     * crea una fruta sin imagen por si no tenemos una todavia
     *
     * @param nombre el nombre de la fruta
     * @param descripcion que poder otorga
     * @param tipo si es paramecia zoan o logia
     */
    public Fruta(String nombre, String descripcion, String tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    /**
     * crea una fruta con todos los datos incluyendo la imagen
     *
     * @param nombre el nombre de la fruta
     * @param descripcion que poder otorga
     * @param tipo si es paramecia zoan o logia
     * @param imagen la url de la imagen
     */
    public Fruta(String nombre, String descripcion, String tipo, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.imagen = imagen;
    }

    /**
     * devuelve el id de la fruta
     *
     * @return el id generado por la base de datos
     */
    public Long getId() {
        return id;
    }

    /**
     * cambia el id de la fruta aunque normalmente esto lo hace la base de datos sola
     *
     * @param id el nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * devuelve el nombre de la fruta
     *
     * @return el nombre como texto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * cambia el nombre de la fruta
     *
     * @param nombre el nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * devuelve la descripcion de lo que hace esta fruta
     *
     * @return la descripcion o null si no tiene ninguna
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * cambia la descripcion de la fruta
     *
     * @param descripcion el nuevo texto descriptivo
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * devuelve el tipo de fruta del diablo que es
     *
     * @return el tipo como texto por ejemplo logia
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * cambia el tipo de la fruta
     *
     * @param tipo el nuevo tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * devuelve la lista de personajes que han comido esta fruta
     *
     * @return la lista de personajes
     */
    public List<Personaje> getPersonajes() {
        return personajes;
    }

    /**
     * cambia la lista de personajes asociados a esta fruta
     *
     * @param personajes la nueva lista de personajes
     */
    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    /**
     * devuelve la url de la imagen de la fruta
     *
     * @return la imagen o null si no tiene
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * cambia la imagen de la fruta
     *
     * @param imagen la nueva url de la imagen
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * muestra un resumen de la fruta con su id tipo descripcion y personajes
     *
     * @return un string con los datos principales
     */
    @Override
    public String toString() {
        return "Fruta [id=" + id + ", descripcion=" + descripcion + ", tipo=" + tipo + ", personajes=" + personajes
                + "]";
    }

}
