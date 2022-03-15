package com.trustrace.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.trustrace.utils.CommonMethods;
import com.trustrace.utils.Log;

public class ProductPage extends LoadableComponent<ProductPage> {

	private final WebDriver driver;
	private boolean isPageLoaded;

	@FindBy(css = ".shopping_cart_link")
	WebElement cartLnk;

	@FindBy(css = ".title")
	WebElement sectionTitleTxt;

	@FindBy(id = "remove-sauce-labs-backpack")
	WebElement removeBtn;

	@FindBy(css = ".shopping_cart_badge")
	WebElement cartCountTxt;

	/**
	 * constructor of the class
	 *
	 * @param driver
	 */
	public ProductPage(WebDriver driver) {

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
	 * Method to get section title
	 *
	 */
	public String sectionTitle() {
		CommonMethods.waitForElementVisibility(sectionTitleTxt, driver);
		String title = CommonMethods.getText(sectionTitleTxt, driver, "Section Name");
		return title;
	}

	/**
	 * Method to get cart item count
	 *
	 */
	public String cartItemCount() {
		CommonMethods.waitForElementVisibility(cartCountTxt, driver);
		String title = CommonMethods.getText(cartCountTxt, driver, "Cart item count");
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
	 * Method to validate cart link
	 *
	 */
	public boolean validateCartLink() {
		CommonMethods.waitForElementVisibility(cartLnk, driver);
		boolean validateRemoveButton = CommonMethods.isElementPresent(cartLnk, driver);
		return validateRemoveButton;
	}

	/**
	 * Method to click add to cart
	 *
	 */
	public void clickAddToCart(String productName) {
		String cartElement = String.format(
				"//div[text()='%s']/ancestor::div[@class='inventory_item_description']//button[@id='add-to-cart-sauce-labs-backpack']",
				productName);
		WebElement addToCart = driver.findElement(By.xpath(cartElement));
		CommonMethods.waitForElementVisibility(addToCart, driver);
		CommonMethods.click(addToCart, "Add to cart link");
	}

	/**
	 * Method to click cart link
	 *
	 */
	public void clickCartLink() {
		CommonMethods.waitForElementVisibility(cartLnk, driver);
		CommonMethods.click(cartLnk, "Cart link");
	}

}
