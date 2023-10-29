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

@Entity
public class Message {

    private Long messageId;
    private String message;
    private String personId;
    private String channel;

    public Message() {
    }

    public Message(String message, String personId, String channel) {
        this.message = message;
        this.personId = personId;
        this.channel = channel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Message [messageId=" + messageId + ", message=" + message + ", personId=" + personId + ", channel="
                + channel + "]";
    }

}