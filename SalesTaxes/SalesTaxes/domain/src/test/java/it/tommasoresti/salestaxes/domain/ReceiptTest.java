package it.tommasoresti.salestaxes.domain;

import org.junit.Test;

import it.tommasoresti.salestaxes.domain.article.Food;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReceiptTest {

    @Test
    public void given_a_chocolate_bar_taxed_by_10_percent() throws Exception {
        Food chocolateBar = new Food("chocolate bar");
        chocolateBar.setPrice(100);

        Receipt receipt = new Receipt();
        receipt.addArticleWithTaxes(chocolateBar, 10);

        assertThat(receipt.getTotal(), is(110f));
        assertThat(receipt.getTaxesPaid(), is(10f));
    }
}