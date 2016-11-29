package it.tommasoresti.salestaxes.domain.textual;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.tommasoresti.salestaxes.domain.ArticleFactory;
import it.tommasoresti.salestaxes.domain.article.Article;

public class TextualCartFactory {
    public static final String PRODUCT_REGEX_PATTERN = "(\\d*) (\\D*) at (\\d*\\.\\d{2})";
    private final ArticleFactory textualArticleFactory;

    public TextualCartFactory(ArticleFactory textualArticleFactory) {
        this.textualArticleFactory = textualArticleFactory;
    }

    public List<Article> make(String articles) {
        List<Article> products = new ArrayList<>();
        Matcher matcher = Pattern.compile(PRODUCT_REGEX_PATTERN).matcher(articles);
        while (hasNextProduct(matcher)) {
            products.add(textualArticleFactory.make(matcher.group(2), new BigDecimal(Float.parseFloat(matcher.group(3)))));
        }
        return products;
    }

    private boolean hasNextProduct(Matcher matcher)
    {
        return matcher.find();
    }
}
