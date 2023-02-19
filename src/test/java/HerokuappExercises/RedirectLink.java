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

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class RedirectLink {
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
    public void redirectLink() throws IOException {
        driver.get("http://the-internet.herokuapp.com/redirector");

        WebElement redirectLink = driver.findElement(By.id("redirect"));
        redirectLink.click();

        WebElement code200Link = driver.findElement(By.xpath("//a[text()='200']"));
        code200Link.click();

        String code200Url = driver.getCurrentUrl();
        Assert.assertEquals(code200Url, "http://the-internet.herokuapp.com/status_codes/200");

        URL url = new URL("http://the-internet.herokuapp.com/status_codes/200");
        HttpURLConnection con200 = (HttpURLConnection) url.openConnection();
        con200.setRequestMethod("GET");

        Assert.assertEquals(con200.getResponseCode(), 200);

        WebElement redirectLinkHere = driver.findElement(By.xpath("//a[text()='here']"));
        redirectLinkHere.click();

        WebElement code301Link = driver.findElement(By.xpath("//a[text()='301']"));
        code301Link.click();

        String code301Url = driver.getCurrentUrl();
        Assert.assertEquals(code301Url, "http://the-internet.herokuapp.com/status_codes/301");

        url = new URL("http://the-internet.herokuapp.com/status_codes/301");
        HttpURLConnection con301 = (HttpURLConnection) url.openConnection();
        con301.setRequestMethod("GET");

        Assert.assertEquals(con301.getResponseCode(), 301);

        redirectLinkHere = driver.findElement(By.xpath("//a[text()='here']"));
        redirectLinkHere.click();

        WebElement code404Link = driver.findElement(By.xpath("//a[text()='404']"));
        code404Link.click();

        String code404Url = driver.getCurrentUrl();
        Assert.assertEquals(code404Url, "http://the-internet.herokuapp.com/status_codes/404");

        url = new URL("http://the-internet.herokuapp.com/status_codes/404");
        HttpURLConnection con404 = (HttpURLConnection) url.openConnection();
        con404.setRequestMethod("GET");

        Assert.assertEquals(con404.getResponseCode(), 404);

        redirectLinkHere = driver.findElement(By.xpath("//a[text()='here']"));
        redirectLinkHere.click();

        WebElement code500Link = driver.findElement(By.xpath("//a[text()='500']"));
        code500Link.click();

        String code500Url = driver.getCurrentUrl();
        Assert.assertEquals(code500Url, "http://the-internet.herokuapp.com/status_codes/500");

        url = new URL("http://the-internet.herokuapp.com/status_codes/500");
        HttpURLConnection con500 = (HttpURLConnection) url.openConnection();
        con500.setRequestMethod("GET");

        Assert.assertEquals(con500.getResponseCode(), 500);
    }
    @AfterMethod
    public void browserClosing() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}

