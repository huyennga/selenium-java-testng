package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_24_Explicit_Wait_II {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    WebDriverWait explicitWait;

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
    public void TC_01_Ajax_Loading() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        explicitWait = new WebDriverWait(driver, 15);

        // wait cho date time picker hiển thị
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='RadAjaxPanel inlinePanel']")));

        // wait cho ngày 19 is clickable
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("  //a[text()='19']")));

        // verify ko ngày nào dc chọn
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(), "No Selected Dates to display.");

        // click vào ngày 19
        driver.findElement(By.xpath("  //a[text()='19']")).click();

        // wait cho Ajax loading biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id, 'ContentPlaceholder1_RadCalendar1')]//div[@class='raDiv']")));

        // wait cho ngày dc chọn dc phép click trở lại
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(@class,'rcSelected')]/a[text()='19']")));

        // verify Selected date là ngày
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(), "Saturday, November 19, 2022");

    }

    @Test
    public void TC_02_UploadFile() throws InterruptedException {
        driver.get("https://gofile.io/uploadFiles");

        explicitWait = new WebDriverWait(driver, 15);

        // wait cho add file  hiển thị
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'btn-lg mb-1 uploadButton')]")));

        // load file lên
//        driver.findElement(By.xpath("//button[contains(@class, 'btn-lg mb-1 uploadButton')]")).sendKeys(image01FilePath);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image01FilePath + "\n" + image02FilePath);

        // wait cho progress bar biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='progress-bar bg-primary']")));

        // wait cho message upload thanh cong dc hien thi
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //div[@class='callout callout-success']/h5")));

        // verify message displayed
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='callout callout-success']/h5")).getText(), "Your files have been successfully uploaded");

        // wait link image is clickable
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")));

        // lick on link to view image
        driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).click();


        // switch to other tab
        String parentTabId = driver.getWindowHandle();
        Set<String> allWindowId = driver.getWindowHandles();
        switchToOtherTab(parentTabId, allWindowId);
        sleepInSecond(2);

        // verify title
        Assert.assertEquals(driver.getTitle(), "Gofile - Free file sharing and storage platform");

        // verify image is shown
        Assert.assertEquals(driver.findElement(By.xpath(String.format("//span[@class='contentName' and text()='%s']", image01))).getText(), image01);
        Assert.assertEquals(driver.findElement(By.xpath(String.format("//span[@class='contentName' and text()='%s']", image02))).getText(), image02);
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond(long timeSecond) throws InterruptedException {
        Thread.sleep(timeSecond * 1000);
    }

    public  void  switchToOtherTab(String tabId, Set<String> allTabIds) throws InterruptedException {
        for(String id:allTabIds){
            if(!id.equals(tabId)){
                driver.switchTo().window(id);
                sleepInSecond(2);
            }
        }
    }


}
