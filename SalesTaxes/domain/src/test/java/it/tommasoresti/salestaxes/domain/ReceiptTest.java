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
        Receipt.Builder builder = new Receipt.Builder();

        addItemAndTaxPercentage(builder, "book", 12.49f, 0);
        addItemAndTaxPercentage(builder, "other", 14.99f, 10);
        addItemAndTaxPercentage(builder, "food", 0.85f, 0);

        Receipt receipt = builder.build();
        assertThat(twoDecimal(receipt.getTaxedArticles().get(1).getPrice()), is(twoDecimal(new BigDecimal(16.49))));
        assertThat(twoDecimal(receipt.getTaxesPaid()), is(twoDecimal(new BigDecimal(1.5))));
        assertThat(twoDecimal(receipt.getTotal()), is(twoDecimal(new BigDecimal(29.83))));
    }

    @Test
    public void given_second_input_case() throws Exception {
        Receipt.Builder builder = new Receipt.Builder();

        addImportedItemAndTaxPercentage(builder, "food", 10f, 5);
        addImportedItemAndTaxPercentage(builder, "other", 47.5f, 15);

        Receipt receipt = builder.build();
        List<TaxedArticle> taxedArticles = receipt.getTaxedArticles();
        assertThat(twoDecimal(taxedArticles.get(0).getPrice()), is(twoDecimal(new BigDecimal(10.5))));
        assertThat(twoDecimal(taxedArticles.get(1).getPrice()), is(twoDecimal(new BigDecimal(54.65))));
        assertThat(twoDecimal(receipt.getTaxesPaid()), is(twoDecimal(new BigDecimal(7.65))));
        assertThat(twoDecimal(receipt.getTotal()), is(twoDecimal(new BigDecimal(65.15))));
    }

    private void addImportedItemAndTaxPercentage(Receipt.Builder receiptBuilder, String category, float price, int percentage) {
        TaxableArticle article = new TaxableArticle(new Imported(buildItem(category, price)));
        setTaxPercentagAndAddToReceipt(receiptBuilder, percentage, article);
    }

    private void addItemAndTaxPercentage(Receipt.Builder receiptBuilder, String category, float price, int percentage) {
        TaxableArticle article = new TaxableArticle(buildItem(category, price));
        setTaxPercentagAndAddToReceipt(receiptBuilder, percentage, article);
    }

    private Item buildItem(String category, float price) {
        return new Item(category, "", new BigDecimal(price));
    }

    private void setTaxPercentagAndAddToReceipt(Receipt.Builder builder, int percentage, TaxableArticle article) {
        article = article.addTaxPercentage(new BigDecimal(percentage));
        builder.addTaxedArticle(factory.make(article));
    }

}