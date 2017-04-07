package it.tommasoresti.salestaxes.domain;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.math.BigDecimal;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.Item;
import it.tommasoresti.salestaxes.domain.article.TaxableArticle;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TaxableArticleTest {

    @Test
    public void given_an_article_taxed_by_10_percent() throws Exception {
        BigDecimal aPrice = new BigDecimal("100");
        BigDecimal aTaxPercentage = new BigDecimal("10");

        Item food = new Item("food", "chocolate", aPrice);

        TaxableArticle taxableArticle = new TaxableArticle(food);
        taxableArticle = taxableArticle.addTaxPercentage(aTaxPercentage);

        assertThat(taxableArticle.getArticle(), CoreMatchers.<Article>is(food));
        assertThat(taxableArticle.getPrice(), is(equalTo(aPrice)));
        assertThat(taxableArticle.getTaxesPercentage(), is(aTaxPercentage));
    }
}