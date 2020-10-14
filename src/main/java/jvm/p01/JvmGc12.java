package jvm.p01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * jvm参数：-Xms20m -Xmx20m -XX:+PrintGCDetails，和JvmGc13.java形成对比，此做法，gc次数更少
 */
public class JvmGc12 {

	public static void main(String[] args) throws Exception {

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {

			}
		});

		ExecutorService threadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 1000000; i++) {
			threadPool.execute(thread);
		}

		threadPool.shutdown();
		while (!threadPool.awaitTermination(1, TimeUnit.MILLISECONDS))
			;
	}

}
