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


public class DragAndDrop {
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
    public void dragAndDrop() {
        driver.get("https://demoqa.com/droppable");

        Actions actions = new Actions(driver);

        WebElement from = driver.findElement(By.id("draggable"));
        WebElement to = driver.findElement(By.id("droppable"));

        int xOffSet1 = from.getLocation().getX();
        int yOffSet1 = from.getLocation().getY();


        int xOffSet = to.getLocation().getX();
        int yOffSet = to.getLocation().getY();

        xOffSet = (xOffSet - xOffSet1) + 10;
        yOffSet = (yOffSet - yOffSet1) + 20;

        actions.dragAndDropBy(from, xOffSet, yOffSet).perform();


        WebElement checkHeader = driver.findElement(By.xpath("//*[@id='droppable']/p"));
        Assert.assertEquals(checkHeader.getText(), "Dropped!");

    }
    @AfterMethod
    public void browserClosing() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
