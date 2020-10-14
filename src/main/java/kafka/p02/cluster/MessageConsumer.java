package kafka.p02.cluster;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class MessageConsumer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9093,localhost:9094,localhost:9095");
		props.put("group.id", "cluster-test");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		final KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		consumer.subscribe(Arrays.asList("topic-test-cluster"), new ConsumerRebalanceListener() {
			@Override
			public void onPartitionsRevoked(Collection<TopicPartition> collection) {
			}

			@Override
			public void onPartitionsAssigned(Collection<TopicPartition> collection) {
				//将偏移设置到最开始
				consumer.seekToBeginning(collection);
				// Below is the same as above
//				for (TopicPartition partition : collection) {
//					System.out.println(partition.partition());
//					consumer.seek(partition, 0);
//				}
			}
		});
		while (true) {
//			ConsumerRecords<String, String> records = consumer.poll(100); // Deprecated
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String, String> record : records) {
//				System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
				System.out.printf("patition = %d, offset = %d, key = %s, value = %s%n", record.partition(),
						record.offset(), record.key(), record.value());
			}
		}
	}
	/**
	 * Kafka的message是按topic分类存储的，topic中的数据又是按照一个一个的partition即分区存储到不同broker节点。
	 * 每个partition对应了操作系统上的一个文件夹，partition实际上又是按照segment分段存储的。
	 * 这也非常符合分布式系统分区分桶的设计思想。
	 * <p>
	 * 通过这种分区分段的设计，Kafka的message消息实际上是分布式存储在一个一个小的segment中的，每次文件操作也是直接操作的segment
	 * 。为了进一步的查询优化，Kafka又默认为分段后的数据文件建立了索引文件，就是文件系统上的.index文件。这种分区分段+索引的设计，
	 * 不仅提升了数据读取的效率，同时也提高了数据操作的并行度。
	 * <p>
	 * from: https://blog.51cto.com/14309075/2420229?source=dra
	 */
