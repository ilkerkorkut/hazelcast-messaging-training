package com.training.messaging;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;

import java.util.Date;

public class TopicPublisher {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("hazelcast.config", "classpath:messaging.xml");

        HazelcastInstance hz = Hazelcast.newHazelcastInstance();

        ITopic<Date> topic = hz.getTopic("myTopic");
        while (true) {
            topic.publish(new Date());
            Thread.sleep(1000L);
        }
    }
}
