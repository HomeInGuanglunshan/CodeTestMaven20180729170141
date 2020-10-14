package kafka.p04.bat.v20;

public class Producer {

	public static void main(String args[]) {
		String brokerList = " localhost:9093,localhost:9094,localhost:9095 ";
		String topic = " topic-test-cluster ";
		Common.callCmd("D:/kafka_2.11-2.0.0/bin/windows/kafka-console-producer.bat --broker-list " + brokerList
				+ " --topic " + topic);
	}

}
