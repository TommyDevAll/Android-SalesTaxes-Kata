package it.tommasoresti.salestaxes.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import it.tommasoresti.salestaxes.domain.article.Imported;
import it.tommasoresti.salestaxes.domain.article.Item;
import it.tommasoresti.salestaxes.domain.tax.TaxableArticle;
import it.tommasoresti.salestaxes.domain.round.RoundUp5CentsPolicy;
import it.tommasoresti.salestaxes.domain.round.RoundingPolicy;
import it.tommasoresti.salestaxes.domain.tax.DefaultTaxRuleHandler;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DefaultTaxRuleHandlerTest {

    private DefaultTaxRuleHandler taxesRuleHandler;
    private BigDecimal aPrice;
    private RoundingPolicy roundingPolicy = new RoundUp5CentsPolicy();

    @Before
    public void setUp() throws Exception {
        taxesRuleHandler = new DefaultTaxRuleHandler();
        aPrice = new BigDecimal(10f);
    }

    @Test
    public void given_a_food() throws Exception {
        TaxableArticle taxedArticle = new TaxableArticle(new Item("food", "chocolate bar", aPrice));
        taxesRuleHandler.handle(taxedArticle);
        assertThat(taxedArticle.getTaxesPercentage(), is(new BigDecimal(0f)));
    }

    @Test
    public void given_a_perfume() throws Exception {
        TaxableArticle taxedArticle = new TaxableArticle(new Item("other", "perfume", aPrice));
        taxesRuleHandler.handle(taxedArticle);
        assertThat(taxedArticle.getTaxesPercentage(), is(new BigDecimal(10f)));
    }

    @Test
    public void given_an_imported_perfume() throws Exception {
        TaxableArticle taxedArticle = new TaxableArticle(new Imported(new Item("other", "perfume", aPrice)));
        taxesRuleHandler.handle(taxedArticle);
        assertThat(taxedArticle.getTaxesPercentage(), is(new BigDecimal(15f)));
    }

    @Test
    public void given_an_imported_box_of_headache_pills() throws Exception {
        TaxableArticle taxedArticle = new TaxableArticle(new Imported(new Item("medical", "box of headache pills", aPrice)));
        taxesRuleHandler.handle(taxedArticle);
        assertThat(taxedArticle.getTaxesPercentage(), is(new BigDecimal(5f)));
    }
}