package com.team4.getvaxi.models;

import java.util.Date;

public class Booking {

    String age;
    String name;
    String vaccineName;
    String appointmentDate;
    Date dateOfBooking;

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
