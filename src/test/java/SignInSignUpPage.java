import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SignInSignUpPage {

    @FindBy(id = "email_create")
    WebElement signUpEmailField;

    @FindBy(id = "SubmitCreate")
    WebElement signUpButton;

    @FindBy(id = "email")
    WebElement signInEmailField;

    @FindBy(id = "passwd")
    WebElement websitePassword;

    @FindBy(id = "SubmitLogin")
    WebElement signInButton;

    @FindBy(id = "customer_firstname")
    WebElement firstName;

    @FindBy(id = "customer_lastname")
    WebElement lastName;

    @FindBy(id = "firstname")
    WebElement addressFirstName;

    @FindBy(id = "lastname")
    WebElement addressLastName;

    @FindBy(id = "address1")
    WebElement addressLine1;

    @FindBy(id = "city")
    WebElement addressCity;

    @FindBy(id = "id_state")
    WebElement stateDropDown;

    @FindBy(id = "postcode")
    WebElement addressPostCode;

    @FindBy(id = "phone_mobile")
    WebElement mobileNumber;

    @FindBy(id = "submitAccount")
    WebElement registerAccountButton;


    public void enterSignUpEmail(String email){
        signUpEmailField.sendKeys(email);
    }

    public void clickSignUp(){
        signUpButton.click();
    }

    public void enterSignInDetails(String email, String password){
        signInEmailField.sendKeys(email);
        websitePassword.sendKeys(password);
    }

    public void clickSignIn(){
        signInButton.click();
    }

    public void enterFirstName(String name){
        firstName.sendKeys(name);
    }

    public void enterLastName(String name){
        lastName.sendKeys(name);
    }

    public void enterPass(String password){
        websitePassword.sendKeys(password);
    }

    public void enterAddressLine1(String address1){
        addressLine1.sendKeys(address1);
    }

    public void enterCity(String city){
        addressCity.sendKeys(city);
    }

    public void selectState(String state){
        Select selects = new Select(stateDropDown);
        List<WebElement> dropDownElements = selects.getOptions();
        for(WebElement element : dropDownElements){
            if(element.getText().trim().equals(state)){
                element.click();
            }
        }
    }

    public void enterPostCode(String postcode){
        addressPostCode.sendKeys(postcode);
    }

    public void enterMobileNumber(String mobileNo){
        mobileNumber.sendKeys(mobileNo);
    }

    public void clickRegisterAccount(){
        registerAccountButton.click();
    }
}
