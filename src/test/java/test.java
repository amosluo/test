import com.amos.config.CallBackWatch;
import com.amos.config.MyConf;
import com.amos.config.zkUtils;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class test {

    static ZooKeeper zk;

    @Before
    public void conn() {
        zk = zkUtils.getZk();
    }

    @Test
    public void test1() throws Exception {
        CallBackWatch watch = new CallBackWatch();
        MyConf conf = new MyConf();
        watch.setConf(conf);
        watch.setZk(zk);
        watch.await();
        while (true) {
            String s = conf.getConf();
            if (s == ""){
                System.out.println("配置被删除");
                watch.await();
            }
            else
                System.out.println(conf.getConf());
            Thread.sleep(200);

        }
    }


    @After
    public void close() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
