package it.tommasoresti.salestaxes.domain.tax;

import java.math.BigDecimal;

import it.tommasoresti.salestaxes.domain.article.Article;

public class TaxableArticle implements Article {
    private Article article;
    private BigDecimal taxesPercentage = new BigDecimal(0);

    public TaxableArticle(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }

    public void addTaxPercentage(BigDecimal taxesPercentage) {
        this.taxesPercentage = this.taxesPercentage.add(taxesPercentage);
    }

    public BigDecimal getTaxesPercentage() {
        return taxesPercentage;
    }

    @Override
    public String getCategory() {
        return article.getCategory();
    }

    @Override
    public String getDescription() {
        return article.getDescription();
    }

    @Override
    public BigDecimal getPrice() {
        return article.getPrice();
    }
}
