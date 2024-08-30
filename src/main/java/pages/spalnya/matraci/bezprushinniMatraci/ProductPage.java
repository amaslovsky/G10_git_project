package pages.spalnya.matraci.bezprushinniMatraci;

import org.openqa.selenium.WebDriver;

public class ProductPage extends BezpruzhinniMatraci {
    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getRelativeUrl() {
        return "/matrats[a-zA-Z0-9]*";
    }

    @Override
    protected String getRelativeBreadCrumb() {
        return "//*[starts-with(text(), 'Матрац ')]";
    }


}
