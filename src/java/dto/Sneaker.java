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
public class Sneaker implements Serializable {

    private int shoesId;
    private String name;
    private double price;
    private String picture;
    private boolean sale;
    private int quantity;
    private String selledBy;
    private int viewed;

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    public Sneaker(int shoesId, String name, double price, String picture, boolean sale, int quantity, String selledBy, int viewed) {
        this.shoesId = shoesId;
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.sale = sale;
        this.quantity = quantity;
        this.selledBy = selledBy;
        this.viewed = viewed;
    }

    public int getViewed() {
        return viewed;
    }

    public Sneaker() {
    }

    public Sneaker(int shoesId, String name, String picture) {
        this.shoesId = shoesId;
        this.name = name;
        this.picture = picture;
    }

    public Sneaker(int shoesId, String name, double price, String picture) {
        this.shoesId = shoesId;
        this.name = name;
        this.price = price;
        this.picture = picture;
    }

    public Sneaker(int shoesId, String name, double price, String picture, boolean sale, int quantity, String selledBy) {
        this.shoesId = shoesId;
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.sale = sale;
        this.quantity = quantity;
        this.selledBy = selledBy;
    }

    public void setSelledBy(String selledBy) {
        this.selledBy = selledBy;
    }

    public String getSelledBy() {
        return selledBy;
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * @return the sale
     */
    public boolean isSale() {
        return sale;
    }

    /**
     * @param sale the sale to set
     */
    public void setSale(boolean sale) {
        this.sale = sale;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
