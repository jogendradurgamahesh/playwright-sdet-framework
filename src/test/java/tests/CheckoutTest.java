package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ConfigReader;
import utils.JsonReader;

import baseTest.BaseTest;

public class CheckoutTest extends BaseTest {

	@Test
	public void completeOrder() {

		//loginPage
		  String username = JsonReader.getValue("login", "username");
		  String password =JsonReader.getValue("login", "password");
		
		  loginPage.login(username, password);

		//products page
		Assert.assertEquals(productsPage.getPageTitle(), "Products");

		//sort products
		productsPage.sortProducts("hilo");

		//add product
		String productName=JsonReader.getValue("product", "name");
		productsPage.addProductToCart(productName);

		//open cart
		productsPage.openCart();	

		//validate cart
		Assert.assertTrue(cartPage.isProductAvailable("Sauce Labs Bolt T-Shirt"));

		//click checkout
		cartPage.clickCheckOut();

		//enter customer details
		String firstName=JsonReader.getValue("customer", "firstName");
		String lastName=JsonReader.getValue("customer", "lastName");
		String postalcode=JsonReader.getValue("customer", "postalCode");
		checkPage.enterCustomerDetails(firstName,lastName, postalcode);

		//continue
		checkPage.clickContinue();

		//finish
		checkPage.finishOrder();

		//confirmation msg
		Assert.assertEquals(checkPage.getConfirmationmessage(),  "Thank you for your order!");
		System.out.println("Order placed successfully");
	}
}
