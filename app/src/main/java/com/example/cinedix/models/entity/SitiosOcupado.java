
package com.example.cinedix.models.entity;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SitiosOcupado implements Serializable
{

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("sitioOcupado")
    @Expose
    private Integer sitioOcupado;
    private final static long serialVersionUID = -6640261004656649164L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SitiosOcupado() {
    }

    /**
     * 
     * @param id
     * @param sitioOcupado
     */
    public SitiosOcupado(Long id, Integer sitioOcupado) {
        super();
        this.id = id;
        this.sitioOcupado = sitioOcupado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSitioOcupado() {
        return sitioOcupado;
    }

    public void setSitioOcupado(Integer sitioOcupado) {
        this.sitioOcupado = sitioOcupado;
    }

}
