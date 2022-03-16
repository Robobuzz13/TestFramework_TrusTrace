package com.trustrace.api;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;

import com.trustrace.utils.EnvironmentPropertiesReader;
import com.trustrace.utils.RestAssuredAPI;

import io.restassured.response.Response;

public class TypeCodeResponse {
	private static EnvironmentPropertiesReader configProperty = EnvironmentPropertiesReader.getInstance();

	/**
	 * Method to hit get call for Typecode
	 *
	 * @param testData
	 * @return
	 */
	public Response postTypeCodeResponse(HashMap<String, String> testData) {

		String baseUrl = configProperty.getProperty("typeCodeApiBaseUrl");
		String endPoint = configProperty.getProperty("typeCodeApiEndPoint");
		Response res = RestAssuredAPI.GET(baseUrl + endPoint);
		return res;

	}

	public int arraySizeResult(Response res) throws JSONException {
		JSONArray json = new JSONArray(res.getBody().asString());
		int size = json.length();
		return size;
	}

}
