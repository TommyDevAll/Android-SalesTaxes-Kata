package it.tommasoresti.salestaxes.domain;

import org.junit.Before;
import org.junit.Test;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.Food;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SalesTaxesTest {

    private SalesTaxes salesTaxes;
    private TaxesRuleRepository taxesRepository;

    @Before
    public void setUp() throws Exception {
        taxesRepository = mock(TaxesRuleRepository.class);
        salesTaxes = new SalesTaxes(taxesRepository);
    }

    @Test
    public void given_a_chocolate_bar() throws Exception {
        when(taxesRepository.of(any(Article.class))).thenReturn(10f);
        Cart cart = new Cart();
        Food chocolateBar = new Food("chocolate bar");
        chocolateBar.setPrice(100f);

        cart.addArticle(chocolateBar);
        Receipt receipt = salesTaxes.of(cart);

        assertThat(receipt.getTotal(), is(110f));
    }
}