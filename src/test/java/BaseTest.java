import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static AppiumDriver<MobileElement> appiumDriver;
    protected static FluentWait<AppiumDriver<MobileElement>> appiumFluentWait;
    protected boolean localAndroid = true;
    @BeforeScenario
    public void beforeScenario() throws MalformedURLException {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!Test basliyor!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (StringUtils.isEmpty(System.getenv("key"))) {
            if (localAndroid) {
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities
                        .setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);

                desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
                desiredCapabilities
                        .setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
                                "com.turkishairlines.mobile");
                desiredCapabilities
                        .setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
                                "com.turkishairlines.mobile.ui.ACSplash");
                desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
                desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3000);
                URL url = new URL("http://127.0.0.1:4723/wd/hub");
                appiumDriver = new AndroidDriver(url, desiredCapabilities);
            }
        }
        appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        appiumFluentWait = new FluentWait<AppiumDriver<MobileElement>>(appiumDriver);
        appiumFluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(450))
                .ignoring(NoSuchElementException.class);

    }

    @AfterScenario
    public void afterScenario() {
        if(appiumDriver != null)
            appiumDriver.quit();
    }
}

