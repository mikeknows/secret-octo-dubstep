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
public class Buyer implements Serializable {

    private String date;
    private String name;

    public Buyer(String date, String name) {
        this.date = date;
        this.name = name;
    }

    public Buyer() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean notLoaned() {
        return (date == null) && (name == null);

    }

    public void setReturn() {
        name = null;
        date = null;
    }

}
