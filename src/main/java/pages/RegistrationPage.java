package pages;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegistrationPage extends HomePage {
    @Override
    protected String getRelativeUrl() {
        //TODO: implement
        return null;
    }

    @Override
    protected String getRelativeBreadCrumb() {
        //TODO: implement
        return null;
    }

    public RegistrationPage(WebDriver webDriver) {
        super(webDriver);
    }

    public RegistrationPage checkRedirectingToRegistrationPage() {
        checkUrl();
        checkBreadCrumb();
        return this;
    }

    String inputField = "//div[@class='row']//input[@name='%s']";

    @FindBy(xpath = "//div[@role='alert']")
    protected List<WebElement> errorListOnPage;

    @FindBy(xpath = "//div[@class='customer-layout']//button")
    protected WebElement submitButton;


    public RegistrationPage enterRegistrationUserEmail(String email) {
    enterText("email", email);
    return this;
    }

    public RegistrationPage enterRegistrationConfirmEmail(String emailConfirm) {
    enterText("emailConfirm", emailConfirm);
    return this;
    }

    public RegistrationPage enterRegistrationUserPassword(String password) {
    enterText("password", password);
    return this;
    }

    public RegistrationPage enterRegistrationUserFirstName(String firstName) {
    enterText("firstName", firstName);
    return this;
    }

    public RegistrationPage enterRegistrationUserLastName(String lastName) {
    enterText("lastName", lastName);
    return this;
    }

    public RegistrationPage activateAcceptTermsAndConditionsCheckbox(String acceptTermsAndConditions) {
        if (!acceptTermsAndConditions.equals("skip"))
            setCheckBoxON(String.format(inputField, "acceptTermsAndConditions"), "accept Terms And Conditions");
    return this;
    }

    public RegistrationPage pressSubmitButton(String submit) {
        if (!submit.equals("skip"))
            clickOnElement(submitButton, "make an Account Button");
    return this;
    }

    private void enterText(String forLocator, String text) {
        if (!"skip".equalsIgnoreCase(text)) {
            if ("null".equalsIgnoreCase(text)) text = "";
            clearInputFieldAndEnterTextAndClickAwayFromField(String.format(inputField, forLocator), text);
        }
    }

    public RegistrationPage checkErrorMessages(String expectedErrors) {
        String[] errorsArrayInExcel = expectedErrors.split(";\\s*\\r?\\n");

        Assert.assertEquals("Number of messages on page is not equal to Excel", errorsArrayInExcel.length, errorListOnPage.size());

        ArrayList<String> errorTextInMessagesOnPage = new ArrayList<>();
        for (WebElement element : errorListOnPage) {
            errorTextInMessagesOnPage.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < errorsArrayInExcel.length; i++) {
            softAssertions
                    .assertThat(errorListOnPage.get(i).getText())
                    .as("Message number " + i)
                    .isIn(errorsArrayInExcel);
        }
        softAssertions.assertAll();
        return this;
    }

    public RegistrationPage checkErrorMessagesByComments(Map<String, String> errorsFromComments) {
//        String[] errorsArrayInExcel = expectedErrors.split(";\\s*\\r?\\n");
//
//        Assert.assertEquals("Number of messages on page is not equal to Excel", errorsArrayInExcel.length, errorListOnPage.size());
//
//        ArrayList<String> errorTextInMessagesOnPage = new ArrayList<>();
//        for (WebElement element : errorListOnPage) {
//            errorTextInMessagesOnPage.add(element.getText());
//        }
//
//        SoftAssertions softAssertions = new SoftAssertions();
//        for (int i = 0; i < errorsArrayInExcel.length; i++) {
//            softAssertions
//                    .assertThat(errorListOnPage.get(i).getText())
//                    .as("Message number " + i)
//                    .isIn(errorsArrayInExcel);
//        }
//        softAssertions.assertAll();
//        return this;
    return null;
    }
}
