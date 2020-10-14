package io.p03.socket.server.to.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			serverSocket = new ServerSocket(9527);
			socket = serverSocket.accept();

			outputStream = socket.getOutputStream();
			inputStream = new FileInputStream("F:/msdia80.dll");

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
			try {
				serverSocket.close();
			} catch (IOException e) {
			}
		}
	}
}
