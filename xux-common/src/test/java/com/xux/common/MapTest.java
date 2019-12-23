/**
 * Project Name:xux
 * Package Name:com.xux.common
 * Date:2019/12/23 15:57
 * <p>
 * All rights Reserved, Designed By MR.X_YY
 * Copyright: Copyright(C) 2016-2020
 * Company    xux  Co., Ltd.
 */
package com.xux.common;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapTest {

    private static void testConcurrentMap() {
        final Map<String, Integer> count = new ConcurrentHashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        final CountDownLatch endLatch = new CountDownLatch(2);
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                Integer value = count.get("k");
                if (null == value) {
                    System.out.println(Thread.currentThread().getName());
                    count.put("k", 1);
                } else {
                    count.put("k", value + 1);
                }
            }
            endLatch.countDown();
        };

        executorService.execute(task);
        executorService.execute(task);

        try {
            endLatch.await();
            if (count.get("k") < 10) {
                System.out.println(count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testtestConcurrentMap() {
        for (int i = 0; i < 1000; i++) {
            testConcurrentMap();
        }
    }


}
