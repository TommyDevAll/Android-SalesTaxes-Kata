package it.tommasoresti.salestaxes.domain.textual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import it.tommasoresti.salestaxes.domain.ArticleFactory;
import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.Imported;
import it.tommasoresti.salestaxes.domain.article.Item;

import static java.util.Collections.singletonList;

public class TextualArticleFactory implements ArticleFactory {
    private static Map<String, List<String>> categoryPatterns = new HashMap<String, List<String>>() {{
        put("food", singletonList("chocolate"));
        put("medical", singletonList("pills"));
        put("book", singletonList("book"));
    }};

    @Override
    public Article make(String description, float price) {
        return createArticleWithDescriptionAndPrice(description, price);
    }

    private Article createArticleWithDescriptionAndPrice(String description, float price) {
        Article article;
        String itemType = findItemType(description, categoryPatterns);
        Item item = new Item(itemType, description, price);
        article = wrappedItemAsImportedIfNeeded(description, item);
        return article;
    }

    private Article wrappedItemAsImportedIfNeeded(String description, Item newItemByType) {
        Article article;
        if(description.toLowerCase().contains("imported"))
            article = new Imported(newItemByType);
        else
            article = newItemByType;
        return article;
    }

    private String findItemType(String description, Map<String, List<String>> categoryPatterns) {
        for(String type : categoryPatterns.keySet()) {
            for(String word : description.split(" ")) {
                if(categoryPatterns.get(type).contains(word))
                    return type;
            }
        }
        return "other";
    }

    private boolean articleHasBeenFound(Matcher matcher) {
        return matcher.find();
    }
}
