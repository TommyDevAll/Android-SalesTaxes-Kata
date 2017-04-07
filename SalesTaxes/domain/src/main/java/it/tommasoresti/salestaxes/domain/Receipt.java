package it.tommasoresti.salestaxes.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import it.tommasoresti.salestaxes.domain.article.TaxedArticle;

public class Receipt {

    private List<TaxedArticle> taxedArticles = new ArrayList<>();
    private BigDecimal total = new BigDecimal(0);
    private BigDecimal taxesPaid = new BigDecimal(0);

    private Receipt(List<TaxedArticle> articles) {
        taxedArticles = articles;
        for(TaxedArticle taxedArticle : taxedArticles) {
            updateTotalWith(taxedArticle);
            updateTaxesPaidWith(taxedArticle);
        }
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getTaxesPaid() {
        return taxesPaid;
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

    public static class Builder {

        private List<TaxedArticle> articles = new ArrayList<>();

        public Builder addTaxedArticle(TaxedArticle taxedArticle) {
            articles.add(taxedArticle);
            return this;
        }

        public Receipt build() {
            return new Receipt(articles);
        }
    }
}
