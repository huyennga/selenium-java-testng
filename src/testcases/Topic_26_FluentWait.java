package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Topic_26_FluentWait {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    WebDriverWait explicitWait;

    FluentWait<WebDriver> fluentWait;
    FluentWait<WebElement> fluentElement;

    long allTime = 15;
    long pollingTime = 100;



    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        FluentWait<WebDriver> fluentWait;
        FluentWait<WebElement> fluentElement;

        long allTime = 15;
        long pollingTime = 100;

    }

    @Test
    public void TC_01_ElementFound() {
        driver.get("https://automationfc.github.io/fluent-wait/");
        WebElement countdown = findElement("//div[@id='javascript_countdown_time']");

        fluentElement = new FluentWait<WebElement>(countdown);
        fluentElement.withTimeout(Duration.ofSeconds(allTime))
                .pollingEvery(Duration.ofMillis(pollingTime))
                .ignoring(NoSuchElementException.class);
        fluentElement.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                String text = webElement.getText();
                return text.endsWith("00");
            }
        });
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void  sleepInSecond(long timeSecond) throws InterruptedException {
        Thread.sleep(timeSecond*1000);
    }

    public String getTimeStamp(){
        Date date = new Date();
        return date.toString();
    }

     public WebElement findElement(String xpathLocator){
         fluentWait = new FluentWait<WebDriver>(driver);
         fluentWait.withTimeout(Duration.ofSeconds(allTime)).pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);
         // apply điều kiện
         return fluentWait.until(new Function<WebDriver, WebElement>() {
             @Override
             public WebElement apply(WebDriver webDriver) {
                 return driver.findElement(By.xpath(xpathLocator));
             }
         });
     }

}
