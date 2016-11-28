package it.tommasoresti.salestaxes.domain.tax;

import it.tommasoresti.salestaxes.domain.article.Article;

public interface TaxRule {
    float getTaxesOf(Article item);
}