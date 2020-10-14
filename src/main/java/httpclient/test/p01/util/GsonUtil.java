package httpclient.test.p01.util;

import com.google.gson.Gson;

import httpclient.test.p01.entity.Person;

public class GsonUtil {

	public static <T> T jsonToObject(String jsonData, Class<T> type) {
		Gson gson = new Gson();
		T result = gson.fromJson(jsonData, type);
		return result;
	}

	public static void main(String[] args) {
		String json = "{'id':'1','name':'zhang','address':'Hubei'}";
		jsonToObject(json, Person.class);
		Person person = jsonToObject(json, Person.class);
		System.out.println(person);
	}

}