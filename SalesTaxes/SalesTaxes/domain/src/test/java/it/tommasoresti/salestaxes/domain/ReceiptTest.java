package it.tommasoresti.salestaxes.domain;

import org.junit.Test;

import java.util.List;

import it.tommasoresti.salestaxes.domain.article.Item;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReceiptTest {

    @Test
    public void given_a_chocolate_bar_taxed_by_10_percent() throws Exception {
        Item chocolateBar = new Item("food", "chocolate bar", 100);

        TaxedArticle taxedChocolateBar = new TaxedArticle(chocolateBar);
        taxedChocolateBar.addTaxPercentage(10);

        Receipt receipt = new Receipt();
        receipt.addTaxedArticle(taxedChocolateBar);

        assertThat(receipt.getTotal(), is(110f));
        assertThat(receipt.getTaxesPaid(), is(10f));
    }

    @Test
    public void given_some_articles() throws Exception {
        Receipt receipt = new Receipt();


        TaxedArticle foodTaxedArticle = new TaxedArticle(new Item("food", "", 100));
        foodTaxedArticle.addTaxPercentage(10);

        TaxedArticle medicalTaxedArticle = new TaxedArticle(new Item("medical", "", 100));
        medicalTaxedArticle.addTaxPercentage(10);

        receipt.addTaxedArticle(foodTaxedArticle);
        receipt.addTaxedArticle(medicalTaxedArticle);

        List<TaxedArticle> articles = receipt.getArticles();
        assertThat(articles.get(0).getArticle().getCategory(), is("food"));
        assertThat(articles.get(1).getTaxesPercentage(), is(10f));
    }
}