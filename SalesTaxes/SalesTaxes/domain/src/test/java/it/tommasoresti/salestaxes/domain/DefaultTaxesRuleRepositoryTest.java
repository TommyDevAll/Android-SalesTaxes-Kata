package it.tommasoresti.salestaxes.domain;

import org.junit.Before;
import org.junit.Test;

import it.tommasoresti.salestaxes.domain.article.Food;
import it.tommasoresti.salestaxes.domain.article.Imported;
import it.tommasoresti.salestaxes.domain.article.Medical;
import it.tommasoresti.salestaxes.domain.article.Other;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DefaultTaxesRuleRepositoryTest {

    private DefaultTaxesRuleRepository taxesRuleRepository;
    private float aPrice;

    @Before
    public void setUp() throws Exception {
        taxesRuleRepository = new DefaultTaxesRuleRepository();
        aPrice = 10f;
    }

    @Test
    public void given_a_food() throws Exception {
        float taxes = taxesRuleRepository.of(new Food("chocolate bar", aPrice));
        assertThat(taxes, is(0f));
    }

    @Test
    public void given_a_perfume() throws Exception {
        float taxes = taxesRuleRepository.of(new Other("perfume", aPrice));
        assertThat(taxes, is(10f));
    }

    @Test
    public void given_an_imported_perfume() throws Exception {
        float taxes = taxesRuleRepository.of(new Imported(new Other("perfume", aPrice)));
        assertThat(taxes, is(15f));
    }

    @Test
    public void given_an_imported_box_of_headache_pills() throws Exception {
        float taxes = taxesRuleRepository.of(new Imported(new Medical("box of headache pills", aPrice)));
        assertThat(taxes, is(5f));
    }
}