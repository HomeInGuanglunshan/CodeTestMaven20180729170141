package kafka.p04.bat.v20;

public class DeleteTopic {

	/**
	 * 一删除就宕机，不知为何
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		String topic = " topic-test-cluster ";
		Common.callCmd("D:/kafka_2.11-2.0.0/bin/windows/kafka-topics.bat --zookeeper localhost:2181 --delete --topic "
				+ topic);
	}

}
