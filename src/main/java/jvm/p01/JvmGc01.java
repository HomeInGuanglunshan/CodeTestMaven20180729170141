package jvm.p01;

public class JvmGc01 {

	public static void main(String[] args) {
		Integer i = 1;
		System.out.println(i);
		System.gc();
		System.out.println(i);
	}

}
