
package com.example.cinedix.models.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pelicula implements Serializable
{
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("rutaImagen")
    @Expose
    private String rutaImagen;
    @SerializedName("estreno")
    @Expose
    private Boolean estreno;
    private final static long serialVersionUID = 2534420322963193706L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Pelicula() {
    }

    /**
     * 
     * @param descripcion
     * @param estreno
     * @param id
     * @param rutaImagen
     * @param nombre
     */
    public Pelicula(Long id, String nombre, String descripcion, String rutaImagen, Boolean estreno) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        this.estreno = estreno;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public Boolean getEstreno() {
        return estreno;
    }

    public void setEstreno(Boolean estreno) {
        this.estreno = estreno;
    }

}
