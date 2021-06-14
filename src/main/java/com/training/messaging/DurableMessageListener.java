package com.training.messaging;

import com.hazelcast.topic.Message;
import com.hazelcast.topic.ReliableMessageListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DurableMessageListener<V> implements ReliableMessageListener<V> {

    public final List<V> objects = new CopyOnWriteArrayList<V>();
    public final List<Long> sequences = new CopyOnWriteArrayList<Long>();
    public volatile long sequence = -1;

    @Override
    public long retrieveInitialSequence() {
        if (sequence == -1) {
            return -1;
        } else {
            // we need to add one because we want to read the next item.
            return sequence + 1;
        }
    }

    @Override
    public void storeSequence(long sequence) {
        System.out.println("Sequences : " + sequences);
        System.out.println("Current Sequence : " + sequence);
        sequences.add(sequence);
        this.sequence = sequence;
    }

    @Override
    public boolean isTerminal(Throwable failure) {
        return true;
    }

    @Override
    public boolean isLossTolerant() {
        return false;
    }

    @Override
    public void onMessage(Message<V> message) {
        System.out.println("On Message: " + message.getMessageObject());
        System.out.println("-----");
        objects.add(message.getMessageObject());
    }
}
