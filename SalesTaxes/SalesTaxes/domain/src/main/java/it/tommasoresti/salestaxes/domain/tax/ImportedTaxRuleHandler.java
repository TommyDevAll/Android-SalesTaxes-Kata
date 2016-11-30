package it.tommasoresti.salestaxes.domain.tax;

import java.math.BigDecimal;

import it.tommasoresti.salestaxes.domain.article.TaxableArticle;
import it.tommasoresti.salestaxes.domain.article.Imported;

public class ImportedTaxRuleHandler implements TaxRuleHandler {

    private static final BigDecimal IMPORTED_ARTICLES_TAXES = new BigDecimal(5);
    @Override
    public boolean canHandle(TaxableArticle taxedArticle) {
        return taxedArticle.getArticle() instanceof Imported;
    }

    @Override
    public void handle(TaxableArticle article) {
        article.addTaxPercentage(IMPORTED_ARTICLES_TAXES);
    }
}