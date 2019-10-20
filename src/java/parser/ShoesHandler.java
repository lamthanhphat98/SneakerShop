/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import dao.SneakerDAO;
import dto.Product;
import dto.Sneaker;
import java.math.BigDecimal;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ShoesHandler extends DefaultHandler {

    private String tagName;
    private Product dto;
    private SneakerDAO dao;
    private int count;
    private int fail;
    private int exist;
    public String message;
    public Sneaker sneaker;
    private boolean error = false;

    public ShoesHandler() {
        dao = new SneakerDAO();
        count = 0;
        exist = 0;
        fail = 0;
    }

    public ShoesHandler(Sneaker sneaker) {
        this.sneaker = sneaker;
        dao = new SneakerDAO();
        count = 0;
        fail = 0;
        exist = 0;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.tagName = qName;
        if (qName.equals("product")) {
            dto = new Product();
        }
    }

    @Override
    public void characters(char[] chars, int start, int length) throws SAXException {
        String str = new String(chars, start, length);
        switch (tagName) {
            case "name":
                if (str.trim().equals("")) {
                    fail++;
                    error = true;
                } else {
                    dto.setName(str.trim());
                }
                break;
            case "price":
                BigDecimal price = new BigDecimal(str.trim());
                if (str.trim().equals("0")) {
                    fail++;
                    error = true;
                }
                dto.setPrice(price);
                break;
            case "picture":
                dto.setPicture(str.trim());
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        try {
            if (qName.equals("product")) {
                //boolean success = dao..add(dto);
                boolean checkShoes = dao.getExistedShoesByDomain(sneaker.getName(), sneaker.getSelledBy());
                if (error != true) {
                    if (checkShoes) {
                        exist++;
                    } else {
                        boolean success = dao.insertShoes(sneaker);
                        if (success) {
                            count++;
                        }
                    }
                }
            }
            this.tagName = "";
            this.error = false;
        } catch (Exception ex) {
            System.out.println("Error at Handler : " + ex.getMessage());
        }

    }

    @Override
    public void endDocument() throws SAXException {
        this.message = "Crawled " + count + " products ";
        this.message += "within " + exist + " products existed in the system and";
        this.message += " " + fail + " failed when inserting into the database";
    }

}
