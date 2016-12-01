package com.ensiie.tp3.bo;

import java.util.ArrayList;

/**
 * Created by Adrian on 30/11/2016.
 */
public class Person {
    private String forename;
    private String name;
    private Address address;
    private String website;
    private ArrayList<Cat> cats;

    public Person(String forename, String name, Address address, String website) {
        this.forename = forename;
        this.name = name;
        this.address = address;
        this.website = website;
    }

    public Person(String forename, String name, Address address, String website, ArrayList<Cat> cats) {
        this.forename = forename;
        this.name = name;
        this.address = address;
        this.website = website;
        this.cats = cats;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Person{" +
                "forename='" + forename + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", website='" + website + '\'' +
                ", cats=" + cats +
                '}';
    }
}
