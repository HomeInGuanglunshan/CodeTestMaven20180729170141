package httpclient.test.p01.main;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUsingUrlParams {
    
    public static void main(String[] args) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();

        String url = "http://yuntuapi.amap.com/datamanage/table/create";

        HttpPost httpPost = new HttpPost(url);

        // 参数形式为key=value&key=value
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("key", "060212638b94290e3dd0648c15753b64"));
        formparams.add(new BasicNameValuePair("name", "火狐"));
                
        // 加utf-8进行编码
        UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
        httpPost.setEntity(uefEntity);

        CloseableHttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        System.out.println(result);
    }

}