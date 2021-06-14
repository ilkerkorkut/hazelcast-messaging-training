package com.training.messaging;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;

import java.util.Date;

public class TopicClient2 {

    public static void main(String[] args) {
        HazelcastInstance client = HazelcastClient.newHazelcastClient();

        client.getTopic("myTopic").addMessageListener(message -> {
            Date publishedDate = (Date) message.getMessageObject();
            System.out.println("Client-2 Subscribed : " + publishedDate.toString());
        });
    }
}
