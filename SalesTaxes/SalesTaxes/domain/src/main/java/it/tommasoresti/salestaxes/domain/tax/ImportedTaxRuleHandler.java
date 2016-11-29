package it.tommasoresti.salestaxes.domain.tax;

import it.tommasoresti.salestaxes.domain.TaxedArticle;
import it.tommasoresti.salestaxes.domain.article.Imported;

public class ImportedTaxRuleHandler implements TaxRuleHandler {

    private static final int IMPORTED_ARTICLES_TAXES = 5;
    @Override
    public boolean canHandle(TaxedArticle taxedArticle) {
        return taxedArticle.getArticle() instanceof Imported;
    }

    @Override
    public void handle(TaxedArticle article) {
        article.addTaxPercentage(IMPORTED_ARTICLES_TAXES);
    }
}