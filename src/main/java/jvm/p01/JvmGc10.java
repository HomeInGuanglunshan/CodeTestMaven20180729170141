package jvm.p01;

public class JvmGc10 {

	public static void main(String[] args) {
		for (;;) {
			new JvmGc10().testMethod();
		}
	}

//	不会GC
//	private void testMethod() {
//		String str = "6666666";
//	}

//	会GC
	private void testMethod() {
		String string = String.valueOf(System.currentTimeMillis());
	}
}
