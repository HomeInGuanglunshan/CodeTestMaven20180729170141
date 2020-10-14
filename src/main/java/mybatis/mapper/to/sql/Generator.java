package mybatis.mapper.to.sql;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Generator {

	public static void main(String[] args) throws Exception {
		generateSql("F:/project/1.springboot/shiro/src/main/resources/config/xml",
				"C:/Users/Administrator/Desktop/tables.sql");
	}

	/**
	 * 生成sql
	 *
	 * @param dirPath
	 *            mapper.xml的父级文件夹
	 * @param sqlFile
	 *            选择你将要生成sql的文件
	 * @throws IOException
	 */
	private static void generateSql(String dirPath, String sqlFile) throws IOException {
		FileWriter fw = null;
		try {
			File dir = new File(dirPath);
			File sql = new File(sqlFile);
			if (sql.exists()) {
				sql.delete();
			}
			sql.createNewFile();
			fw = new FileWriter(sql);

			if (dir.exists() && dir.isDirectory()) {
				File[] files = dir.listFiles();
				for (File file : files) {
					if (file.isFile() && file.getName().endsWith(".xml")) {
						System.out.println(file.getName());
						fw.append(getSql(file));
						fw.append("\r\n");
						fw.flush();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null)
				fw.close();
		}
	}

	private static String getSql(File xmlfile) throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(xmlfile);
		Element root = document.getRootElement();
		Element resultMap = root.element("resultMap");
		Table table = new Table();
		table.setTableName(getTableNameFromInsertion(root));
		table.setColumns(getColumns(resultMap));
		return table.toString();
	}

	@SuppressWarnings("unchecked")
	private static Map<String, String> getColumns(Element resultMap) {
		List<Element> elements = resultMap.elements();
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (Element element : elements) {
			map.put(element.attribute("column").getValue(), element.attribute("jdbcType").getValue());
		}
		return map;
	}

	@SuppressWarnings("unused")
	private static String getTableNameFromSelection(Element root) {
		Element selectByPrimaryKey = root.element("select");
		String selectStr = selectByPrimaryKey.getTextTrim();
		String tableName = selectStr.split("from")[1].trim().split(" ")[0].trim();
		return tableName;
	}

	private static String getTableNameFromInsertion(Element root) {
		Element insertion = root.element("insert");
		String insertionSql = insertion.getTextTrim();
		String tableName = insertionSql.split("into")[1].trim().split(" ")[0].trim();
		return tableName;
	}
}