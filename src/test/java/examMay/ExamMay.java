package examMay;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

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
        driver.get("https://www.staff.am");

        By jobCategory = By.xpath("//select[@id='jobsfilter-category']");
        WebElement jobCategoryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(jobCategory));
        jobCategoryElement.sendKeys("Production" + Keys.ENTER);

        By search = By.xpath("//button[@data-url='/en/site/search']");
        WebElement searchElement = wait.until(ExpectedConditions.elementToBeClickable(search));
        searchElement.click();

        By product = By.cssSelector("[data-key]");
        List<WebElement> productElement = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(product));
        int productSize = productElement.size();

        By prod = By.xpath("//div[@class='categories-sidebar']//label[23]//span[1]");
        WebElement prodChecked = wait.until(ExpectedConditions.visibilityOfElementLocated(prod));
        String prodCheckedNumber = prodChecked.getText();
        String prodCh = prodCheckedNumber.replace("(", "").replace(")", "");
        int productCh = Integer.valueOf(prodCh);

        Assert.assertEquals(productSize, productCh, "The actual number of products: " + productSize + " does not match expected number: " + productCh);


        driver.quit();
    }
}