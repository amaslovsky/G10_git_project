package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import static variables.Variables.*;

public class RegistrationPage extends HomePage {
    @Override
    protected String getRelativeUrl() {
        return "/customer/create";
    }

    @Override
    protected String getRelativeBreadCrumb() {
        return "//ul/li[contains(@class, 'active')]";
    }

    public RegistrationPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Open registration page")
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


    @Step
    public RegistrationPage enterRegistrationUserEmail(String email) {
        enterText("email", email);
    return this;
    }

    @Step
    public RegistrationPage enterRegistrationConfirmEmail(String emailConfirm) {
        enterText("emailConfirm", emailConfirm);
    return this;
    }

    @Step
    public RegistrationPage enterRegistrationUserPassword(String password) {
        enterText("password", password);
    return this;
    }

    @Step
    public RegistrationPage enterRegistrationUserFirstName(String firstName) {
        enterText("firstName", firstName);
    return this;
    }

    @Step
    public RegistrationPage enterRegistrationUserLastName(String lastName) {
        enterText("lastName", lastName);
    return this;
    }

    @Step
    public RegistrationPage activateAcceptTermsAndConditionsCheckbox(String acceptTermsAndConditions) {
        if (!acceptTermsAndConditions.equals(SKIP))
            setCheckBoxON(String.format(inputField, "acceptTermsAndConditions"), "accept Terms And Conditions");
    return this;
    }

    @Step
    public RegistrationPage pressSubmitButton(String submit) {
        if (!submit.equals(SKIP))
            clickOnElement(submitButton, "make an Account Button");
    return this;
    }

    private void enterText(String forLocator, String text) {
        if (!SKIP.equalsIgnoreCase(text)) {
            if ("null".equalsIgnoreCase(text)) text = "";
            clearInputFieldAndEnterTextAndClickAwayFromField(String.format(inputField, forLocator), text);
        }
    }

    @Step("check error messages")
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
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES)));
        return this;
    }

}
