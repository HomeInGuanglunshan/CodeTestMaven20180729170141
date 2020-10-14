package httpclient.test.p01.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class NotJson {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpClient hc = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://localhost:8080/requestMe");
//		post.addHeader("Content-type", "application/json");
		
		// 参数形式为key=value&key=value
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("p1", "060212638b94290e3dd0648c15753b64"));
        formparams.add(new BasicNameValuePair("p2", "火狐"));
                
        // 加utf-8进行编码
        UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
        post.setEntity(uefEntity);
		
//		Map<String, String> param = new HashMap<String, String>();
//		param.put("username", "admin");
//		param.put("password", "111111");
//		String body = JSON.toJSONString(param); // {"username":"admin","password":"111111"}
//		post.setEntity(new StringEntity(body, "utf-8"));
		
		HttpResponse response = hc.execute(post);
		System.out.println(EntityUtils.toString(response.getEntity()));
	}

}
