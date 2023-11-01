package com.osumed.chatapplication.domain;

public class Message {

    private Integer messageId;
    private String message;
    private String userId;
    private String channel;

    public Message() {
    }

    public Message(String message, String userId, String channel) {
        this.message = message;
        this.userId = userId;
        this.channel = channel;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Message [messageId=" + messageId + ", message=" + message + ", userId=" + userId + ", channel="
                + channel + "]";
    }

}