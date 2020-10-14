package httpclient.test.p01.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import httpclient.test.p01.util.HttpClientUtil;

public class MainOfMine {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		String url = "http://localhost:8080/requestToken";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username", "lida");
		param.put("password", "123456");
		String result = HttpClientUtil.postRequest(url, param);
		System.out.println(result);
	}
}
