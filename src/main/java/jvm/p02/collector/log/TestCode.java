package jvm.p02.collector.log;

public class TestCode {

	public static void main(String[] args) throws Exception {

		int times = 50;
		Integer CAP = new Integer(1024 * 1024 * 1); //单位, M

		Byte[] bytes;
		for (int i = 1; i <= times; i++) {
			bytes = new Byte[1 * CAP];
		}
	}

}
