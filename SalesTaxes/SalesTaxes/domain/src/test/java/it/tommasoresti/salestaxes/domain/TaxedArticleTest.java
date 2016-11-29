package it.tommasoresti.salestaxes.domain;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.Item;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TaxedArticleTest {

    private BigDecimal aPrice;
    private BigDecimal aTaxPercentage;

    @Before
    public void setUp() throws Exception {
        this.aPrice = new BigDecimal(100);
        this.aTaxPercentage = new BigDecimal(10);
    }

    @Test
    public void given_an_article_taxed_by_10_percent() throws Exception {
        Item food = new Item("food", "chocolate", aPrice);

        TaxedArticle taxedArticle = new TaxedArticle(food);
        taxedArticle.addTaxPercentage(aTaxPercentage);

        assertThat(taxedArticle.getArticle(), CoreMatchers.<Article>is(food));
        assertThat(taxedArticle.getFinalPrice(), is(equalTo(new BigDecimal(110))));
        assertThat(taxedArticle.getTaxesPercentage(), is(aTaxPercentage));
    }
}