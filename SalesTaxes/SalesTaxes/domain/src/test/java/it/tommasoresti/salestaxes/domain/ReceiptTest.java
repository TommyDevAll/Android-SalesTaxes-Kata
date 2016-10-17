package it.tommasoresti.salestaxes.domain;

import org.junit.Test;

import java.util.List;

import it.tommasoresti.salestaxes.domain.article.Food;
import it.tommasoresti.salestaxes.domain.article.Medical;

import static org.hamcrest.CoreMatchers.instanceOf;
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

    @Test
    public void given_some_articles() throws Exception {
        Receipt receipt = new Receipt();

        receipt.addArticleWithTaxes(new Food(""), 10);
        receipt.addArticleWithTaxes(new Medical(""), 10);

        List<TaxedArticle> articles = receipt.getArticles();
        assertThat(articles.get(0).getArticle(), instanceOf(Food.class));
        assertThat(articles.get(1).getTaxesPercentage(), is(10f));
    }
}