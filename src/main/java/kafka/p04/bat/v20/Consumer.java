package kafka.p04.bat.v20;

public class Consumer {

	public static void main(String args[]) {
		String bootstrapServer = " localhost:9093,localhost:9094,localhost:9095 ";
		String startingPoint = " --from-beginning ";
		String topic = "topic-test-cluster";
		Common.callCmd("D:/kafka_2.11-2.0.0/bin/windows/kafka-console-consumer.bat --bootstrap-server "
				+ bootstrapServer + startingPoint + " --topic " + topic);
	}

}
