package HerokuappExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.layertree.model.StickyPositionConstraint;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

public class BrokenImagesLinks {
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
    public void Links() {
        driver.get("https://demoqa.com/links");

        String url = "";
        List<WebElement> allUrls = driver.findElements(By.tagName("a"));
        System.out.println("Total links on the Web page " + allUrls.size());

        Iterator<WebElement> iterator = allUrls.iterator();
        while (iterator.hasNext()) {
            url = iterator.next().getText();
            System.out.println(url);
        }

    }

    @Test
    public void brokenLinks() {
        driver.get("https://demoqa.com/broken");
        List<WebElement> links = driver.findElements(By.tagName("a"));

        for (int i = 0; i < links.size(); i++) {
            WebElement e1 = links.get(i);
            String url = e1.getAttribute("href");
            verifyUlr(url);
        }
    }
    @Test
    public void brokenImages() {
        driver.get("https://demoqa.com/broken");
        List<WebElement> images = driver.findElements(By.tagName("img"));
        System.out.println("Total number of images on the page are :" + images.size());

        for (int i = 0; i < images.size(); i++) {
            WebElement image = images.get(i);
            String urlImage = image.getAttribute("src");
            System.out.println("Url of image " + (i + 1) + " is " + urlImage);
            verifyUlr(urlImage);

            try {
                boolean imageDisplayed = (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return arguments[0].complete "
                                + "&& typeof arguments[0].naturalWidth != \"undefined\" "
                                + "&& arguments[0].naturalWidth > 0", image);
                if (imageDisplayed) {
                    System.out.println("DISPLAY - OK");
                } else {
                    System.out.println("DISPLAY - BROKEN");
                }
            }
            catch (Exception e){
                System.out.println("Error Occurred");
            }
        }
    }
    @AfterMethod
    public void browserClosing() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    public static void verifyUlr(String linkUrl) {
        try {
            URL url = new URL(linkUrl);
            HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
            httpURLConnect.setConnectTimeout(5000);
            httpURLConnect.connect();
            if (httpURLConnect.getResponseCode() > 400) {
                System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + "is a broken link");
            } else {
                System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
            }
        }
        catch(Exception e){
            }
        }
    }
