package com.team4.getvaxi.models;

import java.util.ArrayList;

public class VaccineByAge {

    String imageLogo;
    String description;
    ArrayList<Vaccine> vaccinesAtThisAge;


    public String getImageLogo() {
        return imageLogo;
    }

    public void setImageLogo(String imageLogo) {
        this.imageLogo = imageLogo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Vaccine> getVaccinesAtThisAge() {
        return vaccinesAtThisAge;
    }

    public void setVaccinesAtThisAge(ArrayList<Vaccine> vaccinesAtThisAge) {
        this.vaccinesAtThisAge = vaccinesAtThisAge;
    }
}
