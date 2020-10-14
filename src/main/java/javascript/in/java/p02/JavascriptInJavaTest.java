package javascript.in.java.p02;
import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class JavascriptInJavaTest {

	public static void main(String[] args) throws Exception {

		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine se = sem.getEngineByExtension("js");

		String jsFilePath = JavascriptInJavaTest.class.getResource("jsencrypt.min.js").getFile();
		se.eval(new FileReader(jsFilePath));
		String s = (String) se.eval("eval(\"(new JSEncrypt()).setPublicKey('MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDKOJybB/0CQYeStbZZmr1SfgAKUou3cV7jT9abur5lin6Ct27CZe7XzmEYJu8fYnVmTYK1o6sioWP3Ji1ueEohDo/bzizFZDoxHp2pbKTefpc0HK6A+bLXo13xdBd+4up88Gz4YQmfHLbPhY+83z78stU/91ap10o/3XPfQ4xHBwIDAQAB').encrypt('f123456');\")");
		System.out.println(s);

	}
}