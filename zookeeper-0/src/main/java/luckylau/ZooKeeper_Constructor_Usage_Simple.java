package luckylau;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 *
 */
public class ZooKeeper_Constructor_Usage_Simple {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main( String[] args ) {

        try {
            ZooKeeper zooKeeper = new ZooKeeper("172.27.162.88:2181", 5000, new MyWatcher(countDownLatch));
            System.out.println(zooKeeper.getState());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ZooKeeper session established.");

    }

}

