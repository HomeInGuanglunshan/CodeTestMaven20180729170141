package kafka.p02.cluster;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MessageProducer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9093,localhost:9094,localhost:9095");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		for (int i = 0; i < 100; i++) {
			producer.send(new ProducerRecord<String, String>("topic-test-cluster", "foo" + i, Integer.toString(i)));
		}
//		for (int i = 0; i < 100; i++) {
//			/**
//			 * 同一个key的message可以保证只发送到同一个partition
//			 * <p>
//			 * from: https://www.zhihu.com/question/266390197/answer/307655477
//			 * <p>
//			 * 但是在多个Partition时，不能保证Topic级别的数据有序性
//			 * <p>
//			 * from: https://www.zhihu.com/question/266390197/answer/308800669
//			 */
//			producer.send(new ProducerRecord<String, String>("topic-test-cluster", i % 2 == 0 ? "foo" : "bar",
//					Integer.toString(i)));
//		}

		producer.close();

	}
}
