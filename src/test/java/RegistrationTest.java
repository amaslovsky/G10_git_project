import initWebdriver.InitWebDriver;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import utilities.ExcelData;
import utilities.ExcelDataMap;

import java.util.Collection;
import java.util.Map;

@RunWith(JUnitParamsRunner.class)
public class RegistrationTest extends InitWebDriver {
    ExcelData excelData = new ExcelData();
    ExcelDataMap excelDataMap = new ExcelDataMap();

    @Test
    @Parameters(method = "parametersForInvalidRegistrationTest")
    public void invalidRegistrationTest(String email, String emailConfirm,
                                        String password, String firstName,
                                        String lastName, String acceptTermsAndConditions,
                                        String submit, String expectedErrors) {
        initPage.getHomePage()
                .openHomePage()
                .getHeaderElement().clickOnProfileButton().clickOnRegistrationButton()
                //.checkRedirectingToRegistrationPage()
                .enterRegistrationUserEmail(email)
                .enterRegistrationConfirmEmail(emailConfirm)
                .enterRegistrationUserPassword(password)
                .enterRegistrationUserFirstName(firstName)
                .enterRegistrationUserLastName(lastName)
                .activateAcceptTermsAndConditionsCheckbox(acceptTermsAndConditions)
                .pressSubmitButton(submit)
                .checkErrorMessages(expectedErrors);
    }

    public Collection parametersForInvalidRegistrationTest() {
        return excelData.excelCollection();
    }




//    @Test
//    @Parameters(method = "parametersForInvalidRegistrationTestAndErrorsFromComments")
//    public void invalidRegistrationTestWithCommentsInExcel(String email, String emailConfirm,
//                                                           String password, String firstName,
//                                                           String lastName, String acceptTermsAndConditions,
//                                                           String submit, Map<String, String> errorsFromComments) {
//        initPage.getHomePage()
//                .openHomePage()
//                .getHeaderElement().clickOnProfileButton().clickOnRegistrationButton()
//                //.checkRedirectingToRegistrationPage()
//                .enterRegistrationUserEmail(email)
//                .enterRegistrationConfirmEmail(emailConfirm)
//                .enterRegistrationUserPassword(password)
//                .enterRegistrationUserFirstName(firstName)
//                .enterRegistrationUserLastName(lastName)
//                .activateAcceptTermsAndConditionsCheckbox(acceptTermsAndConditions)
//                .pressSubmitButton(submit)
//                .checkErrorMessagesByComments(errorsFromComments);
//    }
//
//    public Collection<Object[]> parametersForInvalidRegistrationTestAndErrorsFromComments() {
//        return excelDataMap.excelCollection();
//    }


}
