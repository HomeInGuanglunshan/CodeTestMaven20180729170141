package io.p02.socket.client.to.server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		Socket socket = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			socket = new Socket("127.0.0.1", 9527);

			inputStream = new FileInputStream("C:/AVScanner.ini");
			byte[] buf = new byte[1024];
			int len = 0;
			outputStream = socket.getOutputStream();
			while ((len = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, len);
			}

			socket.shutdownOutput();

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println(bufferedReader.readLine());

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
