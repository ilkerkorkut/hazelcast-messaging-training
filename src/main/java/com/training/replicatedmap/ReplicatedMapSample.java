package com.training.replicatedmap;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.replicatedmap.ReplicatedMap;

import java.util.UUID;

public class ReplicatedMapSample {

    public static void main(String[] args) {
        System.setProperty("hazelcast.config", "classpath:replicated-map.xml");

        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        Hazelcast.newHazelcastInstance();
        Hazelcast.newHazelcastInstance();

        ReplicatedMap<String, String> replicatedMapSample = hz.getReplicatedMap("replicatedMapSample");

        for (int i = 0; i < 1000; i++) {
            replicatedMapSample.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
    }

}
