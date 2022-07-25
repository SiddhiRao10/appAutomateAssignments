package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class multipleAppVersion {
    String username = System.getenv("BROWSERSTACK_USERNAME");
    public String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    public  AppiumDriver<MobileElement> driver;
    private String APP_PKG = "com.browserstack.sample";
    private String APP_ACT = "com.browserstack.sample.MainActivity";
    @BeforeMethod
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserstack.user", username);
        capabilities.setCapability("browserstack.key", accessKey);
        capabilities.setCapability("device", "Samsung Galaxy S8");
        capabilities.setCapability("os_version", "7.0");
        capabilities.setCapability("name", "Test_app_upgrade");

        // Set app_url of the application under test(AUT) which is version v1.0 of app
        capabilities.setCapability("app", "bs://6c5f944cad2252a75f41fc2a751ce102e8500b9d");
        // Set app_url obtained after uploading the upgrade version v1.1 of app
        capabilities.setCapability("browserstack.midSessionInstallApps", new String[]{"bs://9773f390b6d8001962a8d8da3ad101f326068a36"});

        driver = new AndroidDriver<MobileElement>(new URL("http://hub.browserstack.com/wd/hub"), capabilities);
        WebDriverWait wait = new WebDriverWait(driver, 10);
    }
    @Test
    public void appUpgrade() throws MalformedURLException {

            driver.findElementByAccessibilityId("menu").click();
            System.out.println("menu clicked");

            driver.closeApp();
            driver.installApp("bs://9773f390b6d8001962a8d8da3ad101f326068a36");

            // Launch the app using the package and launcher activity of the app
            Activity activity = new Activity(APP_PKG, APP_ACT);
            ((AndroidDriver<MobileElement>)driver).startActivity(activity);
            System.out.println("second app launched");

    }
    @AfterMethod
    public void teardown()
    {
        driver.quit();
    }
}
