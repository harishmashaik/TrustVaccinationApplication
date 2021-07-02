package com.team4.getvaxi.models;

import java.util.ArrayList;

public class Child {

    private String childName;
    private int childAge;
    private ArrayList<Vaccine> vaccinesConsumed;

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public int getChildAge() {
        return childAge;
    }

    public void setChildAge(int childAge) {
        this.childAge = childAge;
    }

    public ArrayList<Vaccine> getVaccinesConsumed() {
        return vaccinesConsumed;
    }

    public void setVaccinesConsumed(ArrayList<Vaccine> vaccinesConsumed) {
        this.vaccinesConsumed = vaccinesConsumed;
    }
}
