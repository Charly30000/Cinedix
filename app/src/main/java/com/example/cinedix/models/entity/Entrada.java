
package com.example.cinedix.models.entity;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entrada implements Serializable
{

    @SerializedName("codigo")
    @Expose
    private String codigo;
    @SerializedName("estado")
    @Expose
    private String estado;
    @SerializedName("sitiosOcupados")
    @Expose
    private List<SitiosOcupado> sitiosOcupados = null;
    @SerializedName("horaPelicula")
    @Expose
    private String horaPelicula;
    @SerializedName("cine")
    @Expose
    private String cine;
    @SerializedName("pelicula")
    @Expose
    private String pelicula;
    @SerializedName("fechaCreacion")
    @Expose
    private String fechaCreacion;
    @SerializedName("id")
    @Expose
    private Long id;
    private final static long serialVersionUID = -5676534767736838626L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Entrada() {
    }

    /**
     * 
     * @param codigo
     * @param estado
     * @param sitiosOcupados
     * @param horaPelicula
     * @param cine
     * @param pelicula
     * @param fechaCreacion
     * @param id
     */
    public Entrada(String codigo, String estado, List<SitiosOcupado> sitiosOcupados, String horaPelicula,
                   String cine, String pelicula, String fechaCreacion, Long id) {
        super();
        this.codigo = codigo;
        this.estado = estado;
        this.sitiosOcupados = sitiosOcupados;
        this.horaPelicula = horaPelicula;
        this.cine = cine;
        this.pelicula = pelicula;
        this.fechaCreacion = fechaCreacion;
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<SitiosOcupado> getSitiosOcupados() {
        return sitiosOcupados;
    }

    public void setSitiosOcupados(List<SitiosOcupado> sitiosOcupados) {
        this.sitiosOcupados = sitiosOcupados;
    }

    public String getHoraPelicula() {
        return horaPelicula;
    }

    public void setHoraPelicula(String horaPelicula) {
        this.horaPelicula = horaPelicula;
    }

    public String getCine() {
        return cine;
    }

    public void setCine(String cine) {
        this.cine = cine;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
