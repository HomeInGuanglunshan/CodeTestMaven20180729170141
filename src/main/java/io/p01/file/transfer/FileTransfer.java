package io.p01.file.transfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTransfer {

	public static void main(String[] args) {
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
			File from = new File("C:/AVScanner.ini");
			File to = new File("F:/" + from.getName());

//			File from = new File("F:/Cs1.6全地图完美中文版.rar");
//			File to = new File("C:/" + from.getName());

			byte[] buf = new byte[1024];
			int len = 0;
			inputStream = new FileInputStream(from);
			outputStream = new FileOutputStream(to);

			while ((len = inputStream.read(buf, 0, buf.length)) != -1) {
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
		}
	}
}
