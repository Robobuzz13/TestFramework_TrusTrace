package com.trustrace.uitest;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.trustrace.pages.LoginPage;
import com.trustrace.pages.ProductPage;
import com.trustrace.utils.DataUtils;
import com.trustrace.utils.EmailReport;
import com.trustrace.utils.EnvironmentPropertiesReader;
import com.trustrace.utils.Log;
import com.trustrace.utils.WebDriverFactory;

@Listeners(EmailReport.class)
public class LoginTest {

	private String browser;
	private String webSite;
	private String fileName;
	private static EnvironmentPropertiesReader configProperty = EnvironmentPropertiesReader.getInstance();

	@BeforeTest
	public void init(ITestContext context) {
		webSite = System.getProperty("webSite") != null ? System.getProperty("webSite")
				: configProperty.getProperty("webSite");
		browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: configProperty.getProperty("browser").toLowerCase();
		fileName = System.getProperty("fileName") != null ? System.getProperty("fileName")
				: configProperty.getProperty("fileName").toLowerCase();
	}

	@Test(description = "Verify user able to login successfully with valid credential")
	public void validLogin() throws Exception {

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.newWebDriverInstance(webSite, browser);

		// Loading the test data from excel using the test case id
		HashMap<String, String> testData = DataUtils.getTestData(fileName, "LoginTest", "TC001");

		String username = testData.get("userName").toString();
		String password = testData.get("password").toString();

		Log.testCaseInfo("Verify user able to login successfully with valid credential");
		try {

			// Step 1: Entering the login credentials
			LoginPage loginPage = new LoginPage(driver);
			loginPage.loginToApplication(username, password);

			// Step 2: Validate user navigate to product section
			ProductPage productPage = new ProductPage(driver);

			Log.assertThat(productPage.sectionTitle().equals("PRODUCTS"), "User logged in successfully",
					"Unable to log in", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	@Test(description = "Verify user cannot able to login with empty user name")
	public void emptyUserName() throws Exception {

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.newWebDriverInstance(webSite, browser);

		// Loading the test data from excel using the test case id
		HashMap<String, String> testData = DataUtils.getTestData(fileName, "LoginTest", "TC002");

		String username = testData.get("userName").toString();
		String password = testData.get("password").toString();

		Log.testCaseInfo("Verify user able to login successfully with empty user name");
		try {

			// Step 1: Entering the login credentials
			LoginPage loginPage = new LoginPage(driver);
			loginPage.loginToApplication(username, password);

			// Step 2: Validate error message
			Log.assertThat(loginPage.loginErrorMessage().equals("Epic sadface: Username is required"),
					"error message obtained successfully", "error message not obtained", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	@Test(description = "Verify user cannot able to login with empty password")
	public void emptyPassword() throws Exception {

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.newWebDriverInstance(webSite, browser);

		// Loading the test data from excel using the test case id
		HashMap<String, String> testData = DataUtils.getTestData(fileName, "LoginTest", "TC003");

		String username = testData.get("userName").toString();
		String password = testData.get("password").toString();

		Log.testCaseInfo("Verify user able to login successfully with empty passwordl");
		try {

			// Step 1: Entering the login credentials
			LoginPage loginPage = new LoginPage(driver);
			loginPage.loginToApplication(username, password);

			// Step 2: Validate error message
			Log.assertThat(loginPage.loginErrorMessage().equals("Epic sadface: Password is required"),
					"error message obtained successfully", "error message not obtained", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	@Test(description = "Verify user cannot able to login with invalid credential")
	public void invalidCredential() throws Exception {

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.newWebDriverInstance(webSite, browser);

		// Loading the test data from excel using the test case id
		HashMap<String, String> testData = DataUtils.getTestData(fileName, "LoginTest", "TC004");

		String username = testData.get("userName").toString();
		String password = testData.get("password").toString();

		Log.testCaseInfo("Verify user able to login successfully with invalid credential");
		try {

			// Step 1: Entering the login credentials
			LoginPage loginPage = new LoginPage(driver);
			loginPage.loginToApplication(username, password);

			/// Step 2: Validate error message
			Log.assertThat(
					loginPage.loginErrorMessage()
							.equals("Epic sadface: Username and password do not match any user in this service"),
					"error message obtained successfully", "error message not obtained", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

}
