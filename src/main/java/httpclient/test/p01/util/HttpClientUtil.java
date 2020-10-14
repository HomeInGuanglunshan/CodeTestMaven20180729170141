package httpclient.test.p01.util;
import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class HttpClientUtil {
    
    private final static String CONTENT_TYPE_TEXT_JSON = "text/json";
    
    public static String postRequest(String url, Map<String, Object> param) throws ClientProtocolException, IOException{
        
        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        
        Gson gson = new Gson();
        String parameter = gson.toJson(param);

        StringEntity se = new StringEntity(parameter);
        se.setContentType(CONTENT_TYPE_TEXT_JSON);
        httpPost.setEntity(se);

        CloseableHttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        
        return result;
    }

}