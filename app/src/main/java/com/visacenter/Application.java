package com.visacenter;

import java.io.Serializable;

public class Application implements Serializable {
    private String name;
    private String visa;
    private int price;
    private int paid;

    public Application(String name, String visa, int price, int paid) {
        this.name = name;
        this.visa = visa;
        this.price = price;
        this.paid = paid;
    }

    public String getName() { return name; }
    public String getVisa() { return visa; }
    public int getPrice() { return price; }
    public int getPaid() { return paid; }

    public void setName(String name) { this.name = name; }
    public void setVisa(String visa) { this.visa = visa; }
    public void setPrice(int price) { this.price = price; }
    public void setPaid(int paid) { this.paid = paid; }

    public int getDebt() { return price - paid; }
}
