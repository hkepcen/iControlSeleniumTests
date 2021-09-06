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

import java.util.ArrayList;
import java.util.Random;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

public class NameFilterTest {

    WebDriver driver;
    String homepage = "http://localhost:4200/login";
    String destinationPage = "http://localhost:4200/userstorys";
    WebElement nameFilterBox;
    String filter;

    @Given("^user is on the userstories page$")
    public void user_is_on_the_userstories_page() {
        driver = utils.DriverFactory.open("chrome");
        driver.manage().window().maximize();
        driver.get(homepage);

        WebElement username = driver.findElement(By.id("username"));
        username.clear();
        username.sendKeys("estell.klein");

        WebElement password = driver.findElement(By.id("password"));
        password.clear();
        password.sendKeys("estell.klein123" + Keys.ENTER);

        WebDriverWait loadingUserstories = new WebDriverWait(driver, 10);
        Boolean userstoriesLoaded = loadingUserstories.until(ExpectedConditions.urlContains("userstorys"));

        if (userstoriesLoaded) {
            Assert.assertEquals(driver.getCurrentUrl(), destinationPage);
        }
    }

    @When("^user clicks on name filter$")
    public void user_clicks_on_name_filter() {

        nameFilterBox = driver.findElement(By.xpath("/html/body/app-root/app-userstory-list/p-table/div/div[2]/table/thead/tr[2]/th[1]/input"));

        WebDriverWait loadingUserstories = new WebDriverWait(driver, 10);
        loadingUserstories.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("/html/body/app-root/app-userstory-list/p-table/div/div[2]/table/tbody"))));

        nameFilterBox.click();
    }

    @And("^enters a substring$")
    public void enters_a_substring() {
        ArrayList<String> userstoryNames = new ArrayList<>();
        nameFilterBox = driver.findElement(By.xpath("/html/body/app-root/app-userstory-list/p-table/div/div[2]/table/thead/tr[2]/th[1]/input"));
        Random randomItem = new Random();

        for (int i = 1; i <= 50; i++) {
            userstoryNames.add(driver.findElement(By.xpath("/html/body/app-root/app-userstory-list/p-table/div/div[2]/table/tbody/tr["+(i)+"]/td[1]/a")).getText());
        }

        int index = randomItem.nextInt(userstoryNames.size());
        filter = userstoryNames.get(index).substring(0,4);

        nameFilterBox.sendKeys(filter);

    }

    @Then("^matching userstories should be shown$")
    public void matching_userstories_should_be_shown() throws InterruptedException {
        Thread.sleep(4000);

        ArrayList<String> filterResults = new ArrayList<>();

        for (int i = 1; i<=30; i++) {
            filterResults.add(driver.findElement(By.xpath("/html/body/app-root/app-userstory-list/p-table/div/div[2]/table/tbody/tr["+i+"]/td[1]")).getText());
        }

        for (String s : filterResults) {
            assertThat(s, containsString(filter));
        }

        driver.close();
    }


}
