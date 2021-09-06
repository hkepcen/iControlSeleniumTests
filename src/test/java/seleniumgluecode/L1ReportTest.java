package seleniumgluecode;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class L1ReportTest {

    WebDriver driver;
    String startingPage = "http://127.0.0.1:4200/login";

    @Given("^user is logged in$")
    public void user_is_logged_in() {

        driver = utils.DriverFactory.open("chrome");
        driver.manage().window().maximize();
        driver.get(startingPage);

        WebElement username = driver.findElement(By.id("username"));
        username.clear();
        username.sendKeys("estell.klein");

        WebElement password = driver.findElement(By.id("password"));
        password.clear();
        password.sendKeys("estell.klein123" + Keys.ENTER);

        WebDriverWait loginDuration = new WebDriverWait(driver, 5);
        loginDuration.until(ExpectedConditions.urlContains("userstorys"));

        Assert.assertEquals(driver.getCurrentUrl(), "http://127.0.0.1:4200/userstorys");

    }


    @When("^user clicks on menu$")
    public void user_clicks_on_menu() {

        WebElement menuButton = driver.findElement(By.xpath("/html/body/app-root/mat-toolbar/mat-toolbar-row/span[1]/button"));
        menuButton.click();

    }


    @And("^selects l1 report$")
    public void selects_l1_report() {

        WebElement l1Report = driver.findElement(By.linkText("L1 Report"));
        l1Report.click();

    }

    @Then("^user should see the l1 report$")
    public void user_should_see_the_l1_report() {

        WebDriverWait loadL1Report = new WebDriverWait(driver, 10);
        Boolean l1Reportloaded = loadL1Report.until(ExpectedConditions.urlContains("nbl"));

        if (l1Reportloaded) {
            Assert.assertEquals(driver.getCurrentUrl(), "http://127.0.0.1:4200/reports/nbl");
        }

        driver.close();

    }



}
