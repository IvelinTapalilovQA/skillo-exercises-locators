package automationTest;

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

public class TestAutomationLocators {
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
    @Test
    void challengingDom(){
        driver.get("http://the-internet.herokuapp.com/challenging_dom");
        boolean isFounded = false;

        List<WebElement> columnHeaders = driver.findElements(By.xpath("//table/thead//th"));
        int tableColumnsCount = columnHeaders.size();
        List<WebElement> cells = driver.findElements(By.xpath("//table/tbody//td"));
        int cellsCount = cells.size();
        int rowsCount = cellsCount / tableColumnsCount;
        String table [][] = new String[rowsCount][tableColumnsCount];
        WebElement tableElements [][] = new WebElement[rowsCount][tableColumnsCount];

        for (int i = 0; i < rowsCount; i ++){
            for (int j = 0; j < tableColumnsCount; j ++){
                String currXPath = String.format("//table/tbody//tr[%s]//td[%s]", i + 1, j + 1);
                table[i][j] = driver.findElement(By.xpath(currXPath)).getText();
                tableElements[i][j] = driver.findElement(By.xpath(currXPath));
                if(table[i][j].equals("Consequuntur6")){
                    WebElement deleteButton = driver.findElement(By.xpath("//*[@id='content']/div/div/div/div[2]/table/tbody/tr" + "[" + i + "]" + "/td" + "[7]/a[2]"));
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
    @Test
    void tableHandle() {
        driver.get("https://demo.guru99.com/test/table.html");

        int index = 0;
        WebElement myTable = driver.findElement(By.xpath("//html/body/table/tbody"));
        List<WebElement> rowsTable = myTable.findElements(By.tagName("tr"));
        int rowsCount = rowsTable.size();
        for (int row = 0; row < rowsCount; row++) {
            List<WebElement> columnsRow = rowsTable.get(row).findElements(By.tagName("td"));
            int columnsCount = columnsRow.size();
            for (int column = 0; column < columnsCount; column++) {
                String cellText = columnsRow.get(column).getText();
                System.out.println(cellText);
            }
        }
    }
    @AfterMethod
    public void browserClosing() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}