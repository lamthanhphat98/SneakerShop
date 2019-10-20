/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class History implements Serializable {

    private String username;
    private int shoesId;

    public History() {
    }

    public History(String username, int shoesId) {
        this.username = username;
        this.shoesId = shoesId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the shoesId
     */
    public int getShoesId() {
        return shoesId;
    }

    /**
     * @param shoesId the shoesId to set
     */
    public void setShoesId(int shoesId) {
        this.shoesId = shoesId;
    }

}
