package automationTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TestAutomationExercise {

    private WebDriver driver;

    @BeforeSuite
    public void setUpSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUpTest() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void testElements(){
        this.driver.get("http://the-internet.herokuapp.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement addRemoveElementLink = driver.findElement(By.linkText("Add/Remove Elements"));
        addRemoveElementLink.click();
        wait.until(ExpectedConditions.urlToBe("http://the-internet.herokuapp.com/add_remove_elements/"));

        WebElement addRemoveElementsTitle = driver.findElement(By.xpath("//*[@id='content']/h3"));
        wait.until(ExpectedConditions.visibilityOf(addRemoveElementsTitle));

        WebElement buttonAddElement = driver.findElement(By.cssSelector("#content > div > button"));
        buttonAddElement.click();

        WebElement deleteButton = driver.findElement(By.cssSelector("#elements > button"));
        Assert.assertTrue(deleteButton.isDisplayed());

        deleteButton.click();

        Assert.assertFalse(isElementPresent(By.cssSelector("#elements > button")));

    }
    @Test
        public void loginAuth(){

        this.driver.get("http://the-internet.herokuapp.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement addRemoveElementLink = driver.findElement(By.linkText("Basic Auth"));
        addRemoveElementLink.click();

        wait.until(ExpectedConditions.urlToBe("http://the-internet.herokuapp.com/basic_auth"));




        WebElement basicAuthLink = driver.findElement(By.cssSelector("#content > div > h3"));
        wait.until(ExpectedConditions.visibilityOf(basicAuthLink));

        WebElement text = driver.findElement(By.cssSelector("#content > div > p"));
        Assert.assertEquals(text.getText(), "Congratulations! You must have the proper credentials.");
    }
    @Test
    public void tableTesting() {
        driver.get("http://the-internet.herokuapp.com/challenging_dom");

        WebElement table = driver.findElement(By.tagName("tbody"));
        List<WebElement> columnHeaders = table.findElements(By.xpath("//*[@id=\"content\"]/div/div/div/div[2]/table/thead/tr/th"));
        List<WebElement> tableRows = table.findElements(By.tagName("tr"));

        System.out.println("Table headers :");
        for (int header = 0; header < columnHeaders.size(); header++) {
                String title = columnHeaders.get(header).getText();
                System.out.print(title + " ");
            }
        System.out.println();

        for (int row = 0; row < tableRows.size(); row++) {
            List<WebElement> columnRows = tableRows.get(row).findElements(By.tagName("td"));
            System.out.println("Row number: " + row);
            for (int column = 0; column < columnRows.size(); column++) {
                String cellText = columnRows.get(column).getText();
                System.out.print(cellText + " ");
            }
        System.out.println();
        }
    }
    @AfterMethod
    public void browserClosing() {
        if (this.driver != null) {
            this.driver.close();
        }
    }
    public boolean isElementPresent(By locatorKey) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
