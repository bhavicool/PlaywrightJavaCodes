package playwrightsessions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightBasics {

    public static void main(String[] args) {

        //Create a Playwright server(create () is static, and it will start server)
        //First run will check if browser binaries are available on system or not, if they are not available then binaries will be downloaded automatically
        Playwright playwright=Playwright.create();

        //Launch Chromium browser(new browser context)
        //Use playwright.firefox() to launch Firefox nightly build
        //Use playwright.webkit() to launch Safari browser which can start on Windows system also
        //By default Playwright launches script in headless mode
        //Browser browser=playwright.chromium().launch();

        //Code to launch script in headed mode in Chromium browser
        Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        //Below section of code is used to start script in Chrome Browser
        //BrowserType.LaunchOptions launchOptions= new BrowserType.LaunchOptions();
        //launchOptions.setChannel("chrome");
        //launchOptions.setHeadless(false);
        //Browser browser=playwright.chromium().launch(launchOptions);

        //Below section of code is used to start script in Edge Browser
        //BrowserType.LaunchOptions launchOptions= new BrowserType.LaunchOptions();
        //launchOptions.setChannel("msedge");
        //launchOptions.setHeadless(false);
        //Browser browser=playwright.chromium().launch(launchOptions);

        //New Page Context inside browser
        Page page=browser.newPage();
        page.navigate("https://www.amazon.com");

        String pageTitle=page.title();
        System.out.println("Page Title is:"+pageTitle);

        //Closing browser and server
        browser.close();
        playwright.close();

    }
}
