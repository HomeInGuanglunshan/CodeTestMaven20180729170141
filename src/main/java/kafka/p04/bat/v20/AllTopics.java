package kafka.p04.bat.v20;

public class AllTopics {

	public static void main(String args[]) {
		Common.callCmd("D:/kafka_2.11-2.0.0/bin/windows/kafka-topics.bat --describe --zookeeper localhost:2181");
	}

}
