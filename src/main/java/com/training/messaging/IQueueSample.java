package com.training.messaging;

import com.hazelcast.collection.IQueue;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class IQueueSample {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("hazelcast.config", "classpath:messaging.xml");

        HazelcastInstance hz = Hazelcast.newHazelcastInstance();

        IQueue<Date> theQueue = hz.getQueue("myQueue");

        Date now = new Date();
        System.out.println(now.toString());

        for (int i = 0; i < 50; i++) {
            theQueue.offer(now);
        }

        TimeUnit.SECONDS.sleep(5L);

        System.out.println("QueueSize before take action : " + theQueue.size());

        Date queueValue = theQueue.take();

        System.out.println("QueueValue: " + queueValue.toString());
        System.out.println("QueueSize after take action : " + theQueue.size());
    }

}
