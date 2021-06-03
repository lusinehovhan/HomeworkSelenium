package staff.am;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.String.format;


public class HomePage extends BasePage {
    private WebDriverWait wait;
    private By jobCategoryInput = By.xpath("//select[@id='jobsfilter-category']");
    private By search = By.xpath("//div[@id='home_page_search_section']//button");
    private String jobCategotyLocator = "//select[@id='jobsfilter-category']//*[text()='%s']";
    private String url = "/";

    public HomePage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 20);
    }

    public HomePage open() {
        driver.get(BASE_URL + url);
        return this;
    }

    public void searchJobCategory(String jobCategoryName) {
        WebElement jobCategoryInputFiled = driver.findElement(jobCategoryInput);
        By jobCategory = By.xpath(String.format(jobCategotyLocator, jobCategoryName));
        String jobCategorySearch = driver.findElement(jobCategory).getText();
        jobCategoryInputFiled.sendKeys(jobCategorySearch);
        driver.findElement(search).click();
    }

    public void waitPageLoad() {

        wait.until(ExpectedConditions.elementToBeClickable(search));
    }
}
