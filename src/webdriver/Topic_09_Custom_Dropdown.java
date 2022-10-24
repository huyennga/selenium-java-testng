package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_09_Custom_Dropdown {
    // khai báo
    WebDriver driver;
    WebDriverWait wait;
    Select select;
    JavascriptExecutor js;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        // khởi tao
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, 30);
        js = (JavascriptExecutor) driver;
//        driver.manage().window().setSize(new Dimension(1366,768));
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_CustomDropdown() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        selectItemInCustomDropdown("//span[@id='number-button']", "ul#number-menu div", String.valueOf(19));
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),"19");
        selectItemInCustomDropdown("//span[@id ='files-button']", "ul#files-menu div", "jQuery.js");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id ='files-button']/span[@class='ui-selectmenu-text']")).getText(),"jQuery.js");
        selectItemInCustomDropdown("//span[@id='speed-button']", "ul#speed-menu div", "Slow");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(),"Slow");
    }
    @Test
    public void TC_02_CustomDropdown() throws InterruptedException {
        driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
        sleepInSecond(1);

        //to perform Scroll on application using Selenium
        scrollToElement("//div[contains(@class,'carousel-item')]");
        sleepInSecond(1);
//        scrollToElement("//div[@class='div-cost-estimates']");
        // custom dropdown
        selectItemInCustomDropdown("//button[@id='selectize-input']", ".dropdown-menu.show a", "CITY G");
        Assert.assertEquals(driver.findElement(By.xpath("//button[@id='selectize-input']")).getText(),"CITY G");
        scrollToElement("//div[@class='div-cost-estimates']");
        // Default dropdown
        select= new Select(driver.findElement(By.xpath("//select[@id ='province']")));
        select.selectByVisibleText("Bắc Kạn");
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Bắc Kạn");

        select= new Select(driver.findElement(By.xpath("//select[@id ='registration_fee']")));
        select.selectByVisibleText("Khu vực III");
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Khu vực III");
    }

    @Test
    public void TC_03_CustomDropdown_ReactJS() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        // custom dropdown
        selectItemInCustomDropdown("//div[@role='listbox']", ".selected.item", "Jenny Hess");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@role='listbox']")).getText(),"Jenny Hess");

    }
    @Test
    public void TC_04_CustomDropdown_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        // custom dropdown
        selectItemInCustomDropdown("//li[@class='dropdown-toggle']", ".dropdown-menu a", "Second Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(),"Second Option");
    }

    @Test
    public void TC_05_CustomDropdown_Editable() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        // custom dropdown
        enterItemInCustomDropdown("//input[@type='text']", ".selected.item", "Albania");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Albania");
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private void selectItemInCustomDropdown(String parentLocator, String childLocator, String textExpectedItem) {
        driver.findElement(By.xpath(parentLocator)).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
        List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
        for (WebElement item : allItems) {
            String textActualItem = item.getText();
            if (textActualItem.equals(textExpectedItem)) {
                item.click();
                break;
            }
        }
    }
    private void enterItemInCustomDropdown(String parentLocator, String childLocator, String textExpectedItem) throws InterruptedException {
        driver.findElement(By.xpath(parentLocator)).clear();
        driver.findElement(By.xpath(parentLocator)).sendKeys("Albania");
        sleepInSecond(1);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
        List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
        for (WebElement item : allItems) {
            String textActualItem = item.getText();
            if (textActualItem.equals(textExpectedItem)) {
                item.click();
                break;
            }
        }
    }

    public void scrollToElement(String xpathLocator){
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(xpathLocator)));
    }

    public void  sleepInSecond(long timeSecond) throws InterruptedException {
        Thread.sleep(timeSecond*1000);
    }
}
