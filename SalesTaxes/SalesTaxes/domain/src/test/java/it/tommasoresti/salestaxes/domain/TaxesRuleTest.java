package it.tommasoresti.salestaxes.domain;

import org.junit.Before;
import org.junit.Test;

import it.tommasoresti.salestaxes.domain.item.Food;
import it.tommasoresti.salestaxes.domain.item.Imported;
import it.tommasoresti.salestaxes.domain.item.Other;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TaxesRuleTest {

    private TaxesRule taxesRule;

    @Before
    public void setUp() throws Exception {
        taxesRule = new TaxesRule();
    }

    @Test
    public void given_a_food() throws Exception {
        float taxes = taxesRule.of(new Food("chocolate bar"));
        assertThat(taxes, is(0f));
    }

    @Test
    public void given_a_perfume() throws Exception {
        float taxes = taxesRule.of(new Other("perfume"));
        assertThat(taxes, is(10f));
    }

    @Test
    public void given_an_imported_perfume() throws Exception {
        float taxes = taxesRule.of(new Imported(new Other("perfume")));
        assertThat(taxes, is(15f));
    }
}