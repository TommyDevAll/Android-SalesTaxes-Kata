package it.tommasoresti.salestaxes.domain;

import org.junit.Before;
import org.junit.Test;

import it.tommasoresti.salestaxes.domain.article.Imported;
import it.tommasoresti.salestaxes.domain.article.Item;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DefaultTaxesRuleChainTest {

    private DefaultTaxesRuleChain taxesRuleHandler;
    private float aPrice;

    @Before
    public void setUp() throws Exception {
        taxesRuleHandler = new DefaultTaxesRuleChain();
        aPrice = 10f;
    }

    @Test
    public void given_a_food() throws Exception {
        TaxedArticle taxedArticle = new TaxedArticle(new Item("food", "chocolate bar", aPrice));
        taxesRuleHandler.handle(taxedArticle);
        assertThat(taxedArticle.getTaxesPercentage(), is(0f));
    }

    @Test
    public void given_a_perfume() throws Exception {
        TaxedArticle taxedArticle = new TaxedArticle(new Item("other", "perfume", aPrice));
        taxesRuleHandler.handle(taxedArticle);
        assertThat(taxedArticle.getTaxesPercentage(), is(10f));
    }

    @Test
    public void given_an_imported_perfume() throws Exception {
        TaxedArticle taxedArticle = new TaxedArticle(new Imported(new Item("other", "perfume", aPrice)));
        taxesRuleHandler.handle(taxedArticle);
        assertThat(taxedArticle.getTaxesPercentage(), is(15f));
    }

    @Test
    public void given_an_imported_box_of_headache_pills() throws Exception {
        TaxedArticle taxedArticle = new TaxedArticle(new Imported(new Item("medical", "box of headache pills", aPrice)));
        taxesRuleHandler.handle(taxedArticle);
        assertThat(taxedArticle.getTaxesPercentage(), is(5f));
    }
}