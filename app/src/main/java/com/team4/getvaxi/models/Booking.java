package com.team4.getvaxi.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Booking implements Serializable {

    String fbDocID;
    String age;
    String name;
    String vaccineName;
    String appointmentDate;
    Date dateOfBooking;
    Boolean bookingReviewed = false;
    HashMap<String,String> vaccinationCenterDetails = new HashMap<>();
    String remarks;
    String boookingStatus;
    String userId;
    int vaccineDose;

    public int getVaccineDose() {
        return vaccineDose;
    }

    public void setVaccineDose(int vaccineDose) {
        this.vaccineDose = vaccineDose;
    }

    public String getBoookingStatus() {
        return boookingStatus;
    }

    public void setBoookingStatus(String boookingStatus) {
        this.boookingStatus = boookingStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getBookingReviewed() {
        return bookingReviewed;
    }

    public void setBookingReviewed(Boolean bookingReviewed) {
        this.bookingReviewed = bookingReviewed;
    }

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
        this.vaccineDose = in.readInt();
        this.appointmentDate = in.readString();
        this.fbDocID =in.readString();
        this.userId = in.readString();
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
                "fbDocID='" + fbDocID + '\'' +
                ", age='" + age + '\'' +
                ", name='" + name + '\'' +
                ", vaccineName='" + vaccineName + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", dateOfBooking=" + dateOfBooking +
                ", bookingReviewed=" + bookingReviewed +
                ", vaccinationCenterDetails=" + vaccinationCenterDetails +
                ", remarks='" + remarks + '\'' +
                ", boookingStatus='" + boookingStatus + '\'' +
                ", userId='" + userId + '\'' +
                ", vaccineDose=" + vaccineDose +
                '}';
    }
}
