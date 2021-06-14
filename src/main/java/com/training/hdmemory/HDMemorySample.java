package com.training.hdmemory;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.util.UUID;

public class HDMemorySample {

    public static void main(String[] args) {
        System.setProperty("hazelcast.config", "classpath:hd-memory.xml");

        HazelcastInstance hz = Hazelcast.newHazelcastInstance();

        IMap<String, String> nativeMap = hz.getMap("nativeMap");

        System.out.println("Putting data to native memory.");

        for (int i = 0; i < 1000; i++) {
            nativeMap.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }

        System.out.println("Data stored in native memory.");
    }
}
