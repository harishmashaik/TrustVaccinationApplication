package com.team4.getvaxi.models;

import java.io.Serializable;

public class Vaccine  implements Serializable {

    private String vaccineName;
    private String vaccineDescription;
    private int vaccineAgeLow;
    private int vaccineAgeHigh;
    private boolean vaccinatedStatus;
    private int vaccineStock;
    private int vaccineDose;


    public int getVaccineDose() {
        return vaccineDose;
    }

    public void setVaccineDose(int vaccineDose) {
        this.vaccineDose = vaccineDose;
    }

    public int getVaccineStock() {
        return vaccineStock;
    }

    public void setVaccineStock(int vaccineStock) {
        this.vaccineStock = vaccineStock;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVaccineDescription() {
        return vaccineDescription;
    }

    public void setVaccineDescription(String vaccineDescription) {
        this.vaccineDescription = vaccineDescription;
    }

    public int getVaccineAgeLow() {
        return vaccineAgeLow;
    }

    public void setVaccineAgeLow(int vaccineAgeLow) {
        this.vaccineAgeLow = vaccineAgeLow;
    }

    public int getVaccineAgeHigh() {
        return vaccineAgeHigh;
    }

    public void setVaccineAgeHigh(int vaccineAgeHigh) {
        this.vaccineAgeHigh = vaccineAgeHigh;
    }

    public boolean isVaccinatedStatus() {
        return vaccinatedStatus;
    }

    public void setVaccinatedStatus(boolean vaccinatedStatus) {
        this.vaccinatedStatus = vaccinatedStatus;
    }

    @Override
    public String toString() {
        return "Vaccine{" +
                "vaccineName='" + vaccineName + '\'' +
                ", vaccineDescription='" + vaccineDescription + '\'' +
                ", vaccineAgeLow=" + vaccineAgeLow +
                ", vaccineAgeHigh=" + vaccineAgeHigh +
                ", vaccinatedStatus=" + vaccinatedStatus +
                '}';
    }
}
