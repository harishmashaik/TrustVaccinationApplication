package com.team4.getvaxi.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.HashMap;

public class Booking implements Parcelable {

    String fbDocID;
    String age;
    String name;
    String vaccineName;
    String appointmentDate;
    Date dateOfBooking;
    HashMap<String,String> vaccinationCenterDetails = new HashMap<>();

    public String getFbDocID() {
        return fbDocID;
    }

    public void setFbDocID(String fbDocID) {
        this.fbDocID = fbDocID;
    }

    public HashMap<String, String> getVaccinationCenterDetails() {
        return vaccinationCenterDetails;
    }

    public void setVaccinationCenterDetails(HashMap<String, String> vaccinationCenterDetails) {
        this.vaccinationCenterDetails = vaccinationCenterDetails;
    }

    public Booking() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.age);
        dest.writeString(this.name);
        dest.writeString(this.vaccineName);
        dest.writeString(this.appointmentDate);

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Booking createFromParcel(Parcel in) {
            return new Booking(in);
        }

        public Booking[] newArray(int size) {
            return new Booking[size];
        }
    };


    public Booking(Parcel in) {
        this.age = in.readString();
        this.name = in.readString();
        this.vaccineName = in.readString();
        this.appointmentDate = in.readString();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Date getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(Date dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "age='" + age + '\'' +
                ", name='" + name + '\'' +
                ", vaccineName='" + vaccineName + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", dateOfBooking=" + dateOfBooking +
                '}';
    }


}
