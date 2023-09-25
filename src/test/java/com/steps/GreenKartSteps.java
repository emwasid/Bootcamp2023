 package com.steps;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pages.GreenKartPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GreenKartSteps {
	
	WebDriver driver;
	GreenKartPage pf;
	ArrayList<Integer> priceList;
	int totalPrice;
	
	@Given("user go to Green Kart home page")
	public void user_go_to_green_kart_home_page() {
	    //Create an object for WebDriver interface to use methods from ChromeDriver class
		driver = new EdgeDriver();
		//Open browser and navigate to GreenKart home page  
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
	    //maximize window
		driver.manage().window().maximize();
		pf = new GreenKartPage(driver);
	}

	@Given("user verifies page title as {string}")
	public void user_verifies_page_title_as(String title) {
	    //Getting actual title from web page
		String actual = driver.getTitle();
		//Capturing title from feature file
		String expected = title;
		
		Assert.assertTrue("Title did not match", expected.equalsIgnoreCase(actual));
	    
	}

	@When("user capture all prices from descending order")
	public void user_capture_all_prices_from_descending_order() {
	    
		List<WebElement> items = driver.findElements(By.xpath("//p[@class='product-price']"));
		
	    priceList = new ArrayList<Integer>();
	    
	    for(WebElement element : items) {
	    	String price = element.getText();
	    	int priceInt = Integer.parseInt(price);
	    	priceList.add(priceInt);
	    	
	    }
		System.out.println("Before sort: "+priceList);
		
		Collections.sort(priceList, Collections.reverseOrder());
		System.out.println("After sort: "+ priceList);
		
	}

	@Then("user adds the max and min price item to cart and capture total price")
	public void user_adds_the_max_and_min_price_item_to_cart_and_capture_total_price() {
	    int max = Collections.max(priceList);
	    int min = Collections.min(priceList);
	    
	    System.out.println(max+","+min);
	    
	    totalPrice = max+75;
	    
	    WebElement maxItem = driver.findElement(By.xpath("//*[contains(text(),'"+ max +"')]/following-sibling::*/button"));
	    
	    WebElement minItem = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[18]/div[3]/button"));
	    
	    maxItem.click();
	    minItem.click();
	}

	@Then("user clicks on cart button then proceed to checkout")
	public void user_clicks_on_cart_button_then_proceed_to_checkout() {
	    
//	    driver.findElement(By.xpath("//*[@alt=\"Cart\"]")).click();
	    pf.getCartIcon().click();
	    driver.findElement(By.xpath("//*[contains(text(), 'PROCEED TO CHECKOUT')]")).click();
	}

	@Then("user verifies total price previous page")
	public void user_verifies_total_price_previous_page() {
	    
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='totAmt']")));
		String totalPriceString = driver.findElement(By.xpath("//*[@class='totAmt']")).getText();
		Integer totalPriceInt = Integer.parseInt(totalPriceString);
		Integer totalPriceAddition = totalPrice;
		Assert.assertTrue("The total price is incorrect", totalPriceInt.equals(totalPriceAddition));
	    
	}

	@Then("user click on place order")
	public void user_click_on_place_order() {
	    
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Place Order')]")));
		driver.findElement(By.xpath("//*[contains(text(),'Place Order')]")).click();
		
	}

	@Then("user chooses country as {string}")
	public void user_chooses_country_as(String string) {
	    
		Select dropdown = new Select(driver.findElement(By.xpath("//*[@style=\"width: 200px;\"]")));
		dropdown.selectByValue(string);
		
		
	    
	}

	@Then("user clicks on check box for terms and conditions")
	public void user_clicks_on_check_box_for_terms_and_conditions() {
	    
		driver.findElement(By.xpath("//*[@type='checkbox']")).click();
	    
	}

	@Then("user clicks on proceed button")
	public void user_clicks_on_proceed_button() {
	    
		driver.findElement(By.xpath("//*[contains(text(), 'Proceed')]")).click();
	}

	@Then("user verify {string} message")
	public void user_verify_message(String string) {
	    
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[contains(text(),'" + string + "')]")));
		WebElement actualElement = driver
				.findElement(By.xpath("//*[contains(text(),'Thank you, your order has been placed successfully')]"));
		String actual = actualElement.getText();
//	    System.out.println(actual);
		Assert.assertTrue("The text for product shipment is incorrect", actual.contains(string));
		driver.quit();
	}

}
