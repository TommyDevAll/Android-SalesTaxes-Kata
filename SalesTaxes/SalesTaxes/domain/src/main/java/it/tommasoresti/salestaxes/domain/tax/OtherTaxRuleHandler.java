package it.tommasoresti.salestaxes.domain.tax;

import it.tommasoresti.salestaxes.domain.TaxedArticle;

public class OtherTaxRuleHandler implements TaxRuleHandler {

    private static final int OTHER_ARTICLES_TAXES = 10;

    @Override
    public boolean canHandle(TaxedArticle taxedArticle) {
        return taxedArticle.getArticle().getCategory().equalsIgnoreCase("other");
    }

    @Override
    public void handle(TaxedArticle article) {
        article.addTaxPercentage(OTHER_ARTICLES_TAXES);
    }
}