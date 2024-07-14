import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;


public class GetAPITest {

    Playwright playwright;
    APIRequest apiRequest;
    APIRequestContext apiRequestContext;

    @BeforeTest
    public void setup() {
        playwright = Playwright.create();
        apiRequest = playwright.request();
        apiRequestContext = apiRequest.newContext();
    }

    @Test
    public void getCallWithoutQueryParams() throws IOException {
        APIResponse apiResponse = apiRequestContext.get("https://gorest.co.in/public/v2/users");
        Assert.assertEquals(apiResponse.status(), 200);
        System.out.println("API Response Text is:" + apiResponse.text());
        System.out.println("API URL is:" + apiResponse.url());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
        String jsonPrettyRespose = jsonResponse.toPrettyString();
        System.out.println("JSON API Response is:" + jsonPrettyRespose);
    }

    @Test
    public void getCallWithQueryParams() throws IOException {
        APIResponse apiResponse = apiRequestContext.get("https://gorest.co.in/public/v2/users", RequestOptions.create()
                .setQueryParam("gender", "male")
                .setQueryParam("status", "active"));
        Assert.assertEquals(apiResponse.status(), 200);
        System.out.println("API Response Text is:" + apiResponse.text());
        System.out.println("API URL is:" + apiResponse.url());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
        String jsonPrettyRespose = jsonResponse.toPrettyString();
        System.out.println("JSON API Response is:" + jsonPrettyRespose);
    }

    @AfterTest
    public void cleanup() {
        apiRequestContext.dispose();
        playwright.close();
    }
}
