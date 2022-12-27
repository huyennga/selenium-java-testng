package testcases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_14_UserInteraction_Action {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    Alert alert;
    JavascriptExecutor js;


    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_HoverToolTip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        Actions action = new  Actions(driver);
        action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class = 'ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");

    }
    @Test
    public void TC_02_Hover() {
        driver.get("https://www.myntra.com/");
        Actions action = new  Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//header[@class='desktop-container']//a[@class='desktop-main']"))).perform();
       // driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
        //co the dung ham nay cho clicl
        action.click(driver.findElement(By.xpath("//header[@class='desktop-container']//a[text()='Kids']"))).perform();
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).getText(), "Kids Home Bath");

    }

    @Test
    public void TC_03_Hover() {
        driver.get("https://www.fahasa.com/");
        Actions action = new  Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//span[@class = 'icon_menu']"))).perform();
        action.moveToElement(driver.findElement(By.xpath("//span[text() = 'Làm Đẹp - Sức Khỏe']"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class = 'fhs_menu_content fhs_column_left']")).isDisplayed());

    }

    @Test
    public void TC_04_ClickAndHover() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        Actions action = new Actions(driver);
        List<WebElement> list = driver.findElements(By.xpath("//ol[@class='ui-selectable']/li[@class ='ui-state-default ui-selectee']"));
        action.clickAndHold(list.get(0)).moveToElement(list.get(9)).release().perform();
        sleepInSecond(3);

        List<WebElement> listSelected = driver.findElements(By.xpath("//ol[@class='ui-selectable']/li[@class ='ui-state-default ui-selectee ui-selected']"));

        //Verify so luon
        Assert.assertEquals(listSelected.size(), 6);

        //verify text đã chọn
        List<String> listNumberSelectedExpected = new ArrayList<>();
        listNumberSelectedExpected.add("1");
        listNumberSelectedExpected.add("2");
        listNumberSelectedExpected.add("5");
        listNumberSelectedExpected.add("6");
        listNumberSelectedExpected.add("9");
        listNumberSelectedExpected.add("10");
        for (int i = 0; i < listSelected.size(); i++) {
            Assert.assertEquals(listSelected.get(i).getText(), listNumberSelectedExpected.get(i));
        }
    }
        @Test
        public void TC_05_ClickAndSelected() throws InterruptedException {
            driver.get("https://automationfc.github.io/jquery-selectable/");
            Actions action = new  Actions(driver);
            List<WebElement> list = driver.findElements(By.xpath("//ol[@class='ui-selectable']/li[@class ='ui-state-default ui-selectee']"));
            // nhấn phím ctril
            action.keyDown(Keys.CONTROL).perform();
            action.click(list.get(0)).click(list.get(1)).click(list.get(2)).perform();
            sleepInSecond(3);
            // nhả phím ctrl
            action.keyDown(Keys.CONTROL).perform();

            //Verify số lượng
            List<WebElement> listSelected =  driver.findElements(By.xpath("//ol[@class='ui-selectable']/li[@class ='ui-state-default ui-selectee ui-selected']"));
            Assert.assertEquals(listSelected.size(), 3);

            //verify text đã chọn
            List<String>  listNumberSelectedExpected = new ArrayList<>();
            listNumberSelectedExpected.add("1");
            listNumberSelectedExpected.add("2");
            listNumberSelectedExpected.add("3");

            for(int i =0; i< listSelected.size(); i++){
                Assert.assertEquals(listSelected.get(i).getText(), listNumberSelectedExpected.get(i));
            }
    }


    @Test
    public void TC_06_DoubleClick() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Scroll down to element
        scrollToElement("///button[@ondblclick='doubleClickMe()']");
        //double click
        Actions action = new  Actions(driver);
        action.doubleClick(driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"))).perform();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(),"Hello Automation Guys!");

    }

    @Test
    public void TC_07_RightClick() throws InterruptedException {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        Actions action = new  Actions(driver);
        //click chuot phai vao context menu
        action.contextClick(driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"))).perform();
        sleepInSecond(2);
        // move den Quit
        action.moveToElement(driver.findElement(By.xpath(" //ul//li/span[text()='Quit']"))).perform();
        // verify
        Assert.assertEquals(driver.findElement(By.xpath("//ul//li/span[text()='Quit']")).getText(), "Quit");
        Assert.assertTrue(driver.findElement(By.xpath("//ul//li/span[text()='Quit']")).isDisplayed());

        action.click(driver.findElement(By.xpath("//ul//li/span[text()='Quit']"))).perform();
        // switch den alert
        alert = driver.switchTo().alert();
        alert.accept();
        // verifu quit is invisible
        Assert.assertFalse(driver.findElement(By.xpath("//ul//li/span[text()='Quit']")).isDisplayed());
//        Assert.assertEquals(driver.findElement(By.xpath("//ul//li/span[text()='Quit']")).getAttribute(), "context-menu-item context-menu-icon context-menu-icon-quit context-menu-visible");


    }

   // @Test
   // public void TC_07_DragDrop() throws InterruptedException {
   //     driver.get("https://automationfc.github.io/drag-drop-html5/");
   //     WebElement webElementA= driver.findElement(By.xpath("//div[@id='column-a']"));
   //     WebElement webElementB = driver.findElement(By.xpath("//div[@id='column-b']"));
   //
   //     sleepInSecond(3);
   //     // cach 1
   //     Actions action = new  Actions(driver);
   //     action.clickAndHold(webElementA).moveToElement(webElementB).release(webElementB).perform();
   //     sleepInSecond(3);
   //     //cach 2
   //     action.dragAndDrop(webElementA,webElementB).build().perform();
   // }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void scrollToElement(String xpathLocator){
       // js = (JavascriptExecutor) driver;
       // js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(xpathLocator)));
    }

    public void sleepInSecond(long timeSecond) throws InterruptedException {
        Thread.sleep(timeSecond*1000);
    }
}
