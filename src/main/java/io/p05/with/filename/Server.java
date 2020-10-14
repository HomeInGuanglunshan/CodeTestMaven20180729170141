package io.p05.with.filename;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		DataInputStream dataInputStream = null;
		OutputStream outputStream = null;
		try {
			serverSocket = new ServerSocket(9527);
			socket = serverSocket.accept();

			dataInputStream = new DataInputStream(socket.getInputStream());
			outputStream = new FileOutputStream("F:/" + dataInputStream.readUTF());
			// 不加下面这句，文件头部有乱码。目前不清楚原因
			dataInputStream.readLong();

			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = dataInputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (Exception e) {
			}
			try {
				dataInputStream.close();
			} catch (Exception e) {
			}
			try {
				socket.close();
			} catch (Exception e) {
			}
			try {
				serverSocket.close();
			} catch (Exception e) {
			}
		}
	}
}
