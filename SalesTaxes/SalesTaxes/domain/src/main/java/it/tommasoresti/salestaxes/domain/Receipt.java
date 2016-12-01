package it.tommasoresti.salestaxes.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import it.tommasoresti.salestaxes.domain.tax.TaxedArticle;

public class Receipt {

    private List<TaxedArticle> taxedArticles = new ArrayList<>();
    private BigDecimal total = new BigDecimal(0);
    private BigDecimal taxesPaid = new BigDecimal(0);

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getTaxesPaid() {
        return taxesPaid;
    }

    public void addTaxedArticle(TaxedArticle taxedArticle) {
        updateTotalWith(taxedArticle);
        updateTaxesPaidWith(taxedArticle);
        taxedArticles.add(taxedArticle);
    }

    private void updateTaxesPaidWith(TaxedArticle taxedArticle) {
        taxesPaid = taxesPaid.add(taxedArticle.getPrice().subtract(taxedArticle.getNonTaxedPrice()));
    }

    private void updateTotalWith(TaxedArticle taxedArticle) {
        total = total.add(taxedArticle.getPrice());
    }

    public List<TaxedArticle> getTaxedArticles() {
        return taxedArticles;
    }
}
