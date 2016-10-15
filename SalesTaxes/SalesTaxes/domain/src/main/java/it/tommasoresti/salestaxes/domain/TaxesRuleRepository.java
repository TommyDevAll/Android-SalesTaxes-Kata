package it.tommasoresti.salestaxes.domain;

import it.tommasoresti.salestaxes.domain.article.Article;

public interface TaxesRuleRepository {
    float of(Article article);
}
