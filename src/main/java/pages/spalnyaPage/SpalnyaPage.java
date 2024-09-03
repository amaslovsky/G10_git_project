package pages.spalnyaPage;

import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class SpalnyaPage extends HomePage {


    @Override
    protected String getRelativeUrl() {
        return "/spalnya";
    }

    @Override
    protected String getRelativeBreadCrumb() {
        return "//*[text()='Спальня']";
    }

    public SpalnyaPage(WebDriver webDriver) {
        super(webDriver);
    }

//    public Spalnya checkIsRedirectToSpalnyaPage() {
//        checkUrl();
//        isElementVisible(breadCrumbXpath);
//        return this;
//    }
//
//    public Matraci openMatraciPage() {
//        WebElement matraci = webDriver.findElement(By.xpath("//span[text()='Матраци']"));
//        Actions actions = new Actions(webDriver);
//        actions.moveToElement(matraci).perform();
//        clickOnElement(matraci);
////        return new Matraci(webDriver);
//        return null;
//    }



}
