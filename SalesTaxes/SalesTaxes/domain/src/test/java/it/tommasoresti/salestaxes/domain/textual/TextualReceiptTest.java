package it.tommasoresti.salestaxes.domain.textual;

import org.junit.Test;

import java.math.BigDecimal;

import it.tommasoresti.salestaxes.domain.Receipt;
import it.tommasoresti.salestaxes.domain.article.Item;
import it.tommasoresti.salestaxes.domain.article.TaxableArticle;

public class TextualReceiptTest {
    @Test
    public void given_first_input_case() throws Exception {
        Receipt receipt = new Receipt();

        TaxableArticle book = new TaxableArticle(new Item("book", "a book", new BigDecimal(12.49)));
        receipt.addTaxedArticle(book);

        TaxableArticle other = new TaxableArticle(new Item("other", "a cd", new BigDecimal(14.99)));
        other.addTaxPercentage(new BigDecimal(10));
        receipt.addTaxedArticle(other);

        TaxableArticle food = new TaxableArticle(new Item("food", "some chocolate", new BigDecimal(0.85)));
        receipt.addTaxedArticle(food);

//        assertThat(new TextualReceipt(receipt).toString(), is(equalTo()));
    }
}
