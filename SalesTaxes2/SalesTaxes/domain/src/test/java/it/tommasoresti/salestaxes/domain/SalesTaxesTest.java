package it.tommasoresti.salestaxes.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.Item;
import it.tommasoresti.salestaxes.domain.article.TaxableArticle;
import it.tommasoresti.salestaxes.domain.round.NoRoundingPolicy;
import it.tommasoresti.salestaxes.domain.round.RoundingPolicy;
import it.tommasoresti.salestaxes.domain.tax.TaxRuleHandler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SalesTaxesTest {

    private SalesTaxes salesTaxes;
    private TaxRuleHandler taxesHandler;
    private Cart cart;
    private RoundingPolicy roundingPolicy;

    @Before
    public void setUp() throws Exception {
        taxesHandler = mock(TaxRuleHandler.class);
        roundingPolicy = new NoRoundingPolicy();
        salesTaxes = new SalesTaxes(taxesHandler, roundingPolicy);
        cart = mock(Cart.class);
    }

    @Test
    public void given_some_non_taxable_articles() throws Exception {
        givenAListOfItems(new Item("", "", new BigDecimal("10.00")));
        givenNoTaxRules();
        Receipt receipt = salesTaxes.of(cart);

        assertThat(receipt.getTaxesPaid(), is(new BigDecimal("0.00")));
        assertThat(receipt.getTotal(), is(new BigDecimal("10.00")));
    }

    @Test
    public void give_some_taxable_article() throws Exception {
        givenAnArticleTaxedBy(new Item("", "", new BigDecimal("10.00")), new BigDecimal("50"));

        Receipt receipt = salesTaxes.of(cart);

        assertThat(receipt.getTaxesPaid(), is(new BigDecimal("5.00")));
        assertThat(receipt.getTotal(), is(new BigDecimal("15.00")));
    }

    private void givenAnArticleTaxedBy(Item item, BigDecimal taxesPercentage) {
        givenATaxRuleThatReturn(new TaxableArticle(item, taxesPercentage));
        givenAListOfItems(item);
    }

    private void givenATaxRuleThatReturn(TaxableArticle aTaxableArticle) {
        when(taxesHandler.canHandle(any(TaxableArticle.class))).thenReturn(true);
        when(taxesHandler.handle(any(TaxableArticle.class))).thenReturn(aTaxableArticle);
    }

    private void givenNoTaxRules() {
        when(taxesHandler.canHandle(any(TaxableArticle.class))).thenReturn(false);
    }

    private void givenAListOfItems(Article ... items) {
        List<Article> list = new ArrayList<>(Arrays.asList(items));
        when(cart.getArticles()).thenReturn(list);
    }
}