package it.tommasoresti.salestaxes.domain.tax;

import it.tommasoresti.salestaxes.domain.TaxedArticle;

public interface TaxRuleHandler {
    boolean canHandle(TaxedArticle article);
    void handle(TaxedArticle article);
}