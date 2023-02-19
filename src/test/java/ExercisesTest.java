import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class ExercisesTest {
    @Test
    public void testLoginFunction(){
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://training.skillo-bg.com:4300/posts/all");

        WebElement loginNavLink = driver.findElement(By.id("nav-link-login"));
        loginNavLink.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        WebElement signInTitle = driver.findElement(By.xpath("//p[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInTitle));

        WebElement registerLink = driver.findElement(By.xpath("//*[text()='Register']"));
        registerLink.click();

        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/register"));

        WebElement signUpTitle = driver.findElement(By.xpath("//*[text() ='Sign up']"));
        wait.until(ExpectedConditions.visibilityOf(signUpTitle));

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.clear();
        String currentName = generateRandomAlphabeticName(5, 10);
        usernameInput.sendKeys(currentName);


        WebElement emailInput = driver.findElement(By.xpath("//*[@type='email']"));
        emailInput.clear();
        emailInput.sendKeys(generateRandomEmail(5, 10));

        WebElement birthDateInput = driver.findElement(By.xpath("//*[@type='date']"));
        birthDateInput.clear();
        birthDateInput.sendKeys("29011994");

        WebElement passwordInput = driver.findElement(By.id("defaultRegisterFormPassword"));
        passwordInput.clear();
        passwordInput.sendKeys("Bayern6");

        WebElement confirmPasswordInput = driver.findElement(By.id("defaultRegisterPhonePassword"));
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys("Bayern6");

        WebElement infoInput = driver.findElement(By.name("pulic-info"));
        infoInput.clear();
        infoInput.sendKeys("The profile was created for test purposes!");

        WebElement signInButton = driver.findElement(By.id("sign-in-button"));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();

        WebElement profileLink = driver.findElement(By.id("nav-link-profile"));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        profileLink.click();

        wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/users/"));

        Boolean isTextDisplayed = wait.until(ExpectedConditions.textToBe(By.tagName("h2"), currentName));
        Assert.assertTrue(isTextDisplayed);

        driver.close();
    }
    private String generateRandomEmail(int minLengthInclusive, int maxLengthExclusive) {
        return  generateRandomAlphabeticName(minLengthInclusive, maxLengthExclusive) + "@gmail.com";
    }

    private String generateRandomAlphabeticName(int minLengthInclusive, int maxLengthExclusive) {
        return RandomStringUtils.randomAlphanumeric(minLengthInclusive, maxLengthExclusive);
    }
}
