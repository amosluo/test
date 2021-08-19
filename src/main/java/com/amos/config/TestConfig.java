package com.amos.config;

import org.apache.zookeeper.ZooKeeper;

public class TestConfig {
    ZooKeeper zk;
//    @Before
    public void conn(){
        zk = zkUtils.getZk();
    }
}
