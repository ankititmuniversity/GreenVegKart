package com.kart.pom;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.kart.utilities.LogHelper;

public class CartPage extends BasePage{
	private static final Logger log = LogHelper.getLogger(CartPage.class);  		
	public CartPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//span[@class='cart-count']")
	private WebElement cartCount;
	
	@FindBy(xpath="//a[@class='cart-icon']")
	private WebElement cartIcon;
	
	@FindBy(xpath="(//ul[@class='cart-items']/li[1]//p[contains(text(),'Brocolli')])[1]")
	private WebElement productName;
	
	@FindBy(xpath="//button[contains(text(),'PROCEED TO CHECKOUT')]")
	private WebElement checkoutBtn;
	
	public CheckoutPage cartPage() {
		log.info("You are in cart page");
		//Integer noOfItemsInCart = Integer.valueOf(getText(cartCount));
		//if(noOfItemsInCart>=1) {
			click(cartIcon);
			log.info("You clicked cart in cart page");
			String itemName = getText(productName);
			if(itemName.contains("Brocolli")) {
				click(checkoutBtn);
				log.info("You clicked checkoutBtn in cart page");
			}else {
				System.out.println("Go to HomePage and add Brocalli Again");
			}
		//}
		return new CheckoutPage(driver);
	}
	
}
