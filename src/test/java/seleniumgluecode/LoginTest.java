package seleniumgluecode;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {

    WebDriver driver;

    @Given("^user is on loginpage$")
    public void user_is_on_loginpage() {
        driver = utils.DriverFactory.open("chrome");
        driver.manage().window().maximize();
        driver.get("http://www.google.de");
        driver.close();

    }

//    @When("^user enters correct username and password$")
//    public void user_enters_correct_username_and_password() {
//        WebElement username = driver.findElement(By.id("username"));
//        username.clear();
//        username.sendKeys("estell.klein");
//
//        WebElement password = driver.findElement(By.id("password"));
//        password.clear();
//        password.sendKeys("estell.klein123");
//    }
//
//    @And("^clicks on login$")
//    public void clicks_on_login() {
//        WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/app-login/div/div/div/form/button"));
//        loginButton.click();
//    }
//
//    @Then("^user should see userstories$")
//    public void user_should_see_userstories() {
//
//        String userstoriesPage;
//
//        WebDriverWait userstories = new WebDriverWait(driver, 3);
//        Boolean userstoriesLoaded = userstories.until(ExpectedConditions.urlContains("userstorys"));
//
//        if (userstoriesLoaded) {
//            userstoriesPage = driver.getCurrentUrl();
//            Assert.assertEquals(userstoriesPage, "http://localhost:4200/userstorys");
//        }
//
//        driver.close();
//    }

}
