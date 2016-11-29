package it.tommasoresti.salestaxes.domain.textual;

import java.util.Locale;

import it.tommasoresti.salestaxes.domain.article.Article;

public class TextualArticle {
    private final Article article;

    public TextualArticle(Article article) {
        this.article = article;
    }

    public String toString() {
        return String.format(new Locale("en"), "1 %s : %.2f", article.getDescription(), article.getPrice().floatValue());
    }
}