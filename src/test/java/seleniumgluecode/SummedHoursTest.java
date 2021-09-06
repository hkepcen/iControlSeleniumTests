package seleniumgluecode;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class SummedHoursTest {

    WebDriver driver;
    String homepage = "http://localhost:4200/login";
    String filter;
    double calculatedHours;

    public void login() {

        driver = utils.DriverFactory.open("chrome");
        driver.get(homepage);
        driver.manage().window().maximize();

        WebElement usernameTextbox = driver.findElement(By.id("username"));
        usernameTextbox.sendKeys("estell.klein");

        WebElement passwordTextbox = driver.findElement(By.id("password"));
        passwordTextbox.sendKeys("estell.klein123" + Keys.ENTER);
    }

    public void enterFilter() {

        ArrayList<String> userstoryNames = new ArrayList<>();
        WebElement nameFilterBox = driver.findElement(By.xpath("/html/body/app-root/app-userstory-list/p-table/div/div[2]/table/thead/tr[2]/th[1]/input"));
        Random randomItem = new Random();

        for (int i = 1; i <= 50; i++) {
            userstoryNames.add(driver.findElement(By.xpath("/html/body/app-root/app-userstory-list/p-table/div/div[2]/table/tbody/tr["+(i)+"]/td[1]/a")).getText());
        }

        int index = randomItem.nextInt(userstoryNames.size());
        filter = userstoryNames.get(index).substring(0,4);

        nameFilterBox.sendKeys(filter + Keys.ENTER);
    }

    @Given("^user sees the userstories$")
    public void user_sees_the_userstories() throws InterruptedException {

        login();
        Thread.sleep(2000);

        enterFilter();
        Thread.sleep(2000);

        for (int i = 1; i <= 5; i++) {
            WebElement tabledata = driver.findElement(By.xpath("/html/body/app-root/app-userstory-list/p-table/div/div[2]/table/tbody/tr["+i+"]/td[1]"));
            assertThat(tabledata.getText(), containsString(filter));
        }
    }

    @When("^all hours were summed up$")
    public void all_hours_were_summed_up() {

        WebElement lastPageButton = driver.findElement(By.xpath("/html/body/app-root/app-userstory-list/p-table/div/p-paginator/div/button[4]"));
        lastPageButton.click();

        WebElement lastPageNumber = driver.findElement(By.xpath("/html/body/app-root/app-userstory-list/p-table/div/p-paginator/div/span/button[5]"));
        int lastPage = Integer.parseInt(lastPageNumber.getText());

        WebElement firstPageButton = driver.findElement(By.xpath("/html/body/app-root/app-userstory-list/p-table/div/p-paginator/div/button[1]"));
        firstPageButton.click();

        WebElement nextPageButton = driver.findElement(By.xpath("/html/body/app-root/app-userstory-list/p-table/div/p-paginator/div/button[3]"));

        for (int i = 1; i <= lastPage; i++) {

            for (int j = 1; j <=50; j++) {

                try {
                    String tabledataHours = driver.findElement(By.xpath("/html/body/app-root/app-userstory-list/p-table/div/div[2]/table/tbody/tr["+j+"]/td[3]")).getText();
                    String tabledataHoursConverted = tabledataHours.replace(",", ".");
                    double userstoryHours = Double.parseDouble(tabledataHoursConverted);

                    if (userstoryHours == 0.0) {
                        continue;
                    }

                    calculatedHours += userstoryHours;

                } catch (NoSuchElementException e) {
                    continue;
                }
            }
            nextPageButton.click();
        }
    }

    @Then("^the summed up hours should be correct$")
    public void the_summed_up_hours_should_be_correct() {

        WebElement summedUpHoursField = driver.findElement(By.xpath("/html/body/app-root/app-userstory-list/p-table/div/div[2]/table/thead/tr[2]/th[3]"));
        double summedUpHours = Double.parseDouble(summedUpHoursField.getText());

        Assert.assertEquals((int) summedUpHours, (int) calculatedHours);

        System.out.println("------------------------------------");
        System.out.println("filter: " + filter);
        System.out.println("summed up hours: " + summedUpHours);
        System.out.println("calculated hours: " + calculatedHours);
        System.out.println("------------------------------------");

    }

}
