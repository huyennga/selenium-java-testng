package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_23_Implicit_Wait {
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


    }

    @Test
    public void TC_01_Not_Enough_Time() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("https://automationfc.github.io/dynamic-loading/");

        // click vao Start
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        // verify text hello word
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");

    }

    @Test
    public void TC_02_Enough_Time() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://automationfc.github.io/dynamic-loading/");

        // click vao Start
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        // verify text hello word
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");
    }

    @Test
    public void TC_03_More_Time() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://automationfc.github.io/dynamic-loading/");

        // click vao Start
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        // verify text hello word
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond(long timeSecond) throws InterruptedException {
        Thread.sleep(timeSecond * 1000);
    }
}
