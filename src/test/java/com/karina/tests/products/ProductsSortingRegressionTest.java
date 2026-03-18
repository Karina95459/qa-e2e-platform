package com.karina.tests.products;

import com.karina.pages.ProductsPage;
import com.karina.tests.base.BaseTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductsSortingRegressionTest extends BaseTest {

    @Test(groups = {"regression"})
    public void shouldSortProductsByNameAZ() {
        ProductsPage products = openProductsPage();
        Assert.assertTrue(products.isOpened());

        products.selectSort("az");
        List<String> names = products.getItemNames();

        List<String> sorted = new ArrayList<>(names);
        Collections.sort(sorted);
        Assert.assertEquals(names,sorted,"Names are not sorted A-Z");
    }

    @Test(groups = {"regression"})
    public void shouldSortProductsByNameZA() {
        ProductsPage products = openProductsPage();
        Assert.assertTrue(products.isOpened());

        products.selectSort("za");
        List<String> names = products.getItemNames();

        List<String> sorted = new ArrayList<>(names);
        Collections.sort(sorted);
        Collections.reverse(sorted);

        Assert.assertEquals(names, sorted,"Names are not sorted Z-A");
    }

    @Test(groups = {"regression"})
    public void shouldSortProductsByPriceLowHigh() {
        ProductsPage products = openProductsPage();
        Assert.assertTrue(products.isOpened());

        products.selectSort("lohi");
        List<Double> prices = products.getItemPrices();
        List<Double> sorted = new ArrayList<>(prices);

        Collections.sort(sorted);

        Assert.assertEquals(prices, sorted,"Prices are not sorted low-high");
    }

    @Test(groups = {"regression"})
    public void shouldSortProductsPriceHighLow() {
        ProductsPage products = openProductsPage();
        Assert.assertTrue(products.isOpened());

        products.selectSort("hilo");
        List<Double> prices = products.getItemPrices();
        List<Double> sorted = new ArrayList<>(prices);

        Collections.sort(sorted);
        Collections.reverse(sorted);

        Assert.assertEquals(prices, sorted,"Prices are not sorted high-low");
    }

}
