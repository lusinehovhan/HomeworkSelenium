package Staff.am.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import staff.am.HomePage;
import staff.am.SearchJobs;


public class StaffamTest {
    private WebDriver driver;
    private HomePage homePage;
    private SearchJobs searchJobs;

    @BeforeClass
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.staff.am");
        homePage = new HomePage(driver);
        homePage.searchJobCategory();
    }

    @Test
    public void testCheckBox() throws InterruptedException {
        searchJobs = new SearchJobs(driver);
        Assert.assertEquals(homePage.getJobCategoryOption(), searchJobs.findCheckBox(), "The checked checkbox with attribute value: " + homePage.getJobCategoryOption() + " does not match to the expected job title with attribute value: " + searchJobs.findCheckBox());
    }

    @Test
    public void testJobsNumber() throws InterruptedException {
        searchJobs = new SearchJobs(driver);
        if (searchJobs.findExpectedJobsCount() < searchJobs.getCurrentPageNumber()) {
            Assert.assertEquals(searchJobs.findActualJobsCount(), searchJobs.findExpectedJobsCount(), "The actual number of products: " + searchJobs.findActualJobsCount() + " does not match expected number: " + searchJobs.findExpectedJobsCount());
        } else {
            Assert.assertEquals(searchJobs.findActualJobsCount(), searchJobs.getCurrentPageNumber(), "The actual number of products: " + searchJobs.findActualJobsCount() + " does not match expected total number that should be on the current page: " + searchJobs.getCurrentPageNumber());
        }
    }

    @AfterClass
    public void quiteTests() {
        driver.quit();
    }
}
