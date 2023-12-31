package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class GreenKartPage {
	
	WebDriver driver;

	public GreenKartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//*[@alt='Cart']")
	private static WebElement cartIcon;

	public static WebElement getCartIcon() {
		return cartIcon;
	}

	public static void setCartIcon(WebElement cartIcon) {
		GreenKartPage.cartIcon = cartIcon;
	}
}
