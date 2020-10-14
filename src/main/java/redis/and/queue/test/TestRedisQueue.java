package redis.and.queue.test;
import redis.and.queue.model.Message;
import redis.and.queue.utils.JedisUtil;
import redis.and.queue.utils.ObjectUtil;
import redis.clients.jedis.Jedis;

import java.io.IOException;

/**
 * Created by Kinglf on 2016/10/17.
 */
public class TestRedisQueue {
    public static byte[] redisKey = "key".getBytes();
    static {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void init() throws IOException {
        for (int i = 0; i < 10; i++) {
            Message message = new Message(i, "这是第" + i + "个内容");
            JedisUtil.lpush(redisKey, ObjectUtil.object2Bytes(message));
        }

    }

    public static void main(String[] args) {
        try {
            pop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void pop() throws Exception {
        byte[] bytes = JedisUtil.rpop(redisKey);
        Message msg = (Message) ObjectUtil.bytes2Object(bytes);
        if (msg != null) {
            System.out.println(msg.getId() + "----" + msg.getContent());
        }
    }
}