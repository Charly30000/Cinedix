
package com.example.cinedix.models.entity;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Role implements Serializable
{

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("authority")
    @Expose
    private String authority;
    private final static long serialVersionUID = -4186349983415814981L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Role() {
    }

    /**
     * 
     * @param authority
     * @param id
     */
    public Role(Long id, String authority) {
        super();
        this.id = id;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
