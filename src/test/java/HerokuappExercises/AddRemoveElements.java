package HerokuappExercises;

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

public class AddRemoveElements {
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
    @AfterMethod
    public void browserClosing() {
        if (this.driver != null) {
            this.driver.quit();
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

