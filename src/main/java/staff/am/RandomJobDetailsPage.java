package staff.am;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RandomJobDetailsPage extends BasePage {
    private WebDriverWait wait;
    private String languageOption = "РУС";
    private By jobTitleDetails = By.xpath("//div[@id='job-post']//h2");
    private By language = By.id("lang-dropdown");
    private String languageRussian = "//ul[@id='w4']//a[contains(text(),'%s')]";
    private String url = "/en/";

    public RandomJobDetailsPage(WebDriver driver) {
       super(driver);
        wait = new WebDriverWait(driver, 20);
    }


    public String checkJobTitleDetails() {
        WebElement jobTitleDetailsElement = driver.findElement(jobTitleDetails);
        return jobTitleDetailsElement.getText();

    }

    public String checkJobTitleAfterLanguageChangeRussian() {
        Actions actions = new Actions(driver);
        WebElement languageElement = driver.findElement(language);
        actions.moveToElement(languageElement).perform();
        By languageOptionLocator = By.xpath(String.format(languageRussian, languageOption));
        WebElement languageRussianElement = driver.findElement(languageOptionLocator);
        actions.moveToElement(languageRussianElement);
        actions.click().build().perform();
        WebElement jobTitleDetailsRussianElement = driver.findElement(jobTitleDetails);
        return jobTitleDetailsRussianElement.getText();
    }

    public void waitPageLoad() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(jobTitleDetails));
    }
}
