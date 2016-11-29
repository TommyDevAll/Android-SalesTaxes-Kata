package it.tommasoresti.salestaxes.domain;

import java.math.BigDecimal;

import it.tommasoresti.salestaxes.domain.article.Article;

public interface ArticleFactory {
    Article make(String description, BigDecimal price);
}
