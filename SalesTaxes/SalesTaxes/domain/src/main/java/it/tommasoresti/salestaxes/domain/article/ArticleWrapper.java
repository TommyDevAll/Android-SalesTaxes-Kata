package it.tommasoresti.salestaxes.domain.article;

import java.math.BigDecimal;

public class ArticleWrapper implements Article {

    private Article article;
    public ArticleWrapper(Article article) {
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
}