package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_10_Button {
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
    public void TC_01_validateBbutton() throws InterruptedException {
        driver.get("https://www.fahasa.com/customer/account/create");
        sleepInSecond(5);
        driver.findElement(By.xpath("//button[@id='moe-dontallow_button']")).click();
        //close pop up
        WebElement frame = driver.findElement(By.xpath("//iframe[contains(@id,'moe-onsite-campaign')]"));
        driver.switchTo().frame(frame);
        driver.findElement(By.cssSelector("div[class='root-container'] button#close-icon>img")).click();

        // quay về page trước đó
        driver.switchTo().defaultContent();

        // chuyển qua tab đăng nhập
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        // verify button đăng nhập is disabled
        Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());

        // input data
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("0909888999");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");

        // verify button đăng nhập is enable
        Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());

        // Verify "Đăng nhập" button - background color
        String rgbaColor= driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color");
        System.out.println("rbg color"+ rgbaColor);

        //rgbaColorert to Hexa color
        String hexaColor = Color.fromString(rgbaColor).asHex().toUpperCase();
        System.out.println("hexa color " + hexaColor);

        // verify background color
        Assert.assertEquals(hexaColor,"#C92127");

    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }
    public void  sleepInSecond(long timeSecond) throws InterruptedException {
        Thread.sleep(timeSecond*1000);
    }
}
