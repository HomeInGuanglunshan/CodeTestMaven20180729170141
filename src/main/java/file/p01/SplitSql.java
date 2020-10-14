package file.p01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

public class SplitSql {

	private static String SOURCE_FILE = "C:/Users/Administrator/Desktop/shiro.sql";
	private static String OUTPUT_FOLDER = "C:/Users/Administrator/Desktop/";

	public static void main(String[] args) {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			File file = new File(SOURCE_FILE);
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);

			getDescription(bufferedReader);

			String tableName = null;
			while ((tableName = getTable(bufferedReader)) != null) {
				System.out.println(tableName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (Exception e) {
			}
			try {
				fileReader.close();
			} catch (Exception e) {
			}
		}
	}

	private static void getDescription(BufferedReader bufferedReader) {
		FileOutputStream description = null;
		OutputStreamWriter outputStreamWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			description = new FileOutputStream(OUTPUT_FOLDER + "description.sql");
			outputStreamWriter = new OutputStreamWriter(description);
			bufferedWriter = new BufferedWriter(outputStreamWriter);

			String line = null;
			while (!"-- ----------------------------".equals(line = bufferedReader.readLine())) {

				if (null == line) {
					break;
				}

				bufferedWriter.write(line);
				bufferedWriter.write("\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedWriter.close();
			} catch (Exception e) {
			}
			try {
				outputStreamWriter.close();
			} catch (Exception e) {
			}
			try {
				description.close();
			} catch (Exception e) {
			}
		}
	}

	private static String getTable(BufferedReader bufferedReader) {
		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			String lineHavingTableName = bufferedReader.readLine();
			String tableName = lineHavingTableName.substring(lineHavingTableName.lastIndexOf(" ") + 1);
			String type = lineHavingTableName.replaceAll("-- (.+?) .*", "$1");

			fileOutputStream = new FileOutputStream(OUTPUT_FOLDER + tableName + ".sql");
			outputStreamWriter = new OutputStreamWriter(fileOutputStream);
			bufferedWriter = new BufferedWriter(outputStreamWriter);

			bufferedWriter.write("-- ----------------------------\r\n");
			bufferedWriter.write(lineHavingTableName + "\r\n");

			String line = null;
			int times = 0;
			int threshold = 4;
			if (!"table".equalsIgnoreCase(type)) {
				threshold = 2;
			}

			while (!("-- ----------------------------".equals(line = bufferedReader.readLine())
					&& ++times == threshold)) {

				if (null == line) {
					break;
				}

				bufferedWriter.write(line);
				bufferedWriter.write("\r\n");
			}

			return tableName;
		} catch (NullPointerException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				bufferedWriter.close();
			} catch (Exception e) {
			}
			try {
				outputStreamWriter.close();
			} catch (Exception e) {
			}
			try {
				fileOutputStream.close();
			} catch (Exception e) {
			}
		}
	}
}
