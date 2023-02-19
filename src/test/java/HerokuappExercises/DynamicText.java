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

public class DynamicText {
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
    public void dynamicText() {
        driver.get("http://the-internet.herokuapp.com/dynamic_content?with_content=static");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement textOne = driver.findElement(By.xpath("//*[@id='content']/div[1]/div[2]"));
        Assert.assertEquals(textOne.getText(), "Accusantium eius ut architecto neque vel voluptatem vel nam eos minus ullam dolores voluptates enim sed voluptatem rerum qui sapiente nesciunt aspernatur et accusamus laboriosam culpa tenetur hic aut placeat error autem qui sunt.");

        WebElement textTwo = driver.findElement(By.xpath("//*[@id='content']/div[2]/div[2]"));
        Assert.assertEquals(textTwo.getText(), "Omnis fugiat porro vero quas tempora quis eveniet ab officia cupiditate culpa repellat debitis itaque possimus odit dolorum et iste quibusdam quis dicta autem sint vel quo vel consequuntur dolorem nihil neque sunt aperiam blanditiis.");

        WebElement dynamicText = driver.findElement(By.xpath("//*[@id='content']/div[3]/div[2]"));
        String lastDynamicText = dynamicText.getText();
        WebElement changingTextButton = driver.findElement(By.xpath("//p/a"));
        Assert.assertEquals(dynamicText.getText(), lastDynamicText);

        changingTextButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='content']/div[3]/div[2]")));
        WebElement currentDynamicText = driver.findElement(By.xpath("//*[@id='content']/div[3]/div[2]"));
        Assert.assertNotEquals(currentDynamicText.getText(), lastDynamicText);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='content']/div[1]/div[2]")));
        textOne = driver.findElement(By.xpath("//*[@id='content']/div[1]/div[2]"));
        Assert.assertEquals(textOne.getText(), "Accusantium eius ut architecto neque vel voluptatem vel nam eos minus ullam dolores voluptates enim sed voluptatem rerum qui sapiente nesciunt aspernatur et accusamus laboriosam culpa tenetur hic aut placeat error autem qui sunt.");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='content']/div[2]/div[2]")));
        textTwo = driver.findElement(By.xpath("//*[@id='content']/div[2]/div[2]"));
        Assert.assertEquals(textTwo.getText(), "Omnis fugiat porro vero quas tempora quis eveniet ab officia cupiditate culpa repellat debitis itaque possimus odit dolorum et iste quibusdam quis dicta autem sint vel quo vel consequuntur dolorem nihil neque sunt aperiam blanditiis.");
    }
    @AfterMethod
    public void browserClosing() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}