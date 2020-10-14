package jvm.p01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * jvm参数：-Xms20m -Xmx20m -XX:+PrintGCDetails，和JvmGc12.java形成对比，此做法，gc次数更多
 */
public class JvmGc13 {

	public static void main(String[] args) throws Exception {

		ExecutorService threadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 1000000; i++) {
			threadPool.execute(new Runnable() {
				@Override
				public void run() {

				}
			});
		}

		threadPool.shutdown();
		while (!threadPool.awaitTermination(1, TimeUnit.MILLISECONDS))
			;
	}

}
