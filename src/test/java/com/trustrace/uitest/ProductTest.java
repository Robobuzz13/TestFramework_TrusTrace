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
public class ProductTest {

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

	@Test(description = "Verify user able to select product")
	public void productSelect() throws Exception {

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.newWebDriverInstance(webSite, browser);

		// Loading the test data from excel using the test case id
		HashMap<String, String> testData = DataUtils.getTestData(fileName, "ProductTest", "TC001");

		String username = testData.get("userName").toString();
		String password = testData.get("password").toString();
		String productName = testData.get("productName").toString();

		Log.testCaseInfo("Verify user able to select product");
		try {

			// Step 1: Entering the login credentials
			LoginPage loginPage = new LoginPage(driver);
			loginPage.loginToApplication(username, password);

			// Step 2: Validate user navigate to product section
			ProductPage productPage = new ProductPage(driver);

			Log.softAssertThat(productPage.sectionTitle().equals("PRODUCTS"), "User logged in successfully",
					"Unable to log in", driver);

			// Step 3: Select product
			productPage.clickAddToCart(productName);

			// Step 4: Validate cart count
			Log.assertThat(productPage.cartItemCount().equals("1"), "Product was successfully added to cart",
					"Product fails to added in cart");

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

}
