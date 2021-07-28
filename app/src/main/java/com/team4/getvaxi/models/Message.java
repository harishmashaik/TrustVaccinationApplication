package com.team4.getvaxi.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.stream.Stream;


public class Message implements Serializable {
    private String message;
    private String userType;
    private String userId;
    private String adminId;
    private String messageDateTime;
    private String messageRepliedDateTime;
    private String senderName;

    public Message() {
    }



    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

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


    public String getMessageDateTime() {
        return messageDateTime;
    }

    public void setMessageDateTime(String messageDateTime) {
        this.messageDateTime = messageDateTime;
    }

    public String getMessageRepliedDateTime() {
        return messageRepliedDateTime;
    }

    public void setMessageRepliedDateTime(String messageRepliedDateTime) {
        this.messageRepliedDateTime = messageRepliedDateTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", userType='" + userType + '\'' +
                ", userId='" + userId + '\'' +
                ", adminId='" + adminId + '\'' +
                ", messageDateTime='" + messageDateTime + '\'' +
                ", messageRepliedDateTime='" + messageRepliedDateTime + '\'' +
                ", senderName='" + senderName + '\'' +
                '}';
    }
}
