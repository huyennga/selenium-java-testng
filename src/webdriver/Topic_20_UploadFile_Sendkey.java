package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_20_UploadFile_Sendkey {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    String image01 = "image01.jpg";
    String image02 = "image02.jpg";

    String image01FilePath = projectPath + "\\uploadfile\\" + image01;
    String image02FilePath = projectPath + "\\uploadfile\\" + image02;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_uploadFile() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        // load file len
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image01FilePath);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image02FilePath);

        // verify file dc load len thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+image01+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '"+image02+"']")).isDisplayed());

        // click upload
        List<WebElement> btnUploads = driver.findElements(By.xpath("//button[contains(@class,'btn btn-primary')]//span[text()='Start']"));
        for(WebElement button: btnUploads){
            button.click();
            sleepInSecond(3);
        }

        // verify upload thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src, '"+image01+"')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src, '"+image02+"')]")).isDisplayed());

    }


    @Test
    public void TC_02_uploadMutipleFiles() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        // load file len
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image01FilePath +"\n" + image02FilePath);

        // verify file dc load len thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+image01+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '"+image02+"']")).isDisplayed());

        // click upload
        List<WebElement> btnUploads = driver.findElements(By.xpath("//button[contains(@class,'btn btn-primary')]//span[text()='Start']"));
        for(WebElement button: btnUploads){
            button.click();
            sleepInSecond(3);
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
