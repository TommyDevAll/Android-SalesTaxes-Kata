package it.tommasoresti.salestaxes.domain;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import it.tommasoresti.salestaxes.domain.article.Imported;
import it.tommasoresti.salestaxes.domain.article.Item;
import it.tommasoresti.salestaxes.domain.article.TaxableArticle;
import it.tommasoresti.salestaxes.domain.article.TaxedArticle;
import it.tommasoresti.salestaxes.domain.round.RoundUp5CentsPolicy;

import static it.tommasoresti.salestaxes.domain.round.Round2DecimalPolicy.twoDecimal;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReceiptTest {

    private TaxedArticle.Factory factory = new TaxedArticle.Factory(new RoundUp5CentsPolicy());

    @Test
    public void given_first_input_case() throws Exception {
        Receipt receipt = new Receipt();

        addItemAndTaxPercentage(receipt, "book", 12.49f, 0);
        addItemAndTaxPercentage(receipt, "other", 14.99f, 10);
        addItemAndTaxPercentage(receipt, "food", 0.85f, 0);

        assertThat(twoDecimal(receipt.getTaxedArticles().get(1).getPrice()), is(twoDecimal(new BigDecimal(16.49))));
        assertThat(twoDecimal(receipt.getTaxesPaid()), is(twoDecimal(new BigDecimal(1.5))));
        assertThat(twoDecimal(receipt.getTotal()), is(twoDecimal(new BigDecimal(29.83))));
    }

    @Test
    public void given_second_input_case() throws Exception {
        Receipt receipt = new Receipt();

        addImportedItemAndTaxPercentage(receipt, "food", 10f, 5);
        addImportedItemAndTaxPercentage(receipt, "other", 47.5f, 15);

        List<TaxedArticle> taxedArticles = receipt.getTaxedArticles();
        assertThat(twoDecimal(taxedArticles.get(0).getPrice()), is(twoDecimal(new BigDecimal(10.5))));
        assertThat(twoDecimal(taxedArticles.get(1).getPrice()), is(twoDecimal(new BigDecimal(54.65))));
        assertThat(twoDecimal(receipt.getTaxesPaid()), is(twoDecimal(new BigDecimal(7.65))));
        assertThat(twoDecimal(receipt.getTotal()), is(twoDecimal(new BigDecimal(65.15))));
    }

    private void addImportedItemAndTaxPercentage(Receipt receipt, String category, float price, int percentage) {
        TaxableArticle article = new TaxableArticle(new Imported(buildItem(category, price)));
        setTaxPercentagAndAddToReceipt(receipt, percentage, article);
    }

    private void addItemAndTaxPercentage(Receipt receipt, String category, float price, int percentage) {
        TaxableArticle article = new TaxableArticle(buildItem(category, price));
        setTaxPercentagAndAddToReceipt(receipt, percentage, article);
    }

    private Item buildItem(String category, float price) {
        return new Item(category, "", new BigDecimal(price));
    }

    private void setTaxPercentagAndAddToReceipt(Receipt receipt, int percentage, TaxableArticle article) {
        article = article.addTaxPercentage(new BigDecimal(percentage));
        receipt.addTaxedArticle(factory.make(article));
    }

}