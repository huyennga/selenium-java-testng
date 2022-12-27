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

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_17_Frame_iFrame {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    Select select;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_iFrame() throws InterruptedException {
        //Step
        driver.get("https://skills.kynaenglish.vn/");

        // verify iframe facebook hiển thị
        Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")).isDisplayed());
        // verify số lượng like
        // switch to iframe có chứa HTMl số lượng
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")));
        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']//parent::div//following-sibling::div")).getText(), "165K likes");
        // click vao chat voi chung toi
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
        driver.findElement(By.xpath("//div[contains(@class, 'chatButton_Button')]")).click();
        sleepInSecond(3);

        // nhap du lieu vao chat
        driver.findElement(By.xpath("//input[contains(@class, 'input_name')]")).sendKeys("name abc");
        driver.findElement(By.xpath("//input[contains(@class, 'input_phone')]")).sendKeys("0909999666");
        select = new Select(driver.findElement(By.xpath("//select[@id='serviceSelect']")));
        select.selectByVisibleText("TƯ VẤN TUYỂN SINH");
        driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("test");
        driver.findElement(By.xpath("//input[contains(@class, 'submit meshim')]")).click();
        // switch ve default frame
        driver.switchTo().defaultContent();
        // nhap text và click search, sau do verify ket qua
        driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
        driver.findElement(By.xpath("//button[@class='search-button']")).click();
        List<WebElement> courseName= driver.findElements(By.xpath("//div[@class='content']//h4"));
        for(WebElement course: courseName){
            Assert.assertTrue(course.getText().contains("Excel"));
        }

    }

    @Test
    public void TC_02_Frame() throws InterruptedException {
        //Step
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        // nhap id va click continue
        driver.findElement(By.xpath("//input[@class='form-control text-muted']")).sendKeys("123");
        driver.findElement((By.xpath("//a[@class='btn btn-primary login-btn']"))).click();

        //input[@id='fldPasswordDispId']
        // switch to iframe có chứa HTMl số lượng
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")));
        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']//parent::div//following-sibling::div")).getText(), "165K likes");
        // click vao chat voi chung toi
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
        driver.findElement(By.xpath("//div[contains(@class, 'chatButton_Button')]")).click();
        sleepInSecond(3);

        // nhap du lieu vao chat
        driver.findElement(By.xpath("//input[contains(@class, 'input_name')]")).sendKeys("name abc");
        driver.findElement(By.xpath("//input[contains(@class, 'input_phone')]")).sendKeys("0909999666");
        select = new Select(driver.findElement(By.xpath("//select[@id='serviceSelect']")));
        select.selectByVisibleText("TƯ VẤN TUYỂN SINH");
        driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("test");
        driver.findElement(By.xpath("//input[contains(@class, 'submit meshim')]")).click();
        // switch ve default frame
        driver.switchTo().defaultContent();
        // nhap text và click search, sau do verify ket qua
        driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
        driver.findElement(By.xpath("//button[@class='search-button']")).click();
        List<WebElement> courseName= driver.findElements(By.xpath("//div[@class='content']//h4"));
        for(WebElement course: courseName){
            Assert.assertTrue(course.getText().contains("Excel"));
        }

    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }

    public void  sleepInSecond(long timeSecond) throws InterruptedException {
        Thread.sleep(timeSecond*1000);
    }
    public Random getRandomEmailAddress(){
        Random rand = new Random();
        rand.nextInt(99999);
        return rand;
    }
}
