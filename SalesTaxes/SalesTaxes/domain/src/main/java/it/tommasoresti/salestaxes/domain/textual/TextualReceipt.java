package it.tommasoresti.salestaxes.domain.textual;

import java.util.Locale;

import it.tommasoresti.salestaxes.domain.Receipt;
import it.tommasoresti.salestaxes.domain.tax.TaxedArticle;

public class TextualReceipt {
    private String string;

    public TextualReceipt(Receipt receipt) {
        this.string = buildString(receipt);
    }

    public String toString() {
        return string;
    }

    private String buildString(Receipt receipt) {
        StringBuilder builder = new StringBuilder();

        addArticlesText(receipt, builder);
        addSpace(builder);

        addSalesTaxesText(receipt, builder);
        addSpace(builder);

        addTotalText(receipt, builder);

        return builder.toString();
    }

    private void addSpace(StringBuilder builder) {
        builder.append(" ");
    }

    private void addArticlesText(Receipt receipt, StringBuilder builder) {
        boolean first = true;
        for(TaxedArticle taxedArticle : receipt.getTaxedArticles()) {
            builder.append(first ? "" : " ");
            builder.append(new TextualArticle(taxedArticle).toString());
            first = false;
        }
    }

    private void addSalesTaxesText(Receipt receipt, StringBuilder builder) {
        builder.append(String.format(Locale.getDefault(), "Sales Taxes: %.2f", receipt.getTaxesPaid().floatValue()));
    }

    private void addTotalText(Receipt receipt, StringBuilder builder) {
        builder.append(String.format(Locale.getDefault(), "Total: %.2f", receipt.getTotal().floatValue()));
    }
}
