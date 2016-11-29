package it.tommasoresti.salestaxes.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import it.tommasoresti.salestaxes.domain.article.Imported;
import it.tommasoresti.salestaxes.domain.article.Item;
import it.tommasoresti.salestaxes.domain.tax.DefaultTaxesRuleChain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DefaultTaxesRuleChainTest {

    private DefaultTaxesRuleChain taxesRuleHandler;
    private BigDecimal aPrice;

    @Before
    public void setUp() throws Exception {
        taxesRuleHandler = new DefaultTaxesRuleChain();
        aPrice = new BigDecimal(10f);
    }

    @Test
    public void given_a_food() throws Exception {
        TaxedArticle taxedArticle = new TaxedArticle(new Item("food", "chocolate bar", aPrice));
        taxesRuleHandler.handle(taxedArticle);
        assertThat(taxedArticle.getTaxesPercentage(), is(equalTo(new BigDecimal(0f))));
    }

    @Test
    public void given_a_perfume() throws Exception {
        TaxedArticle taxedArticle = new TaxedArticle(new Item("other", "perfume", aPrice));
        taxesRuleHandler.handle(taxedArticle);
        assertThat(taxedArticle.getTaxesPercentage(), is(equalTo(new BigDecimal(10f))));
    }

    @Test
    public void given_an_imported_perfume() throws Exception {
        TaxedArticle taxedArticle = new TaxedArticle(new Imported(new Item("other", "perfume", aPrice)));
        taxesRuleHandler.handle(taxedArticle);
        assertThat(taxedArticle.getTaxesPercentage(), is(equalTo(new BigDecimal(15f))));
    }

    @Test
    public void given_an_imported_box_of_headache_pills() throws Exception {
        TaxedArticle taxedArticle = new TaxedArticle(new Imported(new Item("medical", "box of headache pills", aPrice)));
        taxesRuleHandler.handle(taxedArticle);
        assertThat(taxedArticle.getTaxesPercentage(), is(equalTo(new BigDecimal(5f))));
    }
}