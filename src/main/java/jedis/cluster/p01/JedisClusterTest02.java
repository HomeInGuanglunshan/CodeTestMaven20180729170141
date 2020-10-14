package jedis.cluster.p01;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClusterTest02 {

	static Logger logger = LoggerFactory.getLogger(JedisClusterTest02.class);

	public static JedisPoolConfig getJedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(100);
		jedisPoolConfig.setMinIdle(10);
		jedisPoolConfig.setTestOnBorrow(true);
		return jedisPoolConfig;
	}

	public static JedisCluster getJedisCluster() {
		// 创建并填充节点信息
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.1.59", 7001));
		nodes.add(new HostAndPort("192.168.1.59", 7002));
		nodes.add(new HostAndPort("192.168.1.59", 7003));
		nodes.add(new HostAndPort("192.168.1.59", 7004));
		nodes.add(new HostAndPort("192.168.1.59", 7005));
		nodes.add(new HostAndPort("192.168.1.59", 7006));

		return new JedisCluster(nodes, getJedisPoolConfig());
	}

	public static void main(String[] args) throws Exception {
		JedisCluster jedisCluster = getJedisCluster();
		for (;;) {
			try {
				logger.info(jedisCluster.get("insertLock"));
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
			Thread.sleep(200);
		}
	}
//	输出如下：
//	2019-12-12 17:36:48,079 [main] [INFO] [jedis.cluster.p01.JedisClusterTest02] - gggg
//	2019-12-12 17:36:48,279 [main] [INFO] [jedis.cluster.p01.JedisClusterTest02] - gggg
//	2019-12-12 17:36:48,480 [main] [INFO] [jedis.cluster.p01.JedisClusterTest02] - gggg
//	2019-12-12 17:36:48,680 [main] [INFO] [jedis.cluster.p01.JedisClusterTest02] - gggg
//	2019-12-12 17:36:48,880 [main] [INFO] [jedis.cluster.p01.JedisClusterTest02] - gggg
//	2019-12-12 17:36:54,140 [main] [INFO] [jedis.cluster.p01.JedisClusterTest02] - Could not get a resource from the pool
//	2019-12-12 17:36:59,381 [main] [INFO] [jedis.cluster.p01.JedisClusterTest02] - Could not get a resource from the pool
//	2019-12-12 17:37:04,615 [main] [INFO] [jedis.cluster.p01.JedisClusterTest02] - Could not get a resource from the pool
//	2019-12-12 17:37:10,860 [main] [INFO] [jedis.cluster.p01.JedisClusterTest02] - Could not get a resource from the pool
//	2019-12-12 17:37:11,061 [main] [INFO] [jedis.cluster.p01.JedisClusterTest02] - gggg
//	2019-12-12 17:37:11,261 [main] [INFO] [jedis.cluster.p01.JedisClusterTest02] - gggg
//	2019-12-12 17:37:11,461 [main] [INFO] [jedis.cluster.p01.JedisClusterTest02] - gggg
//	2019-12-12 17:37:11,661 [main] [INFO] [jedis.cluster.p01.JedisClusterTest02] - gggg

//	主节点宕机后，JedisCluster需要间隔多久时间才能重新取到值，很大程度取决于redis.conf中cluster-node-timeout的设置。该参数值默认为15000，即15秒。
}