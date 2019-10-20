/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author ADMIN
 */
public class Category {

    private String shopUrl;
    private int crawl;

    public Category(String shopUrl, int crawl) {
        this.shopUrl = shopUrl;
        this.crawl = crawl;
    }

    public Category() {
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

    /**
     * @return the crawl
     */
    public int getCrawl() {
        return crawl;
    }

    /**
     * @param crawl the crawl to set
     */
    public void setCrawl(int crawl) {
        this.crawl = crawl;
    }

}
