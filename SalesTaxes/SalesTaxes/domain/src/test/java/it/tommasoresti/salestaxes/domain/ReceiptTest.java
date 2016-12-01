package it.tommasoresti.salestaxes.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import it.tommasoresti.salestaxes.domain.article.Imported;
import it.tommasoresti.salestaxes.domain.article.Item;
import it.tommasoresti.salestaxes.domain.round.RoundUp5CentsPolicy;
import it.tommasoresti.salestaxes.domain.tax.TaxCalculator;
import it.tommasoresti.salestaxes.domain.article.TaxableArticle;
import it.tommasoresti.salestaxes.domain.article.TaxedArticle;

import static it.tommasoresti.salestaxes.domain.TestUtils.round2Decimals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReceiptTest {

    private BigDecimal aTaxPercentage;
    private BigDecimal aPrice;
    private TaxCalculator taxCalculator = new TaxCalculator(new RoundUp5CentsPolicy());

    @Before
    public void setup() {
        this.aPrice = new BigDecimal(100);
        this.aTaxPercentage = new BigDecimal(10);
    }

    @Test
    public void given_a_chocolate_bar_taxed_by_10_percent() throws Exception {
        Item chocolateBar = new Item("food", "chocolate bar", aPrice);

        TaxableArticle taxableChocolateBar = new TaxableArticle(chocolateBar);
        taxableChocolateBar.addTaxPercentage(aTaxPercentage);

        Receipt receipt = new Receipt();
        receipt.addTaxedArticle(taxCalculator.calculate(taxableChocolateBar));

        assertThat(receipt.getTotal(), is(new BigDecimal("110.00")));
        assertThat(receipt.getTaxesPaid(), is(round2Decimals(aTaxPercentage)));
    }

    @Test
    public void given_first_input_case() throws Exception {
        Receipt receipt = new Receipt();

        TaxableArticle book = new TaxableArticle(new Item("book", "a book", new BigDecimal(12.49)));
        receipt.addTaxedArticle(taxCalculator.calculate(book));

        TaxableArticle other = new TaxableArticle(new Item("other", "a cd", new BigDecimal(14.99)));
        other.addTaxPercentage(new BigDecimal(10));
        receipt.addTaxedArticle(taxCalculator.calculate(other));

        TaxableArticle food = new TaxableArticle(new Item("food", "some chocolate", new BigDecimal(0.85)));
        receipt.addTaxedArticle(taxCalculator.calculate(food));

        assertThat(round2Decimals(receipt.getTaxedArticles().get(1).getPrice()), is(round2Decimals(new BigDecimal(16.49))));
        assertThat(round2Decimals(receipt.getTaxesPaid()), is(round2Decimals(new BigDecimal(1.50))));
        assertThat(round2Decimals(receipt.getTotal()), is(round2Decimals(new BigDecimal(29.83))));
    }

    @Test
    public void given_second_input_case() throws Exception {
        Receipt receipt = new Receipt();

        TaxableArticle importedFood = new TaxableArticle(new Imported(new Item("food", "some chocolate", new BigDecimal(10.00))));
        importedFood.addTaxPercentage(new BigDecimal(5));
        receipt.addTaxedArticle(taxCalculator.calculate(importedFood));

        TaxableArticle other = new TaxableArticle(new Item("other", "a perfume", new BigDecimal(47.50)));
        other.addTaxPercentage(new BigDecimal(5));
        other.addTaxPercentage(new BigDecimal(10));
        receipt.addTaxedArticle(taxCalculator.calculate(other));

        List<TaxedArticle> taxedArticles = receipt.getTaxedArticles();
        assertThat(round2Decimals(taxedArticles.get(0).getPrice()), is(round2Decimals(new BigDecimal(10.50))));
        assertThat(round2Decimals(taxedArticles.get(1).getPrice()), is(round2Decimals(new BigDecimal(54.65))));
        assertThat(round2Decimals(receipt.getTaxesPaid()), is(round2Decimals(new BigDecimal(7.65))));
        assertThat(round2Decimals(receipt.getTotal()), is(round2Decimals(new BigDecimal(65.15))));
    }

}