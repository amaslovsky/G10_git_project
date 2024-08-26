package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.outlet.Outlet;

public class MenuElement extends ActionsOnElements {

    @FindBy(xpath = "//span[text()='Outlet']")
    private WebElement buttonOutlet;

    public MenuElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MenuElement getMenuElement() {
        return new MenuElement(webDriver);
    }

    public Outlet clickOnOutletMenuButton() {
        clickOnElement(buttonOutlet);
        return new Outlet(webDriver);
    }
}
