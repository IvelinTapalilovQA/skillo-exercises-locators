import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.time.Duration;

public class TestAutoExercise {

    @DataProvider(name = "userNames")
    public Object[][] userName(){
        return new Object[][] {
                {"ivelinQA", "Ivelin123", "ivelinQA"},
                {"testAdmin@gmail.com", "Admin1.User1", "AdminUser"},
                {"manager@gmail.com", "Manager1.Use1", "ManagerUser"}};
    }
    @Test(dataProvider = "userNames")
    public void testLogin(String userName, String password, String name) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

        WebElement profileLink = driver.findElement(By.id("nav-link-profile"));
        wait.until(ExpectedConditions.elementToBeClickable(signInElement));

        profileLink.click();

        wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/users/"));


        Boolean isTextDisplayed = wait.until(ExpectedConditions.textToBe(By.tagName("h2"), name));
        Assert.assertTrue(isTextDisplayed);

        driver.close();
    }
}