package kafka.p03.cluster;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * https://www.cnblogs.com/ye-hcj/p/10260954.html
 */
public class Consumer {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093,localhost:9094,localhost:9095");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "cluster-test-again");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

		// 消费者对象
		KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
		kafkaConsumer.subscribe(Arrays.asList("topic-test-cluster-again"));
		while (true) {
			// 不适用于kafka-clients-0.11.0.1
//			ConsumerRecords<String, String> records = kafkaConsumer
//					.poll(Duration.between(LocalDateTime.parse("2019-01-09T11:30:30"), LocalDateTime.now()));
			ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("offset = %d, value = %s", record.offset(), record.value());
				System.out.println();
			}
		}
	}
}