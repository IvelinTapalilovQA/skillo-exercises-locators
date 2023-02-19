package HerokuappExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class FloatingMenu {
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
    public void floatingMenu() {
        driver.get("http://the-internet.herokuapp.com/floating_menu#news");

        WebElement homeButton = driver.findElement(By.xpath("//a[text()='Home']"));
        Assert.assertTrue(homeButton.isDisplayed());
        homeButton.click();
        String currentUrlHome = driver.getCurrentUrl();
        Assert.assertEquals(currentUrlHome, "http://the-internet.herokuapp.com/floating_menu#home");


        WebElement newsButton = driver.findElement(By.xpath("//a[text()='News']"));
        Assert.assertTrue(homeButton.isDisplayed());
        newsButton.click();
        String currentUrlNews = driver.getCurrentUrl();
        Assert.assertEquals(currentUrlNews, "http://the-internet.herokuapp.com/floating_menu#news");

        WebElement contactButton = driver.findElement(By.xpath("//a[text()='Contact']"));
        Assert.assertTrue(contactButton.isDisplayed());
        contactButton.click();
        String currentUrlContact = driver.getCurrentUrl();
        Assert.assertEquals(currentUrlContact, "http://the-internet.herokuapp.com/floating_menu#contact");

        WebElement aboutButton = driver.findElement(By.xpath("//a[text()='About']"));
        Assert.assertTrue(aboutButton.isDisplayed());
        aboutButton.click();
        String currentUrlAbout = driver.getCurrentUrl();
        Assert.assertEquals(currentUrlAbout, "http://the-internet.herokuapp.com/floating_menu#about");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 2500)", "");

        Assert.assertTrue(homeButton.isDisplayed());
        homeButton.click();
        currentUrlHome = driver.getCurrentUrl();
        Assert.assertEquals(currentUrlHome, "http://the-internet.herokuapp.com/floating_menu#home");

        Assert.assertTrue(newsButton.isDisplayed());
        newsButton.click();
        currentUrlNews = driver.getCurrentUrl();
        Assert.assertEquals(currentUrlNews, "http://the-internet.herokuapp.com/floating_menu#news");

        Assert.assertTrue(contactButton.isDisplayed());
        contactButton.click();
        currentUrlContact = driver.getCurrentUrl();
        Assert.assertEquals(currentUrlContact, "http://the-internet.herokuapp.com/floating_menu#contact");

        Assert.assertTrue(aboutButton.isDisplayed());
        aboutButton.click();
        currentUrlAbout = driver.getCurrentUrl();
        Assert.assertEquals(currentUrlAbout, "http://the-internet.herokuapp.com/floating_menu#about");

        WebElement pageFooter = driver.findElement(By.id("page-footer"));
        js.executeScript("arguments[0].scrollIntoView();", pageFooter);


        Assert.assertTrue(homeButton.isDisplayed());
        Assert.assertTrue(newsButton.isDisplayed());
        Assert.assertTrue(contactButton.isDisplayed());
        Assert.assertTrue(aboutButton.isDisplayed());
    }
    @AfterMethod
    public void browserClosing() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}