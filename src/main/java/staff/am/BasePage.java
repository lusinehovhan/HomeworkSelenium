package staff.am;

import org.openqa.selenium.WebDriver;

abstract public class BasePage {
    protected WebDriver driver;
    protected final String BASE_URL = "https://www.staff.am";

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
