package playwrightsessions;

import com.microsoft.playwright.*;

public class PlaywrightWindowHandle {
    public static void main(String[] args) throws InterruptedException {
        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        //First Browser Context
        BrowserContext browserContext = browser.newContext();
        Page orangeHrmMain = browserContext.newPage();
        orangeHrmMain.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        //Clicking OrangeHRM, Inc link which opens a new tab and performing some action inside it

        Page orangeHrmInc = orangeHrmMain.waitForPopup(() ->
        {
            orangeHrmMain.click("//a[text()='OrangeHRM, Inc']");
        });
        //wait for new window to load properly
        orangeHrmInc.waitForLoadState();

        System.out.println("New page title is: " + orangeHrmInc.title());

        orangeHrmInc.fill("input#Form_submitForm_EmailHomePage", "abc@gmail.com");

        Thread.sleep(1500);

        //Closing child tab/window
        orangeHrmInc.close();

        //Closing parent window
        orangeHrmMain.close();
        browser.close();
        playwright.close();

    }
}
