package automationTest;

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
import java.util.ArrayList;
import java.util.List;

public class TestAutomationExerciseTwo {
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
    public void testCheckBox() {
        this.driver.get("https://demoqa.com/checkbox");

        WebElement checkBoxLabel = driver.findElement(By.cssSelector("[for='tree-node-home']"));
        checkBoxLabel.click();

        WebElement checkBox = driver.findElement(By.id("tree-node-home"));
        Assert.assertTrue(checkBox.isSelected());

        WebElement textResult = driver.findElement(By.cssSelector("#result"));
        Assert.assertEquals(textResult.getText(), "You have selected :\n" + "home\n" + "desktop\n" + "notes\n" + "commands\n" + "documents\n" + "workspace\n" + "react\n" + "angular\n" + "veu\n" + "office\n" + "public\n" + "private\n" + "classified\n" + "general\n" + "downloads\n" + "wordFile\n" + "excelFile");

        checkBoxLabel.click();
        Assert.assertFalse(checkBox.isSelected());
    }
    @Test
    public void testCheckBoxes() {
        this.driver.get("https://demoqa.com/checkbox");

        WebElement arrowMenu = driver.findElement(By.xpath("//*[@id='tree-node']/ol/li/span/button"));
        arrowMenu.click();

        WebElement checkBoxLabelDocuments = driver.findElement(By.cssSelector("[for='tree-node-documents']"));
        checkBoxLabelDocuments.click();

        WebElement checkBoxDocuments = driver.findElement(By.id("tree-node-documents"));
        Assert.assertTrue(checkBoxDocuments.isSelected());

        WebElement textResultDocumentsElement = driver.findElement(By.cssSelector("#result"));
        String textResultDocumentsSelected = textResultDocumentsElement.getText();
        Assert.assertEquals(textResultDocumentsSelected, "You have selected :\n" + "documents\n" + "workspace\n" + "react\n" + "angular\n" + "veu\n" + "office\n" + "public\n" + "private\n" + "classified\n" + "general");


        WebElement checkBoxLabelDownloads = driver.findElement(By.cssSelector("[for='tree-node-downloads']"));
        checkBoxLabelDownloads.click();

        WebElement textResultDownloads = driver.findElement(By.cssSelector("#result"));
        String textResultDownloadsSelected = textResultDownloads.getText();

        //We compare the result of the text after selecting the Documents and the result after selecting the Downloads.
        Assert.assertNotEquals(textResultDownloadsSelected, "You have selected :\n" + "documents\n" + "workspace\n" + "react\n" + "angular\n" + "veu\n" + "office\n" + "public\n" + "private\n" + "classified\n" + "general\n" + "downloads");

        //We compare the expected text result and actual text result after both selections.
        Assert.assertEquals(textResultDownloadsSelected, "You have selected :\n" + "documents\n" + "workspace\n" + "react\n" + "angular\n" + "veu\n" + "office\n" + "public\n" + "private\n" + "classified\n" + "general\n" + "downloads\n" + "wordFile\n" + "excelFile");

        WebElement checkBoxDownloads = driver.findElement(By.id("tree-node-downloads"));
        Assert.assertTrue(checkBoxDownloads.isSelected());

        Assert.assertTrue(checkBoxDocuments.isSelected());
    }
    @Test
    public void testElements() {
        this.driver.get("http://the-internet.herokuapp.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement addRemoveElementLink = driver.findElement(By.linkText("Add/Remove Elements"));
        addRemoveElementLink.click();
        wait.until(ExpectedConditions.urlToBe("http://the-internet.herokuapp.com/add_remove_elements/"));

        WebElement addRemoveElementsTitle = driver.findElement(By.xpath("//*[@id='content']/h3"));
        wait.until(ExpectedConditions.visibilityOf(addRemoveElementsTitle));

        WebElement buttonAddElement = driver.findElement(By.cssSelector("#content > div > button"));
        buttonAddElement.click();

        WebElement deleteButton = driver.findElement(By.cssSelector("#elements > button"));
        Assert.assertTrue(deleteButton.isDisplayed());

        deleteButton.click();

        Assert.assertFalse(isElementPresent(By.cssSelector("#elements > button")));
    }
    @Test
    public void testRadioButton(){
        this.driver.get("https://demoqa.com/radio-button");

        WebElement radioYesLabel = driver.findElement(By.xpath("//*[@for='yesRadio']"));
        radioYesLabel.click();

        WebElement radioYes = driver.findElement(By.id("yesRadio"));
        Assert.assertTrue(radioYes.isSelected());

        WebElement textResultYes = driver.findElement(By.xpath("//*[@class='mt-3']"));
        Assert.assertEquals(textResultYes.getText(), "You have selected Yes");

        WebElement radioImpressiveLabel = driver.findElement(By.xpath("//*[@for='impressiveRadio']"));
        radioImpressiveLabel.click();

        WebElement radioImpressiveButton = driver.findElement(By.id("impressiveRadio"));
        Assert.assertTrue(radioImpressiveButton.isSelected());

        WebElement textResultImpressive = driver.findElement(By.xpath("//*[@class='mt-3']"));
        Assert.assertEquals(textResultImpressive.getText(), "You have selected Impressive");

        Assert.assertFalse(radioYes.isSelected());

        WebElement noRadioLabel = driver.findElement(By.xpath("//*[@for='noRadio']"));
        noRadioLabel.click();

        WebElement noRadioButton = driver.findElement(By.id("noRadio"));

        if(noRadioButton.isEnabled()){
            Assert.assertTrue(noRadioButton.isSelected());
        }
        else {
            System.out.println("The NO radio button is disabled!");
        }
    }
    @Test
    public void testTabs(){
        driver.get("https://demoqa.com/browser-windows");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement newTabButton = driver.findElement(By.id("tabButton"));
        newTabButton.click();

        List<String> windowTabs = new ArrayList<>(driver.getWindowHandles());
        String tabTwo = windowTabs.get(1);
        driver.switchTo().window(tabTwo);

        wait.until(ExpectedConditions.urlToBe("https://demoqa.com/sample"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://demoqa.com/sample");

        WebElement newTabHeader = driver.findElement(By.id("sampleHeading"));
        Assert.assertEquals(newTabHeader.getText(), "This is a sample page");

        String mainTab = windowTabs.get(0);
        driver.switchTo().window(mainTab);

        String mainWindowUrl = driver.getCurrentUrl();
        Assert.assertEquals(mainWindowUrl, "https://demoqa.com/browser-windows");
    }
    @Test
    public void testWindows(){
        driver.get("https://demoqa.com/browser-windows");

        WebElement newWindowButton = driver.findElement(By.id("windowButton"));
        newWindowButton.click();

        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        String secondWindow = windows.get(1);
        driver.switchTo().window(secondWindow);

        driver.manage().window().maximize();

        WebElement newWindowHeader = driver.findElement(By.id("sampleHeading"));
        Assert.assertEquals(newWindowHeader.getText(), "This is a sample page");

        String backToMainWindowIndex = windows.get(0);
        driver.switchTo().window(backToMainWindowIndex);

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "https://demoqa.com/browser-windows");
    }

    @AfterMethod
    public void browserClosing() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
    public boolean isElementPresent(By locatorKey) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;

        }
    }
}