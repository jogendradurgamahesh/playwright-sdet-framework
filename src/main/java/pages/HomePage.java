package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {
private  Page page;
	
	private Locator productTitle;
	private Locator menuOptions;
	private Locator logOut;
	
	public HomePage(Page page) {
		this.page=page;
		productTitle=page.locator(".title");
		menuOptions=page.locator("#react-burger-menu-btn");
		logOut=page.locator("#logout_sidebar_link");
	}
	
	
//	public Locator getTitle() {
//		return productTitle;
//	}
	public String getTitle() {
	    return productTitle.textContent();
	}
	
	public void logout() {
		menuOptions.click();
		logOut.click();
	}
	


}
