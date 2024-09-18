package pages.headerElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.ActionsOnElements;
import pages.outletPage.OutletPage;
import pages.spalnyaPage.matraciPage.bezprushinniMatraci.BezpruzhinniMatraciPage;

public class MenuElement extends ActionsOnElements {

    @FindBy(xpath = "//span[text()='Outlet']")
    private WebElement buttonOutlet;

//    @FindBy(xpath = "//ul//span[text()='Спальня']")
    @FindBy(xpath = "(//div[contains(@class, 'completed')]//li)[1]")
    private WebElement buttonSpalnya;

//    @FindBy(xpath = "//span[text()='Матраци']")
    @FindBy(xpath = "(//div[contains(@class, '-in')]//li)[2]")
    private WebElement buttonMatraci;

//    @FindBy(xpath = "//span[text()='Безпружинні матраци']")
    @FindBy(xpath = "(//div[contains(@class, '-in')])[10]//li[3]")
    private WebElement buttonBezpruzhinniMatraci;

    public MenuElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MenuElement getMenuElement() {
        return new MenuElement(webDriver);
    }

    public OutletPage clickOnOutletMenuButton() {
        clickOnElement(buttonOutlet);
        return new OutletPage(webDriver);
    }

    public MenuElement clickOnSlapnyaMenuButton() {
        clickOnElement(buttonSpalnya);
        return new MenuElement(webDriver);
    }

    public MenuElement clickOnMatraciMenuButton() {
        clickOnElement(buttonMatraci);
        return new MenuElement(webDriver);
    }

    public BezpruzhinniMatraciPage clickOnBezpruzhinniMatraciMenuButton() {
        clickOnElement(buttonBezpruzhinniMatraci);
        return new BezpruzhinniMatraciPage(webDriver);
    }


}
