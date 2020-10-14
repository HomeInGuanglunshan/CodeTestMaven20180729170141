package jedis.cluster.p05.sentinel;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 * https://blog.csdn.net/u013086392/article/details/52837684
 */
public class SentinelTest {
	private Logger logger = LoggerFactory.getLogger(SentinelTest.class);
	private JedisSentinelPool jSentinelPool;

	@Before
	public void setUp() {
		String masterName = "mymaster";
		//sentinel地址集合
		Set<String> set = new HashSet<String>();
		/**
		 * 原先误以为n个sentinel节点监控n个redis主从集群，set是这些sentinel节点的集合。
		 * 实际上一个sentinel集群只能监控一个redis主从集群，set只是sentinel集群节点的集合
		 */
		set.add("192.168.1.59:27001");
		set.add("192.168.1.59:27002");
		set.add("192.168.1.59:27003");
		GenericObjectPoolConfig gPoolConfig = new GenericObjectPoolConfig();
		gPoolConfig.setMaxIdle(10);
		gPoolConfig.setMaxTotal(10);
		gPoolConfig.setMaxWaitMillis(10);
		gPoolConfig.setJmxEnabled(true);
		jSentinelPool = new JedisSentinelPool(masterName, set, gPoolConfig);
	}

	@Test
	public void testWriet() {
		Jedis jedis = null;

		for (int i = 0; i < 10; i++) {
			try {

				jedis = jSentinelPool.getResource();

				System.out.println(i);

				String userKey = "user" + i;
				jedis.set(userKey, String.valueOf(i));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
		}
	}
}