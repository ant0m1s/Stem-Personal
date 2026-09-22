package com.onepiece.grandlineAPI.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * esta es la clase base de la que heredan todos los personajes de la api
 * es abstracta porque no tiene sentido crear un personaje generico sin tipo
 * piratas miembros del gobierno y otros todos parten de aqui
 * usa herencia de tipo joined para que cada subclase tenga su propia tabla
 */
@Entity
@Table(name = "personajes")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Personaje {

    /** identificador unico generado automaticamente por la base de datos */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /** el nombre completo del personaje sin apodos */
    @Column(name = "nombre_completo", nullable = false)
    protected String nombreCompleto;

    /** el apodo o nombre por el que se le conoce en el mundo */
    @Column(name = "apodo", nullable = false)
    protected String alias;

    /** la altura del personaje en metros */
    @Column(name = "altura")
    protected Double altura;

    /** la edad del personaje en anos */
    @Column(name = "edad")
    protected Integer edad;

    /** un texto corto explicando quien es el personaje maximo 200 caracteres */
    @Column(name = "descripcion", nullable = false, length = 200)
    protected String descripcion;

    /** si el personaje sigue vivo o ha muerto en la historia */
    @Column(name = "estado", nullable = false)
    protected Boolean estado;

    /** la url de la imagen del personaje */
    @Column(name = "imagen")
    protected String imagen;

    /** lista de frutas del diablo que ha comido este personaje normalmente ninguna o una */
    @ManyToMany
    @JoinTable(name = "personaje_fruta", joinColumns = @JoinColumn(name = "personaje_id"), inverseJoinColumns = @JoinColumn(name = "fruta_id"))
    protected List<Fruta> frutas = new ArrayList<>();

    /**
     * constructor vacio necesario para que jpa pueda funcionar
     */
    public Personaje() {
    }

    /**
     * crea un personaje con sus datos basicos pero sin imagen
     *
     * @param nombreCompleto el nombre completo del personaje
     * @param alias su apodo o nombre conocido
     * @param altura cuanto mide en metros
     * @param edad cuantos anos tiene
     * @param descripcion un texto corto sobre quien es
     * @param estado si sigue vivo o no
     */
    public Personaje(String nombreCompleto, String alias, Double altura, Integer edad, String descripcion,
            Boolean estado) {
        this.nombreCompleto = nombreCompleto;
        this.alias = alias;
        this.altura = altura;
        this.edad = edad;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    /**
     * crea un personaje con todos sus datos incluida la imagen
     *
     * @param nombreCompleto el nombre completo del personaje
     * @param alias su apodo o nombre conocido
     * @param altura cuanto mide en metros
     * @param edad cuantos anos tiene
     * @param descripcion un texto corto sobre quien es
     * @param estado si sigue vivo o no
     * @param imagen la url de su imagen
     */
    public Personaje(String nombreCompleto, String alias, Double altura, Integer edad, String descripcion,
            Boolean estado, String imagen) {
        this.nombreCompleto = nombreCompleto;
        this.alias = alias;
        this.altura = altura;
        this.edad = edad;
        this.descripcion = descripcion;
        this.estado = estado;
        this.imagen = imagen;
    }

    /**
     * devuelve el id del personaje
     *
     * @return el id generado por la base de datos
     */
    public Long getId() {
        return id;
    }

    /**
     * cambia el id del personaje aunque esto normalmente lo hace la base de datos
     *
     * @param id el nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * devuelve el nombre completo del personaje
     *
     * @return el nombre como texto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * cambia el nombre completo del personaje
     *
     * @param nombreCompleto el nuevo nombre
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * devuelve el alias o apodo del personaje
     *
     * @return el alias como texto
     */
    public String getAlias() {
        return alias;
    }

    /**
     * cambia el alias del personaje
     *
     * @param alias el nuevo apodo
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * devuelve la altura del personaje en metros
     *
     * @return la altura como decimal
     */
    public Double getAltura() {
        return altura;
    }

    /**
     * cambia la altura del personaje
     *
     * @param altura la nueva altura en metros
     */
    public void setAltura(Double altura) {
        this.altura = altura;
    }

    /**
     * devuelve la edad del personaje
     *
     * @return la edad en anos
     */
    public Integer getFechaNac() {
        return edad;
    }

    /**
     * cambia la edad del personaje
     *
     * @param edad la nueva edad
     */
    public void setFechaNac(Integer edad) {
        this.edad = edad;
    }

    /**
     * devuelve la descripcion del personaje
     *
     * @return el texto descriptivo
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * cambia la descripcion del personaje
     *
     * @param descripcion el nuevo texto descriptivo
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * devuelve si el personaje esta vivo o muerto
     *
     * @return true si sigue vivo false si ha muerto
     */
    public Boolean getEstado() {
        return estado;
    }

    /**
     * cambia el estado del personaje
     *
     * @param estado true si vivo false si muerto
     */
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    /**
     * devuelve la edad del personaje
     *
     * @return la edad en anos
     */
    public Integer getEdad() {
        return edad;
    }

    /**
     * cambia la edad del personaje
     *
     * @param edad la nueva edad
     */
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    /**
     * devuelve la url de la imagen del personaje
     *
     * @return la imagen o null si no tiene
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * cambia la imagen del personaje
     *
     * @param imagen la nueva url de la imagen
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * devuelve la lista de frutas del diablo que ha comido este personaje
     *
     * @return la lista de frutas puede estar vacia si no ha comido ninguna
     */
    public List<Fruta> getFrutas() {
        return frutas;
    }

    /**
     * cambia la lista de frutas del personaje
     *
     * @param frutas la nueva lista de frutas
     */
    public void setFrutas(List<Fruta> frutas) {
        this.frutas = frutas;
    }

    /**
     * muestra un resumen del personaje con sus datos principales
     *
     * @return un string con los datos mas importantes
     */
    @Override
    public String toString() {
        return "Personaje [id=" + id + ", nombreCompleto=" + nombreCompleto + ", alias=" + alias + ", altura=" + altura
                + ", edad=" + edad + ", descripcion=" + descripcion + ", estado=" + estado + ", frutas="
                + frutas + "]";
    }

}
