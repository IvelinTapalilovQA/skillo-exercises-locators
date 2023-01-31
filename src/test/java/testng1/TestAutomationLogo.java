package testng1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class TestAutomationLogo {

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
    public void testLogo() {

        this.driver.get("http://training.skillo-bg.com:4300/posts/all");

        WebElement logoHomePage = driver.findElement(By.id("homeIcon"));
        Assert.assertTrue(logoHomePage.isDisplayed());

        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        WebElement logoLoginPage = driver.findElement(By.id("homeIcon"));
        Assert.assertTrue(logoHomePage.isDisplayed());
        wait.until(ExpectedConditions.elementToBeClickable(logoLoginPage));
        logoLoginPage.click();

        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));
    }

    @DataProvider(name = "userNames")
    public Object[][] userName() {
        return new Object[][]{
                {"ivelinQA", "Ivelin123", "ivelinQA"},
                {"testAdmin@gmail.com", "Admin1.User1", "AdminUser"}};
    }

    @Test(dataProvider = "userNames")
    public void testLogoLogin(String userName, String password, String name) {
        this.driver.get("http://training.skillo-bg.com:4300/posts/all");

        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        WebElement signInElement = driver.findElement(By.xpath("//p[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInElement));

        WebElement userNameField = driver.findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys(userName);

        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys(password);

        WebElement signInButton = driver.findElement(By.id("sign-in-button"));
        wait.until(ExpectedConditions.elementToBeClickable(signInElement));
        signInButton.click();

        wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/users/"));

        WebElement profileLink = driver.findElement(By.id("nav-link-profile"));
        wait.until(ExpectedConditions.elementToBeClickable(signInElement));
        profileLink.click();

        Boolean isTextDisplayed = wait.until(ExpectedConditions.textToBe(By.tagName("h2"), name));
        Assert.assertTrue(isTextDisplayed);

        WebElement logoProfile = driver.findElement(By.id("homeIcon"));
        Assert.assertTrue(logoProfile.isDisplayed());

        WebElement newPostLink = driver.findElement(By.id("nav-link-new-post"));
        newPostLink.click();

        WebElement logoNewPost = driver.findElement(By.id("homeIcon"));
        Assert.assertTrue(logoNewPost.isDisplayed());
        logoNewPost.click();

        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/all"));

        WebElement logoHomePageRedirect = driver.findElement(By.id("homeIcon"));
        Assert.assertTrue(logoHomePageRedirect.isDisplayed());
    }
        @Test(dataProvider = "userNames")
        public void afterLogoutTest(String userName, String password, String name) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            this.driver.get("http://training.skillo-bg.com:4300/posts/all");

            WebElement loginLink = driver.findElement(By.id("nav-link-login"));
            loginLink.click();

            wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

            WebElement signInElement = driver.findElement(By.xpath("//p[text()='Sign in']"));
            wait.until(ExpectedConditions.visibilityOf(signInElement));

            WebElement userNameField = driver.findElement(By.id("defaultLoginFormUsername"));
            userNameField.sendKeys(userName);

            WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
            passwordField.sendKeys(password);

            WebElement signInButton = driver.findElement(By.id("sign-in-button"));
            wait.until(ExpectedConditions.elementToBeClickable(signInElement));
            signInButton.click();

            wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/users/"));

            WebElement profileLink = driver.findElement(By.id("nav-link-profile"));
            wait.until(ExpectedConditions.elementToBeClickable(signInElement));
            profileLink.click();

            Boolean isTextDisplayed = wait.until(ExpectedConditions.textToBe(By.tagName("h2"), name));
            Assert.assertTrue(isTextDisplayed);

            WebElement logoutButton = driver.findElement(By.cssSelector("#navbarColor01 > ul.navbar-nav.my-ml.d-none.d-md-block > li > a > i"));
            wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
            logoutButton.click();

            wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

            WebElement logoAfterLogout = driver.findElement(By.id("homeIcon"));
            Assert.assertTrue(logoAfterLogout.isDisplayed());
            logoAfterLogout.click();

            wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/all"));

            WebElement logoHomePageRedirect = driver.findElement(By.id("homeIcon"));
            Assert.assertTrue(logoHomePageRedirect.isDisplayed());
        }
        @AfterMethod
        public void browserClosing () {
            if (this.driver != null) {
                this.driver.close();
            }
        }
    }