package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_08_Dropdown {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String firstName, lastName, middleName, employeeId;
    Select select;


    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Dropdown() throws InterruptedException {
        Random rand = new Random();
        driver.get("https://demo.nopcommerce.com/");

        // Login
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        sleepInSecond(2);


        // Input fist, last, middle name ....
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("nga");
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("hoang");
        select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
        select.selectByVisibleText("1");
        select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
        select.selectByVisibleText("May");
        select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
        select.selectByVisibleText("1980");
        String email = "nga"  +rand.nextInt(99999)+ "@gmail.com";
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@id='register-button']")).click();

        // verify message
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");


        // Verify
        driver.findElement(By.xpath(" //a[@class='ico-account']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='FirstName']")).getAttribute("value"), "nga");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='LastName']")).getAttribute("value"), "hoang");

        select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "1");
        select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "May");
        select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "1980");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='Email']")).getAttribute("value"), email);


    }

    @Test
    public void TC_02_Dropdown() throws InterruptedException {
        Random rand = new Random();
        driver.get("https://rode.com/en/support/where-to-buy");
        //
        Select select =  new Select(driver.findElement(By.xpath("//select[@id='country']")));
        select.selectByValue("Vietnam");
        sleepInSecond(2);

        List<WebElement> dealers  = Collections.singletonList(driver.findElement(By.xpath("//div[@class='d-flex flex-wrap']//h4")));
        for(WebElement element: dealers){
            System.out.println(element.getText());
        }

    }
        public void  sleepInSecond(long timeSecond) throws InterruptedException {
        Thread.sleep(timeSecond*1000);
        }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
