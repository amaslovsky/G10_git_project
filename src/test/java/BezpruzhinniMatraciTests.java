import org.junit.Before;
import org.junit.Test;
import initWebdriver.InitWebDriver;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.runner.RunWith;

import java.util.ArrayList;

//@RunWith(JUnitParamsRunner.class)
public class BezpruzhinniMatraciTests extends InitWebDriver {

//    boolean beforeMustBeLaunched = false;
//    ArrayList<String> matraciSize = new ArrayList<>();
//
//    @Before
//    public void diggingDataForTest() {
//        if (beforeMustBeLaunched) {
//            matraciSize.add("65x185");
//            matraciSize.add("80x190");
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
//        }
//    }

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

    /**
     * test verifies product sorting functionality on 'BezpruzhinniMatraci' page
     * radio button sorting options: asc, desc, popular and default (relevance)
     * important note: sorting logic for 'popular' and 'default' is not defined, so these options are not implemented
     */
    @Test
    public void ascSortingBezpruzhinniMatraci() {
        String sortingType = "asc";
        initPage
                .getHomePage()
                .openHomePage()
                .checkRedirectingOnHomePage()
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
                .checkSortingCorrect(sortingType);
    }

    /**
     * This test verifies display of only products marked as 'low price' on the 'BezpruzhinniMatraci' page
     */
    @Test
    public void alwaysLowPriceFilterBezpruzhinniMatraci() {
        initPage
                .getHomePage()
                .openHomePage()
                .checkRedirectingOnHomePage()
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

//    @Test
//    @Parameters(method = "matraciSizeArray")
//    public void sizeFilterBezpruzhinniMatraci() {
//        beforeMustBeLaunched = true;
//        initPage
//                .getHomePage()
//                .openHomePage()
//                .checkRedirectingOnHomePage()
//                .getHeaderElement()
//                .clickOnMenuButton()
//                .getMenuElement()
//                .clickOnSlapnyaMenuButton()
//                .clickOnMatraciMenuButton()
//                .clickOnBezpruzhinniMatraciMenuButton()
//                .checkIsRedirectToBezpruzhinniMatraciPage()
//                .clickOnAllFiltersButton()
//                .getFilterElements()
//                .clickOnAlwaysLowPriceAccordion()
//                .setAlwaysLowPriceCheckBoxON()
//                .clickOnAgreeSortingButton()
//                .checkOnlyAlwaysLowPriceProductsDisplayed();
//    }
//
//    public ArrayList<String> matraciSizeArray() {
//        return matraciSize;
//        };

}
