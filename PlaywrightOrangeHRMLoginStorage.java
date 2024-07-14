package playwrightsessions;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class PlaywrightOrangeHRMLoginStorage {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        //First Browser Context
        BrowserContext browserContext = browser.newContext();
        Page page = browserContext.newPage();
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        page.fill("//input[@name='username']", "Admin");
        page.fill("//input[@name='password']", "admin123");
        page.click("//button[@type='submit']");

        //Maintain above session in specific storage(a new json file where login data is stored)
        browserContext.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("applogin.json")));

        browser.close();
        playwright.close();

    }
}
