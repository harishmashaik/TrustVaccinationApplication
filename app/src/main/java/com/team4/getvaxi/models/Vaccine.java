package com.team4.getvaxi.models;

public class Vaccine {

    private String vaccineName;
    private String vaccineDescription;
    private int vaccineAgeLow;
    private int vaccineAgeHigh;
    private boolean vaccinatedStatus;


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
}
