package jedis.cluster.p03.set.foos;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class SetFoos {

	public static void main(String[] args) throws Exception {
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.1.59", 7001));
		nodes.add(new HostAndPort("192.168.1.59", 7002));

		// 创建JedisCluster对象
		JedisCluster jedisCluster = new JedisCluster(nodes);
		while (true) {
			try {
				if (null == jedisCluster.get("__last__")) {
					jedisCluster.set("__last__", "0");
				} else {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			Thread.sleep(1000);
		}
		for (int i = 0; i < 1000000000; i++) {
			try {
				jedisCluster.set("foo" + i, String.valueOf(i));
				System.out.println(jedisCluster.get("foo" + i));
				jedisCluster.set("__last__", String.valueOf(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Thread.sleep(100);
		}
		jedisCluster.close();
	}

}
