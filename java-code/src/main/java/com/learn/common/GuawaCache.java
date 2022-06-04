package com.learn.common;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


public class GuawaCache {

    @Test
    public void testLRU() {

        List<Integer> l = new CopyOnWriteArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);

        Iterator<Integer> iterator = l.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println(next);


        }

        System.out.println(l);
    }
}
