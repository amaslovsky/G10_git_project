import org.junit.Test;
import initWebdriver.InitWebDriver;

public class BezpruzhinniMatraciTests extends InitWebDriver {

//    @Test
//    public void reachBezprMatrasUsingPageElements() {
//        initPage
//                .getHomePage()
//                .openHomePage()
//                .openSpalnyaPage()
//                .checkIsRedirectToSpalnyaPage()
//                .openMatraciPage()
//                .checkIsRedirectToMatraciPage()
//                .openBezpruzhinniMatraciPage()
//                .checkIsRedirectToBezpruzhinniMatraciPage()
//                .selectFirstMatrac()
//                .checkIsRedirectToSelectedBezpruzhinniMatraciPage();
//
//    }

    @Test
    public void ascSortingBezpruzhinniMatraci() {
        String sortingType = "asc"; // radio-button variants: asc, desc, popular, default (means relevant)
        initPage
                .getHomePage()
                .openHomePage()
                .checkIsRedirectOnHomePage()
                .getHeaderElement()
                .clickOnMenuButton()
                .getMenuElement()
                .clickOnSlapnyaMenuButton()
                .clickOnMatraciMenuButton()
                .clickOnBezpruzhinniMatraciMenuButton()
                .checkIsRedirectToBezpruzhinniMatraciPage()
                .clickOnSortingButton()
                .getFilterElements()
                .setSortingType(sortingType)
                .clickOnAgreeSortingButton()
                .openProductPage()
                .checkIsAscSortingCorrect()
        ;
    }

    @Test
    public void alwaysLowPriceFilterBezpruzhinniMatraci() {
        initPage
                .getHomePage()
                .openHomePage()
                .checkIsRedirectOnHomePage()
                .getHeaderElement()
                .clickOnMenuButton()
                .getMenuElement()
                .clickOnSlapnyaMenuButton()
                .clickOnMatraciMenuButton()
                .clickOnBezpruzhinniMatraciMenuButton()
                .checkIsRedirectToBezpruzhinniMatraciPage()
                .clickOnAllFiltersButton()
                .getFilterElements()
                .clickOnAlwaysLowPriceAccordion()
                .setAlwaysLowPriceCheckBoxON()
                .clickOnAgreeSortingButton()
                .checkOnlyAlwaysLowPriceProductsDisplayed()
        ;
    }

}