//	输出结果：
//	patition = 3, offset = 0, key = 1, value = 1
//	patition = 3, offset = 1, key = 7, value = 7
//	patition = 3, offset = 2, key = 8, value = 8
//	patition = 3, offset = 3, key = 22, value = 22
//	patition = 3, offset = 4, key = 25, value = 25
//	patition = 3, offset = 5, key = 28, value = 28
//	patition = 3, offset = 6, key = 30, value = 30
//	patition = 3, offset = 7, key = 33, value = 33
//	patition = 3, offset = 8, key = 44, value = 44
//	patition = 3, offset = 9, key = 59, value = 59
//	patition = 3, offset = 10, key = 60, value = 60
//	patition = 3, offset = 11, key = 64, value = 64
//	patition = 3, offset = 12, key = 70, value = 70
//	patition = 3, offset = 13, key = 71, value = 71
//	patition = 3, offset = 14, key = 79, value = 79
//	patition = 3, offset = 15, key = 80, value = 80
//	patition = 3, offset = 16, key = 88, value = 88
//	patition = 3, offset = 17, key = 92, value = 92
//	patition = 3, offset = 18, key = 96, value = 96
//	patition = 3, offset = 19, key = 99, value = 99
//	patition = 0, offset = 0, key = 5, value = 5
//	patition = 0, offset = 1, key = 11, value = 11
//	patition = 0, offset = 2, key = 15, value = 15
//	patition = 0, offset = 3, key = 17, value = 17
//	patition = 0, offset = 4, key = 21, value = 21
//	patition = 0, offset = 5, key = 23, value = 23
//	patition = 0, offset = 6, key = 27, value = 27
//	patition = 0, offset = 7, key = 34, value = 34
//	patition = 0, offset = 8, key = 37, value = 37
//	patition = 0, offset = 9, key = 43, value = 43
//	patition = 0, offset = 10, key = 45, value = 45
//	patition = 0, offset = 11, key = 47, value = 47
//	patition = 0, offset = 12, key = 48, value = 48
//	patition = 0, offset = 13, key = 52, value = 52
//	patition = 0, offset = 14, key = 54, value = 54
//	patition = 0, offset = 15, key = 55, value = 55
//	patition = 0, offset = 16, key = 56, value = 56
//	patition = 0, offset = 17, key = 61, value = 61
//	patition = 0, offset = 18, key = 63, value = 63
//	patition = 0, offset = 19, key = 72, value = 72
//	patition = 0, offset = 20, key = 76, value = 76
//	patition = 0, offset = 21, key = 82, value = 82
//	patition = 0, offset = 22, key = 89, value = 89
//	patition = 0, offset = 23, key = 93, value = 93
//	patition = 0, offset = 24, key = 98, value = 98
//	patition = 5, offset = 0, key = 3, value = 3
//	patition = 5, offset = 1, key = 9, value = 9
//	patition = 5, offset = 2, key = 16, value = 16
//	patition = 5, offset = 3, key = 29, value = 29
//	patition = 5, offset = 4, key = 32, value = 32
//	patition = 5, offset = 5, key = 40, value = 40
//	patition = 5, offset = 6, key = 50, value = 50
//	patition = 5, offset = 7, key = 57, value = 57
//	patition = 5, offset = 8, key = 73, value = 73
//	patition = 5, offset = 9, key = 78, value = 78
//	patition = 5, offset = 10, key = 90, value = 90
//	patition = 5, offset = 11, key = 97, value = 97
//	patition = 4, offset = 0, key = 6, value = 6
//	patition = 4, offset = 1, key = 10, value = 10
//	patition = 4, offset = 2, key = 12, value = 12
//	patition = 4, offset = 3, key = 18, value = 18
//	patition = 4, offset = 4, key = 20, value = 20
//	patition = 4, offset = 5, key = 26, value = 26
//	patition = 4, offset = 6, key = 38, value = 38
//	patition = 4, offset = 7, key = 42, value = 42
//	patition = 4, offset = 8, key = 51, value = 51
//	patition = 4, offset = 9, key = 58, value = 58
//	patition = 4, offset = 10, key = 62, value = 62
//	patition = 4, offset = 11, key = 67, value = 67
//	patition = 4, offset = 12, key = 68, value = 68
//	patition = 4, offset = 13, key = 86, value = 86
//	patition = 4, offset = 14, key = 94, value = 94
//	patition = 4, offset = 15, key = 95, value = 95
//	patition = 2, offset = 0, key = 0, value = 0
//	patition = 2, offset = 1, key = 2, value = 2
//	patition = 2, offset = 2, key = 36, value = 36
//	patition = 2, offset = 3, key = 41, value = 41
//	patition = 2, offset = 4, key = 49, value = 49
//	patition = 2, offset = 5, key = 69, value = 69
//	patition = 2, offset = 6, key = 74, value = 74
//	patition = 2, offset = 7, key = 83, value = 83
//	patition = 2, offset = 8, key = 85, value = 85
//	patition = 2, offset = 9, key = 91, value = 91
//	patition = 1, offset = 0, key = 4, value = 4
//	patition = 1, offset = 1, key = 13, value = 13
//	patition = 1, offset = 2, key = 14, value = 14
//	patition = 1, offset = 3, key = 19, value = 19
//	patition = 1, offset = 4, key = 24, value = 24
//	patition = 1, offset = 5, key = 31, value = 31
//	patition = 1, offset = 6, key = 35, value = 35
//	patition = 1, offset = 7, key = 39, value = 39
//	patition = 1, offset = 8, key = 46, value = 46
//	patition = 1, offset = 9, key = 53, value = 53
//	patition = 1, offset = 10, key = 65, value = 65
//	patition = 1, offset = 11, key = 66, value = 66
//	patition = 1, offset = 12, key = 75, value = 75
//	patition = 1, offset = 13, key = 77, value = 77
//	patition = 1, offset = 14, key = 81, value = 81
//	patition = 1, offset = 15, key = 84, value = 84
//	patition = 1, offset = 16, key = 87, value = 87
//	可以看出，100个KV，均匀地放在6个partition中
}
