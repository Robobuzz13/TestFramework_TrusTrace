package com.trustrace.apitest;

import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.trustrace.api.UserCreateResponse;
import com.trustrace.utils.DataUtils;
import com.trustrace.utils.EnvironmentPropertiesReader;
import com.trustrace.utils.Log;

import io.restassured.response.Response;

public class UserCreateApiTest {

	private String fileName;
	private static EnvironmentPropertiesReader configProperty = EnvironmentPropertiesReader.getInstance();

	@BeforeTest
	public void init(ITestContext context) {
		fileName = System.getProperty("fileName") != null ? System.getProperty("fileName")
				: configProperty.getProperty("fileName").toLowerCase();
	}

	@Test(description = "Verify reponse of create user post call")
	public void createUser() throws Exception {

		HashMap<String, String> testData = DataUtils.getTestData(fileName, "UserCreate", "TC001");

		Log.testCaseInfo("Verify reponse of create user post call");
		try {

			// Step 1: Hit post call
			UserCreateResponse userCreateResponse = new UserCreateResponse();
			Response res = userCreateResponse.postTypeCodeResponse(testData);

			// Step 2: Assert status code
			Log.assertThat(res.getStatusCode() == 201, "Response code validated successfully",
					"Responce code validation failed");

			// Step 3: Validate response body
			Log.assertThat(userCreateResponse.validateResponseMessage(res, "id"), "Response object id is present",
					"Response object id is not present");

			Log.assertThat(userCreateResponse.validateResponseMessage(res, "createdAt"),
					"Response object createdAt is present", "Response object createdAt is not present");

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.endTestCase();
		}

	}

}
