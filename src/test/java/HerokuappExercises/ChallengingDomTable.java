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
import java.util.List;

public class ChallengingDomTable {
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
        public void testTable() {
            driver.get("http://the-internet.herokuapp.com/challenging_dom");

            WebElement table = driver.findElement(By.xpath("//table"));
            List<WebElement> Rows = table.findElements(By.xpath("//tr"));
            System.out.println("Total rows " + Rows.size());

            String targetText = "Consequuntur3";
            String cell = null;
            boolean isFounded = false;
            for (int i = 1; i < Rows.size(); i++) {
                List<WebElement> cols = Rows.get(i).findElements(By.tagName("td"));
                for (int j = 1; j < cols.size(); j++) {
                    cell = driver.findElement(By.xpath("//*[@id='content']/div/div/div/div[2]/table/tbody/tr" + "[" + i + "]" + "/td" + "[" + j + "]")).getText();
                    if (cell.equals(targetText)) {
                        WebElement deleteButton = table.findElement(By.xpath("//*[@id='content']/div/div/div/div[2]/table/tbody/tr" + "[" + i + "]" + "/td" + "[7]/a[2]"));
                        deleteButton.click();
                        isFounded = true;
                    }
                }
                if (isFounded) {
                    break;
                }
            }
            String actualUrl = driver.getCurrentUrl();
            Assert.assertEquals(actualUrl, "http://the-internet.herokuapp.com/challenging_dom#delete");
        }
    @AfterMethod
    public void browserClosing() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}

