//package baseTest;
//
//public class BaseTest {
//
//}

package baseTest;

import java.nio.file.Paths;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

import pages.HomePage;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import pages.CheckOutPage;
import utils.ConfigReader;

public class BaseTest {

	protected Playwright playwright;
	protected Browser browser;
	protected BrowserContext context;
	protected Page page;
	protected  LoginPage loginPage;
	protected  HomePage homePage;
	protected ProductsPage productsPage;
	protected CartPage cartPage;
	protected CheckOutPage checkPage;

	@BeforeMethod
	public void setUp() {
		//	 playwright=Playwright.create();
		//	 browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		//	 context=browser.newContext();
		//	 page=context.newPage();
		//	
		//	page.navigate("https://www.saucedemo.com/");
		//	  loginPage = new LoginPage(page);
		//      homePage = new HomePage(page);

		playwright=Playwright.create();
//		String browserName=ConfigReader.getProperty("browser");
//		boolean headless=Boolean.parseBoolean(ConfigReader.getProperty("headless"));
		
		String browserName=ConfigReader.getBrowser();
		boolean headless=ConfigReader.isHeadless();

		if(browserName.equalsIgnoreCase("chromium")) {
			browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			browser=playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));

		}
		else if(browserName.equalsIgnoreCase("webkit")) {
			browser=playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
		}
		else {
			throw new IllegalArgumentException("Invalid browser "+browserName);
		}

		context=browser.newContext(new Browser.NewContextOptions().setRecordVideoSize(1000,800).setRecordVideoDir(Paths.get("test-output/videos/")));
		 context.tracing().start(
			        new Tracing.StartOptions()
			            .setScreenshots(true)
			            .setSnapshots(true)
			            .setSources(true)
			    );
		
		page=context.newPage();

		int timeout=Integer.parseInt(ConfigReader.getProperty("timeout"));
		page.setDefaultTimeout(timeout);
		String baseUrl=ConfigReader.getProperty("baseUrl");

		page.navigate(baseUrl);
		loginPage = new LoginPage(page);
		homePage = new HomePage(page);
		productsPage=new ProductsPage(page);
		cartPage=new CartPage(page);
		checkPage=new CheckOutPage(page);



	}


/*	@AfterMethod(alwaysRun = true)
//	public void tearDown(ITestResult result) {
	public void tearDown(ITestResult result) {
		
//		  if (page != null&& result.getStatus() == ITestResult.FAILURE) {
//
//		        String screenshotName =
//		                "test-output/screenshots/" +
//		                result.getMethod().getMethodName() +
//		                "-" +
//		                System.currentTimeMillis() +
//		                ".png";
//
//		        // Take screenshot only when test fails
//		        if (result.getStatus() == ITestResult.FAILURE) {
//
//		            page.screenshot(
//		                new Page.ScreenshotOptions()
//		                    .setPath(Paths.get(screenshotName))
//		                    .setFullPage(true)
//		            );
//
//		            System.out.println("Screenshot saved: " + screenshotName);
//		        }
//		    }
		
		 try {

		        // Take screenshot only when test fails
		        if (page != null && result.getStatus() == ITestResult.FAILURE) {

		            String screenshotName =
		                    "test-output/screenshots/" +
		                    result.getMethod().getMethodName() +
		                    "-" +
		                    System.currentTimeMillis() +
		                    ".png";

		            page.screenshot(
		                    new Page.ScreenshotOptions()
		                            .setPath(Paths.get(screenshotName))
		                            .setFullPage(true)
		            );

		            System.out.println("Screenshot saved: " + screenshotName);
		        }

		    } catch (Exception e) {

		        System.out.println("Screenshot failed: " + e.getMessage());
		    }

		
		
		  // Stop tracing
		    try {
		if (context != null) {
			 String testName =
		                result.getMethod().getMethodName() +
		                "-" +
		                System.currentTimeMillis();
			


			context.tracing().stop(
			    new Tracing.StopOptions()
			        .setPath(
			            Paths.get(
			            		"test-output/traces/" + testName + ".zip"
			            )
			        )
			);	
		}}
			catch (Exception e) {

		        System.out.println("Tracing stop failed: " + e.getMessage());
		    }
			//context.close();
		    // Close context
		    try {

		        if (context != null) {
		            context.close();
		        }

		    } catch (Exception e) {

		        System.out.println("Context close failed: " + e.getMessage());
		    }
			

		//close browser
		    try {
		if(browser!=null) {
			browser.close();
		}}
		    catch (Exception e) {

		        System.out.println("Browser close failed: " + e.getMessage());
		    }
		//close playwright
		if (playwright != null) {
			playwright.close();
		}

	} */
	
	
	
	
	
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {

	    System.out.println("========== TEARDOWN START ==========");
	    System.out.println("Test: " + result.getMethod().getMethodName());

	    // Screenshot
	    try {
	        if (page != null && result.getStatus() == ITestResult.FAILURE) {

	            String screenshotName =
	                    "test-output/screenshots/" +
	                    result.getMethod().getMethodName() +
	                    "-" +
	                    System.currentTimeMillis() +
	                    ".png";

	            page.screenshot(
	                    new Page.ScreenshotOptions()
	                            .setPath(Paths.get(screenshotName))
	                            .setFullPage(true)
	            );

	            System.out.println("Screenshot completed");

	        }
	    } catch (Exception e) {
	        System.out.println("❌ SCREENSHOT ERROR");
	        e.printStackTrace();
	    }


	    // Tracing
	    try {
	        if (context != null) {

	            String testName =
	                    result.getMethod().getMethodName() +
	                    "-" +
	                    System.currentTimeMillis();

	            System.out.println("Stopping tracing...");

	            context.tracing().stop(
	                    new Tracing.StopOptions()
	                            .setPath(
	                                    Paths.get(
	                                            "test-output/traces/"
	                                                    + testName
	                                                    + ".zip"
	                            )
	                    )
	            );

	            System.out.println("Tracing stopped");

	        }
	    } catch (Exception e) {
	        System.out.println("❌ TRACING ERROR");
	        e.printStackTrace();
	    }


	    // Context
	    try {
	        if (context != null) {

	            System.out.println("Closing context...");

	            context.close();

	            System.out.println("Context closed");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ CONTEXT CLOSE ERROR");
	        e.printStackTrace();
	    }


	    // Browser
	    try {
	        if (browser != null) {

	            System.out.println("Closing browser...");

	            browser.close();

	            System.out.println("Browser closed");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ BROWSER CLOSE ERROR");
	        e.printStackTrace();
	    }


	    // Playwright
	    try {
	        if (playwright != null) {

	            System.out.println("Closing Playwright...");

	            playwright.close();

	            System.out.println("Playwright closed");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ PLAYWRIGHT CLOSE ERROR");
	        e.printStackTrace();
	    }

	    System.out.println("========== TEARDOWN END ==========");
	}
}
