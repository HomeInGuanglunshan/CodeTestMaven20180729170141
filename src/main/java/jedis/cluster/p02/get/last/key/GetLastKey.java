package jedis.cluster.p02.get.last.key;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class GetLastKey {

	public static void main(String[] args) {
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.1.59", 7001));

		// 创建JedisCluster对象
		JedisCluster jedisCluster = new JedisCluster(nodes);
		while (true) {
			System.out.println(jedisCluster.get("__last__"));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
