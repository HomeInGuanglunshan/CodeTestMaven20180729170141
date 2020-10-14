package redis.and.queue.test;

import redis.and.queue.model.Message;
import redis.and.queue.utils.JedisUtil;
import redis.and.queue.utils.ObjectUtil;

public class Pop {

	public static byte[] redisKey = "lidaQueue".getBytes();

	public static void main(String[] args) throws Exception {
		popMessage();
	}

	public static void popMessage() throws Exception {
		for (;;) {
			byte[] bytes = JedisUtil.rpop(redisKey);
			if (null == bytes) {
				break;
			}
			Message msg = (Message) ObjectUtil.bytes2Object(bytes);
			System.out.println(msg.getId() + "----" + msg.getContent());
		}
	}
	
}
