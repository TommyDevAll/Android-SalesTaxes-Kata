package it.tommasoresti.salestaxes.domain;

public interface TaxesRuleChain {
    void handle(TaxedArticle taxedArticle);
}
