package com.visacenter.model;

public class VisaEntry {
    public long id;
    public String from;
    public String name;
    public String country;
    public String visaType;
    public String requestDate;
    public String submitDate;
    public String resultDate;
    public double paid;
    public double expenses;
    public double debt;

    public double net() { return paid - expenses; }
}