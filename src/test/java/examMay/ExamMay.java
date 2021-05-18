package examMay;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ExamMay {
    private WebDriver driver;
    private static WebDriverWait wait;

    @Test
    public void segittup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
        driver.get("https://www.google.com");





        driver.quit();
    }
}