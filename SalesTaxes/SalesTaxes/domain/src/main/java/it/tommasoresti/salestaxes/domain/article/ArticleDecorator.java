package it.tommasoresti.salestaxes.domain.article;

import java.math.BigDecimal;

public class ArticleDecorator implements Article {

    private Article article;
    public ArticleDecorator(Article article) {
        this.article = article;
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

    public Article getArticle() {
        return article;
    }
}