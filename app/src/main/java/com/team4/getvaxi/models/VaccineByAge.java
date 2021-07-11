package com.team4.getvaxi.models;

import java.util.ArrayList;

public class VaccineByAge {

    String imageLogo;
    String description;
    ArrayList<String> vaccines;


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

    public ArrayList<String> getVaccines() {
        return vaccines;
    }

    public void setVaccines(ArrayList<String> vaccines) {
        this.vaccines = vaccines;
    }
}
