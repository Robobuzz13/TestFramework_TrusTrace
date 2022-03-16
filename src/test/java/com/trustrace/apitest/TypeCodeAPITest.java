package com.trustrace.apitest;

import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.trustrace.api.TypeCodeResponse;
import com.trustrace.utils.DataUtils;
import com.trustrace.utils.EmailReport;
import com.trustrace.utils.EnvironmentPropertiesReader;
import com.trustrace.utils.Log;

import io.restassured.response.Response;

@Listeners(EmailReport.class)
public class TypeCodeAPITest {

	private String fileName;
	private static EnvironmentPropertiesReader configProperty = EnvironmentPropertiesReader.getInstance();

	@BeforeTest
	public void init(ITestContext context) {
		fileName = System.getProperty("fileName") != null ? System.getProperty("fileName")
				: configProperty.getProperty("fileName").toLowerCase();
	}

	@Test(description = "Verify reponse of Typecode post call")
	public void getTypeCode() throws Exception {

		HashMap<String, String> testData = DataUtils.getTestData(fileName, "CartTest", "TC001");

		Log.testCaseInfo("Verify reponse of Typecode post call");
		try {

			// Step 1: Hit post call
			TypeCodeResponse typeCodeResponse = new TypeCodeResponse();
			Response res = typeCodeResponse.postTypeCodeResponse(testData);

			// Step 2: Assert status code
			Log.assertThat(res.getStatusCode() == 200, "Response code validated successfully",
					"Responce code validation failed");

			// Step 3: Asset size of response
			Log.assertThat(typeCodeResponse.arraySizeResult(res) == 100, "Response body size validated successfully",
					"Responce body size validation failed");

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.endTestCase();
		}
	}

}
