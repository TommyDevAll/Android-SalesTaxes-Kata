package it.tommasoresti.salestaxes.domain;

import java.util.HashMap;
import java.util.Map;

import it.tommasoresti.salestaxes.domain.item.Article;
import it.tommasoresti.salestaxes.domain.item.Imported;
import it.tommasoresti.salestaxes.domain.item.Other;

public class DefaultTaxesRuleRepository implements TaxesRuleRepository {

    private static final int OTHER_ARTICLES_TAXES = 10;
    private static final int IMPORTED_ARTICLES_TAXES = 5;

    static private Map<Class, TaxRule> rulesMap = new HashMap<Class, TaxRule> () {{
        put(Other.class, new OtherTaxRule());
        put(Imported.class, new ImportedTaxRule());
    }};

    @Override
    public float of(Article article) {
        return getTaxesOf(article);
    }

    private static float getTaxesOf(Article item) {
        TaxRule taxRule = rulesMap.get(item.getClass());
        if(taxRule != null)
            return taxRule.getTaxesOf(item);
        return 0;
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
            return IMPORTED_ARTICLES_TAXES + DefaultTaxesRuleRepository.getTaxesOf(item.getItem());
        }
    }
}
