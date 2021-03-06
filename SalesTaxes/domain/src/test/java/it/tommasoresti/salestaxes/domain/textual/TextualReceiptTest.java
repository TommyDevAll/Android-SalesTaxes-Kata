package it.tommasoresti.salestaxes.domain.textual;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import it.tommasoresti.salestaxes.domain.Receipt;
import it.tommasoresti.salestaxes.domain.article.TaxedArticle;
import it.tommasoresti.salestaxes.domain.round.Round2DecimalPolicy;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TextualReceiptTest {

    private TaxedArticle taxedArticle;

    @Before
    public void setUp() throws Exception {
        taxedArticle = mock(TaxedArticle.class);
    }

    @Test
    public void given_a_taxed_book() throws Exception {
        when(taxedArticle.getPrice()).thenReturn(new BigDecimal("10"));
        when(taxedArticle.getNonTaxedPrice()).thenReturn(new BigDecimal("8"));
        when(taxedArticle.getDescription()).thenReturn("big bamboo");
        when(taxedArticle.getCategory()).thenReturn("sex toy");

        Receipt receipt = new Receipt.Builder().addTaxedArticle(taxedArticle).build();

        assertThat(new TextualReceipt(receipt, new Round2DecimalPolicy()).toString(), is(equalTo("1 big bamboo: 10.00 Sales Taxes: 2.00 Total: 10.00")));
    }
}
