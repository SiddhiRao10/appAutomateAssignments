package commonClass;
import com.browserstack.local.Local;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.MobileHelper;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static javax.swing.UIManager.put;

public class BaseTest {

    static Local bsLocal = new Local();
    DesiredCapabilities capabilities = new DesiredCapabilities();
    String username = System.getenv("BROWSERSTACK_USERNAME");
    public String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");
    private static final String BROWSERSTACK_HUB_URL = "hub-cloud.browserstack.com";
    public MobileHelper mobileHelper;

    public  AppiumDriver<MobileElement> driver;
    @BeforeMethod(alwaysRun = true)
    @Parameters(value = {"config", "environment"})
    public void setUp(String config_file, String environment) throws Exception {
        File name = new File("src/test/resources/" + config_file);
        if (name.toString().equals("src/test/resources/demo.conf.json")) {
            JSONParser parser = new JSONParser();
            JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/" + config_file));
            JSONObject envs = (JSONObject) config.get("environments");
            Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
            Iterator it = commonCapabilities.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if (capabilities.getCapability(pair.getKey().toString()) == null) {
                    capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
                    capabilities.setCapability("build", buildName);
                   capabilities.setCapability("app", "bs://6c5f944cad2252a75f41fc2a751ce102e8500b9d");
               //     capabilities.setCapability("browserstack.local", "true");
                 //   capabilities.setCapability("browserstack.appium_version", "1.18.0");
                    capabilities.setCapability("browserstack.networkLogs", "true");
                    capabilities.setCapability("browserstack.user",username);
                    capabilities.setCapability("browserstack.key",accessKey);
                    capabilities.setCapability("browserstack.appStoreConfiguration", new HashMap<String, String>(){{put("username", "sidtest555@gmail.com");put("password", "testing@89");}});

                }
            }
            if (username == null) {
                username = (String) config.get("user");
            }
            if (accessKey == null) {
                accessKey = (String) config.get("key");
            }
           // HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
           // bsLocalArgs.put("key", accessKey);

            // Starts the Local instance with the required arguments
         //   bsLocal.start(bsLocalArgs);

         //   System.out.println(bsLocal.isRunning());
           // driver.set(new AppiumDriver(new URL("https://" + username + ":" + accessKey + "@" + BROWSERSTACK_HUB_URL + "/wd/hub"), capabilities));
            driver = new AndroidDriver<MobileElement>(new URL("https://" + username + ":" + accessKey + "@" + BROWSERSTACK_HUB_URL + "/wd/hub"), capabilities);

        }
        else  if (name.toString().equals("src/test/resources/demo.android.json")) {
            JSONParser parser = new JSONParser();
            JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/" + config_file));

            JSONObject envs = (JSONObject) config.get("environments");
            Map<String, String> envCapabilities = (Map<String, String>) envs.get(environment);
            Iterator it = envCapabilities.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }


            Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
             it = commonCapabilities.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if (capabilities.getCapability(pair.getKey().toString()) == null) {
                    capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
                    capabilities.setCapability("build", buildName);
                    capabilities.setCapability("app", "BSV1");
                    capabilities.setCapability("browserstack.local", "true");
                    capabilities.setCapability("browserstack.appium_version", "1.18.0");
                    capabilities.setCapability("browserstack.networkLogs", "true");
                    capabilities.setCapability("browserstack.user",username);
                    capabilities.setCapability("browserstack.key",accessKey);
                    capabilities.setCapability("browserstack.networkProfile", "4g-lte-good");
                 //   capabilities.setCapability("deviceOrientation", "landscape");
                    capabilities.setCapability("browserstack.geoLocation", "IN");
                }
            }

            driver = new AndroidDriver<MobileElement>(new URL("https://" + username + ":" + accessKey + "@" + BROWSERSTACK_HUB_URL + "/wd/hub"), capabilities);

        }

        else if (name.toString().equals("src/test/resources/demo.ios.json")) {
            JSONParser parser = new JSONParser();
            JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/" + config_file));
            JSONObject envs = (JSONObject) config.get("environments");
            Map<String, String> envCapabilities = (Map<String, String>) envs.get(environment);
            Iterator it = envCapabilities.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }

            Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
             it = commonCapabilities.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if (capabilities.getCapability(pair.getKey().toString()) == null) {
                    capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
                    capabilities.setCapability("build", buildName);
                    capabilities.setCapability("app", "bs://51249f3ebc5f3a23de71908442d423f50417aa58");
                    capabilities.setCapability("browserstack.appium_version", "1.16.0");
                    capabilities.setCapability("browserstack.networkLogs", "true");
                    capabilities.setCapability("browserstack.user",username);
                    capabilities.setCapability("browserstack.key",accessKey);
                    capabilities.setCapability("browserstack.networkProfile", "3g-umts-good");
                    capabilities.setCapability("browserstack.geoLocation", "IN");
                }
            }

            driver = new IOSDriver<MobileElement>(new URL("https://" + username + ":" + accessKey + "@" + BROWSERSTACK_HUB_URL + "/wd/hub"), capabilities);

        }
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) throws Exception {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        if (result.getStatus() == ITestResult.SUCCESS) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Test Passed\"}}");
        }
        else
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Test Failed\"}}");

        driver.quit();
    }
}
