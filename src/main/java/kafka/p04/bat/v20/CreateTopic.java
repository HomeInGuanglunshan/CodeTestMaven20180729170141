package kafka.p04.bat.v20;

public class CreateTopic {

	public static void main(String args[]) {
		String bootstrapServer = " localhost:2181 ";
		int replicationFactor = 2;
		int partitions = 3;
		String topic = " topic-test-cluster ";

		Common.callCmd("D:/kafka_2.11-2.0.0/bin/windows/kafka-topics.bat --create --zookeeper " + bootstrapServer
				+ " --replication-factor " + replicationFactor + " --partitions " + partitions + " --topic " + topic);
	}

}
