package jvm.p01;

public class JvmGc08 {

	public static void main(String[] args) throws Exception {

		Integer CAP = new Integer(1024 * 1024 * 9); //单位, M

		byte[] bytes;
		bytes = new byte[1 * CAP];

		for (int i = 1; i <= 30; i++) {
			System.out.println(i);
			if (i == 10) {
				bytes = null;
			}
//			手动的System.gc()，不管当前垃圾对象在哪个代，年龄几何，一并回收？
			System.gc();
		}
	}

}
