package jvm.p01;

/**
 * https://blog.csdn.net/zp522123428/article/details/53635161
 */
public class JvmGc02 {
	public static void main(String[] args) {
		Object obj = new Object();
		System.gc();
		System.out.println();
		obj = new Object();
		obj = new Object();
		System.gc();
		System.out.println();
	}
}
