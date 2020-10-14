package kafka.p04.bat.v20;

import java.util.HashMap;
import java.util.Map;

public class AddConfigs {

	public static void main(String args[]) {

		String topic = "topic-test-cluster";

		StringBuffer command = new StringBuffer(
				"D:/kafka_2.11-2.0.0/bin/windows/kafka-configs.bat --entity-type topics --entity-name " + topic
						+ " --zookeeper localhost:2181 --alter ");

		Map<String, Object> config = new HashMap<>();
//		config.put("max.message.bytes", 128000);
		config.put("retention.ms", 10000);

		for (String key : config.keySet()) {
			command.append(" --add-config ");
			command.append(key);
			command.append("=");
			command.append(config.get(key));
		}

		Common.callCmd(command.toString());
	}

}
