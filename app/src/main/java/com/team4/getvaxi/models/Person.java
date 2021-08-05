package com.team4.getvaxi.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {

  private String personUUID;

  private String personName;
  private String personGender;
  private String personCommonLawPartnerName;
  private int personKids;
  private ArrayList<Child> personChildInfo;
  private boolean profileCompletionStatus;
  private String personEmail;
  private String personPhoneNum;
  private String residingProvince;
  private String residingAddress;

  public String getResidingProvince() {
    return residingProvince;
  }

  public void setResidingProvince(String residingProvince) {
    this.residingProvince = residingProvince;
  }

  public String getResidingAddress() {
    return residingAddress;
  }

  public void setResidingAddress(String residingAddress) {
    this.residingAddress = residingAddress;
  }

  public String getPersonPhoneNum() {
    return personPhoneNum;
  }

  public void setPersonPhoneNum(String personPhoneNum) {
    this.personPhoneNum = personPhoneNum;
  }

  public String getPersonEmail() {
    return personEmail;
  }

  public void setPersonEmail(String personEmail) {
    this.personEmail = personEmail;
  }

  public String getPersonUUID() {
    return personUUID;
  }

  public void setPersonUUID(String personUUID) {
    this.personUUID = personUUID;
  }

  public boolean isProfileCompletionStatus() {
    return profileCompletionStatus;
  }

  public void setProfileCompletionStatus(boolean profileCompletionStatus) {
    this.profileCompletionStatus = profileCompletionStatus;
  }

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

  @Override
  public String toString() {
    return "Person{" +
            "personUUID='" + personUUID + '\'' +
            ", personName='" + personName + '\'' +
            ", personGender='" + personGender + '\'' +
            ", personCommonLawPartnerName='" + personCommonLawPartnerName + '\'' +
            ", personKids=" + personKids +
            ", personChildInfo=" + personChildInfo +
            ", profileCompletionStatus=" + profileCompletionStatus +
            ", personEmail='" + personEmail + '\'' +
            ", personPhoneNum='" + personPhoneNum + '\'' +
            ", residingProvince='" + residingProvince + '\'' +
            ", residingAddress='" + residingAddress + '\'' +
            '}';
  }
}
