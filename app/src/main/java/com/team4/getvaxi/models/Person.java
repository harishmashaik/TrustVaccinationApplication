package com.team4.getvaxi.models;

import java.util.ArrayList;

public class Person {

    private  String personUUID;

    private String personName;
    private String personGender;
    private String personCommonLawPartnerName;
    private int personKids;
    private ArrayList<Child> personChildInfo;
    private boolean profileCompletionStatus;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonGender() {
        return personGender;
    }

    public void setPersonGender(String personGender) {
        this.personGender = personGender;
    }

    public String getPersonCommonLawPartnerName() {
        return personCommonLawPartnerName;
    }

    public void setPersonCommonLawPartnerName(String personCommonLawPartnerName) {
        this.personCommonLawPartnerName = personCommonLawPartnerName;
    }

    public int getPersonKids() {
        return personKids;
    }

    public void setPersonKids(int personKids) {
        this.personKids = personKids;
    }

    public ArrayList<Child> getPersonChildInfo() {
        return personChildInfo;
    }

    public void setPersonChildInfo(ArrayList<Child> personChildInfo) {
        this.personChildInfo = personChildInfo;
    }
}
