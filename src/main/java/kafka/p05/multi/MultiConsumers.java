package kafka.p05.multi;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class MultiConsumers {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9093,localhost:9094,localhost:9095");
		props.put("group.id", "cluster-test");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

//		List<String> topicList = Arrays.asList("topic-test-cluster", "springCloudBus");
		List<String> topicList = Arrays.asList("topic-test-cluster");

		KafkaConsumer<String, String> consumer1 = new KafkaConsumer<>(props);
		KafkaConsumer<String, String> consumer2 = new KafkaConsumer<>(props);
//		KafkaConsumer<String, String> consumer3 = new KafkaConsumer<>(props);

		consumer1.subscribe(topicList, new MyConsumerListener(consumer1));
		consumer2.subscribe(topicList, new MyConsumerListener(consumer2));
//		consumer3.subscribe(topicList, new MyConsumerListener(consumer3));

		new Thread(new polling(consumer1), "Consumer1").start();
		new Thread(new polling(consumer2), "Consumer2").start();
//		new Thread(new polling(consumer3), "Consumer3").start();
		/**
		 * 如果是同组的多个consumer同时拉取消息，那么一个partition不会被两个或两个以上的consumer拉取到消息，
		 * 但可能出现多个partition被一个consumer拉取到消息的情况，也就是说，可能某个consumer会拉取不到任何消息
		 */
	}

}

class MyConsumerListener implements ConsumerRebalanceListener {

	KafkaConsumer<?, ?> consumer;

	public MyConsumerListener(KafkaConsumer<?, ?> consumer) {
		this.consumer = consumer;
	}

	@Override
	public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
	}

	@Override
	public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
		consumer.seekToBeginning(partitions);
	}
}

class polling implements Runnable {

	KafkaConsumer<?, ?> consumer;

	public polling(KafkaConsumer<?, ?> consumer) {
		this.consumer = consumer;
	}

	@Override
	public void run() {
		for (;;) {
			ConsumerRecords<?, ?> records = consumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<?, ?> record : records) {
				System.out.printf("patition = %d, offset = %d, key = %s, value = %s, thread = %s%n", record.partition(),
						record.offset(), record.key(), record.value(), Thread.currentThread().getName());
			}
		}
	}

}
