import org.junit.Before;
import org.junit.Test;
import initWebdriver.InitWebDriver;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(JUnitParamsRunner.class)
public class BezpruzhinniMatraciTests extends InitWebDriver {

    boolean beforeMustBeLaunched = false;
    ArrayList<String> matraciSize = new ArrayList<>();

    @Before
    public void diggingDataForTest() {
        if (beforeMustBeLaunched) {
            matraciSize.add("65x185");
            matraciSize.add("80x190");
//            initPage
//                    .getHomePage()
//                    .openHomePage()
//                    .checkIsRedirectOnHomePage()
//                    .getHeaderElement()
//                    .clickOnMenuButton()
//                    .getMenuElement()
//                    .clickOnSlapnyaMenuButton()
//                    .clickOnMatraciMenuButton()
//                    .clickOnBezpruzhinniMatraciMenuButton()
//                    .checkIsRedirectToBezpruzhinniMatraciPage()
//                    .clickOnAllFiltersButton()
//                    .getFilterElements()
//                    .clickOnAlwaysLowPriceAccordion()
//                    .setAlwaysLowPriceCheckBoxON()
//                    .clickOnAgreeSortingButton()
//                    .checkOnlyAlwaysLowPriceProductsDisplayed();
        }
    }

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
                .checkIsAscSortingCorrect();
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
                .checkOnlyAlwaysLowPriceProductsDisplayed();
    }

    @Test
    @Parameters(method = "matraciSizeArray")
    public void sizeFilterBezpruzhinniMatraci() {
        beforeMustBeLaunched = true;
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
                .checkOnlyAlwaysLowPriceProductsDisplayed();
    }

    public ArrayList<String> matraciSizeArray() {
        return matraciSize;
        };

}
