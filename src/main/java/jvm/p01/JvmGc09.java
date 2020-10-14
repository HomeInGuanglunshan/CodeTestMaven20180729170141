package jvm.p01;

public class JvmGc09 {

	/**
	 * 禁止修改本文件代码
	 * 
	 * 听说，JVM性能调优主要目的是避免Full
	 * GC的发生（https://blog.csdn.net/sinlff/article/details/70138651），当前代码 在VM
	 * arguments为-Xms60m -Xmx60m -Xmn20m -XX:NewRatio=2 -XX:SurvivorRatio=8
	 * -XX:MetaspaceSize=30m -XX:MaxMetaspaceSize=30m -XX:+PrintGCDetails
	 * 的时候，会发生Full GC，请问如何调优？
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		Integer CAP = new Integer(1024 * 1024 * 10); //单位, M

		byte[] bytes;
		bytes = new byte[1 * CAP];

		Integer CAP2 = new Integer(1024 * 1024 * 3); //单位, M

		byte[] bytes2;
		bytes2 = new byte[1 * CAP2];

		for (int i = 1; i <= 100; i++) {
			System.out.println(i);
			if (i == 10) {
				bytes = null;
			}
			bytes2 = new byte[1 * CAP2];
		}
	}

}
