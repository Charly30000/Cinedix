
package com.example.cinedix.models.entity;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SesionPeliculaRequest implements Serializable
{

    @SerializedName("sesionPelicula")
    @Expose
    private Integer sesionPelicula;
    @SerializedName("sitiosOcupados")
    @Expose
    private List<Integer> sitiosOcupados = null;
    private final static long serialVersionUID = 1861807930783519229L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SesionPeliculaRequest() {
    }

    /**
     * 
     * @param sesionPelicula
     * @param sitiosOcupados
     */
    public SesionPeliculaRequest(Integer sesionPelicula, List<Integer> sitiosOcupados) {
        super();
        this.sesionPelicula = sesionPelicula;
        this.sitiosOcupados = sitiosOcupados;
    }

    public Integer getSesionPelicula() {
        return sesionPelicula;
    }

    public void setSesionPelicula(Integer sesionPelicula) {
        this.sesionPelicula = sesionPelicula;
    }

    public List<Integer> getSitiosOcupados() {
        return sitiosOcupados;
    }

    public void setSitiosOcupados(List<Integer> sitiosOcupados) {
        this.sitiosOcupados = sitiosOcupados;
    }

}
