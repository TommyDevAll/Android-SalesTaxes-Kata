package it.tommasoresti.salestaxes.domain.textual;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.Book;
import it.tommasoresti.salestaxes.domain.article.Food;
import it.tommasoresti.salestaxes.domain.article.Item;
import it.tommasoresti.salestaxes.domain.article.Medical;
import it.tommasoresti.salestaxes.domain.article.Other;

import static java.util.Collections.singletonList;

public class TextualArticleFactory {
    private static final String PRODUCT_REGEX_PATTERN = "(\\d*) (\\D*) at (\\d*\\.\\d{2})";

    private static Map<Class<? extends Item>, List<String>> categoryPatterns = new HashMap<Class<? extends Item>, List<String>>() {{
        put(Food.class, singletonList("chocolate"));
        put(Medical.class, singletonList("pills"));
        put(Book.class, singletonList("book"));
    }};

    public Article make(String articleString) {
        Article article = null;
        Matcher matcher = Pattern.compile(PRODUCT_REGEX_PATTERN).matcher(articleString);
        while (hasNextProduct(matcher)) {
            String description = matcher.group(2);
            float price = Float.parseFloat(matcher.group(3));
            Class<? extends Item> itemType = findItemType(description, categoryPatterns);
            article = createNewItemByType(itemType, description, price);
        }
        return article;
    }

    private Article createNewItemByType(Class<? extends Item> itemType, String description, float price) {
        Item item = null;
        try {
            Constructor<? extends Item> constructor = itemType.getConstructor(String.class);
            item = constructor.newInstance(description);
            item.setPrice(price);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return item;
    }

    private Class<? extends Item> findItemType(String description, Map<Class<? extends Item>, List<String>> categoryPatterns) {
        for(Class<? extends Item> clazz : categoryPatterns.keySet()) {
            for(String word : description.split(" ")) {
                if(categoryPatterns.get(clazz).contains(word))
                    return clazz;
            }
        }
        return Other.class;
    }

    private boolean hasNextProduct(Matcher matcher)
    {
        return matcher.find();
    }
}
