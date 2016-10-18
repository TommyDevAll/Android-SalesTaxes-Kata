package it.tommasoresti.salestaxes.domain;

import it.tommasoresti.salestaxes.domain.article.Article;

public interface ArticleFactory {
    Article make(String description, float price);
}
