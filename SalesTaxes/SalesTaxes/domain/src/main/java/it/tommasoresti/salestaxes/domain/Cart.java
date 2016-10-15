package it.tommasoresti.salestaxes.domain;

import java.util.ArrayList;
import java.util.List;

import it.tommasoresti.salestaxes.domain.item.Article;

public class Cart {
    private List<Article> articles = new ArrayList<>();

    public void addArticle(Article item) {
        this.articles.add(item);
    }

    public List<Article> getArticles() {
        return articles;
    }
}
