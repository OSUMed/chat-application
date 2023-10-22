package com.osumed.chatapplication.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "channel")
public class Channel {
    private Long channelId;
    private String name;

 

    public Channel(String name, Long channelId) {
        this.name = name;
        this.channelId = channelId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}