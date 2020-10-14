package zookeeper.p02;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class DeleteNodes {

	/** zookeeper地址 */
	static final String CONNECT_ADDR = "localhost:2181";
	/** session超时时间 */
	static final int SESSION_OUTTIME = 2000;// ms
	/** 信号量，阻塞程序执行，用于等待zookeeper连接成功，发送成功信号 */
	static final CountDownLatch connectedSemaphore = new CountDownLatch(1);

	public static void main(String[] args) throws Exception {

		ZooKeeper zk = new ZooKeeper(CONNECT_ADDR, SESSION_OUTTIME, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				// 获取事件的状态
				KeeperState keeperState = event.getState();
				EventType eventType = event.getType();
				// 如果是建立连接
				if (KeeperState.SyncConnected == keeperState) {
					if (EventType.None == eventType) {
						// 如果建立连接成功，则发送信号量，让后续阻塞程序向下执行
						System.out.println("zk 建立连接");
						connectedSemaphore.countDown();
					}
				}
			}
		});

		// 进行阻塞
		connectedSemaphore.await();

		zk.delete("/config", -1); // 不能递归删除：KeeperErrorCode = Directory not empty for /config
//		zk.delete("/config/topics/topic-test-cluster", -1);

		zk.close();
	}
}
