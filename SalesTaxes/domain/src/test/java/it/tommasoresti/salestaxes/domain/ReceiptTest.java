package it.tommasoresti.salestaxes.domain;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import it.tommasoresti.salestaxes.domain.article.Imported;
import it.tommasoresti.salestaxes.domain.article.Item;
import it.tommasoresti.salestaxes.domain.article.TaxableArticle;
import it.tommasoresti.salestaxes.domain.article.TaxedArticle;

import static it.tommasoresti.salestaxes.domain.round.Round2DecimalPolicy.twoDecimal;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReceiptTest {

    private TaxedArticle.Factory factory = new TaxedArticle.Factory();

    @Test
    public void given_a_non_taxed_article() throws Exception {
        Receipt.Builder builder = new Receipt.Builder();

        addItemAndTaxPercentage(builder, "book", 12.49f, 0);

        Receipt receipt = builder.build();
        assertThat(twoDecimal(receipt.getTaxedArticles().get(0).getPrice()), is(twoDecimal(new BigDecimal(12.49))));
        assertThat(twoDecimal(receipt.getTaxesPaid()), is(twoDecimal(new BigDecimal(0))));
        assertThat(twoDecimal(receipt.getTotal()), is(twoDecimal(new BigDecimal(12.49))));
    }

    @Test
    public void given_a_taxed_article() throws Exception {
        Receipt.Builder builder = new Receipt.Builder();

        addImportedItemAndTaxPercentage(builder, "food", 10f, 50);

        Receipt receipt = builder.build();
        List<TaxedArticle> taxedArticles = receipt.getTaxedArticles();
        assertThat(twoDecimal(taxedArticles.get(0).getPrice()), is(twoDecimal(new BigDecimal(15))));
        assertThat(twoDecimal(receipt.getTaxesPaid()), is(twoDecimal(new BigDecimal(5))));
        assertThat(twoDecimal(receipt.getTotal()), is(twoDecimal(new BigDecimal(15))));
    }

    private void addImportedItemAndTaxPercentage(Receipt.Builder receiptBuilder, String category, float price, int percentage) {
        TaxableArticle article = new TaxableArticle(new Imported(buildItem(category, price)));
        setTaxPercentageAndAddToReceipt(receiptBuilder, percentage, article);
    }

    private void addItemAndTaxPercentage(Receipt.Builder receiptBuilder, String category, float price, int percentage) {
        TaxableArticle article = new TaxableArticle(buildItem(category, price));
        setTaxPercentageAndAddToReceipt(receiptBuilder, percentage, article);
    }

    private Item buildItem(String category, float price) {
        return new Item(category, "", new BigDecimal(price));
    }

    private void setTaxPercentageAndAddToReceipt(Receipt.Builder builder, int percentage, TaxableArticle article) {
        article = article.addTaxPercentage(new BigDecimal(percentage));
        builder.addTaxedArticle(factory.make(article));
    }

}