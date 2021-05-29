package Staff.am.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
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
    public void testStaffAM() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        searchJobs = new SearchJobs(driver);
        searchJobs.waitForPageLoad();
        searchJobs.waitForCheckBox();
        softAssert.assertNotNull(searchJobs.checkedBox(), "This selected job catigory does not have checked box");

        homePage = new HomePage(driver);
        homePage.waitForPageLoad();
        softAssert.assertEquals(getJobCategoryOption(), searchJobs.findCheckBox(), "The checked checkbox with attribute value: " + getJobCategoryOption() + " does not match to the expected job title with attribute value: " + searchJobs.findCheckBox());

        searchJobs.waitForProductsList();
        if (searchJobs.findExpectedJobsCount() < SearchJobs.currentPageNum) {
            softAssert.assertEquals(searchJobs.findActualJobsCount(), searchJobs.findExpectedJobsCount(), "The actual number of products: " + searchJobs.findActualJobsCount() + " does not match expected number: " + searchJobs.findExpectedJobsCount());
        } else {
            softAssert.assertEquals(searchJobs.findActualJobsCount(), SearchJobs.currentPageNum, "The actual number of products: " + searchJobs.findActualJobsCount() + " does not match expected total number that should be on the current page: " + SearchJobs.currentPageNum);
        }
        softAssert.assertAll();
    }

    @AfterClass
    public void quiteTests() {

        driver.quit();
    }
}
