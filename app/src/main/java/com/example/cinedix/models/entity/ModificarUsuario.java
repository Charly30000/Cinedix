
package com.example.cinedix.models.entity;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModificarUsuario implements Serializable
{

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("newPassword")
    @Expose
    private String newPassword;
    @SerializedName("oldPassword")
    @Expose
    private String oldPassword;
    @SerializedName("email")
    @Expose
    private String email;
    private final static long serialVersionUID = 2475152125016821411L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ModificarUsuario() {
    }

    /**
     * 
     * @param oldPassword
     * @param newPassword
     * @param email
     * @param username
     */
    public ModificarUsuario(String username, String newPassword, String oldPassword, String email) {
        super();
        this.username = username;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
