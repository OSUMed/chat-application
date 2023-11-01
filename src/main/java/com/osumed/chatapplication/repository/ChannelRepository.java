package com.osumed.chatapplication.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.osumed.chatapplication.domain.Channel;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class ChannelRepository {
    private List<Channel> channels = new ArrayList<>();

    public ChannelRepository() {
        makeChannel("General");
        makeChannel("Sports");
    }

    public void makeChannel(String channelName) {
        Integer uniqueKey = generateUniqueKey();
        Channel generalChannel = new Channel(channelName, uniqueKey);
        channels.add(generalChannel);
    }

    public Integer generateUniqueKey() {
        return channels.size() + 1;
    }

    public Optional<Channel> getChannel(Integer channel_id) {

        return channels.stream()
                .filter(channel -> channel.getChannelId().equals(channel_id))
                .findFirst();
    }

    public List<String> getChannelNames() {

        return channels.stream()
                .map(channel -> channel.getName())
                .collect(Collectors.toList());
    }

    public List<Channel> getChannels() {
        return channels.stream()
                .collect(Collectors.toList());
    }

}