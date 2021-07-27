package com.team4.getvaxi.models;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Message {
    private String message;
    private String userType;
    private String userId;
    private String adminId;
    private LocalDateTime messageDateTime;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public LocalDateTime getMessageDateTime() {
        return messageDateTime;
    }

    public void setMessageDateTime(LocalDateTime messageDateTime) {
        this.messageDateTime = messageDateTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", userType='" + userType + '\'' +
                ", userId='" + userId + '\'' +
                ", adminId='" + adminId + '\'' +
                ", messageDateTime=" + messageDateTime +
                '}';
    }
}
