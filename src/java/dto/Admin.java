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
public class Admin implements Serializable {

    private String username;
    private String password;
    private int viewed;
    private String shopUrl;

    public Admin() {
    }

    public Admin(String username, String password, int viewed, String shopUrl) {
        this.username = username;
        this.password = password;
        this.viewed = viewed;
        this.shopUrl = shopUrl;
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the viewed
     */
    public int getViewed() {
        return viewed;
    }

    /**
     * @param viewed the viewed to set
     */
    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    /**
     * @return the shopUrl
     */
    public String getShopUrl() {
        return shopUrl;
    }

    /**
     * @param shopUrl the shopUrl to set
     */
    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

}
