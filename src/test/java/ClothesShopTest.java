import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.base.Function;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertTrue;

public class ClothesShopTest {

    private static ExtentReports report;
    private SpreadSheetReader spreadSheetReader;
    private JavascriptExecutor js;
    private WebDriver webDriver;

    @BeforeClass
    public static void init(){
        report = new ExtentReports();
        String fileName = "ClothesStoreReport" + ".html";
        String filePath = System.getProperty("user.dir") + File.separatorChar + fileName;
        report.attachReporter(new ExtentHtmlReporter(filePath));
    }

    @Before
    public void setup(){
        spreadSheetReader = new SpreadSheetReader("properties.xlsx");
    }

    @After
    public void tearDown(){
        BrowserFactory.closeAllDriver();
    }

    @AfterClass
    public static void destroy(){
        report.flush();
    }

    @Test
    public void signUp(){

        ExtentTest signUpTestLog = report.createTest("Testing Sign Up Function");
        signUpTestLog.log(Status.INFO, "Testing the sign up function on the website. Will pass in a series of dummy values and then attempt to sign in on that account once it is created.");
        List<String> inputs;
        signUpTestLog.log(Status.DEBUG, "Reading in the url of the page from the properties.xlsx file, on the homepage sheet.");
        inputs = spreadSheetReader.readRow(1, "homepage");
        String browser = inputs.get(0);
        webDriver = BrowserFactory.getBrowser(browser);
        webDriver.manage().window().maximize();
        String url = inputs.get(1);
        signUpTestLog.log(Status.DEBUG, "URL found. The URL we are navigating to is: " + url);
        webDriver.navigate().to(url);

        signUpTestLog.log(Status.INFO, "Browser opened at desired URL. Going to sign in page");
        Homepage homepage = PageFactory.initElements(webDriver, Homepage.class);
        homepage.clickSignIn();
        signUpTestLog.log(Status.DEBUG, "Navigated to: " + webDriver.getCurrentUrl());
        signUpTestLog.log(Status.INFO, "Entering email to sign up with and then clicking \"Create an Account\" button.");
        SignInSignUpPage signInSignUpPage = PageFactory.initElements(webDriver, SignInSignUpPage.class);
        signUpTestLog.log(Status.DEBUG, "Getting data to enter from spreadsheet \"properties.xlsx\". Assigning the email from the data in the spreadsheet and then entering it into the " +
                        "text box through the WebElement.sendKeys() method. Then clicking the \"Create an Account\" button in order to display the full sign up page." );
        inputs = spreadSheetReader.readRow(1, "signup");
        String email = inputs.get(0);
        signInSignUpPage.enterSignUpEmail(email);
        signInSignUpPage.clickSignUp();
        signUpTestLog.log(Status.DEBUG, "Wait needed in order to allow the sign up element to appear on the page. This is achieved by an explicit wait, on the condition that the field to enter first name into the sign up. " +
                "This allows the fields time to appear on the screen before we attempt to edit them.");
        WebElement waitForFirstName = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#customer_firstname")));

        signUpTestLog.log(Status.DEBUG, "Generating Strings from spreadsheet to pass into the methods that allow the test to manipulate the fields on the register page and input the desired actions.");
        String firstNameToEnter = inputs.get(1);
        signInSignUpPage.enterFirstName(firstNameToEnter);
        String lastNameToEnter = inputs.get(2);
        signInSignUpPage.enterLastName(lastNameToEnter);
        String password = inputs.get(3);
        signInSignUpPage.enterPass(password);
        String addressLine1 = inputs.get(4);
        signInSignUpPage.enterAddressLine1(addressLine1);
        String city = inputs.get(5);
        signInSignUpPage.enterCity(city);
        String state = inputs.get(6);
        signInSignUpPage.selectState(state);
        String postCode = inputs.get(7);
        postCode = postCode.substring(0,5);
        WebElement waitForPostCode = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#postcode")));
        signInSignUpPage.enterPostCode(postCode);
        String mobileNo = inputs.get(8);
        String tempMobileNo = mobileNo.substring(0,1);
        tempMobileNo += mobileNo.substring(2, mobileNo.length()-3);
        mobileNo = tempMobileNo;
        signInSignUpPage.enterMobileNumber(mobileNo);

        signUpTestLog.log(Status.DEBUG, "Entered required data for the sign up. Clicking button to complete the registration process.");
        signInSignUpPage.clickRegisterAccount();

        WebElement waitForRegistrationComplete = (new WebDriverWait(webDriver, 20)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#header > div.nav > div > div > nav > div:nth-child(2) > a")));
        WebElement showAccountElement = webDriver.findElement(By.cssSelector("#header > div.nav > div > div > nav > div:nth-child(1) > a"));

        try{
            assertTrue("Sign up failed. Please see the screenshot attached to the report.", showAccountElement.isDisplayed());
            signUpTestLog.log(Status.PASS, "Sign up successful!");
        } catch (AssertionError ae){
            try {
                String filePath = ScreenShot.take(webDriver, "navigationError");
                signUpTestLog.addScreenCaptureFromPath(filePath);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            signUpTestLog.log(Status.FAIL, "Sign up unsuccessful. Please see screenshot for missing fields or incomplete information.");
            throw ae;
        }
    }

    @Test
    public void addToCartTest(){
        ExtentTest addCartTest = report.createTest("Adding Item to Cart Test");
        List<String> inputs;

        inputs = spreadSheetReader.readRow(1, "homepage");
        String browser = inputs.get(0);
        String url = inputs.get(1);

        webDriver = BrowserFactory.getBrowser(browser);

    }
}
