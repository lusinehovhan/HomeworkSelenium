package staff.am;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchJobs {
    private WebDriver driver;
    private WebDriverWait wait;
    public static int currentPageNum = 100;
    private By product = By.cssSelector("[data-key]");
    private By prod = By.xpath("//div[@class='categories-sidebar']//label[" + HomePage.getJobCategoryOption() + "]//span");
    private By checkBox = By.xpath("//div[@class='categories-sidebar']//label[" + HomePage.getJobCategoryOption() + "]//input");
    private By checkBoxCheck = By.xpath("//div[@class='categories-sidebar']//label//input[@checked='checked']");
    private By pageLoad = By.cssSelector(".staff_body");

    public SearchJobs(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public String checkedBox() {
        WebElement checkedBox = driver.findElement(checkBox);
        try {
            return checkedBox.getAttribute("checked");
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public int findCheckBox() {
        WebElement findCheckBoxElement = driver.findElement(checkBoxCheck);
        String checkBox = findCheckBoxElement.getAttribute("value");
        return Integer.parseInt(checkBox);
    }

    public int findActualJobsCount() {
        return driver.findElements(product).size();
    }

    public int findExpectedJobsCount() {
        WebElement jobsExpectedElement = driver.findElement(prod);
        String jobsExpectedNum = jobsExpectedElement.getText().replace("(", "").replace(")", "");
        return Integer.parseInt(jobsExpectedNum);
    }

    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageLoad));
    }

    public void waitForProductsList() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(product));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(prod));
    }

    public void waitForCheckBox() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(checkBox));
    }
}
