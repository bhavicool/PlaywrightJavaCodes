package playwrightsessions;

import com.microsoft.playwright.*;

public class PlaywrightMultipleBrowserContext {

    public static void main(String[] args) {
        Playwright playwright=Playwright.create();

        Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        //First Browser Context
        BrowserContext browserContext1=browser.newContext();
        Page page=browserContext1.newPage();
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        page.fill("input.oxd-input--active","Bhavesh");
        String pageTitle=page.title();
        System.out.println("Page Title is:"+pageTitle);

        //Second Browser Context
        BrowserContext browserContext2=browser.newContext();
        Page page1=browserContext2.newPage();
        page1.navigate("https://www.amazon.com");
        String pageTitle1=page1.title();
        System.out.println("Page Title is:"+pageTitle1);

        page.close();
        browserContext1.close();

        page1.close();
        browserContext1.close();

    }
}
