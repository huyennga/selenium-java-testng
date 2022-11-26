package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_20_UploadFile_AutoIT {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    String image01 = "image01.jpg";
    String image02 = "image02.jpg";

    String image01FilePath = projectPath + "\\uploadfile\\" + image01;
    String image02FilePath = projectPath + "\\uploadfile\\" + image02;

    String ChromePathOnFile =projectPath + "\\autoItScript\\chromeUploadOneTime.exe";
    String ChromePathMultiplFile =projectPath + "\\autoItScript\\chromeUploadMultiple.exe";
    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_uploadFile_AutoIt() throws InterruptedException, IOException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        // click de open diagon
        driver.findElement(By.xpath("//span[contains(@class, 'btn-success fileinput-button')]")).click();

        // load file len
        Runtime.getRuntime().exec(new String[] {ChromePathOnFile, image01FilePath});

        // verify file dc load len thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+image01+"']")).isDisplayed());

        // click upload
        List<WebElement> btnUploads = driver.findElements(By.xpath("//button[contains(@class,'btn btn-primary')]//span[text()='Start']"));
        for(WebElement button: btnUploads){
            button.click();
            sleepInSecond(5);
        }

        // verify upload thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src, '"+image01+"')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src, '"+image02+"')]")).isDisplayed());

    }

    @Test
    public void TC_02_uploadMultipleFile_AutoIt() throws InterruptedException, IOException, IOException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        // click de open diagon
        driver.findElement(By.xpath("//span[contains(@class, 'btn-success fileinput-button')]")).click();

        // load file len
        Runtime.getRuntime().exec(new String[] {ChromePathMultiplFile, image01FilePath, image02FilePath});

        // verify file dc load len thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+image01+"']")).isDisplayed());

        // click upload
        List<WebElement> btnUploads = driver.findElements(By.xpath("//button[contains(@class,'btn btn-primary')]//span[text()='Start']"));
        for(WebElement button: btnUploads){
            button.click();
            sleepInSecond(5);
        }

        // verify upload thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src, '"+image01+"')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src, '"+image02+"')]")).isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void  sleepInSecond(long timeSecond) throws InterruptedException {
        Thread.sleep(timeSecond*1000);
    }
}
