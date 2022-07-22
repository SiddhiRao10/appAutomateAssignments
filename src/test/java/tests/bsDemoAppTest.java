package tests;

import commonClass.BasePO;
import commonClass.BaseTest;
import org.testng.annotations.Test;

public class bsDemoAppTest extends BaseTest {

    @Test
    public void demoSiteTest() throws Exception
    {
        BasePO page = new BasePO(driver);

        Thread.sleep(2000);
        page.menuLink.click();
        page.signInLink.click();
        page.loginWith("fav_user", "testingisfun99");
        page.filterSearch();
        page.addProductToCart("12");
        page.cartClick();
        page.checkoutShipping("firstname", "lastname", "address", "state", "12345");
        page.confirmation();
        int i=page.platform();
        if(i==1) {
            page.localSetting(accessKey);
            page.switchBrowser();
        }
    }

}



