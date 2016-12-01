package it.tommasoresti.salestaxes.domain;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import it.tommasoresti.salestaxes.domain.round.RoundUp5CentsPolicy;
import it.tommasoresti.salestaxes.domain.tax.TaxRuleHandler;
import it.tommasoresti.salestaxes.domain.article.TaxedArticle;

import static org.mockito.Mockito.mock;

public class SalesTaxesTest {

    private SalesTaxes salesTaxes;
    private TaxRuleHandler taxesHandler;

    @Before
    public void setUp() throws Exception {
        taxesHandler = mock(TaxRuleHandler.class);
        salesTaxes = new SalesTaxes(taxesHandler, new TaxedArticle.Factory(new RoundUp5CentsPolicy()));
    }

    @Test @Ignore
    public void given_a_chocolate_bar() throws Exception {

    }
}