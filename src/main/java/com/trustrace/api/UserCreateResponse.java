package com.trustrace.api;

import java.util.HashMap;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.trustrace.utils.EnvironmentPropertiesReader;
import com.trustrace.utils.POJOreflection;
import com.trustrace.utils.RestAssuredAPI;

import io.restassured.response.Response;

public class UserCreateResponse {
	private static EnvironmentPropertiesReader configProperty = EnvironmentPropertiesReader.getInstance();

	/**
	 * Method to hit post call for User create
	 *
	 * @param testData
	 * @return
	 * @throws Exception
	 */
	public Response postTypeCodeResponse(HashMap<String, String> testData) throws Exception {

		Gson gson = new Gson();
		String baseUrl = configProperty.getProperty("userCreateApiBaseUrl");
		String endPoint = configProperty.getProperty("userCreateApiEndPoint");

		UserCreatePojo userCreatePojo = new UserCreatePojo();
		POJOreflection.setValuesInPOJO(userCreatePojo, testData);
		Response res = RestAssuredAPI.post(baseUrl, gson.toJson(userCreatePojo), endPoint);
		return res;

	}

	public boolean validateResponseMessage(Response res, String jsonName) throws Exception {
		JSONObject json = new JSONObject(res.getBody().asString());
		return json.has(jsonName);
	}

}
