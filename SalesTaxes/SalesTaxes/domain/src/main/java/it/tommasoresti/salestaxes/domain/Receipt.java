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

    private float percentageOf(Article article, float taxes) {
        return article.getPrice() * taxes / 100;
    }

    private void updateTaxesPaidWith(TaxedArticle taxedArticle) {
        taxesPaid += percentageOf(taxedArticle.article, taxedArticle.taxes);
    }

    private void updateTotalWith(TaxedArticle taxedArticle) {
        total += taxedArticle.article.getPrice() + percentageOf(taxedArticle.article, taxedArticle.taxes);
    }

    private class TaxedArticle {
        Article article;
        float taxes;
        TaxedArticle(Article article, float taxes) {
            this.article = article;
            this.taxes = taxes;
        }
    }
}
