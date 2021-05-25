package staff.am;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;
    private static int jobCategoryOption = 17;
    private By jobCategoryInput = By.xpath("//select[@id='jobsfilter-category']");
    private By jobCategory = By.xpath("//select[@id='jobsfilter-category']//option[@value='" + jobCategoryOption + "']");
    private By search = By.xpath("//button[@data-url='/en/site/search']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchJobCategory() {
        WebElement jobCategoryInputFiled = driver.findElement(jobCategoryInput);
        String searchJobCategory = driver.findElement(jobCategory).getText();
        jobCategoryInputFiled.sendKeys(searchJobCategory);
        driver.findElement(search).click();
    }

    public int getJobCategoryOption() {
        return jobCategoryOption;
    }
}
