package com.team4.getvaxi.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.stream.Stream;


public class Message implements Serializable {
    private String message;
    private String userType;
    private String userId;
    private String adminId;
    private LocalDateTime messageDateTime;
    private LocalDateTime messageRepliedDateTime;
    private String senderName;

    public Message() {
    }

    public Message(String message, String userType, String userId, String adminId, LocalDateTime messageDateTime) {
        this.message = message;
        this.userType = userType;
        this.userId = userId;
        this.adminId = adminId;
        this.messageDateTime = messageDateTime;
    }

    public LocalDateTime getMessageRepliedDateTime() {
        return messageRepliedDateTime;
    }

    public void setMessageRepliedDateTime(LocalDateTime messageRepliedDateTime) {
        this.messageRepliedDateTime = messageRepliedDateTime;
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
                ", messageRepliedDateTime=" + messageRepliedDateTime +
                ", senderName='" + senderName + '\'' +
                '}';
    }
}
