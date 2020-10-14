package io.p02.socket.client.to.server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		FileOutputStream fileOutputStream = null;
		try {
			serverSocket = new ServerSocket(9527);

			socket = serverSocket.accept();
			InputStream inputStream = socket.getInputStream();

			fileOutputStream = new FileOutputStream("F:/temp.txt");
			byte[] buf = new byte[1024];
			int len = 0;

			while ((len = inputStream.read(buf)) != -1) {
				fileOutputStream.write(buf, 0, len);
			}

			socket.getOutputStream().write(new String("what the f**k?!").getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileOutputStream.close();
			} catch (IOException e) {
			}
			try {
				socket.close();
			} catch (IOException e) {
			}
			try {
				serverSocket.close();
			} catch (IOException e) {
			}
		}
	}
}
