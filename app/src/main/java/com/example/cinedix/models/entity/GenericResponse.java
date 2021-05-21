
package com.example.cinedix.models.entity;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenericResponse implements Serializable
{

    @SerializedName("codigo")
    @Expose
    private String codigo;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 9047010153655043154L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GenericResponse() {
    }

    /**
     * 
     * @param codigo
     * @param message
     */
    public GenericResponse(String codigo, String message) {
        super();
        this.codigo = codigo;
        this.message = message;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
