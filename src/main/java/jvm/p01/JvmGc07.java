package jvm.p01;

public class JvmGc07 {

	public static void main(String[] args) throws Exception {
//		不会GC
//		for (;;) {
//
//		}

//		不会GC
//		int j;
//		for (int i = 1; i <= 1000000000; i++) {
//			j = i;
//		}

//		会GC
//		for (int i = 1; i <= 1000000; i++) {
//			System.out.println(i);
//		}

//		不会GC
//		for (int i = 1; i <= 1000000000; i++) {
//			int j = i;
//		}

//		不会GC
//		for (int i = 0; i <= 1000000000; i++) {
//
//		}

//		会GC
//		for (Integer i = 1024; i <= 1000000000; i++) {
//
//		}

//		不会GC
//		for (;;) {
//			Integer i = new Integer(1000000000);
//		}

//		会GC
		for (Integer i = 0; i <= 1000000000; i++) {
//			Integer integer = new Integer(i);
			Integer integer = i;
		}
	}

}
