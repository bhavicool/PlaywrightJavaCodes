package playwrightsessions;

import com.microsoft.playwright.*;

import java.util.List;

public class PlaywrightAmazonTest {

    public static void main(String[] args) {


        Playwright playwright=Playwright.create();

        Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        //First Browser Context
        BrowserContext browserContext1=browser.newContext();
        Page page=browserContext1.newPage();
        page.navigate("https://www.orangehrm.com/en/hris-hr-software-demo-2/");

        String pageTitle=page.title();
        System.out.println("Page Title is:"+pageTitle);

        Locator getToKnowUsLinks=page.locator("//h5[text()='About Us']/following-sibling::ul/descendant::a");
        List<String> links=getToKnowUsLinks.allInnerTexts();
        for(String s:links)
        {
            System.out.println("Link is: "+s);
        }
    }
}
