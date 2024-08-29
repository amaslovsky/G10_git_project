import initWebdriver.InitWebDriver;
import org.junit.Test;

public class ObraneTest extends InitWebDriver {

    @Test
    public void addProductToObrane() {
        initPage
                .getHomePage()
                .openHomePage()
                .checkIsRedirectOnHomePage()
                .getHeaderElement().clickOnMenuButton()
                .getMenuElement().clickOnOutletMenuButton()
                .checkIsRedirectToOutletPage()
                .markRandomProductsAsObrane()
                .getHeaderElement().clickOnObraneMenuButton()
                .checkIsRedirectToObranePage()
                .isDisplayedSelectedProductsOnObranePage()
        ;
    }
}
