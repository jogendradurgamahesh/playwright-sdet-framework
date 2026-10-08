//package baseTest;
//
//public class APITestBase {
//
//}

package baseTest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Playwright;

public class APITestBase extends BaseTests {

    @BeforeMethod
    public void apiSetUp() {

        playwright = Playwright.create();
    }

    @AfterMethod(alwaysRun = true)
    public void apiTearDown() {

        if (playwright != null) {
            playwright.close();
        }
    }
}