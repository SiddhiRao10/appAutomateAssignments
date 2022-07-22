package commonClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.MobileHelper;

import java.util.HashMap;

import static commonClass.BaseTest.bsLocal;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class BasePO {
   // protected AppiumDriver driver;
   protected AppiumDriver<MobileElement> driver;
    protected MobileHelper mobileHelper;

  //  protected AndroidDriver driver1;
    public BasePO(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        this.mobileHelper = new MobileHelper(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }


    @AndroidFindBy(accessibility = "menu")
    @iOSXCUITFindBy(accessibility = "menu")
    public WebElement menuLink;

    @AndroidFindBy(accessibility = "nav-signin")
    @iOSXCUITFindBy(accessibility = "nav-signin")
    public WebElement signInLink;

    @AndroidFindBy(accessibility = "username-input")
    @iOSXCUITFindBy(accessibility = "username-input")
    public WebElement usernameInput;

    @AndroidFindBy(accessibility = "password-input")
    @iOSXCUITFindBy(accessibility = "password-input")
    public WebElement passwordInput;

    @AndroidFindBy(accessibility = "login-btn")
    @iOSXCUITFindBy(accessibility = "login-btn")
    public WebElement logInButton;

    @AndroidFindBy(accessibility = "filter-btn")
    @iOSXCUITFindBy(accessibility = "filter-btn")
    public WebElement filterButton;

    @AndroidFindBy(xpath = "//*[@content-desc=\"Samsung\"]")
    @iOSXCUITFindBy(xpath = "//*[@name='Samsung']")
    public WebElement filterProduct;

    @AndroidFindBy(xpath = "//*[@content-desc=\"High to Low\"]")
    @iOSXCUITFindBy(xpath = "//*[@name=\"High to Low\"]")
    public WebElement filterProductPrice;

    @iOSXCUITFindBy(accessibility = "nav-cart")
    @AndroidFindBy(xpath = "//*[@content-desc=\"nav-cart\"]")
    public MobileElement cartClick;

    @iOSXCUITFindBy(accessibility = "checkout-btn")
    @AndroidFindBy(accessibility = "checkout-btn")
    private MobileElement checkoutBtn;

    @AndroidFindBy(accessibility = "firstNameInput")
    @iOSXCUITFindBy(accessibility = "firstNameInput")
    private WebElement firstnameInput;

    @AndroidFindBy(accessibility = "lastNameInput")
    @iOSXCUITFindBy(accessibility = "lastNameInput")
    private WebElement lastnameInput;

    @AndroidFindBy(accessibility = "addressInput")
    @iOSXCUITFindBy(accessibility = "addressInput")
    private WebElement addressInput;

    @AndroidFindBy(accessibility = "stateInput")
    @iOSXCUITFindBy(accessibility = "stateInput")
    private WebElement stateInput;

    @AndroidFindBy(accessibility = "postalCodeInput")
    @iOSXCUITFindBy(accessibility = "postalCodeInput")
    private WebElement postcodeInput;

    @AndroidFindBy(accessibility = "submit-btn")
    @iOSXCUITFindBy(accessibility = "submit-btn")
    private WebElement checkoutButton;

    @AndroidFindBy(accessibility = "confirmation-message")
    @iOSXCUITFindBy(accessibility = "confirmation-message")
    private WebElement confirmationMsg;
    @AndroidFindBy(accessibility = "continue-btn")
    @iOSXCUITFindBy(accessibility = "continue-btn")
    private WebElement continueShoppingButton;

    public void loginWith(String username, String password) throws InterruptedException {
        usernameInput.click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Accepted usernames are']", "fav_user");
       // driver.findElement(MobileBy.xpath("//*[@text = '" + username + "']")).click();
        Thread.sleep(2000);
        passwordInput.click();
       // driver.findElement(MobileBy.xpath("//*[@text = '" + password + "']")).click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Password for all users']", "testingisfun99");
        logInButton.click();

        Thread.sleep(3000);
    }

    public void filterSearch() throws InterruptedException {
        filterButton.click();
        filterProduct.click();
        filterProductPrice.click();
        Thread.sleep(3000);
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(PointOption.point(100, 100)).perform();
    }

    public void addProductToCart(String productId) throws InterruptedException {
        driver.findElement(MobileBy.AccessibilityId("add-to-cart-" + productId)).click();
        Thread.sleep(3000);
    }

    public void cartClick() throws InterruptedException {
        cartClick.click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(MobileBy.AccessibilityId("number-of-products")).getText(), "1 product(s) found.");
    }

    public void checkoutShipping(String firstname, String lastname, String address, String state, String s) throws InterruptedException {
        checkoutBtn.click();
        firstnameInput.sendKeys(firstname);
        lastnameInput.sendKeys(lastname);
        addressInput.sendKeys(address);
        stateInput.sendKeys(state);
        postcodeInput.sendKeys(s);
        if(mobileHelper.isAndroid()) {
            driver.hideKeyboard();
        }
        if(mobileHelper.isiOS())
        {
            driver.getKeyboard().sendKeys(Keys.RETURN);
        }
        Thread.sleep(2000);
        MobileElement el22 = (MobileElement) driver.findElementByAccessibilityId("submit-btn");
        el22.click();
      //  checkoutButton.click();
    }

    public int platform()
    {
        int flag = 0;
        if(mobileHelper.isAndroid()) {
           flag=1;
        }
        return flag;
    }
    public void confirmation() throws InterruptedException {

        Thread.sleep(2000);
        Assert.assertEquals(confirmationMsg.getText(), "Your Order has been successfully placed.");
        continueShoppingButton.click();
    }

    public void localSetting(String accessKey) throws Exception {
        HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
        bsLocalArgs.put("key", accessKey);

        // Starts the Local instance with the required arguments
        bsLocal.start(bsLocalArgs);

        System.out.println(bsLocal.isRunning());
        Thread.sleep(2000);
        driver.findElement(MobileBy.AccessibilityId("menu")).click();
        Thread.sleep(2000);
        driver.findElement(MobileBy.xpath("//*[@text = 'Settings']")).click();

        Thread.sleep(2000);
        driver.findElement(MobileBy.AccessibilityId("url-tab")).click();
        Thread.sleep(2000);
        driver.findElement(MobileBy.AccessibilityId("url-input")).clear();
        driver.findElement(MobileBy.AccessibilityId("url-input")).sendKeys("http://bs-local.com:3000/api/");
        // driver.hideKeyboard();
        driver.findElement(MobileBy.AccessibilityId("update-configuration-button")).click();
        Thread.sleep(2000);
        driver.findElement(MobileBy.AccessibilityId("menu")).click();

        Thread.sleep(2000);
        driver.findElement(MobileBy.xpath("//*[@text = 'Home']")).click();
        Thread.sleep(2000);
        if(driver.findElement(MobileBy.AccessibilityId("products-found")).isDisplayed())
        {
            System.out.println("home screen content are loaded fine");
        }
        else
            System.out.println("home screen content are not loaded fine");


    }

    public void switchBrowser() throws InterruptedException {
       // AndroidDriver driver1 = (AndroidDriver)driver;
        try {

            ((AndroidDriver<MobileElement>)driver).startActivity(new Activity("com.android.chrome", "com.google.android.apps.chrome.Main"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        System.out.println("Switched to chrome browser and search BrowserStack");
        driver.findElement(By.xpath("//*[@class='android.widget.EditText']")).sendKeys("BrowserStack");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@class='android.widget.FrameLayout']")).click();
        System.out.println("Clicked to first search BrowserStack result");
        System.out.println("switching back to App");

        Thread.sleep(3000);
        try {

            Activity androidActivity=new Activity("com.browserstack.demo.app", "host.exp.exponent.LauncherActivity");
            ((AndroidDriver<MobileElement>)driver).startActivity(androidActivity);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        Thread.sleep(3000);


    }
}
