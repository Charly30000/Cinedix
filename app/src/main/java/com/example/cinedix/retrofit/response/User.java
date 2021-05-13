
package com.example.cinedix.retrofit.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("authorities")
    @Expose
    private List<Authority> authorities = null;
    @SerializedName("accountNonExpired")
    @Expose
    private Boolean accountNonExpired;
    @SerializedName("accountNonLocked")
    @Expose
    private Boolean accountNonLocked;
    @SerializedName("credentialsNonExpired")
    @Expose
    private Boolean credentialsNonExpired;
    @SerializedName("enabled")
    @Expose
    private Boolean enabled;

    /**
     * No args constructor for use in serialization
     * 
     */
    public User() {
    }

    /**
     * 
     * @param password
     * @param credentialsNonExpired
     * @param accountNonExpired
     * @param authorities
     * @param enabled
     * @param username
     * @param accountNonLocked
     */
    public User(Object password, String username, List<Authority> authorities, Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled) {
        super();
        this.password = password;
        this.username = username;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
