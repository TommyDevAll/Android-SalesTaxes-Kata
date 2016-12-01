package it.tommasoresti.salestaxes.domain.tax;

import java.math.BigDecimal;

public class OtherTaxRuleHandler implements TaxRuleHandler {

    private static final BigDecimal OTHER_ARTICLES_TAXES = new BigDecimal(10);

    @Override
    public boolean canHandle(TaxableArticle taxedArticle) {
        return taxedArticle.getArticle().getCategory().equalsIgnoreCase("other");
    }

    @Override
    public void handle(TaxableArticle article) {
        article.addTaxPercentage(OTHER_ARTICLES_TAXES);
    }
}