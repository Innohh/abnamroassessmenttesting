package Objects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * @author MBRAAM
 *
 *  This class will store all the locators of the main page
 */

public class MainPage {

    public MainPage (AndroidDriver<WebElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id ="com.abnamro.apps.referenceandroid:id/fab")
    public AndroidElement mailButton;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    public AndroidElement extraOptions;

    @AndroidFindBy(id ="com.abnamro.apps.referenceandroid:id/title")
    public AndroidElement settingsButton;

    @AndroidFindBy(id ="com.abnamro.apps.referenceandroid:id/snackbar_text")
    public AndroidElement textBox;

    public void clickMailButton() {
        mailButton.click();
    }

    public void clickExtraOptionsButton() {
        extraOptions.click();
    }

    public void clickSettingsButton() {
        settingsButton.click();
    }

    public AndroidElement changeButtonType (String buttonType){
        AndroidElement button;
        if (buttonType.toLowerCase().contains("mail")) {
            button = mailButton;
        } else if (buttonType.toLowerCase().contains("extra")) {
            button = extraOptions;
        } else {
            button = settingsButton;
        }
        return button;
    }

}
