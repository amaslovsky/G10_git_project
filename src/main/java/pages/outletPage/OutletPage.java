package pages.outletPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.ProductPage;

import java.util.ArrayList;

import static utilities.Utilities.*;
import static variables.Variables.*;

public class OutletPage extends HomePage {

    @Override
    public String getRelativeUrl() {
        return "/outlet";
    }

    @Override
    protected String getRelativeBreadCrumb() {
        return "//*[text()='Outlet']";
    }

    public OutletPage(WebDriver webDriver) {
        super(webDriver);
    }

    public OutletPage checkIsRedirectToOutletPage() {
//        waitUtilSpinnerWorks();
        checkUrl();
        checkBreadCrumb();
        return this;
    }

    public OutletPage markRandomProductsAsObrane() {
        ArrayList<Integer> productsArray = makeArrayOfProducts(allProducts);
        for (int i=1; i<productsArray.size()+1; i++){
            String productName = getProductName(nameProductXpath, i);
            addProductToObraneList(productName);
            setObraneState(i, productName);
        };
        return this;
    }

    public OutletPage getPropertiesFirstProduct() {
        ArrayList<WebElement> elements = getWebElementsArrayByXpath(allProducts);
        productName = getElementName(productNameElement);
        productPrice = convertStringValueInDouble(getElementName(productPriceElement));
        return this;
    }

    public static void waitABit(Integer second){
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ProductPage clickOnFirstProduct() {
        clickOnElement(productNameElement);
        return new ProductPage(webDriver);
    }
}
