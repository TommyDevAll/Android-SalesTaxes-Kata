package it.tommasoresti.salestaxes.domain;

import it.tommasoresti.salestaxes.domain.item.Article;

public interface TaxesRuleRepository {
    float of(Article article);
}
