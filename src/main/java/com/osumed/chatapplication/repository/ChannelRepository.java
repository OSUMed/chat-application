package com.osumed.chatapplication.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.osumed.chatapplication.domain.Channel;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public class ChannelRepository {
    private List<Channel> channels = new ArrayList<>();

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

    public Optional<Channel> getChannel(String channel_id) {

        return channels.stream()
                .filter(channel -> channel.getChannelId().equals(Long.parseLong(channel_id)))
                .findFirst();
    }

}
