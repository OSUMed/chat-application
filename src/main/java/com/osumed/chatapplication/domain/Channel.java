package com.osumed.chatapplication.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

public class Channel {
    private Long channelId;
    private String name;

    public Channel(String name, Long uniqueKey) {
        this.name = name;
        this.channelId = uniqueKey;
    }

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