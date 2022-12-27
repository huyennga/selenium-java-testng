package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_15_Random_PopUp {
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
    public void TC_01_RandomPopup() throws InterruptedException {
        //Step
        driver.get("https://www.javacodegeeks.com/");
        sleepInSecond(15);
        WebElement randomPopUp = driver.findElement(By.xpath("//div[@class='lepopup-popup-container']"));
        //Step 2
        if(randomPopUp.isDisplayed()){
            driver.findElement(By.xpath("//input[@class='lepopup-ta-left ']")).sendKeys("nga"+getRandomEmailAddress().nextInt()+"@gmail.com");
            driver.findElement((By.xpath("//a[@data-label='Get the Books']"))).click();
            sleepInSecond(5);
        }
        else {
            System.out.println("move to step 3");
        }
        // Step 3
        driver.findElement(By.xpath("//input[@id='s']")).sendKeys("selenium");
        sleepInSecond(3);
        driver.findElement(By.xpath("//button[@class='search-button']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[@class='post-title']//a[text()='How To Perform Modern Web Testing With TestCafe Using JavaScript And Selenium']")).isDisplayed());

    }

    @Test
    public void TC_02_RandomPopup() throws InterruptedException {
        //Step 1
        driver.get("https://vnk.edu.vn/");
        sleepInSecond(15);
        WebElement randomPopUp = driver.findElement(By.xpath(" //div[@class='tve-leads-conversion-object']"));

        //Step 2
        if(randomPopUp.isDisplayed()){
            driver.findElement((By.xpath("//div[contains(@class,'form_close tcb-local-vars-root')]"))).click();
            sleepInSecond(5);
        }
        else {
            System.out.println("move to step 3");
        }
        // Step 3
        driver.findElement(By.xpath(" //button[@class='btn btn-danger']")).click();
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.xpath(" //p[@class='ez-toc-title' and text()='Mục lục bài viết']")).isDisplayed());

    }


    @Test
    public void TC_03_RandomPopup_NotInDom() throws InterruptedException {

        //Step 1
        driver.get("https://dehieu.vn/");
        sleepInSecond(15);
        List<WebElement> randomPopUp = driver.findElements(By.xpath(" //div[@class='tve-leads-conversion-object']"));

        //Step 2
        if(randomPopUp.size()>0 && randomPopUp.get(0).isDisplayed()){
            driver.findElement((By.xpath("//button[@id='close-popup']"))).click();
            sleepInSecond(3);
        }
        else {
            System.out.println("move to step 3");
        }
        // Step 3
        driver.findElement(By.xpath("  //div[@class='container hachium-header-default']//a[text()='Đăng nhập']")).click();
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.xpath("//h2[@class='title text-center' and text()='Đăng nhập']")).isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
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
