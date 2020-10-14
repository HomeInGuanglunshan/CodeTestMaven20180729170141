package io.p05.with.filename;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		Socket socket = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		DataOutputStream dataOutputStream = null;
		try {
			socket = new Socket("127.0.0.1", 9527);

			File file = new File("C:/LibAntiPrtSc_INFORMATION.log");
			inputStream = new FileInputStream(file);

			outputStream = socket.getOutputStream();

			dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeUTF(file.getName());
			dataOutputStream.writeLong(file.length());

			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = inputStream.read(buf)) != -1) {
				dataOutputStream.write(buf, 0, len);
			}

			socket.shutdownOutput();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dataOutputStream.close();
			} catch (Exception e) {
			}
			try {
				inputStream.close();
			} catch (Exception e) {
			}
			try {
				outputStream.close();
			} catch (Exception e) {
			}
			try {
				socket.close();
			} catch (Exception e) {
			}
		}
	}
}
