package staff.am;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class SearchJobsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By productNumber = By.cssSelector("[data-key]");
    private By checkBoxCheck = By.xpath("//*[@id='jobsfilter-category']//input[@checked='checked']");

    public SearchJobsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
    }

    public String checkedBox(String jobCategoryName) {
        By checkedBox = By.xpath("//*[@id='jobsfilter-category']//*[text()='" + jobCategoryName + "']/preceding::input[1]");
        WebElement checkedBoxElement = driver.findElement(checkedBox);
        try {
            return checkedBoxElement.getAttribute("checked");
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public int checkIfCheckBoxByJobCategory(String jobCategoryName) {
        By checkedBox = By.xpath("//*[@id='jobsfilter-category']//*[text()='" + jobCategoryName + "']/preceding::input[1]");
        WebElement checkedBoxElement = driver.findElement(checkedBox);
        return Integer.parseInt(checkedBoxElement.getAttribute("value"));
    }

    public int checkIfCheckBoxByValue() {
        WebElement findCheckBoxElement = driver.findElement(checkBoxCheck);
        return Integer.parseInt(findCheckBoxElement.getAttribute("value"));
    }

    public int findActualJobsCount() {
        List<WebElement> count = driver.findElements(productNumber);
        return count.size();
    }

    public int findExpectedJobsCount(String jobCategoryName) {
        By jobNumber = By.xpath("//*[@id='jobsfilter-category']//*[text()='" + jobCategoryName + "']/following::span");
        WebElement jobsExpectedElement = driver.findElement(jobNumber);
        String jobsExpectedNum = jobsExpectedElement.getText().replace("(", "").replace(")", "");
        return Integer.parseInt(jobsExpectedNum);
    }
    public void waitForCheckBox() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(checkBoxCheck));
    }
}
