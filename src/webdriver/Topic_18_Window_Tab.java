package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_18_Window_Tab {
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
    public void TC_01_Tabs() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

        // switch to other tab
        String parentTabId = driver.getWindowHandle();
        Set<String> allWindowId = driver.getWindowHandles();
        switchToOtherTab(parentTabId,allWindowId);
        sleepInSecond(2);

        // verify title
        Assert.assertEquals(driver.getTitle(), "Google");

        // switch back to parent tab
        String otherTabId = driver.getWindowHandle();
        switchToOtherTab(otherTabId,allWindowId);
        sleepInSecond(2);

        // click on Facebook link, switch qu tab mới
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSecond(2);

        // switch to facebook
        switchToOtherTabByPageTitle("Facebook – log in or sign up");
        sleepInSecond(1);
        Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");

        // switch to parent page
        switchToOtherTabByPageTitle("SELENIUM WEBDRIVER FORM DEMO");

        // click on TIKI link
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        sleepInSecond(2);

        //switch to Tiki tab and verify title
        switchToOtherTabByPageTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        sleepInSecond(1);
        Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        // close all tabs exclude parent tab
        closeTabs(parentTabId);
    }

    @Test
    public void TC_02_Window() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//nav[@id='nav']//a[text()='Mobile']")).click();

        // add Sony to compare
        driver.findElement(By.xpath("//a[@title ='Sony Xperia']/parent::h2/following-sibling :: div[@class='actions']//a[@class ='link-compare']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']")).getText(), "The product Sony Xperia has been added to comparison list.");

        // add Samsung to compare
        driver.findElement(By.xpath("//a[@title ='Samsung Galaxy']/parent::h2/following-sibling :: div[@class='actions']//a[@class ='link-compare']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']")).getText(), "The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.xpath("//button[@class='button']//span[text()='Compare']")).click();

        //switch to Tiki tab and verify title
        switchToOtherTabByPageTitle(" Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
        driver.close();

        // switch to parent page
        switchToOtherTabByPageTitle("Mobile");
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();

        driver.switchTo().alert().accept();
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']")).getText(), "The comparison list was cleared.");



    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void  sleepInSecond(long timeSecond) throws InterruptedException {
        Thread.sleep(timeSecond*1000);
    }

    public  void  switchToOtherTab(String tabId, Set<String> allTabIds) throws InterruptedException {
        for(String id:allTabIds){
            if(!id.equals(tabId)){
                driver.switchTo().window(id);
                sleepInSecond(2);
            }
        }
    }

    public  void switchToOtherTabByPageTitle(String expectedTitle){
        Set<String> allTabIds = driver.getWindowHandles();
        for (String id : allTabIds){
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if(expectedTitle.equals(actualTitle)){
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public  void closeTabs(String parentId){
        Set<String> allTabsId = driver.getWindowHandles();
        for (String id : allTabsId){
            if(!id.equals(parentId)){
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentId);
    }
}
