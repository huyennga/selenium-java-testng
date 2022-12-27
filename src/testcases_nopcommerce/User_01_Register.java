package testcases_nopcommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class User_01_Register {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_OrangeHRM() {
        // Login Page Url matching

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();


        Assert.assertEquals(driver.findElement(By.xpath("//p[text()='Time at Work']")).getText(), "Time at Work");

        driver.findElement(By.xpath("//a[@class='oxd-main-menu-item' and contains(.,'PIM')]")).click();
        driver.findElement(By.xpath("//div[@class='oxd-autocomplete-text-input oxd-autocomplete-text-input--active']")).sendKeys("Raj  Kumar");
        driver.findElement(By.xpath("//button[text()=' Search ']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='oxd-table-cell oxd-padding-cell'][3]")).getText(), "Raj");

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void  sleepInSecond(long timeSecond) throws InterruptedException {
        Thread.sleep(timeSecond*1000);
    }


}
