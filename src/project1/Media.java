/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.io.Serializable;

/**
 *
 * @author plymi
 */
public class Media implements Comparable<Media>, Serializable {

    private String title;
    private String format;
    private Buyer buyerdata;

    public Buyer getBuyerdata() {
        return buyerdata;
    }

    public void setBuyerdata(Buyer buyerdata) {
        this.buyerdata = buyerdata;
    }
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Media(String title, String format) {
        this.title = title;
        this.format = format;
        buyerdata = new Buyer();
    }

    @Override
    public int compareTo(Media o) {
        return title.compareTo(o.getTitle());
        
        
        
        
        
    }

}
