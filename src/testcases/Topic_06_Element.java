package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_Element {
    WebDriver driver;
    WebElement element;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_ValidateIsDisplayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
       WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
       if(emailTextbox.isDisplayed()){
           emailTextbox.sendKeys("Automation Testing");
           System.out.println("Email textbox is displayed");
       }
       else
           System.out.println("Email textbox is not displayed");

        WebElement educationTextbox = driver.findElement(By.xpath("//textarea[@id='edu']"));
        if(educationTextbox.isDisplayed()){
            educationTextbox.sendKeys("Automation Testing");
            System.out.println("Education textbox is displayed");
        }
        else
            System.out.println("Education textbox is not displayed");

        WebElement ageRadio = driver.findElement(By.xpath("//input[@id='under_18']"));
        if(ageRadio.isDisplayed()){
            ageRadio.click();
            System.out.println("Age radio is displayed");
        }
        else
            System.out.println("Age radio is not displayed");

        WebElement name = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
        if(name.isDisplayed()){
            System.out.println("Name is displayed");
        }
        else
            System.out.println("Name is not displayed");
    }


    @Test
    public void TC_02_ValidateIsEnabled() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
        if(emailTextbox.isEnabled()){
            emailTextbox.sendKeys("Automation Testing");
            System.out.println("Email textbox is enabled");
        }
        else
            System.out.println("Email textbox is disable");

        WebElement educationTextbox = driver.findElement(By.xpath("//textarea[@id='edu']"));
        if(educationTextbox.isEnabled()){
            educationTextbox.sendKeys("Automation Testing");
            System.out.println("Education textbox is enable");
        }
        else
            System.out.println("Education textbox is not disable");

        WebElement ageRadio = driver.findElement(By.xpath("//input[@id='under_18']"));
        if(ageRadio.isEnabled()){
            ageRadio.click();
            System.out.println("Age radio is enable");
        }
        else
            System.out.println("Age radio is disable");

        WebElement name = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
        if(name.isDisplayed()){
            System.out.println("Name is enable");
        }
        else
            System.out.println("Name is not disable");

        WebElement job1 = driver.findElement(By.xpath("//select[@id='job1']"));
        if(job1.isEnabled()){
            job1.click();
            System.out.println("job is enable");
        }
        else
            System.out.println("job is disable");

        WebElement interest = driver.findElement(By.xpath("//input[@id='development']"));
        if(interest.isEnabled()){
            interest.isSelected();
            System.out.println("interest is enable");
        }
        else
            System.out.println("interest is disable");

        WebElement slider = driver.findElement(By.xpath("//input[@id='slider-1']"));
        if(slider.isEnabled()){
            System.out.println("slider is enable");
        }
        else
            System.out.println("slider is disable");

        WebElement password = driver.findElement(By.xpath("//input[@id='disable_password']"));
        if(password.isEnabled()){
            System.out.println("password is enable");
        }
        else
            System.out.println("password is disable");

        WebElement ageDisable = driver.findElement(By.xpath("//input[@id='radio-disabled']"));
        if(ageDisable.isEnabled()){
            System.out.println("ageDisable is enable");
        }
        else
            System.out.println("ageDisable is disable");

    }

    @Test
    public void TC_03_ValidateIsSelected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement age = driver.findElement(By.xpath("//input[@id='under_18']"));
        age.click();
        if(age.isSelected()){
            System.out.println("age is selected");
        } else {
            System.out.println("age is de-selected");
        }
        WebElement language = driver.findElement(By.xpath("//input[@id='java']"));
        language.click();
        if(language.isSelected()){
            System.out.println("language is selected");
        } else {
            System.out.println("language is de-selected");
        }

    }

    @Test
    public void TC_04_mailChimp() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");
        WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
        email.sendKeys("nga.netco@gmail.com");

        WebElement password = driver.findElement(By.xpath("//input[@id='new_password']"));
        password.sendKeys("a");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        password.clear();
        password.sendKeys("A");
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        password.clear();
        password.sendKeys("1");
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        password.clear();
        password.sendKeys("@");
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        password.clear();
        password.sendKeys("bbaabbcc");
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());

        //check Full
        password.clear();
        password.sendKeys("aA1@aaaa");
        sleepInSecond(2);
    }

    public void  sleepInSecond(long timeSecond) throws InterruptedException {
        Thread.sleep(timeSecond*1000);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
