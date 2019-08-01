package Driver;

import cucumber.api.java.After;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author MBRAAM
 *
 *  This class is created to create the driver.
 */

public class SetupDriver {

    static AndroidDriver driver;

    public static AndroidDriver setUp() throws MalformedURLException {
        // Created object of DesiredCapabilities class.
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set android deviceName desired capability. Set your device name.
        capabilities.setCapability("deviceName", "emulator-5554");

        // Set android platformName desired capability. It's Android in our case here.
        capabilities.setCapability("platformName", "Android");

        // Set android appPackage desired capability. This can be found
        // by using the following line in command prompt:
        // adb shell "dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'"
        capabilities.setCapability("appPackage", "com.abnamro.apps.referenceandroid");

        // Set android appActivity desired capability. This can be found
        // by using the following line in command prompt:
        // adb shell "dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'"
        capabilities.setCapability("appActivity", "com.abnamro.apps.referenceandroid.MainActivity");

        // Created object of RemoteWebDriver will all set capabilities.
        // Set appium server address and port number in URL string.
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;

    }

    @After
    public void End() {
        driver.quit();
    }
}
