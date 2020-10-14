package jvm.p01;

public class JvmGc05 {

	public static void main(String[] args) throws Exception {

		int times = 50;
		Integer CAP = new Integer(1024 * 1024 * 1); //单位, M

//		CAP = capacity
//		猜测：假如即将要在新生代生成的对象，会使(eden + 1 * survivor)的容积使用率超过90%，则在其生成前，jvm会执行一次GC

//		int times = 50000;
//		Integer CAP = new Integer(1024); //单位, K

//		int times = 5000000;
//		Integer CAP = new Integer(1);

//		byte[] bytes;
//		for (int i = 1; i <= times; i++) {
//			bytes = new byte[1 * CAP];
//		}

//		byte所占用内存空间小于其封装类Byte的，所以，后者GC次数比前者要多
//		但CAP = 1时，貌似两者GC数量一致，具体原因或许需要往深层剖析

		Byte[] bytes;
		for (int i = 1; i <= times; i++) {
			bytes = new Byte[1 * CAP];
		}
	}

}
