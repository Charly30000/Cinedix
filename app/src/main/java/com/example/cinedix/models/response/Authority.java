
package com.example.cinedix.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Authority {

    @SerializedName("authority")
    @Expose
    private String authority;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Authority() {
    }

    /**
     * 
     * @param authority
     */
    public Authority(String authority) {
        super();
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
