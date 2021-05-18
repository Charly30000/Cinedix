
package com.example.cinedix.models.entity;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cine implements Serializable
{

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("localizacion")
    @Expose
    private String localizacion;
    private final static long serialVersionUID = -6868473280527418140L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Cine() {
    }

    /**
     * 
     * @param localizacion
     * @param id
     * @param nombre
     */
    public Cine(Long id, String nombre, String localizacion) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.localizacion = localizacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

}
