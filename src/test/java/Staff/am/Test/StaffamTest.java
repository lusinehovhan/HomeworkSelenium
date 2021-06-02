package Staff.am.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import staff.am.HomePage;
import staff.am.SearchJobsPage;


public class StaffamTest {
    private WebDriver driver;
    private HomePage homePage;
    private SearchJobsPage searchJobsPage;
    private String jobCategoryOption = "Sales/service management";
    private int currentPageNum = 100;

    @BeforeSuite
    public void chromeSetup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "src\\main\\resources\\chromedriver.exe");
    }

    @Test
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.staff.am");

        homePage = new HomePage(driver);
        homePage.waitClickable();
        homePage.searchJobCategory(jobCategoryOption);
    }

    @Test
    public void testStaffAM() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        searchJobsPage = new SearchJobsPage(driver);

        searchJobsPage.waitForCheckBox();
        softAssert.assertNotNull(searchJobsPage.checkedBox(jobCategoryOption), "This selected job catigory does not have checked box");

        softAssert.assertEquals(searchJobsPage.checkIfCheckBoxByJobCategory(jobCategoryOption), searchJobsPage.checkIfCheckBoxByValue(),"The checked checkbox does not matched to job category: " + jobCategoryOption);

        if (searchJobsPage.findExpectedJobsCount(jobCategoryOption) < currentPageNum) {
            softAssert.assertEquals(searchJobsPage.findActualJobsCount(), searchJobsPage.findExpectedJobsCount(jobCategoryOption), "The actual number of products: " + searchJobsPage.findActualJobsCount() + " does not match expected number: " + searchJobsPage.findExpectedJobsCount(jobCategoryOption));
        } else {
            softAssert.assertEquals(searchJobsPage.findActualJobsCount(), currentPageNum, "The actual number of products: " + searchJobsPage.findActualJobsCount() + " does not match expected total number that should be on the current page: " + currentPageNum);
        }
        softAssert.assertAll();
    }

    @AfterClass
    public void quitTest() {

        driver.quit();
    }
}
