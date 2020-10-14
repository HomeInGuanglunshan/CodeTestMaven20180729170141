package kafka.p02.cluster;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

public class CreateTopic {

	public static void main(String[] args) {
		// 创建topic
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9093,localhost:9094,localhost:9095");
		AdminClient adminClient = AdminClient.create(props);
		ArrayList<NewTopic> topics = new ArrayList<NewTopic>();
		/**
		 * For a topic with replication factor N, we will tolerate up to N-1
		 * server failures without losing any records committed to the log.
		 * <p>
		 * 译：对于一个replication因数为N的topic， 如果服务器宕机数量不超过N-1
		 * ，我们可以保证不会丢失任何提交到log的record
		 * <p>
		 * from: http://kafka.apache.org/intro#intro_guarantees
		 * <p>
		 * 举个例子，假如replication factor为1，而集群中某一台主机挂了，1 > N -
		 * 1，肯定有record丢失，实际上在此时连consume都不能正常进行了<br>
		 * 假如replication factor为2，只有一台主机挂了，没有超过N-1，实测可以正常consume<br>
		 * replication factor为3、4、5及以上的，不继续推演了
		 * <p>
		 * 一个topic有numPartitions个partition，
		 * 每个partition有replicationFactor个replication，那么一个topic总共有numPartitions*
		 * replicationFactor个replication
		 */
		NewTopic newTopic = new NewTopic("topic-test-cluster", 6, (short) 3);
		topics.add(newTopic);
		CreateTopicsResult result = adminClient.createTopics(topics);
		try {
			result.all().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
