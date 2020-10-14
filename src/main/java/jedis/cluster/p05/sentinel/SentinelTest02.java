package jedis.cluster.p05.sentinel;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class SentinelTest02 {

	public static JedisSentinelPool getJedisSentinelPool() {

		String master = "mymaster";

		//sentinel节点地址集合
		Set<String> set = new HashSet<String>();
		/**
		 * 原先误以为n个sentinel节点监控n个redis主从集群，set是这些sentinel节点的集合。
		 * 实际上一个sentinel集群只能监控一个redis主从集群，set只是sentinel集群节点的集合
		 */
		set.add("192.168.1.59:27001");
		set.add("192.168.1.59:27002");
		set.add("192.168.1.59:27003");

		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMinIdle(10);
		poolConfig.setMaxIdle(100);
		poolConfig.setMaxTotal(100);
		poolConfig.setMaxWaitMillis(10);
		poolConfig.setJmxEnabled(true);

		return new JedisSentinelPool(master, set, poolConfig);
	}

	/**
	 * 在master宕机的时候，会不断报
	 * <p>
	 * Exception in thread "pool-1-thread-1217"
	 * redis.clients.jedis.exceptions.JedisConnectionException: Could not get a
	 * resource from the pool......
	 * <p>
	 * Caused by: redis.clients.jedis.exceptions.JedisConnectionException:
	 * java.net.ConnectException: Connection refused: connect......
	 * <p>
	 * 需要sentinels选出新的master后，才停止报错
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		JedisSentinelPool jedisSentinelPool = getJedisSentinelPool();

		Thread task = new Thread(new Runnable() {
			@Override
			public void run() {
				Jedis jedis = jedisSentinelPool.getResource();
				try {
					int bar = new Random().nextInt(1000);
					jedis.set("bar" + bar, String.valueOf(bar));
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						jedis.close();
					} catch (Exception e) {
					}
				}
			}
		});

		ExecutorService threadPool = Executors.newCachedThreadPool();
		try {
			for (;;) {
				threadPool.execute(task);
				Thread.sleep(10);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
			while (!threadPool.awaitTermination(1, TimeUnit.MILLISECONDS))
				;
		}
	}

}
