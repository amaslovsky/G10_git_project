import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import utilities.ExcelData;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class RegistrationTest extends BaseTest {
    ExcelData excelData = new ExcelData();

    /**
     * this test verifies displaying of error messages in two scenarios:
     * 1. while entering data in input fields
     * 2. after pressing the 'Registration' button
     * note: test data is extracted from Excel-table
     */
    @Test
    @Parameters(method = "parametersForInvalidRegistrationTestCollectedInExcel")
    public void invalidRegistrationTest(String email, String emailConfirm,
                                        String password, String firstName,
                                        String lastName, String acceptTermsAndConditions,
                                        String submit, String expectedErrors) {
        initPage.getHomePage()
                .openHomePage()
                .getHeaderElement().clickOnProfileButton().clickOnRegistrationButton()
                .checkRedirectingToRegistrationPage()
                .enterRegistrationUserEmail(email)
                .enterRegistrationConfirmEmail(emailConfirm)
                .enterRegistrationUserPassword(password)
                .enterRegistrationUserFirstName(firstName)
                .enterRegistrationUserLastName(lastName)
                .activateAcceptTermsAndConditionsCheckbox(acceptTermsAndConditions)
                .pressSubmitButton(submit)
                .checkErrorMessages(expectedErrors);
    }

    public Collection parametersForInvalidRegistrationTestCollectedInExcel() {
        return excelData.excelCollection();
    }


}
