package httpclient.test.p01.main;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class ObjectArray {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpClient hc = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://localhost:8080/objectArray");
        post.addHeader("Content-type", "application/json");
        String body = "[{\"color\": \"red\", \"brand\" : \"benz\"}, {\"color\": \"blue\", \"brand\" : \"byd\"}]";
        post.setEntity(new StringEntity(body, "utf-8"));
        HttpResponse response = hc.execute(post);
        System.out.println(EntityUtils.toString(response.getEntity()));// {"code":"1","message":"成功","result":"d480198c33d940129210a268f7aa499d"}
	}
}
