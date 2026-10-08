package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage {

	private Page page;
	private Locator cartTitle;
	private Locator cartItem;
	private Locator checkoutButton;



	public CartPage(Page page) {
		this.page=page;
		cartTitle=page.locator(".title");
		cartItem = page.locator(".cart_item");
        checkoutButton = page.locator("#checkout");
	}
	
	public String getCartTitle() {
		return cartTitle.textContent();
	}
	
	public int getItemCount() {
		return cartItem.count();
	}
	public boolean isProductAvailable(String pName) {
		return page.locator(".cart_item").filter(new Locator.FilterOptions().setHasText(pName)).isVisible();
	}
	
	public void clickCheckOut() {
		checkoutButton.click();
	}

}
