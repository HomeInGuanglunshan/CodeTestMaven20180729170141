package redis.and.queue.test;

import redis.and.queue.model.Message;
import redis.and.queue.utils.JedisUtil;
import redis.and.queue.utils.ObjectUtil;

public class Push {

	public static byte[] redisKey = "lidaQueue".getBytes();

	public static void main(String[] args) throws Exception {
		pushMessage();
	}
	
	public static void pushMessage() throws Exception {
		for (int i = 1; i <= 100; i++) {
            Message message = new Message(i, "这是第" + i + "个内容");
            JedisUtil.lpush(redisKey, ObjectUtil.object2Bytes(message));
        }
	}
	
}
