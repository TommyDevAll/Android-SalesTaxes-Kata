package it.tommasoresti.salestaxes.domain.textual;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.tommasoresti.salestaxes.domain.article.Article;

import static it.tommasoresti.salestaxes.domain.textual.TextualArticleFactory.PRODUCT_REGEX_PATTERN;

public class TextualCartFactory {

    private final TextualArticleFactory textualArticleFactory;

    public TextualCartFactory() {
        textualArticleFactory = new TextualArticleFactory();
    }

    public List<Article> make(String articles) {
        List<Article> products = new ArrayList<>();
        Matcher matcher = Pattern.compile(PRODUCT_REGEX_PATTERN).matcher(articles);
        while (hasNextProduct(matcher)) {
            products.add(textualArticleFactory.make(matcher.group(0)));
        }
        return products;
    }

    private boolean hasNextProduct(Matcher matcher)
    {
        return matcher.find();
    }
}
