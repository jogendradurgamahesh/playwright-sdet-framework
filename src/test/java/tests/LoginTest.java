//package tests;
//
//public class LoginTest {
//
//}

package tests;

import org.testng.annotations.Test;

import com.microsoft.playwright.assertions.PlaywrightAssertions;

import baseTest.UITestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Feature("Login")
public class LoginTest extends UITestBase {
	
	//public static void main(String[] args) {
		
//		private Playwright playwright;
//		private Browser browser;
//		private BrowserContext context;
//		private Page page;
//		private  LoginPage loginPage;
//		private  HomePage homePage;
		
//		@BeforeMethod
//		public void setUp() {
//		 playwright=Playwright.create();
//		 browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//		 context=browser.newContext();
//		 page=context.newPage();
//		
//		page.navigate("https://www.saucedemo.com/");
//		}
		
		
		@Test
		@Severity(SeverityLevel.CRITICAL)
		@Description("Verify that valid user can login success")
		public void loginMethod() {
		//LoginPage
		// LoginPage  loginPage=new LoginPage(page);
			
			//String screenshotName="test-output/screenshots/"+  result.getMethod().getMethodName() +"-" +System.currentTimeMillis() + ".png";
			// page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/login.png")).setFullPage(true));
			 System.out.println("Login test: " + Thread.currentThread().getName());
		 loginPage.login("standard_user", "secret_sauce");
		 
		
		 
		 PlaywrightAssertions.assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");
		
		 
		 //HomePage
		// HomePage homePage=new HomePage(page);
		 System.out.println(homePage.getTitle());
		 
		 
		// page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/s1.png")));
		 homePage.logout();
		}	
		
		
//		@AfterMethod(alwaysRun = true)
//		public void tearDown() {
//			//close browser
//			if(browser!=null) {
//				browser.close();
//			}
//			//close playwright
//			  if (playwright != null) {
//		            playwright.close();
//		        }
//			
//		}
//		

	
	
	
	

}

