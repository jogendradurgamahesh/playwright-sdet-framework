//package pages;
//
//public class LoginPage {}

	package pages;

	import com.microsoft.playwright.Locator;
	import com.microsoft.playwright.Page;

	public class LoginPage {

		//Page
		private Page page;
		
		
		//Locators
//		private final String username="#user-name";
//		private final String password="#password";
//		private final String loginButton="#login-button"; 
		
		  private Locator username;
		    private Locator password;
		    private Locator loginButton;
		
		public LoginPage(Page page) {
			this.page=page;
			username=page.locator("#user-name");
			password=page.locator("#password");
			loginButton=page.locator("#login-button");
		}
		
		
		//actions
		public void login(String usernameValue,String passwordValue) {
			username.fill(usernameValue);
			password.fill(passwordValue);
			loginButton.click();
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}


