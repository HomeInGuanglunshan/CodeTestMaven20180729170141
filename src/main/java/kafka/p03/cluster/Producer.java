package kafka.p03.cluster;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * https://www.cnblogs.com/ye-hcj/p/10260954.html
 */
public class Producer {
	public static void main(String[] args) {
		Properties props = new Properties();
		// 服务器ip
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093,localhost:9094,localhost:9095");
		// 属性键值对都序列化成字符串
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		// 创建一个生产者，向test主题发送数据
		KafkaProducer<String, String> producer = new KafkaProducer<>(props);
		producer.send(new ProducerRecord<String, String>("topic-test-cluster-again", "生产者传递的消息"));
		producer.close();
	}
}