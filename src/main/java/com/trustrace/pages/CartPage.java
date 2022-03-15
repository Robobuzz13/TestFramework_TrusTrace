package com.trustrace.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.trustrace.utils.Log;

public class CartPage extends LoadableComponent<CartPage> {

	private final WebDriver driver;
	private boolean isPageLoaded;

	@FindBy(id = "checkout")
	WebElement checkoutBtn;

	@FindBy(id = "continue-shopping")
	WebElement continueShoppingBtn;

	@FindBy(id = "remove-sauce-labs-backpack")
	WebElement removeBtn;

	@FindBy(className = "inventory_item_name")
	WebElement itemNameTxt;

	@FindBy(id = "continue")
	WebElement continueBtn;

	@FindBy(id = "cancel")
	WebElement cancelBtn;

	@FindBy(id = "first-name")
	WebElement firstNameTxtFld;

	@FindBy(id = "last-name")
	WebElement lastNameTxtFld;

	@FindBy(id = "postal-code")
	WebElement postalCodeTxtFld;

	@FindBy(id = "finish")
	WebElement finishBtn;

	@FindBy(className = "complete-header")
	WebElement succesNameTxt;

	@FindBy(className = "complete-text")
	WebElement succesDescriptionTxt;

	@FindBy(id = "back-to-products")
	WebElement backHomeBtn;

	/**
	 * constructor of the class
	 *
	 * @param driver
	 */
	public CartPage(WebDriver driver) {

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

}
