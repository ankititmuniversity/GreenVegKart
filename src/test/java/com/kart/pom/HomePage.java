package com.kart.pom;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.kart.utilities.LogHelper;

public class HomePage extends BasePage {
	private static final Logger log = LogHelper.getLogger(HomePage.class);  
	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath ="//h4[contains(text(),'Brocolli')]")
	private WebElement vegName;
	
	@FindBy(xpath = "(//div[@class='product']/descendant::a[1])[1]")
	private WebElement decrementBtn;
	
	@FindBy(xpath = "(//div[@class='product']/descendant::a[2])[1]")
	private WebElement incrementBtn;
	
	@FindBy(xpath = "(//input[@class='quantity'])[1]")
	private WebElement selectQuantity;
	
	@FindBy(xpath = "(//button[text()='ADD TO CART'])[1]")
	private WebElement addToCart;
	
	public String getVegName() {
	    return getText(vegName);
	}

	public String getQuantityValue() {
	    return selectQuantity.getAttribute("value");
	}

	
	public CartPage addProductToCart() {
		log.info("Check Broclli is in stock or not");
		String qtyValue = selectQuantity.getAttribute("value");
		if(getText(vegName).contains("Brocolli")) {
			if (qtyValue.equals("1")) {
			    click(incrementBtn);
			    log.info("1Kg Broclli is added in stock");
			    click(incrementBtn);
			    log.info("1Kg more Broclli is added in stock");
			} else {
			    click(decrementBtn);
			}
		}
		click(addToCart);
		return new CartPage(driver);
	}
	
	
}
