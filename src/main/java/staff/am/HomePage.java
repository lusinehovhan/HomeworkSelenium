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
    private By jobCategoryInput = By.xpath("//select[@id='jobsfilter-category']");
    private By search = By.xpath("//div[@id='home_page_search_section']//button");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
    }

    public void searchJobCategory(String jobCategoryName) {
        WebElement jobCategoryInputFiled = driver.findElement(jobCategoryInput);
        By jobCategory = By.xpath("//select[@id='jobsfilter-category']//*[text()='" + jobCategoryName + "']");
        String jobCategorySearch = driver.findElement(jobCategory).getText();
        jobCategoryInputFiled.sendKeys(jobCategorySearch);
        driver.findElement(search).click();
    }

    public void waitClickable() {

        wait.until(ExpectedConditions.elementToBeClickable(search));
    }
}
