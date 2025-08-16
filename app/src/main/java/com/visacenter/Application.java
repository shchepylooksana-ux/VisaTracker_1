package com.visacenter;

import java.io.Serializable;

public class Application implements Serializable {
    private String name;
    private String visaType;
    private int price;
    private int paid;

    public Application(String name, String visaType, int price, int paid) {
        this.name = name;
        this.visaType = visaType;
        this.price = price;
        this.paid = paid;
    }

    public String getName() { return name; }
    public String getVisaType() { return visaType; }
    public int getPrice() { return price; }
    public int getPaid() { return paid; }

    public void setName(String name) { this.name = name; }
    public void setVisaType(String visaType) { this.visaType = visaType; }
    public void setPrice(int price) { this.price = price; }
    public void setPaid(int paid) { this.paid = paid; }

    // Обчислює борг
    public int getDebt() {
        return price - paid;
    }
}