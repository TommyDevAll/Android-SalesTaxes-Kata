package it.tommasoresti.salestaxes.domain;

import java.math.BigDecimal;

import it.tommasoresti.salestaxes.domain.article.Article;

public class TaxedArticle {
    private Article article;
    private BigDecimal taxesPercentage = new BigDecimal(0);
    private BigDecimal finalPrice = new BigDecimal(0);

    TaxedArticle(Article article) {
        this.article = article;
        this.finalPrice = article.getPrice();
    }

    private BigDecimal calculateFinalPrice() {
        return article.getPrice().add(percentageOf(article, taxesPercentage));
    }

    private BigDecimal percentageOf(Article article, BigDecimal taxes) {
        return article.getPrice().multiply(taxes).divide(new BigDecimal(100));
    }

    public Article getArticle() {
        return article;
    }

    public void addTaxPercentage(BigDecimal taxesPercentage) {
        this.taxesPercentage = this.taxesPercentage.add(taxesPercentage);
        this.finalPrice = calculateFinalPrice();
    }

    public BigDecimal getTaxesPercentage() {
        return taxesPercentage;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }
}
