package it.tommasoresti.salestaxes.domain;

import java.util.Arrays;
import java.util.List;

import it.tommasoresti.salestaxes.domain.tax.ImportedTaxRuleHandler;
import it.tommasoresti.salestaxes.domain.tax.OtherTaxRuleHandler;
import it.tommasoresti.salestaxes.domain.tax.TaxRuleHandler;

public class DefaultTaxesRuleChain implements TaxesRuleChain {

    private static final List<TaxRuleHandler> handlers = Arrays.asList(
            new OtherTaxRuleHandler(),
            new ImportedTaxRuleHandler()
    );

    @Override
    public void handle(TaxedArticle taxedArticle) {
        for(TaxRuleHandler handler : handlers) {
            if(handler.canHandle(taxedArticle))
                handler.handle(taxedArticle);
        }
    }
}
