package HerokuappExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class DownMenu {
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
    public void dropMenu(){
        driver.get("http://the-internet.herokuapp.com/dropdown");

        Select menu = new Select(driver.findElement(By.xpath("//*[@id='dropdown']")));
        menu.selectByVisibleText("Option 1");
        WebElement optionOne = driver.findElement(By.xpath("//*[@id='dropdown']/option[2]"));
        Assert.assertTrue(optionOne.isSelected());

        menu.selectByVisibleText("Option 2");
        WebElement optionTwo = driver.findElement(By.xpath("//*[@id='dropdown']/option[3]"));
        Assert.assertTrue(optionTwo.isSelected());
    }
    @AfterMethod
    public void browserClosing() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}