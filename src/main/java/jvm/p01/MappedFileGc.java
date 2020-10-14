package jvm.p01;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * https://www.cnblogs.com/maokun/p/7629068.html
 */
public class MappedFileGc {
	public static void main(String[] args) throws IOException {

		File tempFile = File.createTempFile("Temp", null);
		tempFile.deleteOnExit();

		RandomAccessFile raTempFile = new RandomAccessFile(tempFile, "rw");
		FileChannel fChannel = raTempFile.getChannel();

		MappedByteBuffer mappedBuffer = fChannel.map(FileChannel.MapMode.READ_WRITE, 0, 512);

		fChannel.close();
		raTempFile.close();

		mappedBuffer = null;

		System.gc();

		if (tempFile.delete())
			System.out.println("Successfully deleted: " + tempFile);
		else
			System.out.println("Unable to delete: " + tempFile);
	}
}