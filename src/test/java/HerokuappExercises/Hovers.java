package HerokuappExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class Hovers {
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
    public void hovers() {
        driver.get("http://the-internet.herokuapp.com/hovers");

        Actions actions = new Actions(driver);

        WebElement userOne = driver.findElement(By.xpath("//*[@id='content']/div/div[1]"));
        actions.moveToElement(userOne).perform();

        WebElement userOneText = driver.findElement(By.xpath("(//div/h5)[1]"));
        Assert.assertEquals(userOneText.getText(), "name: user1");

        WebElement userOneProfile = driver.findElement(By.xpath("(//a[text()='View profile'])[1]"));
        Assert.assertTrue(userOneProfile.isDisplayed());
        userOneProfile.click();

        WebElement title = driver.findElement(By.xpath("//h1"));
        Assert.assertEquals(title.getText(), "Not Found");

        driver.navigate().back();

        WebElement userTwo = driver.findElement(By.xpath("//*[@id='content']/div/div[2]"));
        actions.moveToElement(userTwo).perform();

        WebElement userTwoText = driver.findElement(By.xpath("(//div/h5)[2]"));
        Assert.assertEquals(userTwoText.getText(), "name: user2");

        WebElement userTwoProfile = driver.findElement(By.xpath("(//a[text()='View profile'])[2]"));
        Assert.assertTrue(userTwoProfile.isDisplayed());
        userTwoProfile.click();

        title = driver.findElement(By.xpath("//h1"));
        Assert.assertEquals(title.getText(), "Not Found");
    }
    @AfterMethod
    public void browserClosing() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
