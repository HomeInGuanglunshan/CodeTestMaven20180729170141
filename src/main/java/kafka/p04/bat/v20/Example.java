package kafka.p04.bat.v20;

import java.io.IOException;
import java.io.InputStream;

/**
 * https://www.cnblogs.com/guiyi/p/3229094.html
 */
public class Example {

	public static void main(String args[]) {
		callCmd("echo hello");
	}

	public static void callCmd(String locationCmd) {
		try {
			Process child = Runtime.getRuntime().exec(locationCmd);
			InputStream in = child.getInputStream();
			int c;
			while ((c = in.read()) != -1) {
//				System.out.println((char) c);
				System.out.print((char) c);
			}
			in.close();
			try {
				child.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}