package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_15_PopUp {
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
    public void TC_01_FixedPopup() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn//");
        WebElement loginPopUp = driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]"));
        // verify pop up ko hien thi
        Assert.assertFalse(loginPopUp.isDisplayed());

        // click vao Dang nhap
        driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
        sleepInSecond(3);
        // verify pop up hien thi
        Assert.assertTrue( driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]")).isDisplayed());
        // nhap thong tin user, password
        driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]//input[@id='account-input']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]//input[@id='password-input']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]//button[contains(@class,'btn-v1')]")).click();
        sleepInSecond(3);
        //verify
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]//div[@class='row error-login-panel']")).getText(), "Tài khoản không tồn tại!");

    }

    @Test
    public void TC_02_FixedPopup() throws InterruptedException {
        driver.get("https://skills.kynaenglish.vn/");
        WebElement loginPopUp = driver.findElement(By.xpath("//div[@id='k-popup-account-login']"));
        // verify pop up ko hien thi
        Assert.assertFalse(loginPopUp.isDisplayed());

        // click vao Dang nhap
        driver.findElement(By.xpath("//a[@class='login-btn']")).click();
        sleepInSecond(3);
        // verify pop up hien thi
        Assert.assertTrue(loginPopUp.isDisplayed());
        // nhap thong tin user, password
        driver.findElement(By.xpath("//input[@id='user-login']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@id='user-password']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@id='btn-submit-login']")).click();
        sleepInSecond(3);
        //verify
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='password-form-login-message']")).getText(), "Sai tên đăng nhập hoặc mật khẩu");

    }


    @Test
    public void TC_03_FixedPopup_NotInDom() throws InterruptedException {
        driver.get("https://tiki.vn/");
        // verify pop up ko hien thi
        Assert.assertEquals(driver.findElements(By.xpath("//div[@role='dialog']")).size(), 0);

        // click vao Dang nhap
        driver.findElement(By.xpath("//span[@class='Userstyle__NoWrap-sc-6e6am-12 gJAiTt']")).click();
        sleepInSecond(3);

        // verify pop up hien thi
        WebElement loginPopUp = driver.findElement(By.xpath("//div[@role='dialog']"));
        Assert.assertTrue(loginPopUp.isDisplayed());
        // click dang nhap = email
        driver.findElement(By.xpath(" //p[@class='login-with-email']")).click();
        // click dang nhap
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInSecond(3);
        // verify text
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống'] ")).getText(), "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống'] ")).getText(), " Mật khẩu không được để trống");
        // click close button
        driver.findElement(By.xpath("//button[@class='btn-close']")).click();
        sleepInSecond(3);
        // verify pop up ko hien thi
        Assert.assertFalse(loginPopUp.isDisplayed());


    }




    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void  sleepInSecond(long timeSecond) throws InterruptedException {
        Thread.sleep(timeSecond*1000);
    }
}
