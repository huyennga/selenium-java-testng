package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_22_Wait_FindElement_FindElements {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void TC_01_FindElement() {
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        // - Tìm thấy duy nhất 1 element
        // tìm thấy thì thao tác, ko cần chờ đến hết 10s
        driver.findElement(By.xpath("//input[@type='email']"));


        // - Tìm thấy nhiều hơn 1 elentem
        // => sẽ thao tác với element đầu tiên , ko qtam các note còn lại,  nếu bắt locator sai nó sẽ sai
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("nga@gmail.com");

        // - Ko tìm thấy element nào
        // => có cơ chế tìm laij, nếu trong thời gian đó tìm dc element thì dừng lại, nếu ko tìm thấy thì
        // => + Đánh fail , ko chạy test case tiếp theo hoặc  throw ra 1 ngoại lệ: NoSuchElement
        driver.findElement(By.xpath("//input[@type='abc']"));

    }

    @Test
    public void TC_02_FindElements() {
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        // - Tìm thấy duy nhất 1 element
        // tìm thấy và lưu vào 1 list, ko cần chờ đến hết 10s
        List<WebElement> elements = driver.findElements(By.xpath("//input[@type='email']"));
        System.out.println("List  element number " + elements);

        // - Tìm thấy nhiều hơn 1 elentem
        // => sẽ thao tác với element đầu tiên , ko qtam các note còn lại,  nếu bắt locator sai nó sẽ sai
        elements = driver.findElements(By.xpath("//input[@type='email']"));
        System.out.println("List  element number " + elements);

        // - Ko tìm thấy element nào
        // => có cơ chế tìm laij, nếu trong thời gian đó tìm dc element thì dừng lại, nếu ko tìm thấy thì
        // => + KO dánh fail , vẫn chạy tiếp test case tiếp theo
        // => trả về 1 list rỗng (empty) = 0
        elements = driver.findElements(By.xpath("//input[@type='abc']"));
        System.out.println("List  element number " + elements);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    // public void(long timeSecond) throws InterruptedException {
    //     Thread.sleep(timeSecond * 1000);
    // }
}
