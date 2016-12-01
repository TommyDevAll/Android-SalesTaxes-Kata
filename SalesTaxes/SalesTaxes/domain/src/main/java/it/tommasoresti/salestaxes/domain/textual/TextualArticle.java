package it.tommasoresti.salestaxes.domain.textual;

import java.util.Locale;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.round.RoundingPolicy;

public class TextualArticle {
    private final Article article;
    private RoundingPolicy roundingPolicy;

    public TextualArticle(Article article, RoundingPolicy roundingPolicy) {
        this.article = article;
        this.roundingPolicy = roundingPolicy;
    }

    public String toString() {
        return String.format(new Locale("en"), "1 %s: %s", article.getDescription(), roundingPolicy.round(article.getPrice()).toString());
    }
}