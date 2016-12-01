package it.tommasoresti.salestaxes.domain.tax;

public interface TaxRuleHandler {
    boolean canHandle(TaxableArticle article);
    void handle(TaxableArticle article);
}