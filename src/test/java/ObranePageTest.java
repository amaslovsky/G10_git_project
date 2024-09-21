import baseTest.BaseTest;
import org.junit.Test;
import variables.Variables;

public class ObranePageTest extends BaseTest {

    /**
     * test randomly selects and marks products as 'Obrane' on 'Outlet' page,
     * then verifies that these products are correctly displayed on 'Obrane' page
     * note: Poland site version is used
     */
    @Test
    public void addProductToObrane() {
        String environment = System.getProperty("env", ".ua");
        Variables.url = "https://jysk" + environment;
        initPage
                .getHomePage()
                .openHomePage()
                .checkRedirectingOnHomePage()
                .getHeaderElement().clickOnMenuButton()
                .getMenuElement().clickOnOutletMenuButton()
                .checkRedirectingToOutletPage()
                .markRandomProductsAsObrane()
                .getHeaderElement().clickOnObraneMenuButton()
                .checkRedirectingToObranePage()
                .checkSelectedProductsDisplayedOnObranePage()
        ;
    }
}
