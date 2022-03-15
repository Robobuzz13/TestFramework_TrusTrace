package com.trustrace.test_automation_framework;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.trustrace.pages.CartPage;
import com.trustrace.pages.LoginPage;
import com.trustrace.pages.ProductPage;
import com.trustrace.utils.DataUtils;
import com.trustrace.utils.EmailReport;
import com.trustrace.utils.EnvironmentPropertiesReader;
import com.trustrace.utils.Log;
import com.trustrace.utils.WebDriverFactory;

@Listeners(EmailReport.class)
public class CartTest {

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

	@Test(description = "Verify user able to checkout product")
	public void productCheckout() throws Exception {

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.newWebDriverInstance(webSite, browser);

		// Loading the test data from excel using the test case id
		HashMap<String, String> testData = DataUtils.getTestData(fileName, "CartTest", "TC001");

		String username = testData.get("userName").toString();
		String password = testData.get("password").toString();
		String productName = testData.get("productName").toString();
		String firstName = testData.get("firstName").toString();
		String lastName = testData.get("lastName").toString();
		String postalCode = testData.get("postalCode").toString();

		Log.testCaseInfo("Verify user able to checkout product");
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
			Log.softAssertThat(productPage.cartItemCount().equals("1"), "Product was successfully added to cart",
					"Product fails to added in cart");

			// Step 5: click cart button
			productPage.clickCartLink();

			// Step 6: click check out button
			CartPage cartPage = new CartPage(driver);
			cartPage.clickCheckOutBtn();

			// Step 7: Update user details
			cartPage.updateUserDetail(firstName, lastName, postalCode);

			// Step 8: Validate product name and click finish
			Log.softAssertThat(cartPage.productTitle().equals(productName), "Product name valiate successfully",
					"Product name not validated", driver);

			cartPage.clickFinishBtn();

			// Step 9: Validate success message title and description

			Log.assertThat(cartPage.successTitle().equals("THANK YOU FOR YOUR ORDER"),
					"Success message title is validated", "Error in validate success message title");
			Log.assertThat(
					cartPage.successDescription().equals(
							"Your order has been dispatched, and will arrive just as fast as the pony can get there!"),
					"Success message description is validated", "Error in validate success message description");

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

}
