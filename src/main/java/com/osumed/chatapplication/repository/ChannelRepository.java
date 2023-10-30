package com.osumed.chatapplication.repository;

import java.util.List;

import com.osumed.chatapplication.domain.Channel;
import java.util.UUID;

public class ChannelRepository {
    private List<Channel> channels;

    public ChannelRepository() {
        makeChannel("General");
        makeChannel("Sports");
    }

    public void makeChannel(String channelName) {
        Long uniqueKey = generateUniqueKey();
        Channel generalChannel = new Channel(channelName, uniqueKey);
        channels.add(generalChannel);
    }

    public Long generateUniqueKey() {
        return (long) channels.size() + 1;
    }

}
