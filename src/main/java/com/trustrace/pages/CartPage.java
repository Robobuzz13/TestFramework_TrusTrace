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

	/**
	 * Method to get product title
	 *
	 */
	public String productTitle() {
		CommonMethods.waitForElementVisibility(itemNameTxt, driver);
		String title = CommonMethods.getText(itemNameTxt, driver, "Product Title");
		return title;
	}

	/**
	 * Method to validate remove button
	 *
	 */
	public boolean validateRemoveBtn() {
		CommonMethods.waitForElementVisibility(removeBtn, driver);
		boolean validateRemoveButton = CommonMethods.isElementPresent(removeBtn, driver);
		return validateRemoveButton;
	}

	/**
	 * Method to validate continue shopping button
	 *
	 */
	public boolean validateContinueShoppingBtn() {
		CommonMethods.waitForElementVisibility(continueShoppingBtn, driver);
		boolean validateRemoveButton = CommonMethods.isElementPresent(continueShoppingBtn, driver);
		return validateRemoveButton;
	}

	/**
	 * Method to validate checkOut button
	 *
	 */
	public boolean validateCheckOutBtn() {
		CommonMethods.waitForElementVisibility(checkoutBtn, driver);
		boolean validateRemoveButton = CommonMethods.isElementPresent(checkoutBtn, driver);
		return validateRemoveButton;
	}

	/**
	 * Method to click checkout button
	 *
	 */
	public void clickCheckOutBtn() {
		CommonMethods.waitForElementVisibility(checkoutBtn, driver);
		CommonMethods.click(checkoutBtn, "Check out");
	}

	/**
	 * Method to update customer detail
	 *
	 * @param userName
	 * @param password
	 */
	public void updateUserDetail(String firstName, String lastName, String postalCode) {
		CommonMethods.waitForElementVisibility(firstNameTxtFld, driver);
		CommonMethods.setText(firstNameTxtFld, firstName, "First name");
		CommonMethods.setText(lastNameTxtFld, lastName, "Last name");
		CommonMethods.setText(postalCodeTxtFld, postalCode, "Postal code");
		CommonMethods.click(continueBtn, "Continue button");
	}

	/**
	 * Method to click finish button
	 *
	 */
	public void clickFinishBtn() {
		CommonMethods.waitForElementVisibility(finishBtn, driver);
		CommonMethods.click(finishBtn, "Finish button");
	}

	/**
	 * Method to get success title
	 *
	 */
	public String successTitle() {
		CommonMethods.waitForElementVisibility(succesNameTxt, driver);
		String title = CommonMethods.getText(succesNameTxt, driver, "Success Message");
		return title;
	}

	/**
	 * Method to get success description
	 *
	 */
	public String successDescription() {
		CommonMethods.waitForElementVisibility(succesDescriptionTxt, driver);
		String title = CommonMethods.getText(succesDescriptionTxt, driver, "Success Description");
		return title;
	}

}
