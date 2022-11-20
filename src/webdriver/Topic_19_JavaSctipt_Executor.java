package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_19_JavaSctipt_Executor {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        jsExecutor = (JavascriptExecutor) driver;

    }

    @Test
    public void TC_01_Executor() throws InterruptedException {
        // go to page and verify domain name
        navigateToUrlByJS("http://live.techpanda.org/");
        sleepInSecond(3);
        Assert.assertEquals(getDomainName(), "live.techpanda.org");
        // verify URL
        Assert.assertEquals(getURL(), "http://live.techpanda.org/");
        // open mobile (click)
        clickToElementByJS("//a[text()='Mobile']");
        sleepInSecond(3);
        // add product to Card
        hightlightElement("//a[@title ='Samsung Galaxy']/parent::h2/following-sibling :: div[@class='actions']//button[@class ='button btn-cart']");
        clickToElementByJS("//a[@title ='Samsung Galaxy']/parent::h2/following-sibling :: div[@class='actions']//button[@class ='button btn-cart']");

        Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
        // open customer service
        hightlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");

        executeForBrowser("return document.title");
        Assert.assertEquals( executeForBrowser("return document.title"), "Customer Service");

        // scroll cuoi trang
        scrollToElementOnDown("//input[@id='newsletter']");
        // input email and submit
        hightlightElement("//input[@id='newsletter']");
        sendkeyToElementByJS("//input[@id='newsletter']", "nga@gmail.com");
        clickToElementByJS("//button[@type='submit']//span[text()='Subscribe']");
        // verify message
        Assert.assertEquals(getElement("//li[@class='success-msg']").getText(), "Thank you for your subscription.");
        // navigate to https://demo.guru99.com/v4/
        navigateToUrlByJS("https://demo.guru99.com/v4/");


    }


    @Test
    public void TC_02_HTML5_Validation_Message() throws InterruptedException {
        driver.get("https://warranty.rode.com/");

        String firstName = "//input[@id='firstname']";
        String surName = "//input[@id='surname']";
        String email = "//div[contains(text(), 'Register')]/following-sibling:: div//input[@id='email']";
        String passWord = "//div[contains(text(), 'Register')]/following-sibling:: div//input[@id='password']";
        String passwordConfirm = "//input[@id='password-confirm']";
        String register = "//button[contains(text(), 'Register')]";

        clickToElementByJS(register);
        sleepInSecond(2);
        Assert.assertEquals( getElementValidationMessage(firstName), "Please fill out this field.");
        sendkeyToElementByJS(firstName, "Nga");

        clickToElementByJS(register);
        sleepInSecond(2);
        Assert.assertEquals( getElementValidationMessage(surName), "Please fill out this field.");
        sendkeyToElementByJS(surName, "Hoang");

        clickToElementByJS(register);
        sleepInSecond(2);
        Assert.assertEquals( getElementValidationMessage(email), "Please fill out this field.");
        sendkeyToElementByJS(email, "nga"+getRandomEmailAddress().nextInt()+"@gmail.com");

        clickToElementByJS(register);
        sleepInSecond(2);
        Assert.assertEquals( getElementValidationMessage(passWord), "Please fill out this field.");
        sendkeyToElementByJS(passWord, "abc@123");

        clickToElementByJS(register);
        sleepInSecond(2);
        Assert.assertEquals( getElementValidationMessage(passwordConfirm), "Please fill out this field.");
        sendkeyToElementByJS(passwordConfirm, "abc@123");

        }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }



    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }
    public  String getDomainName(){
        return (String) jsExecutor.executeScript("return document.domain;");
    }
    public  String getURL(){
        return (String) jsExecutor.executeScript("return document.URL;");
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public void hightlightElement(String locator) throws InterruptedException {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");

        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
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
