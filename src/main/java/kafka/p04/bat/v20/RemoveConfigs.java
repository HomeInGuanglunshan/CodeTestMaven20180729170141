package kafka.p04.bat.v20;

import java.util.HashSet;
import java.util.Set;

public class RemoveConfigs {

	public static void main(String args[]) {

		String topic = "topic-test-cluster";

		StringBuffer command = new StringBuffer(
				"D:/kafka_2.11-2.0.0/bin/windows/kafka-configs.bat --entity-type topics --entity-name " + topic
						+ " --zookeeper localhost:2181 --alter ");

		Set<String> configs = new HashSet<>();
		configs.add("max.message.bytes");

		for (String config : configs) {
			command.append(" --delete-config ");
			command.append(config);
		}

		Common.callCmd(command.toString());
	}

}
