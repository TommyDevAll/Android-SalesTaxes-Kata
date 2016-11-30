package it.tommasoresti.salestaxes.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.TaxableArticle;

public class Receipt {

    private List<TaxableArticle> taxedArticles = new ArrayList<>();
    private BigDecimal total = new BigDecimal(0);
    private BigDecimal taxesPaid = new BigDecimal(0);

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getTaxesPaid() {
        return taxesPaid;
    }

    public void addTaxedArticle(TaxableArticle taxedArticle) {
        updateTotalWith(taxedArticle);
        updateTaxesPaidWith(taxedArticle);
        taxedArticles.add(taxedArticle);
    }

    private void updateTaxesPaidWith(TaxableArticle taxedArticle) {
        Article article = taxedArticle.getArticle();
        taxesPaid = taxesPaid.add(taxedArticle.getFinalPrice().subtract(article.getPrice()));
    }

    private void updateTotalWith(TaxableArticle taxedArticle) {
        total = total.add(taxedArticle.getFinalPrice());
    }

    public List<TaxableArticle> getTaxedArticles() {
        return taxedArticles;
    }
}
