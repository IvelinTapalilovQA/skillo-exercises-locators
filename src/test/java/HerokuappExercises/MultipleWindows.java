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

import javax.annotation.Tainted;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MultipleWindows {
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
        public void multipleWindows (){
            driver.get("http://the-internet.herokuapp.com/windows");

            WebElement title = driver.findElement(By.xpath("//div/h3"));
            Assert.assertEquals(title.getText(), "Opening a new window");

            WebElement linkButton = driver.findElement(By.xpath("//*[text()='Click Here']"));
            linkButton.click();

            List<String> windows = new ArrayList<>(driver.getWindowHandles());
            String secondWindow = windows.get(1);
            driver.switchTo().window(secondWindow);

            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl, "http://the-internet.herokuapp.com/windows/new");

            WebElement newPageTitle = driver.findElement(By.xpath("//div/h3"));
            Assert.assertEquals(newPageTitle.getText(), "New Window");

            String mainWindow = windows.get(0);
            driver.switchTo().window(mainWindow);

            currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl, "http://the-internet.herokuapp.com/windows");
            Assert.assertEquals(title.getText(), "Opening a new window");
        }
        @AfterMethod
        public void browserClosing() {
            if (this.driver != null) {
                this.driver.quit();
            }
        }
    }