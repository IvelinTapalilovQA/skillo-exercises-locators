package HerokuappExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class Checkboxes {
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
    public void checkboxes(){
        driver.get("http://the-internet.herokuapp.com/checkboxes");

        WebElement title = driver.findElement(By.xpath("//*[@id='content']/div/h3"));
        Assert.assertEquals(title.getText(), "Checkboxes");

        WebElement inputCheckboxOne = driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]"));
        inputCheckboxOne.click();
        Assert.assertTrue(inputCheckboxOne.isSelected());
        inputCheckboxOne.click();
        Assert.assertFalse(inputCheckboxOne.isSelected());

        WebElement inputCheckboxTwo = driver.findElement(By.xpath("//*[@id='checkboxes']/input[2]"));
        Assert.assertTrue(inputCheckboxTwo.isSelected());
        inputCheckboxTwo.click();
        Assert.assertFalse(inputCheckboxTwo.isSelected());
        inputCheckboxTwo.click();
        Assert.assertTrue(inputCheckboxTwo.isSelected());
    }

    @AfterMethod
    public void browserClosing() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
