package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_07_Textbox_Textarea {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String firstName, lastName, middleName, employeeId;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        firstName="hoang";
        middleName="huyen";
        lastName="nga";


    }

    @Test
    public void TC_01_ValidateCurrentUrl() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Login
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
        sleepInSecond(5);

        // Click on Add  employee
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee");

        // Input fist, last, middle name
        driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@placeholder='Middle Name']")).sendKeys(middleName);
        driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(lastName);


        // get employee id
        employeeId =  driver.findElement(By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")).getAttribute("value");

        // Click Save
        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
        sleepInSecond(3);

        // Verify
        Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='First Name']")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Middle Name']")).getAttribute("value"), middleName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Last Name']")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]")).getAttribute("value"), employeeId);
    }

    public void  sleepInSecond(long timeSecond) throws InterruptedException {
        Thread.sleep(timeSecond*1000);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
