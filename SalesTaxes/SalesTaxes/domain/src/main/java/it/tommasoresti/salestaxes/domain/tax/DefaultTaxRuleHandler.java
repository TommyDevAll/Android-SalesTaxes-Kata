package it.tommasoresti.salestaxes.domain.tax;

import java.util.Arrays;
import java.util.List;

import it.tommasoresti.salestaxes.domain.TaxedArticle;

public class DefaultTaxRuleHandler implements TaxRuleHandler {

    private static final List<TaxRuleHandler> handlers = Arrays.asList(
            new OtherTaxRuleHandler(),
            new ImportedTaxRuleHandler()
    );

    @Override
    public boolean canHandle(TaxedArticle article) {
        return true;
    }

    @Override
    public void handle(TaxedArticle taxedArticle) {
        for(TaxRuleHandler handler : handlers) {
            if(handler.canHandle(taxedArticle))
                handler.handle(taxedArticle);
        }
    }
}
