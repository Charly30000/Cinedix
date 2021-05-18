
package com.example.cinedix.models.entity;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SesionPelicula implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("sitiosTotales")
    @Expose
    private Integer sitiosTotales;
    @SerializedName("horaPelicula")
    @Expose
    private String horaPelicula;
    @SerializedName("sitiosOcupados")
    @Expose
    private List<SitiosOcupado> sitiosOcupados = null;
    @SerializedName("cine")
    @Expose
    private Cine cine;
    private final static long serialVersionUID = 3011134047055310726L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SesionPelicula() {
    }

    /**
     * 
     * @param sitiosTotales
     * @param horaPelicula
     * @param sitiosOcupados
     * @param cine
     * @param id
     */
    public SesionPelicula(Integer id, Integer sitiosTotales, String horaPelicula, List<SitiosOcupado> sitiosOcupados, Cine cine) {
        super();
        this.id = id;
        this.sitiosTotales = sitiosTotales;
        this.horaPelicula = horaPelicula;
        this.sitiosOcupados = sitiosOcupados;
        this.cine = cine;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSitiosTotales() {
        return sitiosTotales;
    }

    public void setSitiosTotales(Integer sitiosTotales) {
        this.sitiosTotales = sitiosTotales;
    }

    public String getHoraPelicula() {
        return horaPelicula;
    }

    public void setHoraPelicula(String horaPelicula) {
        this.horaPelicula = horaPelicula;
    }

    public List<SitiosOcupado> getSitiosOcupados() {
        return sitiosOcupados;
    }

    public void setSitiosOcupados(List<SitiosOcupado> sitiosOcupados) {
        this.sitiosOcupados = sitiosOcupados;
    }

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }

}
