package jedis.cluster.p04.sharded;

import java.util.Arrays;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class ShardedJedisPoolTest02 {

	public static void main(String[] args) {
		// 设置连接池的相关配置
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(2);
		poolConfig.setMaxIdle(1);
		poolConfig.setMaxWaitMillis(2000);
		poolConfig.setTestOnBorrow(false);
		poolConfig.setTestOnReturn(false);

		// 设置Redis信息
		String host = "192.168.1.59";
		JedisShardInfo shardInfo1 = new JedisShardInfo(host, 8001, 500);
		// shardInfo1.setPassword("test123");
		JedisShardInfo shardInfo2 = new JedisShardInfo(host, 8002, 500);
		// shardInfo2.setPassword("test123");
		JedisShardInfo shardInfo3 = new JedisShardInfo(host, 8003, 500);
		// shardInfo3.setPassword("test123");

		// 初始化ShardedJedisPool
		List<JedisShardInfo> infoList = Arrays.asList(shardInfo1, shardInfo2, shardInfo3);
		ShardedJedisPool jedisPool = new ShardedJedisPool(poolConfig, infoList);

		// 进行查询等其他操作
		ShardedJedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			while (true) {
				try {
					if (null == jedis.get("__last__")) {
						jedis.set("__last__", "0");
					} else {
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i < 1000000000; i++) {
				try {
					jedis.set("foo" + i, String.valueOf(i));
					System.out.println(jedis.get("foo" + i));
					jedis.set("__last__", String.valueOf(i));
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 使用后一定关闭，还给连接池
			if (jedis != null) {
				jedis.close();
			}
			if (jedisPool != null) {
				jedisPool.close();
			}
		}
	}

}
