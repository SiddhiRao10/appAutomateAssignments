package tests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class playStore {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    String username = System.getenv("BROWSERSTACK_USERNAME");
    public String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    public  AppiumDriver<MobileElement> driver;
    private static final String BROWSERSTACK_HUB_URL = "hub-cloud.browserstack.com";

    @BeforeMethod(alwaysRun = true)
    public void setup() throws MalformedURLException {

        capabilities.setCapability("deviceName", "Samsung Galaxy S8 Plus");
        capabilities.setCapability("os_version", "7.0");
        capabilities.setCapability("project", "My First Project");
        capabilities.setCapability("build", "My First Build");
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("app", "bs://6c5f944cad2252a75f41fc2a751ce102e8500b9d");

        driver = new AndroidDriver<MobileElement>(new URL("https://" + username + ":" + accessKey + "@" + BROWSERSTACK_HUB_URL + "/wd/hub"), capabilities);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    }
