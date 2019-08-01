package Stepdefs;

import Driver.SetupDriver;
import Objects.MainPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.net.MalformedURLException;

/**
 * @author MBRAAM
 *
 *  In this class all the steps are defined.
 */

public class MyStepdefs {

    AndroidDriver driver = SetupDriver.setUp();
    MainPage main = new MainPage(driver);

    public MyStepdefs() throws MalformedURLException {
    }

    @Given("the \"([^\"]*)\" application is installed on the device")
    public void theApplicationIsActive(String application) {
        /**
         * First we will have to change the value of the String to the correct app package.
         */
        if (application.equalsIgnoreCase("referenceandroid")) {
            application = "com.abnamro.apps.referenceandroid";
        } else {
            /**
             * This is empty but can be filled by multiple different app packages
             */
        }
        /**
         * Here we will verify if the app package is currently installed on the device.
         */
        Assert.assertTrue(driver.isAppInstalled(application), "The App: " + application + " is currently not installed on this device");
    }

    @When("the current activity of the app is \"([^\"]*)\"")
    public void theCurrentActivityOfTheAppIs(String activity) {
        /**
         * Checking the activity which is currently active and verify this.
         */
        if (activity.toLowerCase().contains("main")) {
            activity = ".MainActivity";
        } else { }
        String currentActivity = driver.currentActivity();
        Assert.assertEquals(activity, currentActivity);
    }

    @Then("the text \"([^\"]*)\" should be visible")
    public void theTextShouldBeVisible(String text) {
        /**
         * Checking if a specific String of text is visible.
         */
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(text));
    }

    @When("I change the screen orientation to \"([^\"]*)\"")
    public void iChangeTheScreenOrientationTo(String orientationType) {
        /**
         * With this method I change the screen orientation to landscape or portrait and verify if this is done correctly.
         */
        if (orientationType.equalsIgnoreCase("portrait")){
            driver.rotate(ScreenOrientation.PORTRAIT);
            ScreenOrientation orientation = driver.getOrientation();
            Assert.assertEquals(orientationType.toLowerCase(), orientation.toString().toLowerCase());
        } else {
            driver.rotate(ScreenOrientation.LANDSCAPE);
            ScreenOrientation orientation = driver.getOrientation();
            Assert.assertEquals(orientationType.toLowerCase(), orientation.toString().toLowerCase());
        }
    }

    @Then("the \"([^\"]*)\" button is visible")
    public void theButtonIsVisible(String buttonType) {
        /**
         * With this method I verify if a specific button is visible.
         * The Element will be filled in a different method which can be found in MainPage.java.
         */
        AndroidElement button = main.changeButtonType(buttonType);
        Assert.assertTrue(button.isDisplayed(), "The button: "+ button +" is currently not visible");
        }

    @Then("the \"([^\"]*)\" button is NOT visible")
    public void theButtonIsNOTVisible(String buttonType) {
        /**
         * For this method I can reuse the changeButtonType method from the step above.
         * I verify if the button is displayed or not.
         */
        AndroidElement button = main.changeButtonType(buttonType);
        try {
            Assert.assertFalse(button.isDisplayed());
        } catch (NoSuchElementException nsee) {
            System.out.println("The button: "+ button +" is currently NOT visible");
        }
    }

    @And("I click the \"([^\"]*)\" button")
    public void iClickTheButton(String buttonType) {
        /**
         * For this method I can reuse the changeButtonType method from the step above.
         * I click the button.
         */
        AndroidElement button = main.changeButtonType(buttonType);
        button.click();
    }

    @Then("a text box is visible")
    public void aTextBoxIsVisible() {
        /**
         * For this method I check if the text box specified in the MainPage.java class is displayed.
         */
        Assert.assertTrue(main.textBox.isDisplayed());;
    }

    @Then("I see this text box contains the following text: \"([^\"]*)\"")
    public void iSeeThisTextBoxContainsTheFollowingText(String text) {
        /**
         * For this method I check if the text displayed in the
         * text box specified in the MainPage.java class equals the text specified as a variable in the step.
         */
        String textFromApp = main.textBox.getText();
        Assert.assertEquals(text, textFromApp);
    }
}
