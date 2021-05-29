package staff.am;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static int jobCategoryOption = 16;
    private By jobCategoryInput = By.xpath("//select[@id='jobsfilter-category']");
    private By jobCategory = By.xpath("//select[@id='jobsfilter-category']//option[@value='" + jobCategoryOption + "']");
    private By search = By.xpath("//button[@data-url='/en/site/search']");
    private By pageLoad = By.cssSelector(".staff_body");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public void searchJobCategory() {
        WebElement jobCategoryInputFiled = driver.findElement(jobCategoryInput);
        String jobCategorySearch = driver.findElement(jobCategory).getText();
        jobCategoryInputFiled.sendKeys(jobCategorySearch);
        WebElement searchButton = driver.findElement(search);
        searchButton.click();
    }

    public static int getJobCategoryOption() {

        return jobCategoryOption;
    }

    public void waitForPageLoad() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(pageLoad));
    }
}
