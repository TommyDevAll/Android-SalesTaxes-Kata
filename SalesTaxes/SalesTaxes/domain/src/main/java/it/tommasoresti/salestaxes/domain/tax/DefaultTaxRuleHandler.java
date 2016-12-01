package it.tommasoresti.salestaxes.domain.tax;

import java.util.Arrays;
import java.util.List;

import it.tommasoresti.salestaxes.domain.article.TaxableArticle;

public class DefaultTaxRuleHandler implements TaxRuleHandler {

    private static final List<TaxRuleHandler> handlers = Arrays.asList(
            new OtherTaxRuleHandler(),
            new ImportedTaxRuleHandler()
    );

    @Override
    public boolean canHandle(TaxableArticle article) {
        return true;
    }

    @Override
    public void handle(TaxableArticle article) {
        for(TaxRuleHandler handler : handlers) {
            if(handler.canHandle(article))
                handler.handle(article);
        }
    }
}
