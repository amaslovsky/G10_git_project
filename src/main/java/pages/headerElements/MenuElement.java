package pages.headerElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.ActionsOnElements;
import pages.outlet.Outlet;
import pages.spalnya.matraci.bezprushinniMatraci.BezpruzhinniMatraci;

public class MenuElement extends ActionsOnElements {

    @FindBy(xpath = "//span[text()='Outlet']")
    private WebElement buttonOutlet;

    @FindBy(xpath = "//ul//span[text()='Спальня']")
    private WebElement buttonSpalnya;

    @FindBy(xpath = "//span[text()='Матраци']")
    private WebElement buttonMatraci;

    @FindBy(xpath = "//span[text()='Безпружинні матраци']")
    private WebElement buttonBezpruzhinniMatraci;

    public MenuElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MenuElement getMenuElement() {
        return new MenuElement(webDriver);
//        return this;
    }

    public Outlet clickOnOutletMenuButton() {
        clickOnElement(buttonOutlet);
        return new Outlet(webDriver);
    }

    public MenuElement clickOnSlapnyaMenuButton() {
        clickOnElement(buttonSpalnya);
        return new MenuElement(webDriver);
    }

    public MenuElement clickOnMatraciMenuButton() {
        clickOnElement(buttonMatraci);
        return new MenuElement(webDriver);
    }

    public BezpruzhinniMatraci clickOnBezpruzhinniMatraciMenuButton() {
        clickOnElement(buttonBezpruzhinniMatraci);
        return new BezpruzhinniMatraci(webDriver);
    }


}
