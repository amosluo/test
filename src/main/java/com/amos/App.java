package com.amos;

import com.amos.config.CallBackWatch;
import com.amos.config.MyConf;
import com.amos.config.zkUtils;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.Calendar;

public class App {

    static ZooKeeper zk;

    public static void main(String[] args) throws InterruptedException {
        conn();

        //close();
    }

    static void conn() {
        zk = zkUtils.getZk();
    }

    static void close() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
