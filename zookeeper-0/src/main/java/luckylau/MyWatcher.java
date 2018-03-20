package luckylau;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * @author luckylau
 * @date 2018/3/20/020 17:10
 */
class MyWatcher implements Watcher {

    private CountDownLatch countDownLatch;

    public MyWatcher(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event：" + watchedEvent);
        if(Event.KeeperState.SyncConnected.getIntValue()== watchedEvent.getState().getIntValue()){
            countDownLatch.countDown();
        }
    }
}
