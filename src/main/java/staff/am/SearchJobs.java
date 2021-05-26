package staff.am;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchJobs {
    private WebDriver driver;
    private WebDriverWait wait;
    private int currentPageNumber = 100;
    private By product = By.cssSelector("[data-key]");
    private HomePage homePage = new HomePage(driver);
    private By prod = By.xpath("//div[@class='categories-sidebar']//label[" + homePage.getJobCategoryOption() + "]//span[1]");
    private By checkBox = By.xpath("//input[@checked='checked']");

    public SearchJobs(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public int findActualJobsCount() {
        List<WebElement> jobsElement = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(product));
        return jobsElement.size();
    }

    public int findExpectedJobsCount() {
        WebElement jobsExpectedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(prod));
        String jobsExpectedNum = jobsExpectedElement.getText().replace("(", "").replace(")", "");
        return Integer.valueOf(jobsExpectedNum);
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }
    public int findCheckBox() {
        WebElement findCheckBoxElement = wait.until(ExpectedConditions.visibilityOfElementLocated(checkBox));
        String checkBox = findCheckBoxElement.getAttribute("value");
        return Integer.parseInt(checkBox);
    }
}
