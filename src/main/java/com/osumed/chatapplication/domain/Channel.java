package com.osumed.chatapplication.domain;

public class Channel {
    private Integer channelId;
    private String name;

    public Channel(String name, Integer uniqueKey) {
        this.name = name;
        this.channelId = uniqueKey;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}