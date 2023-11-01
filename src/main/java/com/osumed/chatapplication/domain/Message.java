package com.osumed.chatapplication.domain;

public class Message {

    private Integer messageId;
    private String message;
    private Integer userId;
    private Integer channelId;

    public Message() {
    }

    public Message(String message, Integer userId, Integer channelId) {
        this.message = message;
        this.userId = userId;
        this.channelId = channelId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return "Message [messageId=" + messageId + ", message=" + message + ", userId=" + userId + ", channelId="
                + channelId + "]";
    }

}