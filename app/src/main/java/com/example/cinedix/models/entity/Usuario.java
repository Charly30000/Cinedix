
package com.example.cinedix.models.entity;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario implements Serializable
{

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("enabled")
    @Expose
    private Boolean enabled;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("roles")
    @Expose
    private List<Role> roles = null;
    private final static long serialVersionUID = 2822291488280587027L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Usuario() {
    }

    /**
     * 
     * @param password
     * @param roles
     * @param id
     * @param enabled
     * @param email
     * @param username
     */
    public Usuario(Long id, String username, String password, Boolean enabled, String email, List<Role> roles) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
