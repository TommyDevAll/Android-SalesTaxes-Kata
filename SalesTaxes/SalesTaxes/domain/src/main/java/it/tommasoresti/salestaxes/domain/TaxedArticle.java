package it.tommasoresti.salestaxes.domain;

import it.tommasoresti.salestaxes.domain.article.Article;

public class TaxedArticle {
    private Article article;
    private float taxesPercentage;
    private float finalPrice;
    TaxedArticle(Article article, float taxesPercentage) {
        this.article = article;
        this.taxesPercentage = taxesPercentage;
        this.finalPrice = calculateFinalPrice();
    }

    private float calculateFinalPrice() {
        return article.getPrice() + percentageOf(article, taxesPercentage);
    }

    private float percentageOf(Article article, float taxes) {
        return article.getPrice() * taxes / 100;
    }

    public Article getArticle() {
        return article;
    }

    public float getTaxesPercentage() {
        return taxesPercentage;
    }

    public float getFinalPrice() {
        return finalPrice;
    }
}
