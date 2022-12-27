package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Topic_25_Mix_Explicit_Implicit {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_ElementFound() {
        // Element có xuất hiện và ko cần chờ hết timeout
        // Dù có wait cả hai loại thì không ảnh hưởng

        explicitWait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Implicit Wait: chỉ apply cho việc find element/findelements
        // Explicit Wait: cho các điều kiện của element

        driver.get("https://www.facebook.com/");

        // Explicit
        System.out.println("thời gian bắt đầu " + getTimeStamp());
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email']")));
        System.out.println("thời gian kết thúc " + getTimeStamp());

        // implicit
        System.out.println("thời gian bắt đầu " + getTimeStamp());
        driver.findElement(By.xpath("//input[@name='email']"));
        System.out.println("thời gian kết thúc " + getTimeStamp());
    }

    @Test
    public void TC_02_Element_NotFound_Implicit() {

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        explicitWait = new WebDriverWait(driver, 5);

        driver.get("https://www.facebook.com/");

        // output
        // có cơ chế tìm lại 0.5s
        // khi hết timeout sẽ fail test case
        // throw ra 1 exception: NosuchElement

        // implicit
        System.out.println("thời gian bắt đầu " + getTimeStamp());
        try{
            driver.findElement(By.xpath("//input[@name='not found']"));
        }catch (Exception e){
            System.out.println("thời gian kết thúc " + getTimeStamp());
        }
    }

    @Test
    public void TC_03_Element_NotFound_Implicit_Explicit() {

        // 3.1 Implicit  = Explicit
        // 3.2 Implicit < Expliciy
        // 3.3 Implicit > Explicit

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        explicitWait = new WebDriverWait(driver, 5);

        driver.get("https://www.facebook.com/");

        // implicit
        System.out.println("thời gian bắt đầu implicit " + getTimeStamp());
        try{
            driver.findElement(By.xpath("//input[@name='not found']"));
        }catch (Exception e){
            System.out.println("thời gian kết thúc implicit " + getTimeStamp());
        }

        // Explicit
        System.out.println("thời gian bắt đầu Explicit " + getTimeStamp());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='not found']")));
        }catch (Exception e) {
            System.out.println("thời gian kết thúc Explicit " + getTimeStamp());
        }
    }

    @Test
    public void TC_04_Element_NotFound_Explicit_By() {

        explicitWait = new WebDriverWait(driver, 5);

        driver.get("https://www.facebook.com/");

        // Explicit - By là tham số nhận vào của hàm explicit
        // Implicit = 0
        // tổng thời gian = explicit
        System.out.println("thời gian bắt đầu Explicit " + getTimeStamp());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='not found']")));
        }catch (Exception e) {
            System.out.println("thời gian kết thúc Explicit " + getTimeStamp());
        }
    }

    @Test
    public void TC_04_Element_NotFound_Explicit_Element() {

        explicitWait = new WebDriverWait(driver, 5);

        driver.get("https://www.facebook.com/");

        // Explicit - Element là tham số nhận vào của hàm explicit
        // Implicit = 0
        // tổng thời gian = Implicit
        System.out.println("thời gian bắt đầu Explicit " + getTimeStamp());
        try {
            explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='not found']"))));
        }catch (Exception e) {
            System.out.println("thời gian kết thúc Explicit " + getTimeStamp());
        }
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
}
