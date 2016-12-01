package it.tommasoresti.salestaxes.domain.tax;

import it.tommasoresti.salestaxes.domain.article.TaxableArticle;

public interface TaxRuleHandler {
    boolean canHandle(TaxableArticle article);
    void handle(TaxableArticle article);
}