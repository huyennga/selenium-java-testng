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

public class Topic_11_Default_Radio_Checkbox {
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
    public void TC_01_DefaultCheckbox() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
        //verify checkbox đã dc chọn
        Assert.assertTrue( driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
        // uncheck checkbox
        driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
        //verify checkbox đã bỏ chọn chọn
        Assert.assertFalse( driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).click();
        checkToCheckboxOrRadio("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");

    }

    @Test
    public void TC_02_CheckAll() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

//        driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).clear();
        List<WebElement> allCheckbox  =   driver.findElements(By.xpath("//div[@id='example']//input[@type='checkbox']"));
        // click all checkbox
        for (WebElement checkbox : allCheckbox) {
            if (!checkbox.isSelected() && checkbox.isEnabled()) {
                checkbox.click();
                sleepInSecond(1);
                Assert.assertTrue(checkbox.isSelected());
            }
        }

    }
    public  void checkToCheckboxOrRadio(String xpathLocator){
        if(!driver.findElement(By.xpath(xpathLocator)).isSelected()){
            driver.findElement(By.xpath(xpathLocator)).click();
        }
    }
    public  void uncheckToCheckboxOrRadio(String xpathLocator){
        if(driver.findElement(By.xpath(xpathLocator)).isSelected()){
            driver.findElement(By.xpath(xpathLocator)).click();
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
