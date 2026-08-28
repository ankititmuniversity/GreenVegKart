package com.kart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.kart.pom.CartPage;
import com.kart.pom.CheckoutPage;
import com.kart.pom.HomePage;

public class OnlineVegKartTest extends BaseTest {
	
	@Test(groups ="Smoke")
	public void purchaseVegOnline() {
		HomePage homePage = new HomePage(getDriver());
		homePage.addProductToCart()
		.cartPage();
		CheckoutPage cp = new CheckoutPage(getDriver());
		cp.submitFinalOrder();
	}
	@Test(groups="Sanity",retryAnalyzer=com.kart.utilities.RetryAnalyzer.class)
	public void assertPurchase() {
	    HomePage homePage = new HomePage(getDriver());

	    // Action
	    CartPage cartPage = homePage.addProductToCart();

	    // Assertions
	    Assert.assertTrue(homePage.getVegName().contains("Brocolli"),
	        "Expected Brocolli in HomePage but got: " + homePage.getVegName());

	    Assert.assertEquals(homePage.getQuantityValue(), "3",
	        "Quantity mismatch after incrementing Brocolli");
	}

	
}
