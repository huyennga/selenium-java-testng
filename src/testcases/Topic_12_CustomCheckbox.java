package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_12_CustomCheckbox {
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
    public void TC_01_CustomCheckbox() throws InterruptedException {
        driver.get("https://material.angular.io/components/checkbox/examples");

        // kiem tra checkbox da chon
        WebElement element = driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        sleepInSecond(2);

        Assert.assertTrue(element.isSelected());
        // bo chon checkbox
//        uncheckToCheckbox("//span[@class='mat-checkbox-inner-container'");
        // kiem tra checkbox da chon

    }

    @Test
    public void TC_02_Customradio() {
        driver.get("https://material.angular.io/components/radio/examples");
        WebElement element = driver.findElement(By.xpath("//span[contains(text(),' Spring ')]/preceding-sibling::span/input"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        Assert.assertTrue(element.isSelected());
    }
    @Test
    public void TC_03_CustomCheckbox_Google() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        WebElement element = driver.findElement(By.xpath("//div[@aria-label='Cần Thơ']"));
        //verify truoc khi click
        element.getAttribute("aria-checked");
        Assert.assertEquals(element.getAttribute("aria-checked"), "false");
        Assert.assertTrue( driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']")).isDisplayed());
        // click
        element.click();
        //verify sau khi click
        Assert.assertEquals(element.getAttribute("aria-checked"), "true");
        Assert.assertTrue( driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());

    }

    public void  checkToRadio(String xpath){
        if(!driver.findElement(By.xpath(xpath)).isSelected()){
            driver.findElement(By.xpath(xpath)).click();
        }
    }
    public void  checkToCheckbox(String xpath){
        if(!driver.findElement(By.xpath(xpath)).isSelected()){
            driver.findElement(By.xpath(xpath)).click();
        }
    }
    public void  uncheckToCheckbox(String xpath){
        if(driver.findElement(By.xpath(xpath)).isSelected()){
            driver.findElement(By.xpath(xpath)).click();
        }
    }



    @AfterClass
    public void afterClass() {
//        driver.quit();
    }

    public void  sleepInSecond(long timeSecond) throws InterruptedException {
        Thread.sleep(timeSecond*1000);
    }
}
