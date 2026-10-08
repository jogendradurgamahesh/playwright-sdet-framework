package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckOutPage {
	
	private Page page;
	private Locator checkoutTitle;
	private Locator firstname;
	private Locator lastname;
	private Locator pincode;
	private Locator continueBtn;
	private Locator cancelBtn;
	private Locator finishBtn;
	private Locator confirmationMessage;
	
	public CheckOutPage(Page page) {
		this.page=page;
		checkoutTitle=page.locator(".title");
		firstname=page.locator("#first-name");
		lastname=page.locator("#last-name");
		pincode=page.locator("#postal-code");
		continueBtn=page.locator("#continue");
		cancelBtn=page.locator("#cancel");
		finishBtn=page.locator("#finish");
		confirmationMessage=page.locator(".complete-header");
	}
	
	public void enterCustomerDetails(String firstName,String lastName,String code) {
		firstname.fill(firstName);
		lastname.fill(lastName);
		pincode.fill(code);
	}
	
	public void clickContinue() {
		continueBtn.click();
	}
	
	public void finishOrder() {
		finishBtn.click();
	}
	
	public void cancelOrder() {
		cancelBtn.click();
	}
	
	public String getConfirmationmessage() {
		return confirmationMessage.textContent();
	}

}
