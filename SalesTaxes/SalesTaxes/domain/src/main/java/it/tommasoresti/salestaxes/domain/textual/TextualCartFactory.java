package it.tommasoresti.salestaxes.domain.textual;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextualCartFactory {
    private static final String PRODUCT_REGEX_PATTERN = "(\\d*) (\\D*) at (\\d*\\.\\d{2})";
    public List<String> make(String articles) {
        List<String> products = new ArrayList<>();

        Matcher matcher = Pattern.compile(PRODUCT_REGEX_PATTERN).matcher(articles);

        while (hasNextProduct(matcher)) {
            System.out.println(matcher.group(0));
        }

        return products;
    }

    private boolean hasNextProduct(Matcher matcher)
    {
        return matcher.find();
    }
}
