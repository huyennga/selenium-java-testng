package testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_13_Alert {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    Alert alert;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_AcceptAlert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        sleepInSecond(3);

        // swicht to alert
        alert = driver.switchTo().alert();

        //verify alert title
        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        //Accept alert
        alert.accept();

        //verify sau khi accept alert thanh cong
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");


    }
    @Test
    public void TC_02_ConfirmAlert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        sleepInSecond(3);

        // swicht to alert
        alert = driver.switchTo().alert();

        //verify alert title
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        //Accept alert
        alert.accept();

        //verify sau khi accept alert thanh cong
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Ok");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        sleepInSecond(3);

        //cancel alert
        alert.dismiss();

        //verify sau khi accept alert thanh cong
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
    }

    @Test
    public void TC_03_PromptAlert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        sleepInSecond(3);

        // swicht to alert
        alert = driver.switchTo().alert();

        //verify alert title
        Assert.assertEquals(alert.getText(), "Click for JS Prompt");

        // nhap vao alert
        alert.sendKeys("hello");

        //Accept alert
        alert.accept();

        //verify sau khi accept alert thanh cong
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: hello");

    }

    @Test
    public void TC_04_AuthenticationAlert() throws InterruptedException {
        // pass thang user/pass vao url truoc khi open
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        sleepInSecond(3);

        // Congratulations! You must have the proper credentials.
       Assert.assertTrue(driver.findElement(By.xpath("//div[@class='example']/p")).getText().contains("Congratulations! You must have the proper credentials."));
    }

    @Test
    public void TC_05_AuthenticationAlert() throws InterruptedException {

        driver.get("http://the-internet.herokuapp.com/");
        String baseAuthenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

        getAuthenticationUrl(baseAuthenUrl, "admin", "admin");
        sleepInSecond(3);
        // Congratulations! You must have the proper credentials.
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='example']/p")).getText().contains("Congratulations! You must have the proper credentials."));
    }

    @Test
    public void TC_06_AuthenticationAlert_AutoIt() throws InterruptedException {

        driver.get("http://the-internet.herokuapp.com/");
        String baseAuthenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

        driver.get(getAuthenticationUrl(baseAuthenUrl, "admin", "admin"));
        sleepInSecond(3);
        // Congratulations! You must have the proper credentials.
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='example']/p")).getText().contains("Congratulations! You must have the proper credentials."));
    }
        @AfterClass
    public void afterClass() {
//        driver.quit();
    }

    public void  sleepInSecond(long timeSecond) throws InterruptedException {
        Thread.sleep(timeSecond*1000);
    }

    public  String getAuthenticationUrl(String baseAuthenUrl, String username, String password){
        String[] authenUrlArray = baseAuthenUrl.split("//");
        baseAuthenUrl = authenUrlArray[0] +"//"+ username + ":" + password + "@" + authenUrlArray[1];
        return baseAuthenUrl;
    }
}
