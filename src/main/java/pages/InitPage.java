package pages;

import com.google.gson.internal.bind.util.ISO8601Utils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import static variables.Variables.*;


public class InitPage extends ActionsOnElements{

    private ProductPage productPage;

    public InitPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage getHomePage() {
        return new HomePage(webDriver) {
            @Override
            protected String getRelativeUrl() {
                return "/";
            }

            @Override
            protected String getRelativeBreadCrumb() {
                return null;
            }
        };
    }

    public ProductPage getProductPage() {
        return new ProductPage(webDriver) {
//            @Override
//            //TODO: calculate relative URL
//            protected String getRelativeUrl() {
//                return null;
//            }
//
//            @Override
//            //TODO: calculate relative BreadCrumb
//            protected String getRelativeBreadCrumb() {
//                return null;
//            }
        };
    }

}
