import initWebdriver.InitWebDriver;
import org.junit.Test;

public class ObraneTest extends InitWebDriver {

    @Test
    public void addProductToObrane() {
        initPage
                .getHomePage()
                .openHomePage()
                .clickOnMenuButton()
                .getMenuElement()
                .clickOnOutletMenuButton()
                .checkIsRedirectToOutletPage()
                .markRandomProductsAsObrane()
                .openObranePage()
                .checkIsRedirectToObranePage()
                .printObraneList()
                .compareSelectedProductsAndDisplayedProducts()
                ;
    }
}
