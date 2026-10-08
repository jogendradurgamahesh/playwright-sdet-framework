package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;

public class ProductsTest extends BaseTest{

	@Test
	public void addProdctsToCart() {
		loginPage.login("standard_user", "secret_sauce");

		//verify ProductsTitle
		Assert.assertEquals(productsPage.getPageTitle(),"Products");

		//sort products
		productsPage.sortProducts("za");

		//Add to Cart
		productsPage.addProductToCart("Sauce Labs Bike Light");

		System.out.println("Added successfully");

		productsPage.openCart();

		//check title of cartPAge
		Assert.assertEquals(cartPage.getCartTitle(), "Your Cart");

		//check product
		Assert.assertTrue(cartPage.isProductAvailable("Sauce Labs Bike Light"));

		System.out.println("Product successfully verified in cart");
	}



}
