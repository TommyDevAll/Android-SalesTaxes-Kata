package it.tommasoresti.salestaxes.domain.tax;

import java.math.BigDecimal;

import it.tommasoresti.salestaxes.domain.article.Imported;
import it.tommasoresti.salestaxes.domain.article.TaxableArticle;

public class ImportedTaxRuleHandler implements TaxRuleHandler {

    private static final BigDecimal IMPORTED_ARTICLES_TAXES = new BigDecimal(5);
    @Override
    public boolean canHandle(TaxableArticle taxedArticle) {
        return taxedArticle.getArticle() instanceof Imported;
    }

    @Override
    public TaxableArticle handle(TaxableArticle article) {
        return article.addTaxPercentage(IMPORTED_ARTICLES_TAXES);
    }
}