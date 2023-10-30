package com.osumed.chatapplication.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

public class Message {

    private Integer messageId;
    private String message;
    private String personId;
    private String channelId;

    public Message() {
    }

    public Message(String message, String personId, String channelId) {
        this.message = message;
        this.personId = personId;
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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return "Message [messageId=" + messageId + ", message=" + message + ", personId=" + personId + ", channel="
                + channelId + "]";
    }

}