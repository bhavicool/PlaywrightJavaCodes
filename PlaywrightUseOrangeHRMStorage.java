package playwrightsessions;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class PlaywrightUseOrangeHRMStorage {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        //First Browser Context
        BrowserContext browserContext=browser.newContext(new Browser.NewContextOptions()
                .setStorageStatePath(Paths.get("applogin.json")));
        Page page = browserContext.newPage();
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        browser.close();
        playwright.close();

    }
}
