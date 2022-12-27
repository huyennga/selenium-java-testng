package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_21_Wait_Element_Condition_Status {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void TC_01_Visibility_Displayed_Visible() {
        driver.get("https://www.facebook.com/");

        //1, có trên UI (bắt buộc)
        //2, Có trong HTML (bắt buộc)

        // chờ cho email textbox hiển trị trong vòng 10s
//        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.xpath("//input[@id='email']"))));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("nga@gmail.com");

    }

    @Test
    public void TC_02_Invisibility_Undisplayed_Invisible() {
        driver.get("https://www.facebook.com/");

        //1, KO có trên UI (bắt buộc)
        //2, Có trong HTML

        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

        // chờ cho Re-email textbox KO hiển trị trong vòng 10s
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));

        driver.findElement(By.xpath("//img[@class='_8idr img']")).click();

    }

    @Test
    public void TC_02_Invisibility_Undisplayed_Invisible_II() {
        driver.get("https://www.facebook.com/");

        //1, KO có trên UI (bắt buộc)
        //2, KO có trong HTML

        // chờ cho Re-email textbox KO hiển trị trong vòng 10s
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
    }

    @Test
    public void TC_03_Presence_I() {
        driver.get("https://www.facebook.com/");

        //1, Có trên Ui
        //2, Có trong HTML (bắt buộc)

        // chờ cho Re-email textbox presence trong vòng 10s
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
    }

    @Test
    public void TC_03_Presence_II() {
        driver.get("https://www.facebook.com/");

        //1, KO Có trên Ui
        //2, Có trong HTML (bắt buộc)

        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

        // chờ cho Re-email textbox KO hiển trị trong vòng 10s
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
    }

    @Test
    public void TC_04_Staleness() {
        driver.get("https://www.facebook.com/");

        //1, KO Có trên Ui (bắt buộc)
        //2, Có trong HTML

        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

        // Phase 1: Element có trong HTML
        WebElement reEnterEmailTextbox = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
        // thao tác với element khác, khiến cho re-enter email ko còn trong DOM nữa
        driver.findElement(By.xpath("//img[@class='_8idr img']")).click();

        // chờ cho Re-email textbox KO hiển trị trong vòng 10s
        explicitWait.until(ExpectedConditions.stalenessOf(reEnterEmailTextbox));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond(long timeSecond) throws InterruptedException {
        Thread.sleep(timeSecond * 1000);
    }
}
