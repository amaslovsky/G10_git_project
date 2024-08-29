package pages.outlet;

import org.openqa.selenium.WebDriver;
import pages.HomePage;
import java.util.ArrayList;

public class Outlet extends HomePage {

    @Override
    public String getRelativeUrl() {
        return "/outlet";
    }

    @Override
    protected String getRelativeBreadCrumb() {
        return "//*[text()='Outlet']";
    }

    public Outlet(WebDriver webDriver) {
        super(webDriver);
    }

    public Outlet checkIsRedirectToOutletPage() {
        checkUrl();
        checkBreadCrumb();
        return this;
    }

    public Outlet markRandomProductsAsObrane() {
        ArrayList<Integer> productsArray = makeArrayOfProducts();
        productsArray.forEach(element -> {
            String productName = getProductName(element);
            addProductToObraneList(productName);
            setObraneState(element, productName);
        });
        return this;
    }


}
