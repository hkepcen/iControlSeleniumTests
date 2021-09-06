package seleniumgluecode;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AccountFactory;

public class RegisterNewAccountTest {

    WebDriver driver;
    String landingpage = "http://localhost:4200/login";
    AccountFactory accountFactory = new AccountFactory();

    @Given("^user is on landingpage$")
    public void user_is_on_landingpange() {
        driver = utils.DriverFactory.open("chrome");
        driver.manage().window().maximize();
        driver.get(landingpage);
    }

    @When("^user clicks on register$")
    public void user_clicks_on_register() {
        WebElement registerLink = driver.findElement(By.linkText("REGISTER"));
        registerLink.click();
    }

    @And("^enters required information$")
    public void enters_required_information() {
        WebDriverWait loadRegisterPage = new WebDriverWait(driver, 3);
        Boolean registerPageLoaded = loadRegisterPage.until(ExpectedConditions.urlContains("users/add"));

        if (registerPageLoaded) {
            WebElement username = driver.findElement(By.name("username"));
            username.sendKeys(accountFactory.getUsername());

            WebElement firstname = driver.findElement(By.name("firstname"));
            firstname.sendKeys(accountFactory.getFirstname());

            WebElement lastname = driver.findElement(By.name("lastname"));
            lastname.sendKeys(accountFactory.getLastname());

            WebElement email = driver.findElement(By.name("email"));
            email.sendKeys(accountFactory.getEmail());

            WebElement password = driver.findElement(By.name("password"));
            password.sendKeys(accountFactory.getPassword());

            WebElement confirmPassword = driver.findElement(By.xpath("//*[@id=\"mat-input-5\"]"));
            confirmPassword.sendKeys(accountFactory.getPassword());

            WebElement confirmButton = driver.findElement(By.xpath("/html/body/app-root/app-add/mat-card/button"));
            confirmButton.click();

            System.out.println("username: " + accountFactory.getUsername());
            System.out.println("password: " + accountFactory.getPassword());
        }

    }


    @Then("^user should be on the landingpage again$")
    public void user_should_be_on_the_landingpage_again() {

        WebDriverWait returnToLandingpage = new WebDriverWait(driver, 15);
        Boolean landingpageLoaded = returnToLandingpage.until(ExpectedConditions.urlToBe(landingpage));

        if (landingpageLoaded) {
            String currentUrl = driver.getCurrentUrl();

            Assert.assertEquals(currentUrl, landingpage);
        }

        driver.close();
    }


}
