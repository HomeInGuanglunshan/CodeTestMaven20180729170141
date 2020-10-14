package json.test.p01;

import net.sf.json.JSONObject;

public class JsonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String params = "{\"a\" : \"1\", \"b\" : \"2\"}";
		JSONObject jsonObj = JSONObject.fromObject(params);
		System.out.println(jsonObj.get("a"));
		System.out.println(jsonObj.get("b"));
		System.out.println(jsonObj.toString());
	}

}
