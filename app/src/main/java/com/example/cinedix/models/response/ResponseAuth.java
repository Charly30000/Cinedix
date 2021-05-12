
package com.example.cinedix.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAuth {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("token")
    @Expose
    private String token;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseAuth() {
    }

    /**
     * 
     * @param message
     * @param user
     * @param token
     */
    public ResponseAuth(String message, User user, String token) {
        super();
        this.message = message;
        this.user = user;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
