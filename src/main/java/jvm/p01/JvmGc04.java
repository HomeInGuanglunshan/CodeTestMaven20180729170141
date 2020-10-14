package jvm.p01;

/**
 * https://blog.csdn.net/zp522123428/article/details/53635161
 */
public class JvmGc04 {

	/**
	 * -Xms60m -Xmx60m -Xmn20m -XX:NewRatio=2 (若 Xms = Xmx, 并且设定了 Xmn,
	 * 那么该项配置就不需要配置了 ) -XX:SurvivorRatio=8 -XX:PermSize=30m -XX:MaxPermSize=30m
	 * -XX:+PrintGCDetails
	 */
	/**
	 * -Xms60m -Xmx60m -Xmn20m -XX:NewRatio=2 -XX:SurvivorRatio=8
	 * -XX:PermSize=30m -XX:MaxPermSize=30m -XX:+PrintGCDetails
	 */
	public static void main(String[] args) {
		JvmGc04 jvmGc04 = new JvmGc04();
		for (int i = 0; i < 5; i++) {

			jvmGc04.doTest();

			for (int j = 0; j < 100; j++) {
				System.out.print("-");
			}
			System.out.println();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void doTest() {
		Integer M = new Integer(1024 * 1024 * 1); //单位, 兆(M)
		byte[] bytes = new byte[1 * M]; //申请1M 大小的内存空间
		bytes = null; //断开引用链
		System.gc(); //通知 GC 收集垃圾
		System.out.println();
		bytes = new byte[1 * M]; //重新申请1M 大小的内存空间
		bytes = new byte[1 * M]; //再次申请1M 大小的内存空间
		System.gc();
		System.out.println();
	}
}
