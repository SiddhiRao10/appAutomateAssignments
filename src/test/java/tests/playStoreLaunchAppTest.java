package tests;

import commonClass.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.By.*;


public class playStoreLaunchAppTest extends BaseTest {
    @Test
    public void demoSiteTest() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("android session launched");

        String app="whatsapp";

        FluentWait<AppiumDriver<MobileElement>> wait=new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .ignoring(NotFoundException.class);

        try {

            Activity androidActivity=new Activity("com.android.vending", "com.google.android.finsky.activities.MainActivity");
            ((AndroidDriver<MobileElement>)driver).startActivity(androidActivity);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        Thread.sleep(3000);
        driver.findElement(xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.view.View")).click();

        driver.findElement(xpath("//android.widget.EditText[@text='Search for apps & games']")).sendKeys(app);
        Thread.sleep(2000);
        driver.findElement(xpath("//android.widget.TextView[@text='whatsapp']")).click();
        Thread.sleep(2000);
        driver.findElement(xpath("//android.widget.TextView[@text='Install']")).click();
        Thread.sleep(10000);
      int confirm=0;
        FluentWait<AppiumDriver<MobileElement>> installwait=new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(2))
                .pollingEvery(Duration.ofSeconds(10))
                .ignoring(NotFoundException.class);
        Thread.sleep(50000);
       driver.findElement(By.xpath("//android.widget.TextView[@text='Open']")).click();
            System.out.println("App is installed");
        Thread.sleep(8000);
        
    }
}
