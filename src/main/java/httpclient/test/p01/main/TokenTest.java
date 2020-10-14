package httpclient.test.p01.main;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class TokenTest {

    public static void main(String[] args) throws Exception {

        HttpClient hc = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://localhost:8080/requestToken");
        post.addHeader("Content-type", "application/json");
        Map<String, String> param = new HashMap<String, String>();
        param.put("username", "admin");
        param.put("password", "111111");
        String body = JSON.toJSONString(param); // {"username":"admin","password":"111111"}
        post.setEntity(new StringEntity(body, "utf-8"));
        HttpResponse response = hc.execute(post);
        System.out.println(EntityUtils.toString(response.getEntity()));// {"code":"1","message":"成功","result":"d480198c33d940129210a268f7aa499d"}

    }

}
