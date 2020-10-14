package jvm.p01;

public class JvmGc06 {

	public static void main(String[] args) {
		byte[] bytes;
		Integer M = new Integer(1024 * 1024 * 1); //单位, 兆(M)
		bytes = new byte[1 * M];
		bytes = new byte[1 * M];
		bytes = new byte[1 * M];
		bytes = new byte[1 * M];
		bytes = new byte[1 * M];
		bytes = new byte[1 * M];
		bytes = new byte[1 * M];
		bytes = new byte[1 * M];
		bytes = new byte[1 * M];
		bytes = new byte[1 * M];
		bytes = new byte[1 * M];
		bytes = new byte[1 * M];
		bytes = new byte[1 * M];
		bytes = new byte[1 * M];
		bytes = new byte[1 * M];

//		byte所占用内存空间小于其封装类Byte的，所以，后者GC次数比前者要多

//		Byte[] bytes;
//		Integer M = new Integer(1024 * 1024 * 1); //单位, 兆(M)
//		bytes = new Byte[1 * M];
//		bytes = new Byte[1 * M];
//		bytes = new Byte[1 * M];
//		bytes = new Byte[1 * M];
//		bytes = new Byte[1 * M];
//		bytes = new Byte[1 * M];
//		bytes = new Byte[1 * M];
//		bytes = new Byte[1 * M];
//		bytes = new Byte[1 * M];
//		bytes = new Byte[1 * M];
//		bytes = new Byte[1 * M];
//		bytes = new Byte[1 * M];
//		bytes = new Byte[1 * M];
//		bytes = new Byte[1 * M];
//		bytes = new Byte[1 * M];
	}

}
