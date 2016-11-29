package it.tommasoresti.salestaxes.domain;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class SalesTaxesTest {

    private SalesTaxes salesTaxes;
    private TaxesRuleChain taxesRepository;

    @Before
    public void setUp() throws Exception {
        taxesRepository = mock(TaxesRuleChain.class);
        salesTaxes = new SalesTaxes(taxesRepository);
    }

    @Test @Ignore
    public void given_a_chocolate_bar() throws Exception {

    }
}