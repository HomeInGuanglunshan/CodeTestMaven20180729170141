package log4j.test.p02;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.springframework.util.Log4jConfigurer;
 
@SuppressWarnings("deprecation")
public class TestErrOut {
 
	static {
		try {
			Log4jConfigurer.initLogging(TestErrOut.class.getResource("log4j.xml").getFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	private static final Logger logger = Logger.getLogger(TestErrOut.class);
	
	public static void main(String[] args) {
		logger.debug(" This is debug!!!");
		logger.info(" This is info!!!");
		logger.warn(" This is warn!!!");
		logger.error(" This is error!!!");
		logger.fatal(" This is fatal!!!");
	}
 
}
