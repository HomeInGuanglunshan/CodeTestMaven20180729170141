package io.p03.socket.server.to.client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		Socket socket = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			socket = new Socket("127.0.0.1", 9527);

			inputStream = socket.getInputStream();
			outputStream = new FileOutputStream("C:/temp.txt");

			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
			}
			try {
				outputStream.close();
			} catch (IOException e) {
			}
			try {
				socket.close();
			} catch (IOException e) {
			}
		}

	}
}
