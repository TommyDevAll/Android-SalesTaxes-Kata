package it.tommasoresti.salestaxes.domain;

import java.util.HashMap;
import java.util.Map;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.Imported;
import it.tommasoresti.salestaxes.domain.article.Other;

public class DefaultTaxesRuleRepository implements TaxesRuleRepository {

    private static final int OTHER_ARTICLES_TAXES = 10;
    private static final int IMPORTED_ARTICLES_TAXES = 5;

    static private Map<Class, TaxRule> rulesMap = new HashMap<Class, TaxRule> () {{
        put(Other.class, new OtherTaxRule());
        put(Imported.class, new ImportedTaxRule());
    }};

    @Override
    public float of(Article article) {
        return calculate(article);
    }

    private static float calculate(Article article) {
        TaxRule taxRule = rulesMap.get(article.getClass());
        return (taxRule != null)? taxRule.getTaxesOf(article): 0;
    }

    private interface TaxRule {
        float getTaxesOf(Article item);
    }

    private static class OtherTaxRule implements TaxRule {
        public float getTaxesOf(Article item) {
            return OTHER_ARTICLES_TAXES;
        }
    }

    private static class ImportedTaxRule implements TaxRule {
        public float getTaxesOf(Article item) {
            return IMPORTED_ARTICLES_TAXES + calculate(item.getItem());
        }
    }
}
