package com.training.messaging;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;

import java.util.UUID;

public class ReliableTopicSample {

    public static void main(String[] args) throws InterruptedException {
        HazelcastInstance local = Hazelcast.newHazelcastInstance();

        ITopic<String> topic = local.getReliableTopic("myTopic");
        final DurableMessageListener<String> listener = new DurableMessageListener<>();

        UUID id = topic.addMessageListener(listener);
        topic.publish("item1");

        Thread.sleep(1000L);

        topic.removeMessageListener(id);

        Thread.sleep(1000L);


        topic.publish("item2");
        topic.publish("item3");

        topic.addMessageListener(listener);
    }
}
