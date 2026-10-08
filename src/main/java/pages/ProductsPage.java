package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductsPage {

	private Page page;
	
	private Locator productTitle;
	private Locator productSort;
	private Locator shoppingCart;
	
	public ProductsPage(Page page) {
		this.page=page;
		productTitle=page.locator(".title");
		productSort=page.locator(".product_sort_container");
		shoppingCart=page.locator(".shopping_cart_link");
	}
	
	public String getPageTitle() {
		return productTitle.textContent();
	}
	
	public void sortProducts(String option) {
		productSort.selectOption(option);
	}
	
	public void addProductToCart(String pName) {
		Locator product=page.locator(".inventory_item").filter(new Locator.FilterOptions().setHasText(pName));
	    product.locator("button").click();
	}
	
	public void openCart() {
		shoppingCart.click();
	}
	
	
}
