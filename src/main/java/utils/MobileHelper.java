package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import java.util.HashMap;
public class MobileHelper {
    AppiumDriver driver;

    public MobileHelper(AppiumDriver driver) {
        this.driver = driver;
    }
    public boolean isAndroid() {
        return driver.getPlatformName().equalsIgnoreCase("android");
    }

    public boolean isiOS() {
        return driver.getPlatformName().equalsIgnoreCase("ios");
    }

    public void selectFromPickerWheel(String iOSPicker, String option) {
        if (isAndroid()) {
            driver.findElement(MobileBy.xpath("//*[@text = '" + option + "']")).click();
        } else if (isiOS()) {
            driver.findElement(By.xpath(iOSPicker)).sendKeys(new CharSequence[]{option});
            driver.findElement(By.name("done_button")).click();
        }

    }

   // }

}
