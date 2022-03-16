package com.trustrace.apitest;

import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.trustrace.utils.DataUtils;
import com.trustrace.utils.EmailReport;
import com.trustrace.utils.EnvironmentPropertiesReader;
import com.trustrace.utils.Log;

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
	public void productCheckout() throws Exception {

		DataUtils.getTestData(fileName, "TypeCodeApi", "TC001");

		Log.testCaseInfo("Verify reponse of Typecode post call");
		try {

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.endTestCase();
		}
	}

}
