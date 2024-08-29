package pages.headerElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.ActionsOnElements;
import pages.MenuElement;
import pages.obrane.Obrane;
import pages.outlet.Outlet;

public class HeaderElements extends ActionsOnElements {
    @FindBy(xpath = "//span[text()='Меню']")
    private WebElement buttonMenu;


    @FindBy(xpath = "//span[text()='Обране']")
    private WebElement buttonObrane;

    public HeaderElements(WebDriver webDriver) {
        super(webDriver);
    }

    public MenuElement getMenuElement() {
        return new MenuElement(webDriver);
    }

    public MenuElement clickOnMenuButton() {
        clickOnElement(buttonMenu);
        return new MenuElement(webDriver);
    }


    public Obrane clickOnObraneMenuButton() {
        clickOnElement(buttonObrane);
        return new Obrane(webDriver);
    }
}
