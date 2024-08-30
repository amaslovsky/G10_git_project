package pages.headerElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.ActionsOnElements;
import pages.filterElements.FilterElements;
import pages.obrane.Obrane;

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
