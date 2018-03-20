package luckylau;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author luckylau
 * @date 2018/3/20/020 17:06
 */
public class ZooKeeper_Constructor_Usage_With_SID_PASSWD {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper("119.27.162.88:2181", 5000, new MyWatcher(countDownLatch));
            System.out.println(zooKeeper.getState());
        } catch (IOException e) {
            e.printStackTrace();
        }

        long sessionId = zooKeeper.getSessionId();
        System.out.println("sessionId: " + sessionId);
        byte[] passwd  = zooKeeper.getSessionPasswd();
        System.out.println("passwd: "+ passwd.toString());



        //使用非法Id
        CountDownLatch countDownLatch2 = new CountDownLatch(1);
        try {
            zooKeeper = new ZooKeeper("119.27.162.88:2181", 5000, new MyWatcher(countDownLatch2),1111L, passwd);
            System.out.println(zooKeeper.getState());
        } catch (IOException e) {
            e.printStackTrace();
        }



        //使用合法Id
        CountDownLatch countDownLatch3 = new CountDownLatch(1);
        try {
            zooKeeper = new ZooKeeper("119.27.162.88:2181", 5000, new MyWatcher(countDownLatch3),1111L, passwd);
            System.out.println(zooKeeper.getState());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //线程睡眠，等待Watch通知
        try {
            Thread.sleep( Integer.MAX_VALUE );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
