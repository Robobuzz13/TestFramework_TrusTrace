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

	public String sectionTitle() {
		CommonMethods.waitForElementVisibility(sectionTitleTxt, driver);
		String title = CommonMethods.getText(sectionTitleTxt, driver);
		return title;
	}

}
