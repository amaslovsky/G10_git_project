import initWebdriver.InitWebDriver;
import org.junit.Before;
import org.junit.Test;

public class BasketTests extends InitWebDriver {

    @Before
    public void navigateToProductOnOutletPage() {
        initPage
                .getHomePage()
                .openHomePage()
                .getHeaderElement().clickOnMenuButton()
                .getMenuElement().clickOnOutletMenuButton()
                .checkIsRedirectToOutletPage()
                .getPropertiesFirstProduct()
                .clickOnFirstProduct();
    }

    @Test
    public void testChangingProductQuantityInBasket() {
        int numberOfProducts = 1;
        int numberOfProductsChanged = 2;
        initPage
                .getProductPage()
                .openProductPage()
                .checkProductNameAndPriceAreCorrect()
                .addProductToBasket(numberOfProducts)
                .checkProductNameAndPriceInCheckoutPopup()
                .closeCheckoutPopup()
                .getHeaderElement()
                .checkProductAddedToBasket()
                .clickOnBasketButton()
                .checkProductAmountAndPriceInBasketPopup() //тут тільки кількість та ці, бо ім'я не співпадає з імененем на сторінці продукту
                .changeProductNumberInBasketPopup(numberOfProductsChanged)
                .checkProductAmountAndPriceInBasketPopup()
                .openBasketPage()
                .changeProductNumberInBasketPopup(numberOfProducts)
                .checkProductAmountAndPriceInBasketPopup();
    }





}
