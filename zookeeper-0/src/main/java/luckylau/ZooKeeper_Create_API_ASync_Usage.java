package luckylau;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author luckylau
 * @date 2018/3/23/023 16:59
 */
public class ZooKeeper_Create_API_ASync_Usage {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) {

        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper("172.27.162.88:2181", 5000, new MyWatcher(countDownLatch));
            System.out.println(zooKeeper.getState());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        zooKeeper.create("/luckylau-ephemeral-1", "zookeeperLearning".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL, new IStringCallback(), "I am context");

        zooKeeper.create("/luckylau-ephemeral-2", "zookeeperLearning".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL, new IStringCallback(), "I am context");

        zooKeeper.create("/luckylau-ephemeral-3", "zookeeperLearning".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL, new IStringCallback(), "I am context");


        //线程睡眠，等待Watch通知
        try {
            Thread.sleep( Integer.MAX_VALUE );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
class IStringCallback implements AsyncCallback.StringCallback{

    public void processResult(int rc, String path, Object ctx, String name) {
        System.out.println("Create path result: [" + rc + ", " + path + ", "
                + ctx + ", real path name: " + name);
    }
}
