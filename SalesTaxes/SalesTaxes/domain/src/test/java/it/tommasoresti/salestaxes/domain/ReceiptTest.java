package it.tommasoresti.salestaxes.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import it.tommasoresti.salestaxes.domain.article.Item;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReceiptTest {

    private BigDecimal aTaxPercentage;
    private BigDecimal aPrice;

    @Before
    public void setup() {
        this.aPrice = new BigDecimal(100);
        this.aTaxPercentage = new BigDecimal(10);
    }

    @Test
    public void given_a_chocolate_bar_taxed_by_10_percent() throws Exception {
        Item chocolateBar = new Item("food", "chocolate bar", aPrice);

        TaxedArticle taxedChocolateBar = new TaxedArticle(chocolateBar);
        taxedChocolateBar.addTaxPercentage(aTaxPercentage);

        Receipt receipt = new Receipt();
        receipt.addTaxedArticle(taxedChocolateBar);

        assertThat(receipt.getTotal(), is(equalTo(new BigDecimal(110))));
        assertThat(receipt.getTaxesPaid(), is(equalTo(aTaxPercentage)));
    }

    @Test
    public void given_some_articles() throws Exception {
        Receipt receipt = new Receipt();


        TaxedArticle foodTaxedArticle = new TaxedArticle(new Item("food", "", aPrice));
        foodTaxedArticle.addTaxPercentage(aTaxPercentage);

        TaxedArticle medicalTaxedArticle = new TaxedArticle(new Item("medical", "", aPrice));
        medicalTaxedArticle.addTaxPercentage(aTaxPercentage);

        receipt.addTaxedArticle(foodTaxedArticle);
        receipt.addTaxedArticle(medicalTaxedArticle);

        List<TaxedArticle> articles = receipt.getArticles();
        assertThat(articles.get(0).getArticle().getCategory(), is("food"));
        assertThat(articles.get(1).getTaxesPercentage(), is(aTaxPercentage));
    }
}