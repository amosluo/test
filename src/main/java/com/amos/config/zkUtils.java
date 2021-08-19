package com.amos.config;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class zkUtils {
    static ZooKeeper zk;
    static DefaultWatch watch = new DefaultWatch();
    static CountDownLatch cd = new CountDownLatch(1);
    public static ZooKeeper getZk(){
        try {
            zk = new ZooKeeper("192.168.3.121:2181,192.168.3.122:2181,192.168.3.123:2181,192.168.3.124:2181/mes", 1000, watch);
            watch.setCd(cd);
            cd.await();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return zk;
    }
}
