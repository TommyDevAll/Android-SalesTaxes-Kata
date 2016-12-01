package it.tommasoresti.salestaxes.domain.textual;

import org.junit.Test;

import java.math.BigDecimal;

import it.tommasoresti.salestaxes.domain.Receipt;
import it.tommasoresti.salestaxes.domain.article.Item;
import it.tommasoresti.salestaxes.domain.round.RoundUp5CentsPolicy;
import it.tommasoresti.salestaxes.domain.tax.TaxCalculator;
import it.tommasoresti.salestaxes.domain.article.TaxableArticle;

public class TextualReceiptTest {
    @Test
    public void given_first_input_case() throws Exception {
        Receipt receipt = new Receipt();
        TaxCalculator calculator = new TaxCalculator(new RoundUp5CentsPolicy());

        TaxableArticle book = new TaxableArticle(new Item("book", "a book", new BigDecimal(12.49)));
        receipt.addTaxedArticle(calculator.calculate(book));

        TaxableArticle other = new TaxableArticle(new Item("other", "a cd", new BigDecimal(14.99)));
        other.addTaxPercentage(new BigDecimal(10));
        receipt.addTaxedArticle(calculator.calculate(other));

        TaxableArticle food = new TaxableArticle(new Item("food", "some chocolate", new BigDecimal(0.85)));
        receipt.addTaxedArticle(calculator.calculate(food));

//        assertThat(new TextualReceipt(receipt).toString(), is(equalTo()));
    }
}
