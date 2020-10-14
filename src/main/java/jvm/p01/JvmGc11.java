package jvm.p01;

import java.io.FileOutputStream;
import java.io.IOException;

public class JvmGc11 {

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new JvmGc11().genFiles(String.valueOf(i));
		}
	}

	private void genFiles(String filename) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream("C:/Users/Administrator/Desktop/files/" + filename + ".txt");
			fileOutputStream.write(("hello world " + filename).getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileOutputStream.close();
			} catch (IOException e) {
			}
		}
	}
}
