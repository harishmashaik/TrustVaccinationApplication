package com.team4.getvaxi.models;

import android.text.Editable;

import java.io.Serializable;
import java.util.ArrayList;

public class Child implements Serializable {

    private String childName;
    private int childAge;
    private String dateOfBirth;
    private ArrayList<Vaccine> vaccinesConsumed;

    public Child() {
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

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

    @Override
    public String toString() {
        return "Child{" +
                "childName='" + childName + '\'' +
                ", childAge=" + childAge +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", vaccinesConsumed=" + vaccinesConsumed +
                '}';
    }

    public void setVaccinesConsumed(ArrayList<Vaccine> vaccinesConsumed) {
        this.vaccinesConsumed = vaccinesConsumed;
    }


}
