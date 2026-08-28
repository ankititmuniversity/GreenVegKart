package com.kart.pom;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.kart.utilities.LogHelper;

public class CheckoutPage extends BasePage {
	private static final Logger log = LogHelper.getLogger(CheckoutPage.class);  
	public CheckoutPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//table[@id='productCartTables']")
	private WebElement vegTable;
	
	@FindBy(xpath = "//button[contains(text(),'Apply')]")
	private WebElement applyPromoCodeBtn;
	
	@FindBy(xpath = "//b[contains(text(),'No. of Items')]")
	private WebElement itemCount;
	
	@FindBy(xpath = "//b[contains(text(),'Total Amount')]")
	private WebElement totalBill;
	
	@FindBy(xpath = "//b[contains(text(),'Discount')]")
	private WebElement discount;
	
	@FindBy(xpath = "//b[contains(text(),'Total After Discount')]")
	private WebElement billAfterDiscount;
	
	@FindBy(xpath = "//button[contains(text(),'Place Order')]")
	private WebElement placeOrderBtn;
	
	@FindBy(xpath = "//option[text()='Select']/parent::select")
	private WebElement selectCountryDD;
	
	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement selectTerms;
	
	@FindBy(xpath = "//button[text()='Proceed']")
	private WebElement submitOrder;
	
	public void submitFinalOrder() {
		log.info("You are now in CheckoutPage");
		manageTable(vegTable);
		log.info("You are looking order summary in CheckoutPage");
		getText(itemCount);
		log.info("You are now counting total item in CheckoutPage");
		getText(totalBill);
		log.info("You are now calculating total bill in CheckoutPage");
		getText(discount);
		log.info("You are now checking discount in CheckoutPage");
		getText(billAfterDiscount);	
		log.info("You are now checking billafter discount in CheckoutPage");
		click(placeOrderBtn);
		log.info("You click place order button in CheckoutPage");
		staticDropDown(selectCountryDD);
		log.info("You clicked dropdown list now in CheckoutPage");
		click(selectTerms);
		log.info("You have selected T&C  in CheckoutPage");
		click(submitOrder);
		log.info("You have submitted order now in CheckoutPage");
		
	}
	
}
