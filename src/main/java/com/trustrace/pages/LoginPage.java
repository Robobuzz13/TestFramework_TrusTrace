package com.trustrace.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.trustrace.utils.CommonMethods;
import com.trustrace.utils.Log;

/**
 * Class to hold page objects for Login page *
 */
public class LoginPage extends LoadableComponent<LoginPage> {

	private final WebDriver driver;
	private boolean isPageLoaded;

	@FindBy(id = "user-name")
	WebElement userNameTxtFld;

	@FindBy(id = "password")
	WebElement passwordTxtFld;

	@FindBy(id = "login-button")
	WebElement loginBtn;

	@FindBy(css = "div[class='error-message-container error']")
	WebElement errorMsg;

	/**
	 * constructor of the class
	 *
	 * @param driver
	 */
	public LoginPage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(finder, this);
	}

	@Override
	protected void load() {
		isPageLoaded = true;
	}

	@Override
	protected void isLoaded() throws Error {
		if (!isPageLoaded) {
			Log.fail("Page not loaded");
		}
		String url = driver.getTitle();
		Log.assertThat(url.equals("Swag Labs"), "Page loaded successfully", "Page not loaded successfully");
	}

	/**
	 * Method to perform login operation
	 *
	 * @param userName
	 * @param password
	 */
	public void loginToApplication(String userName, String password) {
		CommonMethods.waitForElementVisibility(userNameTxtFld, driver);
		CommonMethods.setText(userNameTxtFld, userName, "UserName");
		CommonMethods.setText(passwordTxtFld, password, "Password");
		CommonMethods.click(loginBtn, "Login button");
	}

	/**
	 * Method to get error message
	 *
	 */
	public String loginErrorMessage() {
		CommonMethods.waitForElementVisibility(errorMsg, driver);
		String title = CommonMethods.getText(errorMsg, driver, "Error message");
		return title;
	}

}
