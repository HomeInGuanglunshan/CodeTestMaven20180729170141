package method.invoking.factory.bean.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * descrption: authohr: wangji date: 2017-08-24 13:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-methodInvoking.xml" })
public class MethodTest extends AbstractJUnit4SpringContextTests {

	private final Logger LOGGER = LoggerFactory.getLogger(MethodTest.class);

	@Resource(name = "sysProps")
	public Properties properties;

	@Resource(name = "javaVersion")
	public String javaVersion;

	@Test
	public void test() {
		LOGGER.info(properties.toString());
		LOGGER.info(javaVersion.toString());
	}

}