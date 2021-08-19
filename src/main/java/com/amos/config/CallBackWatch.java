package com.amos.config;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class CallBackWatch implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {
    ZooKeeper zk;
    CountDownLatch cd = new CountDownLatch(1);
    MyConf conf;

    public CountDownLatch getCd() {
        return cd;
    }

    public void setCd(CountDownLatch cd) {
        this.cd = cd;
    }

    public MyConf getConf() {
        return conf;
    }

    public void setConf(MyConf conf) {
        this.conf = conf;
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public void await() throws InterruptedException {
        zk.exists("/AppConf",this,this,"sb");
        cd.await();
    }

    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        if (data != null) {
            String s = new String(data);
            conf.setConf(s);
            cd.countDown();
        }
    }

    public void processResult(int rc, String path, Object ctx, Stat stat) {
        if (stat != null) {
            zk.getData("/AppConf", this, this, "sdfs");
        }
    }

    public void process(WatchedEvent event) {
        switch (event.getType()) {
            case None:
                break;
            case NodeCreated:
                zk.getData("/AppConf", this, this, "asb");
                break;
            case NodeDeleted:
                conf.setConf("");
                cd = new CountDownLatch(1);
                break;
            case NodeDataChanged:
                zk.getData("/AppConf", this, this, "asb");
                break;
            case NodeChildrenChanged:
                break;
        }
    }
}
