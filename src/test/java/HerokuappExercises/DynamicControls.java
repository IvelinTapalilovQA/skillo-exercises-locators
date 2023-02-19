package HerokuappExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class DynamicControls {

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
    public void dynamicControls() {
            driver.get("http://the-internet.herokuapp.com/dynamic_controls");

        WebElement checkBoxLabel = driver.findElement(By.id("checkbox"));
        Assert.assertTrue(checkBoxLabel.isDisplayed());

        WebElement checkBoxInput = driver.findElement(By.xpath("//input[@type='checkbox']"));
        checkBoxInput.click();
        Assert.assertTrue(checkBoxInput.isSelected());

        WebElement removeButton = driver.findElement(By.xpath("//button[text()='Remove']"));
        removeButton.click();

        WebElement message = driver.findElement(By.id("message"));

        Assert.assertEquals(message.getText(), "It's gone!");

        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add']"));
        addButton.click();

        message = driver.findElement(By.id("message"));
        Assert.assertEquals(message.getText(), "It's back!");

        checkBoxInput = driver.findElement(By.xpath("//input[@type='checkbox']"));
        checkBoxInput.click();
        Assert.assertTrue(checkBoxInput.isSelected());

        removeButton.click();
        message = driver.findElement(By.id("message"));
        Assert.assertEquals(message.getText(), "It's gone!");


    }
    @Test
    public void Enable() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");

        WebElement textInput = driver.findElement(By.xpath("//input[@type='text']"));
        Assert.assertFalse(textInput.isEnabled());

        WebElement enableButton = driver.findElement(By.xpath("//button[text()='Enable']"));
        enableButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(textInput));
        Assert.assertTrue(textInput.isEnabled());

        WebElement message = driver.findElement(By.xpath("//*[@id='message']"));
        Assert.assertEquals(message.getText(), "It's enabled!");

        textInput.sendKeys("test");
        Assert.assertEquals(textInput.getAttribute("value"), "test");

        WebElement disableButton = driver.findElement(By.xpath("//button[text()='Disable']"));
        disableButton.click();

        textInput = driver.findElement(By.xpath("//input[@type='text']"));
        Thread.sleep(3000);
        Assert.assertFalse(textInput.isEnabled());

        message = driver.findElement(By.xpath("//*[@id='message']"));
        Assert.assertEquals(message.getText(), "It's disabled!");
    }
    @AfterMethod
    public void browserClosing() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}

