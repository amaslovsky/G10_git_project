package pages.spalnyaPage.matraciPage;

import org.openqa.selenium.WebDriver;
import pages.spalnyaPage.SpalnyaPage;

public class MatraciPage extends SpalnyaPage {

    public MatraciPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getRelativeUrl() {
        return super.getRelativeUrl() + "/matraci";
    }

    @Override
    protected String getRelativeBreadCrumb() {
        return "//*[text()='Матраци']";
    }

//    public Matraci(WebDriver webDriver) {
//        super(webDriver);
//    }
//
//    public Matraci checkIsRedirectToMatraciPage() {
//        checkUrl();
//        isElementVisible(breadCrumbXpath);
//        return this;
//    }
//
//    public BezpruzhinniMatraci openBezpruzhinniMatraciPage() {
//        WebElement matraci = webDriver.findElement(By.xpath("//span[text()='Безпружинні матраци']"));
//        Actions actions = new Actions(webDriver);
//        actions.moveToElement(matraci).perform();
//        clickOnElement(matraci);
//        return new BezpruzhinniMatraci(webDriver);
//    }
//
//    public abstract String getProductURL();
}
