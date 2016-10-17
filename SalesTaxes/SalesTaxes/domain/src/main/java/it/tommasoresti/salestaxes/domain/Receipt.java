package it.tommasoresti.salestaxes.domain;

import java.util.ArrayList;
import java.util.List;

import it.tommasoresti.salestaxes.domain.article.Article;

public class Receipt {

    private List<TaxedArticle> taxedArticles = new ArrayList<>();
    private float total = 0;
    private float taxesPaid = 0;

    public float getTotal() {
        return total;
    }

    public float getTaxesPaid() {
        return taxesPaid;
    }

    public void addArticleWithTaxes(Article article, float taxesPercentage) {
        TaxedArticle taxedArticle = new TaxedArticle(article, taxesPercentage);
        updateTotalWith(taxedArticle);
        updateTaxesPaidWith(taxedArticle);
        taxedArticles.add(taxedArticle);
    }

    private void updateTaxesPaidWith(TaxedArticle taxedArticle) {
        Article article = taxedArticle.getArticle();
        taxesPaid += taxedArticle.getFinalPrice() - article.getPrice();
    }

    private void updateTotalWith(TaxedArticle taxedArticle) {
        total += taxedArticle.getFinalPrice();
    }

    public List<TaxedArticle> getArticles() {
        return taxedArticles;
    }
}
