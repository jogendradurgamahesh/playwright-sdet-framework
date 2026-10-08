//package baseTest;
//
//public class UITestBase {
//
//}

package baseTest;

import java.nio.file.Paths;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.*;

import pages.*;
import utils.ConfigReader;

public class UITestBase extends BaseTests {

    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected LoginPage loginPage;
    protected HomePage homePage;
    protected ProductsPage productsPage;
    protected CartPage cartPage;
    protected CheckOutPage checkPage;

    @BeforeMethod
    public void setUp() {

        playwright = Playwright.create();

        String browserName = ConfigReader.getBrowser();
        boolean headless = ConfigReader.isHeadless();

        if (browserName.equalsIgnoreCase("chromium")) {

            browser = playwright.chromium()
                    .launch(new BrowserType.LaunchOptions()
                            .setHeadless(headless));

        } else if (browserName.equalsIgnoreCase("firefox")) {

            browser = playwright.firefox()
                    .launch(new BrowserType.LaunchOptions()
                            .setHeadless(headless));

        } else if (browserName.equalsIgnoreCase("webkit")) {

            browser = playwright.webkit()
                    .launch(new BrowserType.LaunchOptions()
                            .setHeadless(headless));

        } else {

            throw new IllegalArgumentException(
                    "Invalid browser: " + browserName);
        }

        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setRecordVideoSize(1000, 800)
                        .setRecordVideoDir(
                                Paths.get("test-output/videos/")
                        )
        );

        context.tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );

        page = context.newPage();

        int timeout = Integer.parseInt(
                ConfigReader.getProperty("timeout")
        );

        page.setDefaultTimeout(timeout);

        page.navigate(
                ConfigReader.getProperty("baseUrl")
        );

        loginPage = new LoginPage(page);
        homePage = new HomePage(page);
        productsPage = new ProductsPage(page);
        cartPage = new CartPage(page);
        checkPage = new CheckOutPage(page);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        System.out.println("========== TEARDOWN START ==========");

        try {
            if (page != null &&
                    result.getStatus() == ITestResult.FAILURE) {

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
            }

        } catch (Exception e) {
            System.out.println("Screenshot failed: "
                    + e.getMessage());
        }

        try {
            if (context != null) {

                String testName =
                        result.getMethod().getMethodName()
                        + "-"
                        + System.currentTimeMillis();

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

                context.close();
            }

        } catch (Exception e) {
            System.out.println("Context/tracing failed: "
                    + e.getMessage());
        }

        try {
            if (browser != null) {
                browser.close();
            }
        } catch (Exception e) {
            System.out.println("Browser close failed: "
                    + e.getMessage());
        }

        try {
            if (playwright != null) {
                playwright.close();
            }
        } catch (Exception e) {
            System.out.println("Playwright close failed: "
                    + e.getMessage());
        }

        System.out.println("========== TEARDOWN END ==========");
    }
}
