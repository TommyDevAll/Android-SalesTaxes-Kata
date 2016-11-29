package it.tommasoresti.salestaxes.domain.tax;

import java.math.BigDecimal;

import it.tommasoresti.salestaxes.domain.TaxedArticle;
import it.tommasoresti.salestaxes.domain.article.Imported;

public class ImportedTaxRuleHandler implements TaxRuleHandler {

    private static final BigDecimal IMPORTED_ARTICLES_TAXES = new BigDecimal(5);
    @Override
    public boolean canHandle(TaxedArticle taxedArticle) {
        return taxedArticle.getArticle() instanceof Imported;
    }

    @Override
    public void handle(TaxedArticle article) {
        article.addTaxPercentage(IMPORTED_ARTICLES_TAXES);
    }
}