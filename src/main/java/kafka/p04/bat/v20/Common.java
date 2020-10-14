package kafka.p04.bat.v20;

import java.io.InputStream;

public class Common {

	public static void callCmd(String command) {
		int c;
		InputStream inputStream = null;
		InputStream errorStream = null;
		try {
			Process child = Runtime.getRuntime().exec(command);

			inputStream = child.getInputStream();
			while ((c = inputStream.read()) != -1) {
				System.out.print((char) c);
			}

			errorStream = child.getErrorStream();
			while ((c = errorStream.read()) != -1) {
				System.out.print((char) c);
			}

			child.waitFor();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (Exception e) {
			}
			try {
				errorStream.close();
			} catch (Exception e) {
			}
		}
	}

}
