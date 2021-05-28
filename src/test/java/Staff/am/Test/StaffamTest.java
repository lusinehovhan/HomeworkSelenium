package Staff.am.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import staff.am.HomePage;
import staff.am.SearchJobs;

import static staff.am.HomePage.getJobCategoryOption;


public class StaffamTest {
    private WebDriver driver;
    private HomePage homePage;
    private SearchJobs searchJobs;

    @BeforeSuite
    public void chromeSetup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "src\\main\\resources\\chromedriver.exe");
    }

    @BeforeClass
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.staff.am");
        homePage = new HomePage(driver);
        homePage.waitForPageLoad();
        homePage.searchJobCategory();
    }

    @Test
    public void testIfCheckBoxIsChecked()throws InterruptedException{
        searchJobs = new SearchJobs(driver);
        searchJobs.waitForPageLoad();
        Assert.assertNotNull(searchJobs.checkedBox(), "This selected job catigory does not have checked box");
    }

    @Test
    public void testCheckBox() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.waitForPageLoad();
        searchJobs = new SearchJobs(driver);
        searchJobs.waitForCheckBox();
        Assert.assertEquals(getJobCategoryOption(), searchJobs.findCheckBox(), "The checked checkbox with attribute value: " + getJobCategoryOption() + " does not match to the expected job title with attribute value: " + searchJobs.findCheckBox());
    }

    @Test
    public void testJobsNumber() throws InterruptedException {
        searchJobs = new SearchJobs(driver);
        searchJobs.waitForProductsList();
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
