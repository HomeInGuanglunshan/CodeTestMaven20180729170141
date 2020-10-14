package javascript.in.java.p01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestEnvironment {

	public static void main(String[] args) throws Exception {
		WebDriver dr = new ChromeDriver();
		dr.get("http://www.baidu.com"); // 打开首页
		dr.manage().window().maximize(); // 最大化
		Thread.sleep(3000);
		dr.quit();
	}

}
